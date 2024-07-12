package com.quanlyphongkhamvadatlich.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quanlyphongkhamvadatlich.dto.client.ApiResponse;
import com.quanlyphongkhamvadatlich.entity.Appointment;
import com.quanlyphongkhamvadatlich.security.UserPrincipal;
import com.quanlyphongkhamvadatlich.service.client.impl.AppointmentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/client/appointments")
@RequiredArgsConstructor
public class CancelAppointmentAPI {
    private final AppointmentService appointmentService;

    @PostMapping("/cancel/{appointmentId}")
    public ResponseEntity<ApiResponse<Appointment>> cancelBooking(
            @AuthenticationPrincipal UserPrincipal principal,
            @PathVariable(value = "appointmentId") Long appointmentId) {
        ApiResponse<Appointment> response = appointmentService.cancelAppointment(principal,appointmentId);

        return new ResponseEntity<ApiResponse<Appointment>>(response, HttpStatus.OK);
    }
}
