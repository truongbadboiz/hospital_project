package com.example.project_hospital.service.impl;

import com.example.project_hospital.dto.request.XuatVienReq;
import com.example.project_hospital.dto.response.XuatVienRes;
import com.example.project_hospital.entity.*;
import com.example.project_hospital.repository.*;
import com.example.project_hospital.service.XuatVienService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class XuatVienImpl implements XuatVienService {

    private final XuatVienRepo xuatVienRepo;
    private final BenhAnNoiTruRepo benhAnNoiTruRepo;

    @Override
    public List<XuatVienRes> getAllXuatVien() {
        return xuatVienRepo.findAll()
                .stream()
                .map(this::toRes)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<XuatVienRes> getXuatVienById(Long maXuatVien) {
        return xuatVienRepo.findById(maXuatVien).map(this::toRes);
    }

    @Transactional
    @Override
    public XuatVienRes createXuatVien(XuatVienReq req) {
        BenhAnNoiTru benhAn = benhAnNoiTruRepo.findById(req.getMaBenhAn())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy bệnh án với mã: " + req.getMaBenhAn()));

        XuatVien xuatVien = XuatVien.builder()
                .benhAnNoiTru(benhAn)
                .ngayRaVien(req.getNgayRaVien())
                .trangThai(req.getTrangThai() != null ? req.getTrangThai() : "Đã ra viện")
                .ghiChu(req.getGhiChu())
                .build();

        return toRes(xuatVienRepo.save(xuatVien));
    }

    private XuatVienRes toRes(XuatVien entity) {
        BenhAnNoiTru benhAn = entity.getBenhAnNoiTru();
        BenhNhan benhNhan = benhAn.getNhapVien().getBenhNhan();

        return XuatVienRes.builder()
                .maXuatVien(entity.getMaXuatVien())
                .maBenhAn(benhAn.getMaBenhAn())
                .maBenhNhan(benhNhan.getMaBenhNhan())
                .hoTen(benhNhan.getHoTen())
                .ngaySinh(benhNhan.getNgaySinh())
                .gioiTinh(benhNhan.getGioiTinh())
                .soCMND(benhNhan.getSoCCCD())
                .tinhTrang(benhAn.getTinhTrangBenh() != null ? benhAn.getTinhTrangBenh().getTinhTrang() : "Đã xuất viện")
                .ghiChu(entity.getGhiChu())
                .trangThai(entity.getTrangThai())
                .ngayRaVien(entity.getNgayRaVien())
                .build();
    }
}
