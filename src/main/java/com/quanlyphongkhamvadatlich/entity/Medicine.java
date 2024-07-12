package com.quanlyphongkhamvadatlich.entity;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "medicines")
public class Medicine extends BaseEntity{
    @Id
    @Column(name = "medicine_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "medicine_name")
    private String medicine_name;

    @Column(name = "description")
    private String description;
}
