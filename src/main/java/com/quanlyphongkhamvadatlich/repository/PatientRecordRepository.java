package com.quanlyphongkhamvadatlich.repository;

import com.quanlyphongkhamvadatlich.dto.Statistical;
import com.quanlyphongkhamvadatlich.entity.PatientRecord;
import com.quanlyphongkhamvadatlich.service.PatientRecordService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface PatientRecordRepository extends JpaRepository<PatientRecord, Long> {
    @Query("FROM PatientRecord pr WHERE DATE(pr.createdAt) BETWEEN :startDate AND :endDate AND pr.patient.id = :patientId")
    List<PatientRecord> getAllBetweenDatesAndPatientId(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            @Param("patientId") Long patientId);

    @Query("SELECT NEW com.quanlyphongkhamvadatlich.dto.Statistical( MONTH(p.createdAt), SUM(p.totalFees)) FROM PatientRecord p WHERE YEAR(p.createdAt) = :year GROUP BY MONTH(p.createdAt) ORDER BY MONTH(p.createdAt) ASC")
    List<Statistical> getMonthlyRevenueByYear(@Param("year") int year);
}
