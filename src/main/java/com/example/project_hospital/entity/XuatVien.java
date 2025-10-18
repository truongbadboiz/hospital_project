package com.example.project_hospital.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "xuatvien")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class XuatVien {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maXuatVien;

    @ManyToOne
    @JoinColumn(name = "maBenhAn", referencedColumnName = "maBenhAn")
    private BenhAnNoiTru benhAnNoiTru;

    @Column(name = "ngayRaVien")
    private LocalDate ngayRaVien;

    @Column(name = "trangThai", columnDefinition = "ENUM('Đã xuất viện', 'Tái khám')")
    private String trangThai;

    @Column(name = "ghiChu", columnDefinition = "TEXT")
    private String ghiChu;

}
