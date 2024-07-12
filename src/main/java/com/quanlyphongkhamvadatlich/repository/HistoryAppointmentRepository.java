package com.quanlyphongkhamvadatlich.repository;

import com.quanlyphongkhamvadatlich.dto.dashboard.HistoryAppointmentDTO;
import com.quanlyphongkhamvadatlich.entity.PatientRecord;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface HistoryAppointmentRepository extends JpaRepository<PatientRecord, Long> {
    @Query("SELECT new com.quanlyphongkhamvadatlich.dto.dashboard.HistoryAppointmentDTO(a.appointmentDate,a.appointmentShift,a.orderNumber,d.username,pr.diagnosis,a.status.id,pr.id) FROM PatientRecord pr JOIN pr.appointment a JOIN pr.doctor d WHERE (a.appointmentDate BETWEEN :startDate AND :endDate) AND (a.status.id = 3)")
    public List<HistoryAppointmentDTO> ListOfHistoryAppointmentByDates(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    public PatientRecord getPatientRecordById(Long id);
}
