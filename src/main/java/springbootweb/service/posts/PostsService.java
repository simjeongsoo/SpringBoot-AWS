package springbootweb.service.posts;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springbootweb.domain.posts.Posts;
import springbootweb.domain.posts.PostsRepository;
import springbootweb.web.dto.PostsResponseDto;
import springbootweb.web.dto.PostsSaveRequestDto;
import springbootweb.web.dto.PostsUpdateRequestDto;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    // JPA를 통한 모든 데이터 변경은 트랜잭션 안에서 실행해야 한다.
    @Transactional // for jpa
    public Long save(PostsSaveRequestDto requestDto) { // 등록 로직
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) { // 수정 로직
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        posts.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }

    public PostsResponseDto findById(Long id) { // 조회 로직
        Posts entity = postsRepository.findById(id)
                .orElseThrow(()->
                        new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        return new PostsResponseDto(entity);

    }
}
