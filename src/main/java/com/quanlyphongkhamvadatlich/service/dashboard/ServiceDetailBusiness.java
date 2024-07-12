package com.quanlyphongkhamvadatlich.service.dashboard;

import com.quanlyphongkhamvadatlich.entity.ServiceDetail;
import com.quanlyphongkhamvadatlich.repository.ServiceDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceDetailBusiness {
    @Autowired
    private ServiceDetailRepository serviceDetailRepository;
    public ServiceDetail save(ServiceDetail serviceDetail){
        return serviceDetailRepository.save(serviceDetail);
    };
}
