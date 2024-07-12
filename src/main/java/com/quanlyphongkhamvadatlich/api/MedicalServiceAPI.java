package com.quanlyphongkhamvadatlich.api;

import com.quanlyphongkhamvadatlich.dto.dashboard.MedicalServiceParam;
import com.quanlyphongkhamvadatlich.service.dashboard.MedicalServiceBusiness;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/medical-service")
@RequiredArgsConstructor
public class MedicalServiceAPI {
    /* Khi nãy em thiếu final cho medicalServiceBusiness nên khi khởi tạo controller medicalServiceBusiness không nhận được giá trị */
    /* Do mình đang sử dụng @RequiredArgsConstructor nó khác với sử dụng @Autowire */
    private final MedicalServiceBusiness medicalServiceBusiness;

    @PostMapping
    public ResponseEntity<?> createService(@Valid @RequestBody MedicalServiceParam serviceParam) {
        medicalServiceBusiness.createMedicalService(serviceParam);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<?> getAllServices() {
        try {
            return ResponseEntity.ok(medicalServiceBusiness.getAll());
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateService(@PathVariable Long id, @Valid @RequestBody MedicalServiceParam serviceParam) {
        medicalServiceBusiness.updateMedicalService(id, serviceParam);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteService(@PathVariable Long id) {
        medicalServiceBusiness.deleteMedicalService(id);
        return ResponseEntity.ok().build();
    }
}
