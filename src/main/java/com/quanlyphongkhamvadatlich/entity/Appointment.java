package com.quanlyphongkhamvadatlich.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "appointments")
public class Appointment extends BaseEntity {
    @Column(name = "appointment_date")
    private Date appointmentDate;

    @Id
    @Column(name = "appointment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "appointment_shift")
    private String appointmentShift;

    @Column(name = "symptom")
    private String symptom;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "status_id", nullable = false)
    private Status status;

    @Column(name = "order_number")
    private Integer orderNumber;

    @Column(name = "is_sended_invoice", nullable = false)
    @Builder.Default
    private boolean isSendedInvoice = false;

// them cot trang thai da goi hoa don hay chua, cho gia tri mac dinh trong database là false
    // kiem tra neu trang thai cuoc hen nay la complete va is_senđded_invoice là faslse thi cho goi mail - hien thi nut goi mail
    // sau khi goi xong thi cap nhat trang thai, va hien thi text/button la da goi hoa don
}
