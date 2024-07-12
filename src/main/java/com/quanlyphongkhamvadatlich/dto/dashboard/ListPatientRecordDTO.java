package com.quanlyphongkhamvadatlich.dto.dashboard;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ListPatientRecordDTO {
    @NotEmpty(message = "Chuẩn đoán không được để trống!")
    private String diagnosis;
    @NotEmpty(message = "Ghi chú không được để trống!")
    private String note;
    @NotEmpty(message = "Danh sách thuốc không được để trống!")
    private List<PrescriptionDetailDTO> listPrescription;
    @NotEmpty(message = "Danh sách dịch vụ không được để trống!")
    private List<MedicalServiceDTO> listService;
}
