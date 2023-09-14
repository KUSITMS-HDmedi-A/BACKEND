package HDmedi.Server.domain.user_child.repository;

import HDmedi.Server.domain.alarm.entity.Alarm;

import HDmedi.Server.domain.child_medicine.entity.ChildMedicine;
import HDmedi.Server.domain.user_child.entity.UserChild;
import HDmedi.Server.domain.user_entity.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserChildRepository extends JpaRepository<UserChild, Long> {

    List<UserChild> findByUserEntity(UserEntity userEntity);

    UserChild findByNameAndUserEntity(String name, UserEntity userEntity);

    Optional<UserChild> findByName(String name);

    @Query("SELECT a FROM Alarm a JOIN FETCH a.childMedicine cm JOIN cm.userChild uc WHERE uc = :userChild")
    List<Alarm> findAlarmsByUserChild(@Param("userChild") UserChild userChild);



























}
