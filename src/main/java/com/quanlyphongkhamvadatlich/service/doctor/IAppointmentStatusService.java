package com.quanlyphongkhamvadatlich.service.doctor;

import com.quanlyphongkhamvadatlich.entity.Status;

import java.util.List;

public interface IAppointmentStatusService {
    List<Status> findAll();
}
