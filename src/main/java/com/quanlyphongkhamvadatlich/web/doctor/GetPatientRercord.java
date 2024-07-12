package com.quanlyphongkhamvadatlich.web.doctor;

import com.quanlyphongkhamvadatlich.entity.PatientRecord;
import com.quanlyphongkhamvadatlich.entity.ServiceDetail;
import com.quanlyphongkhamvadatlich.service.dashboard.impl.HistoryAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("doctor")
public class GetPatientRercord {

    @Autowired
    private HistoryAppointmentService historyAppointmentService;
    @GetMapping("/getPatientRecord")
    public ResponseEntity<PatientRecord> getPatientRecord(@RequestParam("id") Long patientRecordId) {
        PatientRecord patientRecord = historyAppointmentService.getPatientRecordById(patientRecordId);

        if (patientRecord != null) {
            return ResponseEntity.ok(patientRecord);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }


}