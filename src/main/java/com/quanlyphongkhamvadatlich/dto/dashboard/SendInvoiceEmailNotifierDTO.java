package com.quanlyphongkhamvadatlich.dto.dashboard;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.quanlyphongkhamvadatlich.entity.*;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SendInvoiceEmailNotifierDTO {

    private String email;
    private Integer orderNumber;
    private Long patientId;
    private String name;
    private String phone;
    private Date appointmentDate;
    private String appointmentShift;
    private String nameDoctor;
    private String diagnosis;//chẩn đoán
    private List<ServiceDetail> serviceDetails;
    private BigDecimal totalFees;


}
