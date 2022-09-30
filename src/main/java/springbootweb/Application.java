package springbootweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

// main class
// @SpringBootApplication 를 통해 스프링부트 자동생성 , Bean 읽기 생성 자동설정
@EnableJpaAuditing // JPA Auditing 활성화
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        //SpringApplication.run 으로 내장 WAS 실행
        SpringApplication.run(Application.class, args);
    }
}


