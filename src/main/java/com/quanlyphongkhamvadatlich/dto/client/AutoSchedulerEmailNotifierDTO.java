package com.quanlyphongkhamvadatlich.dto.client;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AutoSchedulerEmailNotifierDTO {
    private String email;
    private Integer orderNumber;
    private Long patientId;
    private String name;
    private String phone;
    private Date appointmentDate;
    private String appointmentShift;

    public AutoSchedulerEmailNotifierDTO(String email, Integer orderNumber, Long patientId, String name, String phone,
            Date appointmentDate, String appointmentShift) {
        this.email = email;
        this.orderNumber = orderNumber;
        this.patientId = patientId;
        this.name = name;
        this.phone = phone;
        this.appointmentDate = appointmentDate;
        this.appointmentShift = appointmentShift;
    }
}
