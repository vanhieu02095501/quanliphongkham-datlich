package com.quanlyphongkhamvadatlich.event.listener;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.UUID;

import org.springframework.context.ApplicationListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.quanlyphongkhamvadatlich.entity.User;
import com.quanlyphongkhamvadatlich.event.ClientRegistrationEvent;
import com.quanlyphongkhamvadatlich.repository.UserRepository;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

@Component
@Getter
@Setter
@RequiredArgsConstructor
public class ClientRegistrationEventListener implements ApplicationListener<ClientRegistrationEvent> {

    private final UserRepository userRepository;
    private final JavaMailSender javaMailSender;
    private final SpringTemplateEngine templateEngine;

    @Override
    @Async
    public void onApplicationEvent(ClientRegistrationEvent event) {
        // set token and set token expiration time for new client account
        User user = event.getUser();
        String token = getToken();
        Date expTokenTime = getTokenExpirationTime();
        user.setToken(token);
        user.setTokenExpirationTime(expTokenTime);
        userRepository.save(user);
        // send the verification url to the client via mail
        String urlVerification = event.getWebHostPath() + "/client/verifyEmail?token=" + token;
        try {
            sendVerificationEmail(urlVerification, user.getEmail());
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private Date getTokenExpirationTime() {
        Date currentTime = new Date();
        long timeToAdd = 15 * 60 * 1000; // milliseconds

        return new Date(currentTime.getTime() + timeToAdd);
    }

    private String getToken() {
        return UUID.randomUUID().toString();
    }

    private void sendVerificationEmail(String url, String receiver) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        Context context = new Context();
        context.setVariable("url", url);
        String process = templateEngine.process("template-email/active-account", context);
        MimeMessageHelper helper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());

        helper.setTo(receiver);
		helper.setFrom(new InternetAddress("nhatminhle1402@gmail.com"));
		helper.setSubject("XÁC MINH TÀI KHOẢN");
		helper.setText(process, true);
		
		javaMailSender.send(message);
    }
}
