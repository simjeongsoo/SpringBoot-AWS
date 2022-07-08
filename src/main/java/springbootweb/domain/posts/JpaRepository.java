package springbootweb.domain.posts;

// Posts 클래스로 Database 를 접근하게 해줄 인터페이스 (MyBatis 에서 DAO)
// extends JpaRepository<Entity 클래스, PK 타입> -> 상속시 기본적인 CRUD 메소드 자동생성
// Entity class 와 Entity Repository class 는 같은 패키지에 위치 
public interface JpaRepository extends org.springframework.data.jpa.repository.JpaRepository<Posts,Long> {

}
