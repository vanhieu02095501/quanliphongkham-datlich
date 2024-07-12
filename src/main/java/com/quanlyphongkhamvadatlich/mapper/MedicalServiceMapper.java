package com.quanlyphongkhamvadatlich.mapper;

import com.quanlyphongkhamvadatlich.dto.dashboard.MedicalServiceParam;
import com.quanlyphongkhamvadatlich.dto.dashboard.MedicalServiceResult;
import com.quanlyphongkhamvadatlich.entity.MedicalService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component

public class MedicalServiceMapper {
    private ModelMapper modelMapper;

    public MedicalServiceMapper() {
        this.modelMapper = new ModelMapper();
    }

    public MedicalService toEntity(MedicalServiceParam serviceParam) {
        return this.modelMapper.map(serviceParam, MedicalService.class);
    }
    public MedicalServiceResult toDto(MedicalService service) {
        return this.modelMapper.map(service, MedicalServiceResult.class);
    }
}
