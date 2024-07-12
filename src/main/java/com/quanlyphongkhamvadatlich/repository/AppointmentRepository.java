package com.quanlyphongkhamvadatlich.repository;

import com.quanlyphongkhamvadatlich.entity.Appointment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.quanlyphongkhamvadatlich.dto.client.AutoSchedulerEmailNotifierDTO;
import com.quanlyphongkhamvadatlich.dto.client.DisableAppointmentDTO;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

        // Tìm kiếm cuộc hẹn theo ID
        Optional<Appointment> findById(Long id);

        @Query("SELECT a FROM Appointment a " +
                        "ORDER BY " +
                        "CASE " +
                        "WHEN a.appointmentShift = 'sáng' THEN 1 " +
                        "WHEN a.appointmentShift = 'chiều' THEN 2 " +
                        "WHEN a.appointmentShift = 'tối' THEN 3 " +
                        "ELSE 4 END, " +
                        "a.orderNumber ASC")
        List<Appointment> findAllOrderByAppointmentShiftAndOrderNumber();

        @Query("SELECT a FROM Appointment a WHERE a.appointmentDate = :appointmentDate " +
                        "ORDER BY " +
                        "CASE " +
                        "WHEN a.appointmentShift = 'sáng' THEN 1 " +
                        "WHEN a.appointmentShift = 'chiều' THEN 2 " +
                        "WHEN a.appointmentShift = 'tối' THEN 3 " +
                        "ELSE 4 END, " +
                        "a.orderNumber ASC")
        Page<Appointment> findByAppointmentDate(Date appointmentDate, Pageable pageable);

        // Thêm mới một cuộc hẹn
        Appointment save(Appointment appointment);

        // Cập nhật thông tin của một cuộc hẹn
        Appointment saveAndFlush(Appointment appointment);

        // Xóa một cuộc hẹn dựa trên ID
        void deleteById(Long id);

        @Modifying
        @Transactional
        @Query("UPDATE Appointment a SET a.status.id = :appointmentStatusId WHERE a.id = :appointmentId")
        void updateAppointmentStatus(Long appointmentId, Long appointmentStatusId);

        public List<Appointment> findByAppointmentDateAndAppointmentShift(Date appointmentDate,
                        String appointmentShift);

        @Query(value = "SELECT new com.quanlyphongkhamvadatlich.dto.client.DisableAppointmentDTO(a.appointmentDate) FROM Appointment a GROUP BY a.appointmentDate HAVING COUNT(a.id) >= 60")
        public List<DisableAppointmentDTO> getAllDisableAppointment();

        @Query("SELECT new com.quanlyphongkhamvadatlich.dto.client.AutoSchedulerEmailNotifierDTO(u.email, a.orderNumber, p.id, p.name, p.phone, a.appointmentDate, a.appointmentShift) FROM Appointment a JOIN a.patient p JOIN p.user u where a.appointmentDate = :appointmentDate AND a.status.id = :statusId")
        List<AutoSchedulerEmailNotifierDTO> findByAppointmentDateAndStatusId(
                        @Param("appointmentDate") Date appointmentDate, @Param("statusId") int statusId);

        @Query("SELECT a FROM Appointment a WHERE a.patient.id = :patientId")
        Appointment findByPatientId(@Param("patientId") Long patientId);

        Appointment getAppointmentById(Long id);

        Optional<Appointment> findByIdAndUserId(Long appointmentID, Long userID);

        @Query("SELECT a FROM Appointment a WHERE a.patient.id = :patientId and a.status.id = :statusId")
        List<Appointment> findByStatusIdAndPatientId(@Param("statusId") Long statusId, @Param("patientId") Long patientId);
}