package com.quanlyphongkhamvadatlich.service.doctor.impl;
import com.quanlyphongkhamvadatlich.dto.client.AutoSchedulerEmailNotifierDTO;
import com.quanlyphongkhamvadatlich.dto.dashboard.MedicalServiceDTO;
import com.quanlyphongkhamvadatlich.dto.dashboard.SendInvoiceEmailNotifierDTO;
import com.quanlyphongkhamvadatlich.entity.Appointment;
import com.quanlyphongkhamvadatlich.entity.MedicalService;
import com.quanlyphongkhamvadatlich.entity.PatientRecord;
import com.quanlyphongkhamvadatlich.entity.Status;
import com.quanlyphongkhamvadatlich.repository.AppointmentRepository;
import com.quanlyphongkhamvadatlich.repository.AppointmentStatusRepository;
import com.quanlyphongkhamvadatlich.repository.PatientRecordRepository;
import com.quanlyphongkhamvadatlich.service.doctor.IAppointmentService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class AppointmentServiceImpl implements IAppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private AppointmentStatusRepository appointmentStatusRepository;

    @Autowired
    private PatientRecordRepository patientRecordRepository;

    @Autowired
    private  JavaMailSender javaMailSender;

    @Autowired
    private  SpringTemplateEngine templateEngine;

    @Override
    public List<Appointment> fillAll() {
        return appointmentRepository.findAll();
    }

    @Override
    public List<Appointment> findAllOrderByAppointmentShiftAndOrderNumber() {
        return appointmentRepository.findAllOrderByAppointmentShiftAndOrderNumber();
    }


//
//    public Page<Appointment> findPage(int pageNumber) {
//        Pageable pageable = PageRequest.of(pageNumber - 1, 10); // 10 là số mục mỗi trang, bạn có thể thay đổi nếu cần
//        return appointmentRepository.findAll(pageable);
//    }

    public Page<Appointment> findByAppointmentDate(Date appointmentDate, int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber - 1, 10); // 10 là số mục mỗi trang, bạn có thể thay đổi nếu cần
        return appointmentRepository.findByAppointmentDate(appointmentDate, pageable);
    }
    @Override
    public Page<Appointment> findPage(int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber - 1,10);
        return appointmentRepository.findAll(pageable);
    }



//    @Override
//    public Page<List<Appointment>> findPageWithKeyword(int pageNumber, Date date) {
//        Pageable pageable = PageRequest.of(pageNumber - 1, 5);
//        return appointmentRepository.findByAppointmentDate(date, pageable);
//    }

    @Override
    public Optional<Appointment> findById(Long id) {
        return appointmentRepository.findById(id);
    }

    @Override
    public Optional<PatientRecord> findByPatientRecordId(Long id) {
        return patientRecordRepository.findById(id);
    }
//    @Override
//    public List<Appointment> findByAppointmentDate(Date date) {
//        return appointmentRepository.findByAppointmentDate(date);
//    }



    @Override
    public Appointment createAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    @Override
    public Appointment updateAppointment(Long id, Appointment appointment) {
            return null;
    }

    @Override
    public void deleteAppointment(Long id) {
        appointmentRepository.deleteById(id);
    }

    @Override
    public SendInvoiceEmailNotifierDTO createInvoiceEmailNotifier(Long appointmentId) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                                .orElseThrow(() -> new RuntimeException("Appointment not found"));



        // Lấy thông tin hồ sơ bệnh nhân từ database

        PatientRecord patientRecord = patientRecordRepository.findById(appointment.getPatient().getId())
                .orElseThrow(() -> new RuntimeException("Patient not found"));


        // Tạo đối tượng DTO
        SendInvoiceEmailNotifierDTO dto = new SendInvoiceEmailNotifierDTO();
        dto.setEmail(patientRecord.getPatient().getUser().getEmail());
        dto.setOrderNumber(appointment.getOrderNumber());
        dto.setPatientId(patientRecord.getPatient().getId());
        dto.setName(patientRecord.getPatient().getName());
        dto.setPhone(patientRecord.getPatient().getPhone());
        dto.setAppointmentDate(appointment.getAppointmentDate());
        dto.setAppointmentShift(appointment.getAppointmentShift());
        dto.setNameDoctor(patientRecord.getDoctor().getUsername());
        dto.setDiagnosis(patientRecord.getDiagnosis());
        dto.setServiceDetails(patientRecord.getServiceDetails());
        dto.setTotalFees(patientRecord.getTotalFees());

        return dto;
    }

    @Override
    public void save(Appointment existingAppointment) {
        appointmentRepository.save(existingAppointment);
    }


    @Override
    public void appointmentSendInvoice(SendInvoiceEmailNotifierDTO notifierDTO) throws MessagingException{
        MimeMessage message = javaMailSender.createMimeMessage();
        Context context = new Context();
        context.setVariable("notifierInvoice", notifierDTO);

        String process = templateEngine.process("template-email/send-invoice", context);
        MimeMessageHelper helper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());

        helper.setTo(notifierDTO.getEmail());
        //helper.setFrom(new InternetAddress("nhatminhle1402@gmail.com"));
        helper.setFrom(new InternetAddress("Vanphongnhakhoa@gmail.com"));
        helper.setSubject("KẾT QUẢ KHÁM VÀ CHI TIẾT HÓA ĐƠN!");
        helper.setText(process, true);

        javaMailSender.send(message);
    }



    @Transactional
    @Override
    public void updateAppointmentStatus(Long appointmentId, Long appointmentStatusId) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

//        Status status = appointmentStatusRepository.findById(appointmentStatusId)
//                .orElseThrow(() -> new RuntimeException("Appointment status not found"));

        Status status = appointmentStatusRepository.findById(appointmentStatusId)
                .orElseThrow(() -> new RuntimeException("Appointment status not found"));

        appointment.setStatus(status);
        appointmentRepository.saveAndFlush(appointment);
    }


}
