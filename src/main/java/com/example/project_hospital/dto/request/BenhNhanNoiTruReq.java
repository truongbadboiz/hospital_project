package com.example.project_hospital.dto.request;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BenhNhanNoiTruReq {
    private Long maNhapVien;
    private Date ngayLap;
    private String tomTatBenhAn;
    private String tienSuBenh;
    private String ketQuaDieuTri;
    private String trangThai;
    private MultipartFile hinhAnh;
}
