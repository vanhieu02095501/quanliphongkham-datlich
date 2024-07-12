package com.quanlyphongkhamvadatlich.web.client;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

import com.quanlyphongkhamvadatlich.repository.UserRepository;
import com.quanlyphongkhamvadatlich.service.UserDetailService;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.quanlyphongkhamvadatlich.dto.client.UpdatePersonalInforRequest;
import com.quanlyphongkhamvadatlich.entity.Customer;
import com.quanlyphongkhamvadatlich.entity.User;
import com.quanlyphongkhamvadatlich.security.UserPrincipal;
import com.quanlyphongkhamvadatlich.service.client.impl.UploadFileService;
import com.quanlyphongkhamvadatlich.service.client.impl.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/client")
@RequiredArgsConstructor
public class PersonalInformationController {
    private final UserService userService;

    private final UploadFileService fileService;


    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("/personalinfo/{id}")
    public String personalinfo(Model model,  @PathVariable(value = "id") Long id) {
        User user = userService.getCustomerById(id);
        model.addAttribute("user", user);
        return "client/pages/personalinfo";
    }

    @GetMapping("/personalinfo/edit")
    public String editpersonalinfo(Model model) {
        UserPrincipal userDetails = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDetails.getUser();
        Optional<Customer> userDetailInfor = Optional.ofNullable(user.getCustomer());

        UpdatePersonalInforRequest.UpdatePersonalInforRequestBuilder builder = UpdatePersonalInforRequest
                .builder()
                .fullName(user.getUsername())
                .email(user.getEmail());

        if (userDetailInfor.isPresent()) {
                    builder
                    .address(userDetailInfor.get().getAddress())
                    .gender(userDetailInfor.get().getGender())
                    .phone(userDetailInfor.get().getPhone());
        }

        UpdatePersonalInforRequest userInfor = builder.build();
        model.addAttribute("user", userInfor);
        model.addAttribute("avatarSrc", userDetails.getUser().getAvatar());
        return "client/pages/editpersonalinfo";
    }

    @PostMapping("/personalinfo/edit")
    public String savepersonalinfo(@ModelAttribute("user") @Valid UpdatePersonalInforRequest request,
            BindingResult bindingResult, Model model,
            @RequestParam("avatar") MultipartFile multipartFile) {

        if (bindingResult.hasErrors()) {
            return "client/pages/editpersonalinfo";
        }

        UserPrincipal userDetails = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        User userUpdate = userDetails.getUser();

        // check if user update is exists
        Optional<User> user = userService.findByEmail(request.getEmail());

        if (user.isPresent() && !userUpdate.getEmail().equals(user.get().getEmail())) {
            bindingResult.rejectValue("email", "error.email", "Email đã tồn tại trong hệ thống!");
            return "client/pages/editpersonalinfo";
        }

        if (!multipartFile.isEmpty()) {
            try {
                // Generate unique filename
                String fileNameUpload = UUID.randomUUID().toString() + "."
                        + StringUtils.getFilenameExtension(multipartFile.getOriginalFilename());

                // Save new file
                String newFilePath = "/client/uploads/avatars/" + fileNameUpload;
                fileService.saveFile("src/main/resources/static/client/uploads/avatars", fileNameUpload, multipartFile);

                // Delete old file if exists
                String oldFilePath = userUpdate.getAvatar();
                if (oldFilePath != null) {
                    fileService.delete("src/main/resources/static" + oldFilePath);
                }

                // Update user avatar
                userUpdate.setAvatar(newFilePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // update infor
        userService.updatePersonalInfor(userUpdate, request);
        return "redirect:/client/personalinfo/" + userUpdate.getId();
    }
}
