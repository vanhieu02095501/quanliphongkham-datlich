package com.quanlyphongkhamvadatlich.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "patient_records")
public class PatientRecord extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "patient_record_id")
    private Long id;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    @Column(name = "diagnosis")
    private String diagnosis;

    @JsonManagedReference
    @OneToMany(mappedBy = "patientRecord", fetch = FetchType.EAGER)
    private List<ServiceDetail> serviceDetails;

    @OneToOne(mappedBy = "patientRecord")
    private Prescription prescription;

    @OneToOne
    @JoinColumn(name = "appointment_id")
    private Appointment appointment;

    @Column(name = "total_fees")
    private BigDecimal totalFees;

    @Column(name = "note")
    private String note;
}