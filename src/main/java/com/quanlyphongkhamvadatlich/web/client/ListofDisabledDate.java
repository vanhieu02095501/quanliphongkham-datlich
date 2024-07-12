package com.quanlyphongkhamvadatlich.web.client;

import com.quanlyphongkhamvadatlich.dto.client.DisableAppointmentDTO;
import com.quanlyphongkhamvadatlich.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ListofDisabledDate {
    @Autowired
    private AppointmentRepository appointmentRepository;
    @GetMapping("/getListOfDisabledDate")
    public ResponseEntity<List<DisableAppointmentDTO>> test() {
        return new ResponseEntity<List<DisableAppointmentDTO>>(appointmentRepository.getAllDisableAppointment(), HttpStatus.OK);
    }
}
