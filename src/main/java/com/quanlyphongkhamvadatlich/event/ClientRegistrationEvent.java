package com.quanlyphongkhamvadatlich.event;

import org.springframework.context.ApplicationEvent;

import com.quanlyphongkhamvadatlich.entity.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientRegistrationEvent extends ApplicationEvent{
    private User user;
    private String webHostPath;

    public ClientRegistrationEvent(User user, String webHostPath) {
        super(user);
        this.user = user;
        this.webHostPath = webHostPath;
    }
}
