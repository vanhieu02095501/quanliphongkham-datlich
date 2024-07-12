package com.quanlyphongkhamvadatlich.api;

import com.quanlyphongkhamvadatlich.dto.dashboard.DoctorResister;
import com.quanlyphongkhamvadatlich.service.doctor.DoctorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/doctor")
public class DoctorAPI {
    private final DoctorService doctorService;

    @PostMapping()
    public ResponseEntity<?> createDoctor(@Valid @RequestBody DoctorResister doctorResister) {
        doctorService.createDoctor(doctorResister);
        return ResponseEntity.ok().build();
    }
}
