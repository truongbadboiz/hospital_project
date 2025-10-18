package com.example.project_hospital.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class PhacDoDieuTriReq {
    private Long maBenhAn;
    private String maBacSi;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime ngayGio;
    private String noiDung;
    private String trangThai;
    private MultipartFile file;
}
