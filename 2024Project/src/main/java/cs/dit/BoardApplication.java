package cs.dit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("cs.dit.mapper")  // 매퍼 인터페이스가 위치한 패키지 지정
public class BoardApplication {
	public static void main(String[] args) {
        SpringApplication.run(BoardApplication.class, args);
    }
}
