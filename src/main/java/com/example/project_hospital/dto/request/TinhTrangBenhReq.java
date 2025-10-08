package com.example.project_hospital.dto.request;

import lombok.Data;

import java.sql.Date;
@Data
public class TinhTrangBenhReq {
    private Long maBenhAn;
    private Date ngay;
    private String tinhTrang;
    private String ghiChu;

}
