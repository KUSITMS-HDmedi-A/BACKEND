package HDmedi.Server.domain.user_entity.repository;

import HDmedi.Server.domain.user_entity.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {


    Optional<UserEntity> findByEmail(String userId);
}
