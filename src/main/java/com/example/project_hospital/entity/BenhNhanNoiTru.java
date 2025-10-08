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
@Table(name="benhannoitru")
public class BenhNhanNoiTru {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maBenhAn;
    @ManyToOne
    @JoinColumn(name = "maNhapVien")
    private NhapVien nhapVien;
    @Column(name="ngayLap")
    private Date ngayLap;
    @Column(name="tomTatBenhAn")
    private String tomTatBenhAn;
    @Column(name="tienSuBenh")
    private String tienSuBenh;
    @Column(name="ketQuaDieuTri")
    private String ketQuaDieuTri;
    @Column(name="trangThai")
    private String trangThai;
}
