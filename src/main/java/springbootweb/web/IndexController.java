package springbootweb.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import springbootweb.service.posts.PostsService;
import springbootweb.web.dto.PostsResponseDto;

@RequiredArgsConstructor
@Controller
public class IndexController {
    // 페이지 관련 컨트롤러 //

    private final PostsService postsService;

    @GetMapping("/") // home view mapping
    public String index(Model model) {
        model.addAttribute("posts", postsService.findAllDesc());
        return "index";
    }

    @GetMapping("/posts/save") // 게시글 등록 mapping
    public String postSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}") // 게시글 수정 mapping
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }

}
