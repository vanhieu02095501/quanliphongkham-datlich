package com.quanlyphongkhamvadatlich.schedule;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.quanlyphongkhamvadatlich.dto.client.AutoSchedulerEmailNotifierDTO;
import com.quanlyphongkhamvadatlich.service.client.impl.AppointmentService;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableScheduling
@RequiredArgsConstructor
public class AutoSchedulerEmailNotifier {

    private final AppointmentService appointmentService;

    @Scheduled(cron = "1 30 8 * * 1-5", zone = "Asia/Ho_Chi_Minh")
    public void run() throws MessagingException, ParseException {
        // find the next day
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String nextDay = LocalDate.now().plusDays(1).toString();
        Date day = dateFormat.parse(nextDay);
        // Get a list of patients with appointments scheduled for tomorrow
        List<AutoSchedulerEmailNotifierDTO> appointments = appointmentService.findByAppointmentDateAndStatus(day, 1);

        for (AutoSchedulerEmailNotifierDTO appointment : appointments) {
            appointmentService.appointmentReminder(appointment);
        }
    }
}
