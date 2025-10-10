package com.example.project_hospital.service.impl;

import com.example.project_hospital.dto.request.BenhAnNoiTruReq;
import com.example.project_hospital.dto.response.BenhAnNoiTruRes;
import com.example.project_hospital.entity.BenhAnNoiTru;
import com.example.project_hospital.entity.NhapVien;
import com.example.project_hospital.repository.BenhAnNoiTruRepo;
import com.example.project_hospital.service.BenhAnNoiTruService;
import com.example.project_hospital.specification.BenhAnNoiTruSpec;
import com.example.project_hospital.utils.FileUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BenhAnNoiTruImpl implements BenhAnNoiTruService {
    private final BenhAnNoiTruRepo benhAnNoiTruRepo;
    private final FileUtils fileUtils;

    @Override
    public BenhAnNoiTruRes createBenhNhan(BenhAnNoiTruReq req) {
        String imageUrl = fileUtils.saveFile(req.getHinhAnh(), "hoso");
        BenhAnNoiTru benhAn = BenhAnNoiTru.builder()
                .nhapVien(NhapVien.builder().maNhapVien(req.getMaNhapVien()).build())
                .ngayLap(req.getNgayLap())
                .tomTatBenhAn(req.getTomTatBenhAn())
                .tienSuBenh(req.getTienSuBenh())
                .ketQuaDieuTri(req.getKetQuaDieuTri())
                .trangThai(req.getTrangThai())
                .hinhAnhUrl(imageUrl)
                .build();
        benhAnNoiTruRepo.save(benhAn);
        return toRes(benhAn);
    }
    @Override
    public BenhAnNoiTruRes updateBenhAn(Long maBenhAn, BenhAnNoiTruReq req) {
        BenhAnNoiTru benhAn = benhAnNoiTruRepo.findById(maBenhAn)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy bệnh án : " + maBenhAn));

        if (req.getHinhAnh() != null && !req.getHinhAnh().isEmpty()) {
            fileUtils.deleteFile(benhAn.getHinhAnhUrl());
            benhAn.setHinhAnhUrl(fileUtils.saveFile(req.getHinhAnh(), "hoso"));
        }

        benhAn.setNgayLap(req.getNgayLap());
        benhAn.setTomTatBenhAn(req.getTomTatBenhAn());
        benhAn.setTienSuBenh(req.getTienSuBenh());
        benhAn.setKetQuaDieuTri(req.getKetQuaDieuTri());
        benhAn.setTrangThai(req.getTrangThai());

        benhAnNoiTruRepo.save(benhAn);
        return toRes(benhAn);
    }
    @Override
    public List<BenhAnNoiTru> search(String keyword) {
        return benhAnNoiTruRepo.findAll(BenhAnNoiTruSpec.hasKeyword(keyword));
    }
    @Override

    public void delete(Long maBenhAn) {
        BenhAnNoiTru benhAn = benhAnNoiTruRepo.findById(maBenhAn)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy bệnh án: " + maBenhAn));
        try {
            if (benhAn.getHinhAnhUrl() != null) {
                fileUtils.deleteFile(benhAn.getHinhAnhUrl());
            }
        } catch (Exception ignored) {}

        benhAnNoiTruRepo.delete(benhAn);
    }



    @Override
    public List<BenhAnNoiTruRes> getBenhNhanDangDieuTri() {
        List<BenhAnNoiTru> ds = benhAnNoiTruRepo.findByTrangThai("Đang điều trị");

        return ds.stream()
                .map(ba -> BenhAnNoiTruRes.builder()
                        .maBenhAn(ba.getMaBenhAn())
                        .maNhapVien((ba.getNhapVien().getMaNhapVien()))
                        .ngayLap(ba.getNgayLap())
                        .tomTatBenhAn(ba.getTomTatBenhAn())
                        .tienSuBenh(ba.getTienSuBenh())
                        .ketQuaDieuTri(ba.getKetQuaDieuTri())
                        .trangThai(ba.getTrangThai())
                        .build()
                )
                .collect(Collectors.toList());

    }
    private BenhAnNoiTruRes toRes(BenhAnNoiTru entity) {
        return BenhAnNoiTruRes.builder()
                .maBenhAn(entity.getMaBenhAn())
                .maNhapVien(entity.getNhapVien().getMaNhapVien())
                .ngayLap(entity.getNgayLap())
                .tomTatBenhAn(entity.getTomTatBenhAn())
                .tienSuBenh(entity.getTienSuBenh())
                .ketQuaDieuTri(entity.getKetQuaDieuTri())
                .trangThai(entity.getTrangThai())
                .hinhAnhUrl(entity.getHinhAnhUrl())
                .build();
    }
}
