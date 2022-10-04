package springbootweb.config.auth.dto;

import lombok.Builder;
import lombok.Getter;
import springbootweb.domain.user.Role;
import springbootweb.domain.user.User;

import java.util.Map;

@Getter
public class OAuthAttributes {
    //--OAuth2UserService 를 통해 가져온 OAuth2User의 attribute를 담을 클래스--//
    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String name;
    private String email;
    private String picture;

    @Builder
    public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String name, String email, String picture) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.email = email;
        this.picture = picture;
    }

    public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes) {
        //--OAuth2User에서 반환하는 사용자 정보는 Map이기 때문에 값 하나하나를 변환--//
        return ofGoogle(userNameAttributeName, attributes);
    }

    private static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes) {
        return OAuthAttributes.builder()
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .picture((String) attributes.get("picture"))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    public User toEntity() {
        //--- User 엔티티를 생성
        //- OAuthAttributes에서 엔티티를 생성하는 시점은 처음 가입할때 이다.--//
        return User.builder()
                .name(name)
                .email(email)
                .picture(picture)
                .role(Role.GUEST) // 가입할 때의 기본 권한을 GUEST로 주기 위해서 role 빌더값에는 Role.GUEST를 사용
                .build();
    }
}