package com.quanlyphongkhamvadatlich.dto.dashboard;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Setter
@Getter
public class MedicalServiceResult {
    private Long id;
    private String serviceName;
    private BigDecimal price;
    private String description;
}
