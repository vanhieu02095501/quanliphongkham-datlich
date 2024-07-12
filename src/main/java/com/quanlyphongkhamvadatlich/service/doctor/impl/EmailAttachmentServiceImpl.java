package com.quanlyphongkhamvadatlich.service.doctor.impl;

import com.quanlyphongkhamvadatlich.entity.EmailAttachment;
import com.quanlyphongkhamvadatlich.repository.EmailAttachmentRepository;
import com.quanlyphongkhamvadatlich.service.doctor.IEmailAttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
 public class EmailAttachmentServiceImpl implements IEmailAttachmentService {

   @Autowired
   private EmailAttachmentRepository emailAttachmentRepository;

    @Override
    public EmailAttachment save(EmailAttachment emailAttachment) {
        return emailAttachmentRepository.save(emailAttachment);
    }

    @Override
    public Optional<EmailAttachment> findById(Long id) {
        return emailAttachmentRepository.findById(id);
    }

    @Override
    public List<EmailAttachment> findAll() {
        return emailAttachmentRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        emailAttachmentRepository.deleteById(id);
    }

    @Override
    public List<EmailAttachment> findByAppointmentId(Long appointmentId) {
        return emailAttachmentRepository.findByAppointmentId(appointmentId);
    }
}