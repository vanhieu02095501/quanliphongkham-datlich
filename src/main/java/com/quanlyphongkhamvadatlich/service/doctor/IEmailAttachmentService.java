package com.quanlyphongkhamvadatlich.service.doctor;

import com.quanlyphongkhamvadatlich.entity.EmailAttachment;

import java.util.List;
import java.util.Optional;

public interface IEmailAttachmentService {
    EmailAttachment save(EmailAttachment emailAttachment);
    Optional<EmailAttachment> findById(Long id);
    List<EmailAttachment> findAll();
    void deleteById(Long id);
    List<EmailAttachment> findByAppointmentId(Long appointmentId);  // Phương thức tùy chỉnh
}