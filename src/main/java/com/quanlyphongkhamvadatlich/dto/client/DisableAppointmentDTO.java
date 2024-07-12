package com.quanlyphongkhamvadatlich.dto.client;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
public class DisableAppointmentDTO {
    private Date appointmentDate;


    public DisableAppointmentDTO(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }
}
