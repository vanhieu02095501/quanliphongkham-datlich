package com.quanlyphongkhamvadatlich.service.admin.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.quanlyphongkhamvadatlich.dto.Statistical;
import com.quanlyphongkhamvadatlich.repository.PatientRecordRepository;
import com.quanlyphongkhamvadatlich.service.admin.IStatisticalService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StatisticalService implements IStatisticalService{

    private final PatientRecordRepository patientRecordRepository;

    @Override
    public List<Statistical> getMonthlyRevenueByYear(int year) {
        return patientRecordRepository.getMonthlyRevenueByYear(year);
    }
}
