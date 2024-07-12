package com.quanlyphongkhamvadatlich.dto.client;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ResetPasswordRequest {
    @NotEmpty(message = "Mật khẩu không được để trống")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*\\W)(?!.* ).{6,20}$", message = "Mật khẩu phải có độ dài từ 6-20 ký tự (bao gồm ít nhất 1 ký tự in hoa và 1 ký tự đặc biệt.)")
    private String password;

    @NotEmpty(message = "Mật khẩu không được để trống")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*\\W)(?!.* ).{6,20}$", message = "Mật khẩu phải có độ dài từ 6-20 ký tự (bao gồm ít nhất 1 ký tự in hoa và 1 ký tự đặc biệt.)")
    private String rePassword;
}
