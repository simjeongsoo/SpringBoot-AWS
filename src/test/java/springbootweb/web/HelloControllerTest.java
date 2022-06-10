package springbootweb.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner; // Junit4
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// 테스트를 진행할 때 JUnit 에 내장된 실행자 외에 다른 실행자를 실행
// SpringRunner 라는 실행자 사용
// 스프링 부트 테스트와 JUnit 사이에 연결자 역할
@RunWith(SpringRunner.class)
// Web(Spring MVC) 에 집중할 수 있는 어노테이션
// 선언 할 경우 @Controller, @ControllerAdvice 등 사용
// @Service, @Component, @Repository 등은 사용 불가
@WebMvcTest(controllers = HelloController.class)
public class HelloControllerTest {

    // 스프링이 관리하는 Bean 주입
    @Autowired
    // 웹 API 테스트 할때 사용 , 스프링 MVC 테스트의 시작점
    // 이 클래스를 통해 HTTP GET, POST 등에 대한 API 테스트를 수행
    private MockMvc mvc;

    @Test
    public void hello가_리턴된다() throws Exception{
        String hello = "hello";

        // MockMVC를 통해 /hello 주소로 HTTP GET 요청을 함
        // 체이닝이 지원되어여러 검증기능 선언 가능
        mvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string(hello));
                /* .andExpect(status().isOk())
                *   = mvc.perform 의 결과 검증
                *   = HTTP Header 의 status 검증
                *   = 200,404,500 등의 상태검사 ( isOk = 200 )
                * */
                /* .andExpect(content().string(hello))
                *   = mvc.perform 의 결과 검증
                *   = 응답 본문의 내용 검증
                *   = Controller 에서 "hello"를 리턴하기 때문에 이 값이 맞는지 검증
                * */
    }
}