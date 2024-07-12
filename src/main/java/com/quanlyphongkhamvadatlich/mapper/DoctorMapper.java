package com.quanlyphongkhamvadatlich.mapper;
import com.quanlyphongkhamvadatlich.dto.dashboard.DoctorResister;
import com.quanlyphongkhamvadatlich.entity.Doctor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class DoctorMapper {
    private ModelMapper modelMapper;

    public DoctorMapper() {
        this.modelMapper = new ModelMapper();
    }

    public Doctor toEntity(DoctorResister doctorResister) {
        return this.modelMapper.map(doctorResister, Doctor.class);
    }
}
