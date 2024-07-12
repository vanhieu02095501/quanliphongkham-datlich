package com.quanlyphongkhamvadatlich.api;

import com.quanlyphongkhamvadatlich.entity.Appointment;
import com.quanlyphongkhamvadatlich.entity.EmailAttachment;
import com.quanlyphongkhamvadatlich.service.doctor.IAppointmentService;
import com.quanlyphongkhamvadatlich.service.doctor.IEmailAttachmentService;
import com.quanlyphongkhamvadatlich.service.doctor.IFileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/doctor/emailAttachments")
public class EmailAttachmentAPI {
//
//    @Autowired
//    private IEmailAttachmentService emailAttachmentService;
//    @Autowired
//    private IFileStorageService fileStorageService;
//
//    private IAppointmentService appointmentService;
//
//
//    @PostMapping
//    public ResponseEntity<EmailAttachment> createEmailAttachment(
//            @RequestParam("file") MultipartFile file,
//            @RequestParam("appointmentId") Long appointmentId) {
//        try {
//            String fileName = fileStorageService.storeFile(file);
//            Optional<Appointment> appointmentOptional = appointmentService.findById(appointmentId);
//
//            if (!appointmentOptional.isPresent()) {
//                return ResponseEntity.status(404).body(null); // Trả về lỗi 404 nếu không tìm thấy appointment
//            }
//
//            Appointment appointment = appointmentOptional.get();
//
//            EmailAttachment emailAttachment = EmailAttachment.builder()
//                    .fileName(fileName)
//                    .appointment(appointment)
//                    .build();
//
//            EmailAttachment savedEmailAttachment = emailAttachmentService.save(emailAttachment);
//            return ResponseEntity.ok(savedEmailAttachment);
//        } catch (IOException ex) {
//            return ResponseEntity.status(500).body(null);
//        }
//    }
//    @GetMapping("/{id}")
//    public ResponseEntity<EmailAttachment> getEmailAttachmentById(@PathVariable Long id) {
//        Optional<EmailAttachment> emailAttachment = emailAttachmentService.findById(id);
//        return emailAttachment.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
//    }
//
//    @GetMapping
//    public ResponseEntity<List<EmailAttachment>> getAllEmailAttachments() {
//        List<EmailAttachment> emailAttachments = emailAttachmentService.findAll();
//        return ResponseEntity.ok(emailAttachments);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteEmailAttachment(@PathVariable Long id) {
//        emailAttachmentService.deleteById(id);
//        return ResponseEntity.noContent().build();
//    }
//
//    @GetMapping("/appointment/{appointmentId}")
//    public ResponseEntity<List<EmailAttachment>> getEmailAttachmentsByAppointmentId(@PathVariable Long appointmentId) {
//        List<EmailAttachment> emailAttachments = emailAttachmentService.findByAppointmentId(appointmentId);
//        return ResponseEntity.ok(emailAttachments);
//    }
}