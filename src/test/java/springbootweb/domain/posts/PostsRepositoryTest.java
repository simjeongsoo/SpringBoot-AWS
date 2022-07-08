package springbootweb.domain.posts;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest // H2 데이터베이스 자동 실행
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    /*
    * Junit 에서 단위 테스트가 끝날 때 마다 수행되는 메소드를 지정
    * 배포전 전체 테스트를 수행할 때 테스트간 데이터 침범을 막기 위해 사용
    * 여러 테스트가 수행되면 테스트용 데이터베이스인 H2 에 데이터가 그대로 남아있어 다음 테스트 실행 시 테스트가 실패할 수 있음 */
    @After
    public void cleanup() {
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기() { // test passed 
        // given
        String title = "테스트 게시글";
        String content = "테스트 본문";

        /*
        *postsRepository.save() : 테이블 posts 에 insert/update 쿼리 실행
        *                         id 값이 있다면 update 가, 없다면 insert 쿼리 실행  */
        postsRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .author("testauthor@gmail.com")
                .build());

        //when
        List<Posts> postsList = postsRepository.findAll(); // findAll() :  테이블 posts 에 있는 모든데이터 조회

        //then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }

}