package com.example.project_hospital.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class XuatVienReq {

    private Long maBenhAn;
    private LocalDate ngayRaVien;
    private String trangThai;
    private String ghiChu;
}
