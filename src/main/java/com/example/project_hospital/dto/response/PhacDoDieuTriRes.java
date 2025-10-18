package com.example.project_hospital.dto.response;


import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PhacDoDieuTriRes {
    private Long maYLenh;
    private Long maBenhAn;
    private String maBacSi;
    private String hoTen;
    private String phong;
    private String giuong;
    private String noiDung;
    private String trangThai;
    @Lob
    private byte[] fileData;
}
