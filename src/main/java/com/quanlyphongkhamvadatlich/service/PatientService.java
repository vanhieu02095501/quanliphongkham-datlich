package com.quanlyphongkhamvadatlich.service;

import com.quanlyphongkhamvadatlich.dto.client.PatientDTO;
import com.quanlyphongkhamvadatlich.entity.Patient;
import com.quanlyphongkhamvadatlich.entity.User;
import com.quanlyphongkhamvadatlich.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    public Optional<Patient> findByIdAndUser(Long id, User user){return patientRepository.findByIdAndUser(id, user);}
    public Optional<Patient> findById(Long id) {
        return patientRepository.findById(id);
    }
    public Patient addNewPatient(PatientDTO model, User user) {
        Patient newPatient = Patient
                .builder()
                .name(model.getName())
                .phone(model.getPhone())
                .birthday(model.getBirthday())
                .gender(model.getGender())
                .address(model.getAddress())
                .citizenNumber(model.getCitizen_number())
                .career(model.getCareer())
                .insurance_number(model.getInsurance_number())
                .user(user)
                .build();

        return patientRepository.save(newPatient);

    }
    public Patient getPatientById(Long idPatient) {
        Patient patient = patientRepository.getPatientById(idPatient);
        return patient;
    }
    public List<Patient> getPatientByUserId(Long id){
        return patientRepository.getPatientByUserId(id);
    }

}
