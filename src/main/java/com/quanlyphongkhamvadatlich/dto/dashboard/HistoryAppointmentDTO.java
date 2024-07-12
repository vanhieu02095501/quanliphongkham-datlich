package com.quanlyphongkhamvadatlich.dto.dashboard;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class HistoryAppointmentDTO {
    private Date appointmentDate;
    private String appointmentShift;
    private Integer orderNumber;
    private String doctorName;
    private String diagnosis;
    private Integer statusId;
    private Long patientRecordId;

    public HistoryAppointmentDTO(Date appointmentDate, String appointmentShift, Integer orderNumber, String doctorName, String diagnosis, Integer statusId, Long patientRecordId) {
        this.appointmentDate = appointmentDate;
        this.appointmentShift = appointmentShift;
        this.orderNumber = orderNumber;
        this.doctorName = doctorName;
        this.diagnosis = diagnosis;
        this.statusId = statusId;
        this.patientRecordId = patientRecordId;
    }

    public HistoryAppointmentDTO() {
    }
}
