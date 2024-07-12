package com.quanlyphongkhamvadatlich.dto.dashboard;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class MedicalServiceParam {
    @NotBlank(message = "Tên dịch vụ không được trống")
    @Size(max = 255, message = "Tên dịch vụ tối đa 255 kí tự")
    private String serviceName;

    @NotNull(message = "Giá dịch vụ không được trống")
    @DecimalMin(value = "0.0", inclusive = false, message = "Giá dịch vụ không được âm")
    private BigDecimal price;

    @NotBlank(message = "Mô tả dịch vụ không được trống")
    @Size(max= 1000, message = "Mô tả dịch vụ tối đa 1000 kí tự")
    private String description;
}
