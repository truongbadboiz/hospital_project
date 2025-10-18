package com.example.project_hospital.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class XuatVienRes {

    private Long maXuatVien;
    private Long maBenhAn;
    private String maBenhNhan;
    private String hoTen;
    private Date ngaySinh;
    private String gioiTinh;
    private String soCMND;
    private String tinhTrang;
    private String ghiChu;
    private String trangThai;
    private LocalDate ngayRaVien;
}
