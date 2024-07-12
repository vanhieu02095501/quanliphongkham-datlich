package com.quanlyphongkhamvadatlich.api;


import com.quanlyphongkhamvadatlich.dto.client.AppointmentDTO;
import com.quanlyphongkhamvadatlich.dto.dashboard.SendInvoiceEmailNotifierDTO;
import com.quanlyphongkhamvadatlich.entity.Appointment;
import com.quanlyphongkhamvadatlich.entity.Patient;
import com.quanlyphongkhamvadatlich.entity.PatientRecord;
import com.quanlyphongkhamvadatlich.entity.Status;
import com.quanlyphongkhamvadatlich.repository.PatientRecordRepository;
import com.quanlyphongkhamvadatlich.security.UserPrincipal;
import com.quanlyphongkhamvadatlich.service.PatientService;
import com.quanlyphongkhamvadatlich.service.doctor.IAppointmentService;
import com.quanlyphongkhamvadatlich.service.doctor.IAppointmentStatusService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

@Controller
@RequestMapping("/doctor/appointments")
public class AppointmentAPI {


    @Autowired
    private IAppointmentService appointmentService;

    @Autowired
    private IAppointmentStatusService appointmentStatusService;

    @Autowired
    private PatientRecordRepository patientRecordRepository;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private SpringTemplateEngine templateEngine;



    @GetMapping("")
    public String getAllPages(Model model,
                              @RequestParam(name = "keyword", required = false) String keyword,
                              @RequestParam(name = "page", defaultValue = "1") int page) {
        if (keyword == null || keyword.isEmpty()) {
            return getOnePage(model, page, null);
        } else {
            try {
                List<Status> statusList = appointmentStatusService.findAll();

                // Chuyển đổi chuỗi thành đối tượng Date với định dạng "yyyy-MM-dd"
                SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date date = inputDateFormat.parse(keyword.trim());

                // Tìm danh sách cuộc hẹn theo ngày với phân trang
                Page<Appointment> appointmentPage = appointmentService.findByAppointmentDate(date, page);
                int totalPages = appointmentPage.getTotalPages();
                long totalItems = appointmentPage.getTotalElements();
                List<Appointment> appointments = appointmentPage.getContent();

                model.addAttribute("currentPage", page);
                model.addAttribute("totalPages", totalPages);
                model.addAttribute("totalItems", totalItems);
                model.addAttribute("appointments", appointments);
                model.addAttribute("keyword", keyword);
                model.addAttribute("statusList", statusList);

                return "dashboard/doctor/appointment_schedule";
            } catch (ParseException e) {
                // Xử lý nếu chuỗi không thể được phân tích thành ngày
                e.printStackTrace();
                model.addAttribute("error", "Ngày không hợp lệ. Vui lòng chọn ngày khác.");
                return "dashboard/doctor/appointment_schedule"; // Trả về trang với thông báo lỗi
            }
        }
    }


    @GetMapping("/page/{pageNumber}")
    public String getOnePage(Model model,
                             @PathVariable("pageNumber") int currentPage,
                             @RequestParam(name = "keyword", required = false) String keyword) {

        List<Status> statusList = appointmentStatusService.findAll();
        Page<Appointment> page;

        // Lấy ngày hiện tại và chuyển đổi thành chuỗi với định dạng "yyyy-MM-dd"
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String defaultKeyword = today.format(formatter);

        if (keyword == null || keyword.isEmpty()) {
            keyword = defaultKeyword;
        }

        try {
            // Chuyển đổi chuỗi thành đối tượng Date với định dạng "yyyy-MM-dd"
            SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = inputDateFormat.parse(keyword.trim());
            // Tìm danh sách cuộc hẹn theo ngày với phân trang
            page = appointmentService.findByAppointmentDate(date, currentPage);
        } catch (ParseException e) {
            // Xử lý nếu chuỗi không thể được phân tích thành ngày
            e.printStackTrace();
            model.addAttribute("error", "Ngày không hợp lệ. Vui lòng chọn ngày khác.");
            return "dashboard/doctor/appointment_schedule"; // Trả về trang với thông báo lỗi
        }

        int totalPages = page.getTotalPages();
        long totalItems = page.getTotalElements();
        List<Appointment> appointments = page.getContent();

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("appointments", appointments);
        model.addAttribute("statusList", statusList);
        model.addAttribute("keyword", keyword);

        return "dashboard/doctor/appointment_schedule";
    }




    @PostMapping
    public String createAppointment(@ModelAttribute Appointment appointment) {
        appointmentService.createAppointment(appointment);
        return "redirect:/doctor/appointments"; // Redirect to appointment list page
    }

    @PutMapping("/{id}")
    public String updateAppointment(@PathVariable Long id, @ModelAttribute Appointment appointment) {
        appointmentService.updateAppointment(id, appointment);
        return "redirect:/doctor/appointments"; // Redirect to appointment list page
    }

    @GetMapping("/{id}/status")
    public String updateAppointmentStatus(@PathVariable Long id, @RequestParam Long statusId) {
        appointmentService.updateAppointmentStatus(id, statusId);
        return "redirect:/doctor/appointments"; // Redirect to appointment list page
    }

    @GetMapping  ("/delete/{id}")
    public String deleteAppointment(@PathVariable Long id) {
        appointmentService.deleteAppointment(id);
        return "redirect:/doctor/appointments"; // Redirect to appointment list page
    }

//    @GetMapping("/invoice")
//    public String success(Model model, @RequestParam(name = "appointmentId") Long appointmentId) throws MessagingException {
//
//
//        Appointment appointment = appointmentService.findById(appointmentId)
//                .orElseThrow(() -> new RuntimeException("Appointment not found"));
//
//
//        // Lấy thông tin hồ sơ bệnh nhân từ database
//
//        PatientRecord patientRecord = appointmentService.findByPatientRecordId(appointment.getPatient().getId())
//                .orElseThrow(() -> new RuntimeException("Patient not found"));
//
//
//        // Tạo đối tượng DTO
//        SendInvoiceEmailNotifierDTO dto = new SendInvoiceEmailNotifierDTO();
//        dto.setEmail(patientRecord.getPatient().getUser().getEmail());
//        dto.setOrderNumber(appointment.getOrderNumber());
//        dto.setPatientId(patientRecord.getPatient().getId());
//        dto.setName(patientRecord.getPatient().getName());
//        dto.setPhone(patientRecord.getPatient().getPhone());
//        dto.setAppointmentDate(appointment.getAppointmentDate());
//        dto.setAppointmentShift(appointment.getAppointmentShift());
//        dto.setNameDoctor(patientRecord.getDoctor().getUsername());
//        dto.setDiagnosis(patientRecord.getDiagnosis());
//        dto.setServiceDetails(patientRecord.getServiceDetails());
//        dto.setTotalFees(patientRecord.getTotalFees());
//
////        MimeMessage message = javaMailSender.createMimeMessage();
////        Context context = new Context();
////        context.setVariable("notifierInvoice", dto);
//        appointmentService.appointmentSendInvoice(dto);
//        return null;
//
//
////        return "redirect:/doctor/appointments/page/";
//    }
}
