package springbootweb.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import springbootweb.config.auth.LoginUser;
import springbootweb.config.auth.dto.SessionUser;
import springbootweb.service.posts.PostsService;
import springbootweb.web.dto.PostsResponseDto;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {
    // 페이지 관련 컨트롤러 //

    private final PostsService postsService;
    private final HttpSession httpSession;

    @GetMapping("/") // home view mapping
    public String index(Model model, @LoginUser SessionUser user) {
        model.addAttribute("posts", postsService.findAllDesc());

        //--- 기존에(User) httpSession.getAttribute(”user”) 로 가져오던 세션값이 개선
        //- 이제는 어느 컨트롤러든지 @LoginUser 만 사용하면 세션 정보를 가져올 수 있게 됨--//
        if (user != null) {
            model.addAttribute("userName", user.getName());
        }

        return "index";
    }

    @GetMapping("/posts/save") // 게시글 등록 mapping
    public String postSave(Model model) {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}") // 게시글 수정 mapping
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }

}
