package com.quanlyphongkhamvadatlich.repository;

import com.quanlyphongkhamvadatlich.entity.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicineRepository extends JpaRepository<Medicine, Long> {
    Medicine getMedicineById(Long medicineId);
}
