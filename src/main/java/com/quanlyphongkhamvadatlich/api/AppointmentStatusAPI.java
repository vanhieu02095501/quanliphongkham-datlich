package com.quanlyphongkhamvadatlich.api;

import com.quanlyphongkhamvadatlich.dto.dashboard.SendInvoiceEmailNotifierDTO;
import com.quanlyphongkhamvadatlich.entity.Appointment;
import com.quanlyphongkhamvadatlich.entity.PatientRecord;
import com.quanlyphongkhamvadatlich.entity.User;
import com.quanlyphongkhamvadatlich.service.doctor.IAppointmentService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/doctor/appointments")
public class AppointmentStatusAPI {
    @Autowired
    IAppointmentService appointmentService;

    @GetMapping("/{id}/status1")
    public ResponseEntity<String> updateAppointmentStatus(@PathVariable(value = "id") Long id, @RequestParam(value = "statusId") Long statusId) {
        appointmentService.updateAppointmentStatus(id, statusId);
        return ResponseEntity.ok("Appointment status updated successfully");
    }

    @GetMapping("/email/{id}")
    public ResponseEntity<Map<String, String>> getUserByAppId(@PathVariable Long id) {
        Optional<Appointment> appointment = appointmentService.findById(id);



        if (appointment.isPresent()) {

            String userEmail = appointment.get().getUser().getEmail();
            Map<String, String> response = new HashMap<>();
            response.put("email", userEmail);
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/invoice")
    public ResponseEntity<String> success(Model model, @RequestParam(name = "appointmentId") Long appointmentId) throws MessagingException {


        Appointment appointment = appointmentService.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));


        if (appointment!=null) {
            //cập nhật issendinvoice
            Appointment existingAppointment = appointment;
            // Update the isSendedInvoice field to true
            existingAppointment.setSendedInvoice(true);
            // Save the updated appointment
            appointmentService.save(existingAppointment);
            // Lấy thông tin hồ sơ bệnh nhân từ database
        }
        PatientRecord patientRecord = appointmentService.findByPatientRecordId(appointment.getPatient().getId())
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



//        MimeMessage message = javaMailSender.createMimeMessage();
//        Context context = new Context();
//        context.setVariable("notifierInvoice", dto);
        appointmentService.appointmentSendInvoice(dto);
        return ResponseEntity.ok("Send mail successfully");
    }
}
