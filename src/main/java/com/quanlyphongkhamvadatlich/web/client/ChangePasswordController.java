package com.quanlyphongkhamvadatlich.web.client;

import com.quanlyphongkhamvadatlich.dto.client.ChangePasswordRequest;
import com.quanlyphongkhamvadatlich.entity.User;
import com.quanlyphongkhamvadatlich.security.UserPrincipal;
import com.quanlyphongkhamvadatlich.service.client.impl.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/client")
public class ChangePasswordController {
    @Autowired
    private UserService userService;
    @GetMapping("/changepassword")
    public String changepassword(Model model) {
        model.addAttribute("changePasswordForm", new ChangePasswordRequest());
        return "client/pages/changepassword";
    }
    @PostMapping("/changepassword")
    public String changepassword(@ModelAttribute("changePasswordForm") @Valid ChangePasswordRequest request, BindingResult bindingResult, Model model){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userDetails = (UserPrincipal) authentication.getPrincipal();
        User user = userDetails.getUser();

        if (bindingResult.hasErrors()) {
            return "client/pages/changepassword";
        }
        if(!userService.oldPasswordIsValid(user, request.getPassword())){
            bindingResult.rejectValue("password", "error.password", "Mật khẩu cũ không trùng khớp.");
            return "client/pages/changepassword";
        }
        if(request.getNewPassword().equals(request.getPassword())){
            bindingResult.rejectValue("password", "error.password", "Mật khẩu cũ không được trùng với mật khẩu mới.");
            bindingResult.rejectValue("newPassword", "error.newPassword", "Mật khẩu cũ không được trùng với mật khẩu mới.");
            return "client/pages/changepassword";
        }
        if (!request.getNewPassword().equals(request.getRePassword())) {
            bindingResult.rejectValue("newPassword", "error.newPassword", "Mật khẩu không trùng khớp.");
            bindingResult.rejectValue("rePassword", "error.rePassword", "Mật khẩu không trùng khớp.");
            return "client/pages/changepassword";
        }
        userService.changePassword(user, request.getNewPassword());
        model.addAttribute("passwordChanged", true);
        return "client/pages/changepassword";
    }
}
