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

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "maBenhNhan", nullable = false)
    private BenhNhan benhNhan;

    // 1 lần nhập viện chỉ có 1 bệnh án
    @OneToOne(mappedBy = "nhapVien", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private BenhAnNoiTru benhAnNoiTru;
}
