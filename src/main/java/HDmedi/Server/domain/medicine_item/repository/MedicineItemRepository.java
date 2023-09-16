package HDmedi.Server.domain.medicine_item.repository;

import HDmedi.Server.domain.child_medicine.entity.ChildMedicine;
import HDmedi.Server.domain.medicine_item.entity.MedicineItem;
import HDmedi.Server.domain.medicines.entity.Medicines;
import HDmedi.Server.domain.user_child.entity.UserChild;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MedicineItemRepository extends JpaRepository<MedicineItem, Long> {


   Optional<MedicineItem> findById(Long id);

    MedicineItem findByName(String name);

}
