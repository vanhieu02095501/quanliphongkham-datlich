package com.quanlyphongkhamvadatlich.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "doctors")
public class Doctor extends BaseEntity {
    @Id
    @Column(name="doctor_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "doctor_name")
    private String username;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "specialty")
    private String specialty;

    @Column(name= "diploma")
    private String diploma;

    @Column(name = "workplace")
    private String workplace;

    @Column(name = "introduction", columnDefinition = "TEXT")
    private String introduction;
}
