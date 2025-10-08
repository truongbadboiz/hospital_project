package com.example.project_hospital.service.impl;

import com.example.project_hospital.dto.response.BenhNhanNoiTruRes;
import com.example.project_hospital.entity.BenhNhanNoiTru;
import com.example.project_hospital.repository.BenhNhanNoiTruRepo;
import com.example.project_hospital.service.BenhNhanNoiTruService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BenhNhanNoiTruImpl implements BenhNhanNoiTruService {
    private final BenhNhanNoiTruRepo benhNhanNoiTruRepo;


    @Override
    public List<BenhNhanNoiTruRes> getBenhNhanDangDieuTri() {
        List<BenhNhanNoiTru> ds = benhNhanNoiTruRepo.findByTrangThai("Đang điều trị");

        return ds.stream()
                .map(ba -> BenhNhanNoiTruRes.builder()
                        .maNhapVien(String.valueOf(ba.getNhapVien().getMaNhapVien()))
                        .hoTen(ba.getNhapVien().getBenhNhan().getHoTen())
                        .phong(ba.getNhapVien().getPhong())
                        .khoaDieuTri(ba.getNhapVien().getKhoaDieuTri())
                        .trangThai(ba.getTrangThai())
                        .build()
                )
                .collect(Collectors.toList());
    }

}
