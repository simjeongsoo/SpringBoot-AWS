package springbootweb.domain.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import springbootweb.domain.BaseTimeEntity;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class User extends BaseTimeEntity { // 사용자 정보를 담당할 도메인

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column
    private String picture;

    @Enumerated(EnumType.STRING)        // JPA로 데이터베이스로 저장할 때 Enum 값을 어떤 형태로 저장할 지 결정
    @Column(nullable = false)
    private Role role;

    @Builder
    public User(String name, String email, String picture, Role role) {
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.role = role;
    }

    public User update(String name, String picture) {
        //--이름, 프로필사진 이 변경되면 User 엔티티에 반영--//
        this.name = name;
        this.picture = picture;

        return this;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }
}
