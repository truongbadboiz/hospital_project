package com.example.project_hospital.service.impl;

import com.example.project_hospital.dto.request.NhapVienReq;
import com.example.project_hospital.dto.response.BenhNhanRes;
import com.example.project_hospital.dto.response.NhapVienRes;
import com.example.project_hospital.entity.BenhNhan;
import com.example.project_hospital.entity.NhapVien;
import com.example.project_hospital.repository.BenhNhanRepo;
import com.example.project_hospital.repository.NhapVienRepo;
import com.example.project_hospital.service.NhapVienService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;


@Service
@RequiredArgsConstructor
public class NhapVienImpl implements NhapVienService {
    private final NhapVienRepo nhapVienRepo;
    private final BenhNhanRepo benhNhanRepo;
    @Override
    public NhapVienRes createNhapVienn(NhapVienReq request)
    {
        BenhNhan bn = benhNhanRepo.findById(request.getMaBenhNhan())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy bệnh nhân"));


        NhapVien nv = NhapVien.builder()
                .benhNhan(BenhNhan.builder().maBenhNhan(request.getMaBenhNhan()).build())
                .ngayNhapVien(request.getNgayNhapVien())
                .khoaDieuTri(request.getKhoaDieuTri())
                .giuong(request.getGiuong())
                .chanDoan(request.getChuanDoan())
                .maBacSi(request.getMaBacSi())
                .build();

        NhapVien saved = nhapVienRepo.save(nv);
        return new NhapVienRes(
                saved.getMaNhapVien(),
                saved.getBenhNhan().getMaBenhNhan(),
                saved.getBenhNhan().getHoTen(),
                saved.getNgayNhapVien(),
                saved.getKhoaDieuTri(),
                saved.getPhong(),
                saved.getGiuong(),
                saved.getChanDoan(),
                saved.getBenhNhan().getTrangThai() == null
                        ? "Đang điều trị"
                        : saved.getBenhNhan().getTrangThai().toString()

        );

    }
    public List<BenhNhanRes> getAllBenhNhanRes() {
        return benhNhanRepo.findAll().stream()
                .map(bn -> BenhNhanRes.builder()
                        .maBenhNhan(bn.getMaBenhNhan())
                        .hoTen(bn.getHoTen())
                        .ngaySinh(bn.getNgaySinh())
                        .gioiTinh(bn.getGioiTinh())
                        .soCMND(bn.getSoCCCD())
                        .diaChi(bn.getDiaChi())
                        .build())
                .toList();
    }
}
