package springbootweb.web;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import springbootweb.domain.posts.Posts;
import springbootweb.domain.posts.PostsRepository;
import springbootweb.web.dto.PostsSaveRequestDto;
import springbootweb.web.dto.PostsUpdateRequestDto;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostsApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate; // 간편하게 Rest 방식 api 를 호출할 수 있는 spring 내장 클래스, return json

    @Autowired
    private PostsRepository postsRepository;

    @After
    public void tearDown() throws Exception {
        postsRepository.deleteAll();
    }

    @Test // Posts 등록 API 테스트
    public void Posts_등록된다() throws Exception { // passed
        //given
        String title = "testTitle";
        String content = "testContent";
        PostsSaveRequestDto requestDto = PostsSaveRequestDto.builder()
                .title(title)
                .content(content)
                .author("testAuthor")
                .build();

        String url = "http://localhost:" + port + "/api/v1/posts";

        //when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts> all = postsRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(title);
        assertThat(all.get(0).getContent()).isEqualTo(content);
    }

    @Test
    public void Posts_수정된다() throws Exception{
        //given
        Posts savedPosts = postsRepository.save(Posts.builder() // DB에 데이터 저장
                .title("testTitle")
                .content("testContent")
                .author("testAuthor")
                .build());

        Long updateId = savedPosts.getId(); // 수정할 id 지정
        String expectedTitle = "updateTitle";
        String expectedContent = "updateContent"; // 수정할 데이터 지정

        PostsUpdateRequestDto requestDto = PostsUpdateRequestDto.builder() // dto 에 수정 데이터 담음
                .title(expectedTitle)
                .content(expectedContent)
                .build();

        String url = "http://localhost:" + port + "/api/v1/posts/" + updateId; // test url 지정

           // HTTP 요청 또는 응답에 해당하는 HttpHeader와 HttpBody를 만들어 주는 클래스
        HttpEntity<PostsUpdateRequestDto> requestDtoHttpEntity = new HttpEntity<>(requestDto);

        //when
                // 호출 결과로 http status code, 헤더 정보, 실제 데이터가 존재하는 body정보를 확인
        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestDtoHttpEntity, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L); // body null check

        List<Posts> all = postsRepository.findAll(); // 전체 조회

        assertThat(all.get(0).getTitle()).isEqualTo(expectedTitle); // title 비교 테스트
        assertThat(all.get(0).getContent()).isEqualTo(expectedContent); // content 비교 테스트

    }
}
