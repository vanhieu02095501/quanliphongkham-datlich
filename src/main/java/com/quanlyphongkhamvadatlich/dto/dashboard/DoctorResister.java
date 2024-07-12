package com.quanlyphongkhamvadatlich.dto.dashboard;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DoctorResister {
    @NotBlank(message = "Họ và tên không được bỏ trống")
    private String username;
    @NotBlank(message = "Chuyên môn không đuộc bỏ trống")
    private String specialty;
    @NotBlank(message = "Bằng cấp không được bỏ trống")
    private String diploma;
    @NotBlank(message = "Nơi công tác không được bỏ trống")
    private String workplace;
    private String introduction;
}
