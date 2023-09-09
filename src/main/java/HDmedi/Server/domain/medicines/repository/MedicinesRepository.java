package HDmedi.Server.domain.medicines.repository;

import HDmedi.Server.domain.medicines.entity.Medicines;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicinesRepository extends JpaRepository<Medicines, Long> {
}
