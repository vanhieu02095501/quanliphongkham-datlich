package com.quanlyphongkhamvadatlich.service.doctor;

import com.quanlyphongkhamvadatlich.dto.dashboard.DoctorResister;
import com.quanlyphongkhamvadatlich.entity.Doctor;
import com.quanlyphongkhamvadatlich.mapper.DoctorMapper;
import com.quanlyphongkhamvadatlich.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
@RequiredArgsConstructor
@Service
public class DoctorService {
    private final DoctorMapper doctorMapper;
    private final DoctorRepository doctorRepository;
    public void createDoctor(DoctorResister doctorResister){
        Doctor doctor = doctorMapper.toEntity(doctorResister);
        doctorRepository.save(doctor);
    }
}
