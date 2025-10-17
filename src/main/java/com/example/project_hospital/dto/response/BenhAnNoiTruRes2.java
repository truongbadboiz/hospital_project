package com.example.project_hospital.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BenhAnNoiTruRes2 {
    private Long maBenhAn;
    private Long maNhapVien;
    private Date ngayLap;
    private String tomTatBenhAn;
    private String tienSuBenh;
    private String ketQuaDieuTri;
    private String trangThai;
    private String hinhAnhUrl;
}
