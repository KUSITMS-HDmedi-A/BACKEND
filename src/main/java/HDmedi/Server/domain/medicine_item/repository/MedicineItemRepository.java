package HDmedi.Server.domain.medicine_item.repository;

import HDmedi.Server.domain.medicine_item.entity.MedicineItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface MedicineItemRepository extends JpaRepository<MedicineItem, Long> {


//    @Modifying
//    @Transactional
//    @Query("SELECT m.id FROM MedicineItem m WHERE m.name = :name")
//    MedicineItem findUsernameById(@Param("name") String name);

    MedicineItem findByName(String name);
}
