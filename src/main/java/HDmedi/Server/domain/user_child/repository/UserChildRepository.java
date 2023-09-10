package HDmedi.Server.domain.user_child.repository;

import HDmedi.Server.domain.user_child.entity.UserChild;
import HDmedi.Server.domain.user_entity.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserChildRepository extends JpaRepository<UserChild, Long> {

    List<UserChild> findByUserEntity(UserEntity userEntity);

    UserChild findByNameAndUserEntity(String name, UserEntity userEntity);

    Optional<UserChild> findByName(String name);


}
