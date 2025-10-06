package com.example.project_hospital.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NhapVienRes {
    private Long maNhapVien;
    private String maBenhNhan;
    private String hoTen;
    private Date  ngayNhapVien;
    private String khoaDieuTri;
    private String phong;
    private String giuong;
    private String chuanDoan;
    private String trangThai;
}
