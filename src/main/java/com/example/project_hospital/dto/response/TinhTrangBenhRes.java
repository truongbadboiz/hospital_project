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

public class TinhTrangBenhRes {
    private Date ngay;
    private String tinhTrang;
    private String ghiChu;

}
