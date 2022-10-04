package springbootweb.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/")
    public String index(Model model) { // home view mapping
        model.addAttribute("posts", postsService.findAllDesc());

        //--index.mustache 에서 userName을 사용할 수 있게 IndexController에서 userName을 model에 저장하는 코드--//

        // CustomOAuth2UserService 에서 로그인 성공 시 세션에 SessionUser를 저장하도록 구성
        // 로그인 성공 시 httpSession.getAttribute(”user”)에서 값을 가져 올 수 있음
        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        if (user != null) {
            // 세션에 저장된 값이 있을때만 model에 userName으로 등록
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
