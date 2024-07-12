package com.quanlyphongkhamvadatlich.service;
import com.quanlyphongkhamvadatlich.entity.PrescriptionDetail;
import com.quanlyphongkhamvadatlich.repository.PrescriptionDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrescriptionDetailService {
    @Autowired
    private PrescriptionDetailRepository prescriptionDetailRepository;
    public PrescriptionDetail save(PrescriptionDetail prescriptionDetail){
        return prescriptionDetailRepository.save(prescriptionDetail);
    };
}
