package springbootweb.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    // 페이지 관련 컨트롤러 //

    @GetMapping("/") // home view mapping
    public String index() {
        return "index";
    }

    @GetMapping("/posts/save") // 게시글 등록 mapping
    public String postSave() {
        return "posts-save";
    }

}
