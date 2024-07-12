package com.quanlyphongkhamvadatlich.web.client;

import java.util.Optional;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.quanlyphongkhamvadatlich.dto.client.ForgotPasswordRequest;
import com.quanlyphongkhamvadatlich.dto.client.ResetPasswordRequest;
import com.quanlyphongkhamvadatlich.entity.User;
import com.quanlyphongkhamvadatlich.service.client.impl.ForgotPasswordService;
import com.quanlyphongkhamvadatlich.service.client.impl.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/client")
@RequiredArgsConstructor
public class ForgotPasswordController {

    private final UserService userService;
    private final ForgotPasswordService forgotPasswordService;
    private final PasswordEncoder encoder;

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("/forgotpassword")
    public String forgotPassword(Model model) {
        model.addAttribute("forgotPasswordForm", new ForgotPasswordRequest());
        return "client/pages/forgotpassword";
    }

    @PostMapping("/forgotpassword")
    public String forgotPassword(@ModelAttribute("forgotPasswordForm") @Valid ForgotPasswordRequest request,
            BindingResult bindingResult,
            HttpServletRequest servletRequest) {

        if (bindingResult.hasErrors()) {
            return "client/pages/forgotpassword";
        }

        Optional<User> user = userService.findByEmail(request.getEmail());

        if (user.isEmpty()) {
            bindingResult.rejectValue("email", "error.email", "Email chưa được đăng ký trong hệ thống!");
            return "client/pages/forgotpassword";
        }

        if (!user.get().getStatus()) {
            bindingResult.rejectValue("email", "error.email", "Tài khoản chưa được kích hoạt trong hệ thống!");
            return "client/pages/forgotpassword";
        }

        forgotPasswordService.forgotPassword(user.get(), getWebHostPath(servletRequest));

        return "client/pages/request-forgot-password-success";
    }

    @GetMapping("/password/reset")
    public String resetPasswordForm(@RequestParam(name = "token") String token, Model model) {
        model.addAttribute("resetPasswordForm", new ResetPasswordRequest());
        model.addAttribute("token", token);

        Optional<User> user = userService.findByToken(token);

        if (user.isEmpty() || token == null || token.isEmpty()) {
            model.addAttribute("tokenError", "Mã xác thực không chính xác hoặc không được tìm thấy!.");
            return "client/pages/reset-password-form";
        }

        return "/client/pages/reset-password-form";
    }

    @PostMapping("password/reset")
    public String verifyTokenUpdate(@RequestParam(name = "token") String token,
            @ModelAttribute("resetPasswordForm") @Valid ResetPasswordRequest request, BindingResult bindingResult,
            Model model) {

        model.addAttribute("token", token);

        if (bindingResult.hasErrors()) {
            return "client/pages/reset-password-form";
        }

        if (!request.getPassword().equals(request.getRePassword())) {
            bindingResult.rejectValue("rePassword", "error.rePassword", "Mật khẩu không trùng khớp.");
            bindingResult.rejectValue("password", "error.password", "Mật khẩu không trùng khớp.");
            return "client/pages/reset-password-form";
        }

        Optional<User> user = userService.findByToken(token);

        if (user.isEmpty() || token == null || token.isEmpty()) {
            model.addAttribute("tokenError", "Mã xác thực không chính xác hoặc không được tìm thấy!.");
            return "client/pages/reset-password-form";
        }

        // update new password for user
        var userUpdate = user.get();
        userUpdate.setPassword(encoder.encode(request.getPassword()));
        userUpdate.setTokenExpirationTime(null);
        userUpdate.setToken(null);
        // save new information of user
        userService.saveUser(userUpdate);
        return "client/pages/update-password-success";
    }

    private String getWebHostPath(HttpServletRequest request) {
        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }
}
