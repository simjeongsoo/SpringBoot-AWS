package springbootweb.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import springbootweb.service.posts.PostsService;
import springbootweb.web.dto.PostsSaveRequestDto;
import springbootweb.web.dto.PostsUpdateRequestDto;

@RequiredArgsConstructor // 생성자 주입(lombok)
@RestController
public class PostsApiController {
    private final PostsService postsService;

    @PostMapping("/api/v1/posts") // 등록 api
    public Long save(@RequestBody PostsSaveRequestDto requestDto) {
        return postsService.save(requestDto);
    }

    @PutMapping("/api/v1/posts/{id}") // 수정 api 
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto) {
        return postsService.update(id, requestDto);
    }

    // 조회
}
