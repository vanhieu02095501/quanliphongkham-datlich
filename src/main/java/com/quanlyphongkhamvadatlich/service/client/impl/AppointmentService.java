package com.quanlyphongkhamvadatlich.service.client.impl;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import com.quanlyphongkhamvadatlich.entity.Appointment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import com.quanlyphongkhamvadatlich.dto.client.ApiResponse;
import com.quanlyphongkhamvadatlich.dto.client.AutoSchedulerEmailNotifierDTO;
import com.quanlyphongkhamvadatlich.repository.AppointmentRepository;
import com.quanlyphongkhamvadatlich.repository.StatusRepository;
import com.quanlyphongkhamvadatlich.security.UserPrincipal;
import com.quanlyphongkhamvadatlich.service.client.IAppointmentService;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AppointmentService implements IAppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final JavaMailSender javaMailSender;
    private final SpringTemplateEngine templateEngine;
    private final StatusRepository statusRepository;

    @Override
    public List<AutoSchedulerEmailNotifierDTO> findByAppointmentDateAndStatus(Date appointmentDate, int status) {
        return appointmentRepository.findByAppointmentDateAndStatusId(appointmentDate, status);
    }

    @Override
    public void appointmentReminder(AutoSchedulerEmailNotifierDTO notifierDTO) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        Context context = new Context();
        context.setVariable("notifier", notifierDTO);
        String process = templateEngine.process("template-email/nhac-kham", context);
        MimeMessageHelper helper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());

        helper.setTo(notifierDTO.getEmail());
        helper.setFrom(new InternetAddress("nhatminhle1402@gmail.com"));
        helper.setSubject("ĐỪNG QUÊN BẠN CÓ LỊCH KHÁM VÀO NGÀY MAI!");
        helper.setText(process, true);

        javaMailSender.send(message);
    }

    @Override
    public ApiResponse<Appointment> cancelAppointment(UserPrincipal principal, Long appointmentId) {
        Optional<Appointment> appointment = appointmentRepository.findByIdAndUserId(appointmentId, principal.getId());

        if (appointment.isEmpty()) {
            return new ApiResponse<Appointment>("Lịch khám không tìm thấy", null, HttpStatus.NOT_FOUND);
        }

        Appointment appointmentUpdate = appointment.get();
        appointmentUpdate.setStatus(statusRepository.findById((Integer) 4).get());
        appointmentRepository.save(appointmentUpdate);

        return new ApiResponse<Appointment>("Hủy đặt khám thành công", appointmentUpdate, HttpStatus.OK);
    }
    
    public Appointment getAppointmentById(Long id) {
        return appointmentRepository.getAppointmentById(id);
    }
}
