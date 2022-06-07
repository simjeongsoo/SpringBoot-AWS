package springbootweb.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// 컨트롤러를 JSON 반환하는 컨트롤러로 만들어줌
// @ResponseBody 를 각 메소드 마다 선언했던 것을 한번에 사용 할 수 있게 해줌
@RestController
public class HelloController {
    // HTTP Method 인 Get 의 요청을 받을 수 있는 API 를 만들어줌
    @GetMapping("/hello")
    public String hello() {
        return "hello";
        // "/hello" 로 요청이 오면 문자열 hello 를 반환하는 기능을 가짐
    }
}
