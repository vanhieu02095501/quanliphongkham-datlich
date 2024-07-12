package com.quanlyphongkhamvadatlich.dto.client;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AppointmentDTO {
    private Long appointmentId;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @NotNull(message = "Vui lòng chọn ngày khám")
    private Date appointmentDate;

    @NotNull(message = "Vui lòng chọn ca khám")
    private String appointmentShift;

    @NotEmpty(message = "Vui lòng mô tả triệu chứng bạn gặp phải")
    private String symptom;

    private Long patientId;

    private Integer orderNumber;
    public AppointmentDTO(Long patientId) {
        this.patientId = patientId;
    }
    public AppointmentDTO(Long appointmentId, Long patientId){
        this.appointmentId = appointmentId;
        this.patientId = patientId;
    }
}
