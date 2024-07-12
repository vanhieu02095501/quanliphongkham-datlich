package com.quanlyphongkhamvadatlich.web.client;

import java.util.Optional;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.quanlyphongkhamvadatlich.entity.User;
import com.quanlyphongkhamvadatlich.enums.TokenValidationResult;
import com.quanlyphongkhamvadatlich.event.ClientRegistrationEvent;
import com.quanlyphongkhamvadatlich.service.client.impl.UserService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/client")
@RequiredArgsConstructor
public class EmailVerificationController {

    private final UserService userService;
    private final ApplicationEventPublisher eventPublisher;

    @GetMapping("/verifyEmail")
    public String verifyEmail(@RequestParam(name = "token") String token, Model model) {
        TokenValidationResult result = userService.validateToken(token);

        switch (result) {
            case TOKEN_EXPIRED:
                model.addAttribute("result", "TOKEN_EXPIRED");
                model.addAttribute("token", token);
                return "client/pages/token-validation";
            case TOKEN_NOT_FOUND:
                model.addAttribute("result", "TOKEN_NOT_FOUND");
                return "client/pages/token-validation";
            case USER_ALREADY_ACTIVATED:
                model.addAttribute("result", "USER_ALREADY_ACTIVATED");
                return "client/pages/token-validation";
            case USER_ACTIVATED_SUCCESSFULLY:
                model.addAttribute("result", "SUCCESS");
                return "client/pages/token-validation";
            default:
                model.addAttribute("result", "TOKEN_NOT_FOUND");
                return "client/pages/token-validation";
        }
    }

    @GetMapping("/resend-verification-token")
    public String resendToken(@RequestParam("token") String oldToken,
            Model model,
            HttpServletRequest request) {
        Optional<User> userUpdate = userService.updateToken(oldToken);

        if (userUpdate.isEmpty()) {
            model.addAttribute("result", "TOKEN_NOT_FOUND");
            return "client/pages/token-validation";
        }

        eventPublisher.publishEvent(new ClientRegistrationEvent(userUpdate.get(), this.getWebHostPath(request)));
        return "client/pages/resend-token";
    }

    private String getWebHostPath(HttpServletRequest request) {
        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }
}
