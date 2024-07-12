package com.quanlyphongkhamvadatlich.dto.dashboard;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MedicalServiceDTO {
    private Long serviceId;
    private String serviceName;
    private BigDecimal servicePrice;
}
