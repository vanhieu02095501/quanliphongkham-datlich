package com.quanlyphongkhamvadatlich.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "prescriptions")
public class Prescription {
    @Id
    @Column(name = "prescription_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @OneToOne
    @JoinColumn(name = "patient_record_id")
    private PatientRecord patientRecord;


    @JsonManagedReference
    @OneToMany(mappedBy = "prescription", fetch = FetchType.EAGER)
    private List<PrescriptionDetail> prescriptionDetails;
}
