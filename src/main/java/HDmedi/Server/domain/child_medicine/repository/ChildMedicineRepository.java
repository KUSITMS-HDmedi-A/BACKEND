package HDmedi.Server.domain.child_medicine.repository;

import HDmedi.Server.domain.child_medicine.entity.ChildMedicine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChildMedicineRepository extends JpaRepository<ChildMedicine, Long> {
}
