package HDmedi.Server.domain.medicine_item.repository;

import HDmedi.Server.domain.medicine_item.entity.MedicineItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MedicineItemRepository extends JpaRepository<MedicineItem, Long> {

    @Query("SELECT m.id FROM MedicineItem m WHERE m.name = : name")
    String findUsernameById(@Param("name") String name);
}
