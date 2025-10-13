package com.example.project_hospital.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PhacDoDieuTriReq {
    private Long maBenhAn;
    private String maBacSi;
    private Date ngayGio;
    private String noiDung;
    private String trangThai;
    private String fileBase64;
}
