package com.quanlyphongkhamvadatlich.api;

import com.quanlyphongkhamvadatlich.dto.dashboard.DoctorServiceParam;
import com.quanlyphongkhamvadatlich.dto.dashboard.DoctorServiceResult;
import com.quanlyphongkhamvadatlich.service.dashboard.DoctorCRUDService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/doctors")
@RequiredArgsConstructor
public class DoctorCRUDAPI {
    private final DoctorCRUDService doctorCRUDService;

    // Save Operation
    @PostMapping("/create")
    public ResponseEntity<?> createDoctor(@Valid @RequestBody DoctorServiceParam doctorServiceParam){
        System.out.println("Received DoctorServiceParam: " + doctorServiceParam);
        doctorCRUDService.createDoctor(doctorServiceParam);
        return ResponseEntity.ok().build();
    }

    // Read operation
    @GetMapping("/get")
    public ResponseEntity<?> getAllDoctors(){
        try {
            return ResponseEntity.ok(doctorCRUDService.fetchDoctorList());
        } catch (Exception e) {
            System.out.println("Error fetching doctors: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while fetching doctors.");
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Optional<DoctorServiceResult>> getDoctorById(@PathVariable Long id) {
        return ResponseEntity.ok(doctorCRUDService.fetchDoctorById(id));
    }

    // Update operation
    @PutMapping("/put/{id}")
    public ResponseEntity<?> updateDoctor(@PathVariable Long id,
                                          @Valid @RequestBody DoctorServiceParam doctorServiceParam){
        try {
            System.out.println("Updating doctor with ID: " + id);
            doctorCRUDService.updateDoctor(id, doctorServiceParam);
            return ResponseEntity.ok().build();
        } catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An error occurred while updating the doctor.");
        }
    }

    // Delete operation
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteDoctor(@PathVariable Long id){
        try {
            doctorCRUDService.deleteDoctorById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            System.out.println("Error deleting doctor: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while deleting the doctor.");
        }
    }
}
