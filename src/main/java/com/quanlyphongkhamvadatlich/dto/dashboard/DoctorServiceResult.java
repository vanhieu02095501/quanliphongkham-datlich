package com.quanlyphongkhamvadatlich.dto.dashboard;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DoctorServiceResult {
    private Long id;
    private String username;
    private String specialty;
    private String diploma;
    private String workplace;
    private String introduction;
}
