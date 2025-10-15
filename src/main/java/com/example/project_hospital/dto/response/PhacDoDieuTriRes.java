package com.example.project_hospital.dto.response;


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
    private String noiDung;
    private String trangThai;
    private byte[] fileData;
}
