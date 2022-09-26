package springbootweb.service.posts;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springbootweb.domain.posts.PostsRepository;
import springbootweb.web.dto.PostsSaveRequestDto;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    // JPA를 통한 모든 데이터 변경은 트랜잭션 안에서 실행해야 한다.
    @Transactional // for jpa
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }
}
