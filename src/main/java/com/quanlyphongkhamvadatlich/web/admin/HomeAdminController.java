package com.quanlyphongkhamvadatlich.web.admin;

import com.quanlyphongkhamvadatlich.entity.Patient;
import com.quanlyphongkhamvadatlich.repository.PatientRecordRepository;
import com.quanlyphongkhamvadatlich.repository.PatientRepository;
import com.quanlyphongkhamvadatlich.security.UserPrincipal;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class HomeAdminController {
    private final PatientRepository patientRepository;

    @GetMapping("/login")
    public String toLoginAdmin(@AuthenticationPrincipal UserPrincipal principal) {
        if (principal != null)
            return "redirect:/admin/patient";

        return "dashboard/admin/login";
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
        return "redirect:/admin/login"; // Điều hướng đến trang đăng nhập và thông báo đăng xuất thành công
    }
    @GetMapping("/doctor")
    public ModelAndView toDoctor() {
        return new ModelAndView("dashboard/admin/doctor");
    }
    @GetMapping("/patient")
    public ModelAndView toPatient(@RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "key", defaultValue = "") String key, Model model) {
        Pageable pageable = PageRequest.of(-1, 5);

        Page<Patient> listPatient = null;

        if(key == null || key.isBlank()) {
            listPatient = patientRepository.findAll(pageable);
        } else {
            listPatient = patientRepository.search(key, pageable);
        }

        model.addAttribute("listPatient", listPatient);
        model.addAttribute("totalPage", listPatient.getTotalPages());
        model.addAttribute("currentPage", page);
        model.addAttribute("key", key);

        return new ModelAndView("dashboard/admin/patient");
    }
    @GetMapping("/service")
    public ModelAndView toPatientEdit() {
        return new ModelAndView("dashboard/admin/service");
    }
    
    @GetMapping("/statistical")
    public String statistical() {
        return "dashboard/admin/revenue-statistics";
    }
}
