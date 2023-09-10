package HDmedi.Server.domain.user_child.repository;

import HDmedi.Server.domain.user_child.entity.UserChild;
import HDmedi.Server.domain.user_entity.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserChildRepository extends JpaRepository<UserChild, Long> {

    Optional<UserChild> findByUserEntity(UserEntity userEntity);


}
