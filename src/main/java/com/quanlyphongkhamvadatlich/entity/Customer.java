package com.quanlyphongkhamvadatlich.entity;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "customers")
public class Customer extends BaseEntity {
    @Id
    @Column(name="customer_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "address")
    private String address;

    @Column(name= "gender")
    private Boolean gender;

    @Column(name = "phone")
    private String phone;
}
