package HDmedi.Server.domain.medicine_item.repository;

import HDmedi.Server.domain.medicine_item.entity.MedicineItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicineItemRepository extends JpaRepository<MedicineItem, Long> {
}
