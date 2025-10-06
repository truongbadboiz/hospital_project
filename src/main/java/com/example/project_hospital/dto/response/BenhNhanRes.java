package com.example.project_hospital.dto.response;

import lombok.Builder;
import lombok.Data;

import java.sql.Date;

@Data
@Builder
public class BenhNhanRes {
    private String maBenhNhan;
    private String hoTen;
    private Date ngaySinh;
    private String gioiTinh;
    private String soCMND;
    private String diaChi;
}
