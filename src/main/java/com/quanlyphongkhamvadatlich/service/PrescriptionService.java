package com.quanlyphongkhamvadatlich.service;

import com.quanlyphongkhamvadatlich.entity.Prescription;
import com.quanlyphongkhamvadatlich.repository.PrescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrescriptionService {
    @Autowired
    private PrescriptionRepository prescriptionRepository;
    public Prescription save(Prescription prescription){
        return prescriptionRepository.save(prescription);
    };
}
