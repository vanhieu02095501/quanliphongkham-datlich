package com.quanlyphongkhamvadatlich.web.client;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.quanlyphongkhamvadatlich.dto.client.RegistrationRequest;
import com.quanlyphongkhamvadatlich.entity.User;
import com.quanlyphongkhamvadatlich.event.ClientRegistrationEvent;
import com.quanlyphongkhamvadatlich.service.client.impl.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/client")
@RequiredArgsConstructor
public class AuthenticationController {
    private final UserService userService;
    private final ApplicationEventPublisher eventPublisher;

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("/login")
    public String login() {
        return "client/pages/login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new RegistrationRequest());
        return "client/pages/register";
    }

    @PostMapping("/register")
    public String save(@ModelAttribute("user") @Valid RegistrationRequest request,
            BindingResult bindingResult,
            HttpServletRequest servletRequest) {

        if (bindingResult.hasErrors()) {
            return "client/pages/register";
        }

        if (request.getEmail() != null) {
            if (userService.findByEmail(request.getEmail()).isPresent()) {
                bindingResult.rejectValue("email", "error.email", "Email đã được đăng ký trong hệ thống.");
                return "client/pages/register";
            }
        }

        if (request.getPassword() != null && request.getRePassword() != null) {
            if (!request.getPassword().equals(request.getRePassword())) {
                bindingResult.rejectValue("rePassword", "error.rePassword", "Mật khẩu không trùng khớp.");
                return "client/pages/register";
            }
        }

        User user = userService.registerUser(request);
        eventPublisher.publishEvent(new ClientRegistrationEvent(user, this.getWebHostPath(servletRequest)));

        return "client/pages/verify-sucess";
    }

    private String getWebHostPath(HttpServletRequest request) {
        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }
}
