package com.quanlyphongkhamvadatlich.repository;

import com.quanlyphongkhamvadatlich.entity.Patient;
import com.quanlyphongkhamvadatlich.entity.User;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    public Optional<Patient> findById(Long id);
    public Optional<Patient> findByIdAndUser(Long id, User user);
    Patient getPatientById(Long patientId);
    List<Patient> getPatientByUserId(Long id);
    Page<Patient> findAll(Pageable pageable);
    
    @Query("SELECT p FROM Patient p WHERE p.name LIKE %:keyWord% OR p.phone LIKE %:keyWord% OR p.address LIKE %:keyWord% OR p.citizenNumber LIKE %:keyWord% OR p.career LIKE %:keyWord% OR p.insurance_number LIKE %:keyWord%")
    Page<Patient> search(@Param("keyWord") String keyWord, Pageable pageable);
}
