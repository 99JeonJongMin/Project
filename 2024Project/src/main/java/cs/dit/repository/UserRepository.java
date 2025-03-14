package cs.dit.repository;

import cs.dit.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    
    // 특정 userid로 사용자 조회
    Optional<UserEntity> findByUserid(String userid);
    
    // 특정 userid를 가진 사용자 삭제
    void deleteByUserid(String userid);
}
