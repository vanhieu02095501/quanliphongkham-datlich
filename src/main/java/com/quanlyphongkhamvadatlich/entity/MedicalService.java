package com.quanlyphongkhamvadatlich.entity;

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
@Table(name="medical_services")
public class MedicalService extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="medical_service_id")
    private Long id;
    @Column(name = "service_name")
    private String serviceName;

    @Column(name= "description", columnDefinition = "TEXT" )
    private String description;

    @Column(name = "price")
    private BigDecimal price;

    @JsonManagedReference
    @OneToMany(mappedBy = "medicalService", fetch = FetchType.EAGER)
    private List<ServiceDetail> serviceDetails;
}
//    INSERT INTO medicines (medicine_name, description) VALUES
//('Paracetamol', 'Thuốc giảm đau và hạ sốt'),
//        ('Ibuprofen', 'Thuốc giảm đau, kháng viêm'),
//        ('Amoxicillin', 'Thuốc kháng sinh phổ rộng'),
//        ('Metronidazole', 'Thuốc kháng khuẩn, chống viêm'),
//        ('Chlorhexidine', 'Thuốc sát khuẩn, dùng để súc miệng'),
//        ('Clindamycin', 'Thuốc kháng sinh sử dụng trong điều trị nhiễm khuẩn'),
//        ('Aspirin', 'Thuốc giảm đau, chống viêm, hạ sốt'),
//        ('Lidocaine', 'Thuốc tê tại chỗ'),
//        ('Dexamethasone', 'Thuốc chống viêm và ức chế miễn dịch'),
//        ('Azithromycin', 'Thuốc kháng sinh điều trị nhiễm khuẩn');
//
//    INSERT INTO medical_services (service_name, description, price) VALUES
//('Khám tổng quát', 'Khám tổng quát răng miệng và tư vấn điều trị', 200000.00),
//        ('Cạo vôi răng', 'Làm sạch vôi răng và mảng bám', 300000.00),
//        ('Tẩy trắng răng', 'Dịch vụ tẩy trắng răng bằng công nghệ cao', 1500000.00),
//        ('Trám răng thẩm mỹ', 'Trám răng bằng vật liệu thẩm mỹ', 500000.00),
//        ('Nhổ răng khôn', 'Nhổ răng khôn không đau', 1000000.00),
//        ('Chỉnh nha', 'Chỉnh nha, niềng răng thẩm mỹ', 20000000.00),
//        ('Răng sứ thẩm mỹ', 'Làm răng sứ thẩm mỹ', 5000000.00),
//        ('Cấy ghép Implant', 'Cấy ghép răng Implant', 20000000.00),
//        ('Điều trị tủy', 'Điều trị tủy răng', 800000.00),
//        ('Khám và tư vấn miễn phí', 'Dịch vụ khám và tư vấn miễn phí', 0.00);