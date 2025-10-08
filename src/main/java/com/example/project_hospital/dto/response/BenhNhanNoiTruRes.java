package com.example.project_hospital.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BenhNhanNoiTruRes {
    private String maNhapVien;
    private String hoTen;
    private String phong;
    private  String khoaDieuTri;
    private String trangThai;
}
