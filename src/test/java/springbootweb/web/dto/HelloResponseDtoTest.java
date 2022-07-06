package springbootweb.web.dto;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HelloResponseDtoTest {

    @Test
    public void 롬복_기능_테스트() { // tests passed , 롬복의 @Getter 로 get 메소드가, @RequiredArgsConstructor 로 생성자가 자동 생성
        //given
        String name = "test";
        int amount = 1000;

        //when
        HelloResponseDto dto = new HelloResponseDto(name,amount);

        //then
        assertThat(dto.getName()).isEqualTo(name);
        assertThat(dto.getAmount()).isEqualTo(amount);

        /*
        * assertThat() : assertj 라는 테스트 검증 라이브러리의 검증 메소드
        *                검증하고 싶은 대상을 메소드 인자로 받음(dto 객체)
        *                메소드 체이닝이 지원되어 isEqualTo 와 같이 메소드를 이어서 사용할 수 있음
        * isEqualTo() : assertj 의 동등 비교 메소드
        *               assertThat 에 있는 값과 isEqualTo 의 값을 비교해서 같을 때만 성공
        *
        * Junit 의 기본 assertThat 이 아닌 assertj 의 assertThat 을 사용한 이유
        *   1. CoreMatchers 와 달리 추가적으로 라이브러리가 필요없음
        *       -> Junit 의 assertThat 을 쓰게되면 is() 와 같이 CoreMatchers 라이브러리 필요
        *   2. 자동완성이 좀 더 확실하게 지원
        * */
    }
}
