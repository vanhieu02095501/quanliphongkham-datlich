package com.quanlyphongkhamvadatlich.repository;

import com.quanlyphongkhamvadatlich.entity.MedicalService;
import com.quanlyphongkhamvadatlich.entity.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalServiceRepository extends JpaRepository<MedicalService, Long> {
    MedicalService getServiceById(Long serviceId);

}
