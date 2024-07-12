package com.quanlyphongkhamvadatlich.repository;

import com.quanlyphongkhamvadatlich.entity.EmailAttachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmailAttachmentRepository extends JpaRepository<EmailAttachment, Long> {
    // Các phương thức truy vấn tùy chỉnh (nếu cần) có thể được định nghĩa ở đây


    // Phương thức tìm tất cả các tệp đính kèm theo appointmentId
    List<EmailAttachment> findByAppointmentId(Long appointmentId);

    // Ví dụ một truy vấn JPQL tùy chỉnh
    @Query("SELECT ea FROM EmailAttachment ea WHERE ea.appointment.id = :appointmentId")
    List<EmailAttachment> findAttachmentsByAppointmentId(@Param("appointmentId") Long appointmentId);
}