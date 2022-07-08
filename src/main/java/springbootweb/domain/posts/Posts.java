package springbootweb.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter // 롬복 어노테이션 , 클래스 내 모든 필드의 Getter 메소드 자동 생성
@NoArgsConstructor // 롬복 어노테이션 , 기본 생성자 자동 추가 public Posts(){}과 같은 효과
@Entity // JPA 어노테이션
public class Posts { // 실제 DB 테이블과 매칭될 클래스 (=Entity 클래스)
    @Id // 해당 테이블의 PK 필드를 나타냄
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PK 생성규칙 지정 ,GenerationType.IDENTITY=auto_increment
    private Long id;

    @Column(length = 500, nullable = false) // 테이블의 컬럼
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder // 해당 클래스의 빌더 패턴 클래스 생성, 생성자 상단에 선언시 생성자에 포함된 필드만 빌더 포함
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }
}

/*
* JPA 를 사용하면 DB 데이터에 작업할 경우 실제 쿼리를 날리기보다는, 이 Entity 클래스의 수정을 통해 작업
* @Entity : 테이블과 링크될 클래스임을 나타냄
*           기본값으로 클래스의 카멜케이스 이름을 언더스코어 네이밍(_) 으로 테이블 이름을 매칭
* Entity 클래스에서는 절대 Setter 메소드를 만들지 않음 */