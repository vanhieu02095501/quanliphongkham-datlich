package com.quanlyphongkhamvadatlich.mapper;

import com.quanlyphongkhamvadatlich.dto.dashboard.DoctorServiceParam;
import com.quanlyphongkhamvadatlich.dto.dashboard.DoctorServiceResult;
import com.quanlyphongkhamvadatlich.entity.Doctor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class DoctorServiceMapper {
    private final ModelMapper modelMapper;

    public DoctorServiceMapper() {this.modelMapper = new ModelMapper();}

    public Doctor toEntity(DoctorServiceParam doctorServiceParam) {
        return this.modelMapper.map(doctorServiceParam, Doctor.class);
    }

    public DoctorServiceResult toResult(Doctor doctor) {
        return this.modelMapper.map(doctor, DoctorServiceResult.class);
    }
}
