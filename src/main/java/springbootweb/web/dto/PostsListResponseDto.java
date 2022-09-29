package springbootweb.web.dto;

import lombok.Getter;
import springbootweb.domain.posts.Posts;

import java.time.LocalDateTime;

@Getter
public class PostsListResponseDto { // 전체 조회 Dto
    private Long id;
    private String title;
    private String author;
    private LocalDateTime modifiedDate; // 구현예정

    public PostsListResponseDto(Posts entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.author = entity.getAuthor();
    }
}
