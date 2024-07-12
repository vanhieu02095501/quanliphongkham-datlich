package com.quanlyphongkhamvadatlich.service;

import com.quanlyphongkhamvadatlich.entity.PatientRecord;
import com.quanlyphongkhamvadatlich.repository.PatientRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientRecordService {
    @Autowired
    private PatientRecordRepository patientRecordRepository;
    public PatientRecord save(PatientRecord patientRecord){
        return patientRecordRepository.save(patientRecord);
    };
}
