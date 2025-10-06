package com.example.project_hospital.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="nhapvien")
public class NhapVien {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maNhapVien;
    @Column(name = "ngayNhapVien")
    private Date ngayNhapVien;
    @Column(name="khoaDieuTri")
    private String khoaDieuTri;
    @Column(name="phong")
    private String phong;
    @Column(name = "giuong")
    private String giuong;
    @Column(name = "ChanDoan")
    private String chanDoan;
    @Column(name = "maBacSi")
    private String maBacSi;
    @Column(name = "maDieuDuong")
    private String maDieuDuong;
    // Nhiều lần nhập viện thuộc về 1 bệnh nhân
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "maBenhNhan", nullable = false)
    private BenhNhan benhNhan;
}
