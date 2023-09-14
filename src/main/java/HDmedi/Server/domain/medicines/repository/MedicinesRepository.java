package HDmedi.Server.domain.medicines.repository;

import HDmedi.Server.domain.child_medicine.entity.ChildMedicine;
import HDmedi.Server.domain.medicines.entity.Medicines;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicinesRepository extends JpaRepository<Medicines, Long> {

    List<Medicines> findByChildMedicine(ChildMedicine childMedicine);
}
