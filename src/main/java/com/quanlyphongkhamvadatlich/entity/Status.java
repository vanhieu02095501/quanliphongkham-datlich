package com.quanlyphongkhamvadatlich.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Status {
    @Id
    @Column(name = "status_id")
    private int id;

    @Column(name = "name")
    private String name;

}
