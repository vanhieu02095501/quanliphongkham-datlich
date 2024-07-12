package com.quanlyphongkhamvadatlich.service;

import com.quanlyphongkhamvadatlich.entity.Medicine;
import com.quanlyphongkhamvadatlich.repository.MedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicineService {
    @Autowired
    private MedicineRepository medicineRepository;

    public List<Medicine> getMedicine() {
        return medicineRepository.findAll();
    }
    public Medicine getMedicineById(Long idMedicine) {
        Medicine medicine = medicineRepository.getMedicineById(idMedicine);
        return medicine;
    }
}
