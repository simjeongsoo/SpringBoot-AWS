package springbootweb.config.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import springbootweb.domain.user.Role;


@RequiredArgsConstructor
@EnableWebSecurity  // Spring Security 설정들을 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // h2-console 화면을 사용하기 위한 설정
                .csrf().disable()
                .headers().frameOptions().disable()

                .and()
                .authorizeRequests() // URL별 권한 관리를 설정하는 옵션의 시작점 , antMatchers 옵션 사용을 위한 선언

                // 권한 관리 대상을 지정하는 옵션
                .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**").permitAll()
                .antMatchers("/api/v1/**").hasRole(Role.USER.name())

                // 설정된 값들 이외 나머지 URL들을 나타냄
                .anyRequest().authenticated()
                .and()
                .logout()// 로그아웃 기능에 대한 여러 설정의 진입점
                .logoutSuccessUrl("/")
                .and()
                .oauth2Login() // OAuth 2 로그인 성공 이후 사용자 정보를 가져올 때의 설정들을 담당
                .userInfoEndpoint().userService(customOAuth2UserService); // 소셜 로그인 성공 시 후속 조치를 진행할 UserService 인터페이스의 구현체를 등록한다.
    }
}
