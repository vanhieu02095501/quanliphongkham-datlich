package com.quanlyphongkhamvadatlich.web.client;

import com.quanlyphongkhamvadatlich.entity.Appointment;
import com.quanlyphongkhamvadatlich.entity.Patient;
import com.quanlyphongkhamvadatlich.entity.PatientRecord;
import com.quanlyphongkhamvadatlich.repository.AppointmentRepository;
import com.quanlyphongkhamvadatlich.repository.PatientRecordRepository;
import com.quanlyphongkhamvadatlich.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/client")
public class PatientRecordController {
    @Autowired
    private PatientService patientService;
    @Autowired
    private PatientRecordRepository patientRecordRepository;
    @Autowired
    private AppointmentRepository appointmentRepository;

    @GetMapping("/record/{id}")
    public String record(Model model, @PathVariable(value = "id") Long id){
        List<Patient> patient= patientService.getPatientByUserId(id);
        model.addAttribute("patient", patient);
        return "client/pages/record";
    }

    @GetMapping("/record/PatientRecord/{id}")
    public String PatientRecord(@PathVariable(value = "id") Long id, Model model, @RequestParam(name = "dateranges", required = false) String dateRange) {
        Patient patient = patientService.getPatientById(id);
        if(patient != null){
            List<Appointment> appointments = patient.getAppointments().stream()
                    .filter(appointment -> appointment.getStatus().getId() == 1)
                    .collect(Collectors.toList());
            LocalDate startDate;
            LocalDate endDate;

            List<Appointment> listCancelAppointment = appointmentRepository.findByStatusIdAndPatientId(4L, id);

            if (dateRange == null || dateRange.isEmpty()) {
                endDate = LocalDate.now();
                startDate = LocalDate.now().minusMonths(1);
            } else {
                String decodedDateRange = null;
                try {
                    decodedDateRange = URLDecoder.decode(dateRange, "UTF-8");

                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                String[] dates = decodedDateRange.split(" - ");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                startDate = LocalDate.parse(dates[0], formatter);
                endDate = LocalDate.parse(dates[1], formatter);
            }
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String values = startDate.format(formatter) + " - " + endDate.format(formatter);
            List<PatientRecord> patientRecords = patientRecordRepository.getAllBetweenDatesAndPatientId(startDate, endDate, id);
            model.addAttribute("patientRecords", patientRecords);
            model.addAttribute("patient", patient);
            model.addAttribute("appointments", appointments);
            model.addAttribute("value", values);
            model.addAttribute("listCancelAppointment", listCancelAppointment);



            return "client/pages/PatientRecord";
        }
        else {
            model.addAttribute("errorMessage", "Patient not found");
            return "client/pages/PatientRecord";
        }

    }
}
