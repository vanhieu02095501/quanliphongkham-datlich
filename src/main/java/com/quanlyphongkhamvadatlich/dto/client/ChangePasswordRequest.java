package com.quanlyphongkhamvadatlich.dto.client;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ChangePasswordRequest {
    @NotEmpty(message = "Mật khẩu không được để trống")
    @Size(min = 6, max = 20, message = "Mật khẩu phải có độ dài từ 6 đến 20 ký tự")
    private String password;

    @NotEmpty(message = "Mật khẩu không được để trống")
    @Size(min = 6, max = 20, message = "Mật khẩu phải có độ dài từ 6 đến 20 ký tự")
    private String newPassword;

    @NotEmpty(message = "Mật khẩu không được để trống")
    @Size(min = 6, max = 20, message = "Mật khẩu phải có độ dài từ 6 đến 20 ký tự")
    private String rePassword;

}
