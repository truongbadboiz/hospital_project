package com.example.project_hospital.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NhapVienReq {
    private String maBenhNhan;
    private Date ngayNhapVien;
    private String khoaDieuTri;
    private String phong;
    private String giuong;
    private String chuanDoan;
    private String maBacSi;
}
