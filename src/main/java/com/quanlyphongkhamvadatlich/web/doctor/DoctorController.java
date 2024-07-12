package com.quanlyphongkhamvadatlich.web.doctor;

import com.quanlyphongkhamvadatlich.dto.client.PatientDTO;
import com.quanlyphongkhamvadatlich.dto.dashboard.HistoryAppointmentDTO;
import com.quanlyphongkhamvadatlich.entity.Patient;
import com.quanlyphongkhamvadatlich.entity.PatientRecord;
import com.quanlyphongkhamvadatlich.repository.HistoryAppointmentRepository;
import com.quanlyphongkhamvadatlich.security.UserPrincipal;
import com.quanlyphongkhamvadatlich.service.dashboard.impl.HistoryAppointmentService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    private HistoryAppointmentService historyAppointmentService;

    @GetMapping("/login")
    public String toLoginDoctor(@AuthenticationPrincipal UserPrincipal principal) {
        if (principal != null)
            return "redirect:/doctor/appointments";

        return "dashboard/doctor/login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        // Xóa phiên đăng nhập
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        // Xóa session
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/doctor/login"; // Điều hướng đến trang đăng nhập và thông báo đăng xuất thành công
    }
    @GetMapping("/visits_statistics") //  thống kê sô lượt khám cua bác sĩ
    public ModelAndView VisitsStatistics() {
        return new ModelAndView("dashboard/doctor/visits_statistics");
    }

    @GetMapping("/history_exam")
    public String historyExam(@RequestParam(value = "dataDate", required = false) String dataDate, Model model) throws ParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate startDate;
        LocalDate endDate;

        if(dataDate == null){
            LocalDate today = LocalDate.now();
            LocalDate twoDaysLater = today.plusDays(2);

            String strToday = formatter.format(today);
            String strTwoDayLater = formatter.format(twoDaysLater);

            model.addAttribute("searchDate", strToday + " - " + strTwoDayLater);

            startDate = LocalDate.parse(strToday,formatter);
            endDate = LocalDate.parse(strTwoDayLater,formatter);
        }
        else {
            String[] dates = dataDate.split("-");
            String strToday = dates[0].trim();
            String strTwoDateLater = dates[1].trim();

            model.addAttribute("searchDate", strToday + " - " + strTwoDateLater);

            startDate = LocalDate.parse(strToday,formatter);
            endDate = LocalDate.parse(strTwoDateLater,formatter);
        }
;
        SimpleDateFormat sp = new SimpleDateFormat("yyyy-MM-dd");

        List<HistoryAppointmentDTO> listOfHistoryAppointment = historyAppointmentService.ListOfHistoryAppointmentByDates(sp.parse(startDate.toString()),sp.parse(endDate.toString()));

        model.addAttribute("history", new HistoryAppointmentDTO());
        model.addAttribute("historyAppointment", listOfHistoryAppointment);
        return "dashboard/doctor/history_exam";
    }

    @GetMapping("history_exam/patientRecord/{patientRecordId}")
    public String getHistoryExam(@PathVariable Long patientRecordId, Model model){
        PatientRecord patientRecord = historyAppointmentService.getPatientRecordById(patientRecordId);
        model.addAttribute("patientRecord", patientRecord);
        return "dashboard/doctor/history";

    }

    @GetMapping("/appointment_schedule")
    public ModelAndView appointmentSchedule() {
        return new ModelAndView("dashboard/doctor/appointment_schedule");
    }

}
