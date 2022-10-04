package springbootweb.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springbootweb.web.dto.HelloResponseDto;

// 컨트롤러를 JSON 반환하는 컨트롤러로 만들어줌
// @ResponseBody 를 각 메소드 마다 선언했던 것을 한번에 사용 할 수 있게 해줌
@RestController
public class HelloController {
    // HTTP Method 인 Get 의 요청을 받을 수 있는 API 를 만들어줌
    @GetMapping("/hello") // api 1
    public String hello() {
        return "hello";
        // "/hello" 로 1요청이 오면 문자열 hello 를 반환하는 기능을 가짐
    }

    // HelloController 에서 ResponseDto 사용
    @GetMapping("/hello/dto") // api 2
    public HelloResponseDto helloDto(@RequestParam("name") String name, @RequestParam("amount") int amount) {
        return new HelloResponseDto(name, amount);

        /*
        * @RequestParam : 외부에서 API 로 넘긴 파리미터를 가져오는 어노테이션
        *                 여기에서는 외부에서 name(@RequestParam("name")) 이란 이름으로 넘긴 파리미터를 메소드 파라미터 name(String name) 에 저장
        *                 name 과 amount 는 API 를 호출하는 곳에서 넘겨준 값들
        * */
    }
}
