package com.quanlyphongkhamvadatlich.service.client;

import com.quanlyphongkhamvadatlich.entity.User;

public interface IForgotPasswordService {
    void forgotPassword(User user, String urlVerificationToken);
}
