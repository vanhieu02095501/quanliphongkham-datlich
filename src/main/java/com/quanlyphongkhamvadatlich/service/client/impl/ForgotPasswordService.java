package com.quanlyphongkhamvadatlich.service.client.impl;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import com.quanlyphongkhamvadatlich.entity.User;
import com.quanlyphongkhamvadatlich.repository.UserRepository;
import com.quanlyphongkhamvadatlich.service.client.IForgotPasswordService;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ForgotPasswordService implements IForgotPasswordService {

    private final UserRepository userRepository;
    private final JavaMailSender javaMailSender;
    private final SpringTemplateEngine templateEngine;

    @Override
    public void forgotPassword(User user, String webHostPath) {
        try {
            // update token for user
            String token = getToken();
            user.setToken(token);
            userRepository.save(user);
            // send an email to user
            sendVerificationEmail(webHostPath + "/client/password/reset?token=" + token, user.getEmail());
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private String getToken() {
        return UUID.randomUUID().toString();
    }

    private void sendVerificationEmail(String urlVerification, String receiver) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        Context context = new Context();
        context.setVariable("resetPasswordLink", urlVerification);
        String process = templateEngine.process("template-email/forgot-password", context);
        MimeMessageHelper helper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());

        helper.setTo(receiver);
        helper.setFrom(new InternetAddress("nhatminhle1402@gmail.com"));
        helper.setSubject("ĐỔI MẬT KHẨU HỆ THỐNG");
        helper.setText(process, true);

        javaMailSender.send(message);
    }
}
