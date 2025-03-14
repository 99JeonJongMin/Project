package cs.dit.domain;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users") // DB 테이블명 지정
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동 증가
    private Long id; // 기본 키

    @Column(nullable = false, unique = true)
    private String userid; // 사용자 ID

    @Column(nullable = false)
    private String passwd; // 비밀번호

    @Column(nullable = false)
    private String name; // 이름

    @Column(nullable = false, unique = true)
    private String email; // 이메일

    @Column(updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now(); // 가입 날짜 자동 설정

}
