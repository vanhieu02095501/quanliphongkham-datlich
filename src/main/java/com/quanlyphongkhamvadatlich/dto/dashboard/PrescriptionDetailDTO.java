package com.quanlyphongkhamvadatlich.dto.dashboard;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PrescriptionDetailDTO {
    private Long medicineId;
    @NotEmpty(message = "Bạn chỉ được nhập chữ số")
    private int quantity;
    @NotEmpty(message = "Bạn cần nhập đơn vị")
    private String unit;
    @NotEmpty(message = "Bạn cần nhập liều dùng")
    private String dosage;
}
