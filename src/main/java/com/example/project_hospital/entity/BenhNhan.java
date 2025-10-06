package com.example.project_hospital.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "benhnhan")
public class BenhNhan {
    @Id
    @Column(name = "maBenhNhan")
    private String maBenhNhan;

    @Column(name = "hoTen")
    private String hoTen;

    @Column(name = "ngaySinh")
    private Date ngaySinh;

    @Column(name = "gioiTinh")
    private String gioiTinh;

    @Column(name = "soCCCD")
    private String soCCCD;

    @Column(name = "soBHYT")
    private String soBHYT;

    @Column(name = "diaChi")
    private String diaChi;

    @Column(name = "soDienThoai")
    private String soDienThoai;

    @Column(name = "email")
    private String email;

    @Column(name = "ngheNghiep")
    private String ngheNghiep;

    @Column(name = "tinhTrangHonNhan")
    private String tinhTrangHonNhan;

    @Column(name = "nguoiLienHe")
    private String nguoiLienHe;

    @Column(name = "sdtLienHe")
    private String sdtLienHe;

    @Column(name = "ngayTao")
    private Date ngayTao;

    @Column(name = "nguoiTao")
    private String nguoiTao;

    @Column(name = "trangThai")
    private String trangThai;

    // Quan hệ: Một bệnh nhân có thể có nhiều lần nhập viện
    @OneToMany(mappedBy = "benhNhan", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<NhapVien> nhapViens;

}
