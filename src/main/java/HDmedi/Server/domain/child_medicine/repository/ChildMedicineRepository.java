package HDmedi.Server.domain.child_medicine.repository;

import HDmedi.Server.domain.child_medicine.entity.ChildMedicine;
import HDmedi.Server.domain.user_child.entity.UserChild;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChildMedicineRepository extends JpaRepository<ChildMedicine, Long> {

    List<ChildMedicine> findByUserChild(UserChild userChild);



}
