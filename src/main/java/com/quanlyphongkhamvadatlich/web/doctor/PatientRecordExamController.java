package com.quanlyphongkhamvadatlich.web.doctor;

import com.quanlyphongkhamvadatlich.entity.*;
import com.quanlyphongkhamvadatlich.service.*;
import com.quanlyphongkhamvadatlich.service.client.impl.AppointmentService;
import com.quanlyphongkhamvadatlich.service.dashboard.MedicalServiceBusiness;
import com.quanlyphongkhamvadatlich.service.doctor.IAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/doctor")
public class PatientRecordExamController {
    @Autowired
    private AppointmentService appointmentService;
    @Autowired
    private IAppointmentService iAppointmentService;
    @Autowired
    private MedicalServiceBusiness medicalServiceBusiness;

    @Autowired
    private MedicineService medicineService;

    @GetMapping("/physical_exam/{id}")
    public String createPhysicalExam(Model model,  @PathVariable(value = "id") Long id){
        Appointment appointment = appointmentService.getAppointmentById(id);
        iAppointmentService.updateAppointmentStatus(id, Long.parseLong("2"));
        model.addAttribute("appointment", appointment);
        List<MedicalService> medical_services = medicalServiceBusiness.getMedicalService();
        model.addAttribute("medical_services", medical_services);
        List<Medicine> medicines = medicineService.getMedicine();
        model.addAttribute("medicines", medicines);
        return "dashboard/doctor/physical_exam";
    }
}
