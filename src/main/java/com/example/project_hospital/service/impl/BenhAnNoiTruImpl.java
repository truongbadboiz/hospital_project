package com.example.project_hospital.service.impl;

import com.example.project_hospital.dto.request.BenhAnNoiTruReq;
import com.example.project_hospital.dto.response.BenhAnNoiTruRes;
import com.example.project_hospital.dto.response.PageResponse;
import com.example.project_hospital.entity.BenhAnNoiTru;
import com.example.project_hospital.entity.BenhNhan;
import com.example.project_hospital.entity.NhapVien;
import com.example.project_hospital.repository.BenhAnNoiTruRepo;
import com.example.project_hospital.repository.BenhNhanRepo;
import com.example.project_hospital.service.BenhAnNoiTruService;
import com.example.project_hospital.specification.BenhAnNoiTruSpec;
import com.example.project_hospital.utils.FileUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BenhAnNoiTruImpl implements BenhAnNoiTruService {
    private final BenhAnNoiTruRepo benhAnNoiTruRepo;
    private final BenhNhanRepo benhNhanRepository;
    private final FileUtils fileUtils;

    @Override
    public BenhAnNoiTruRes createBenhNhan(BenhAnNoiTruReq req) {
        String imageUrl = null;
        if (req.getHinhAnh() != null && !req.getHinhAnh().isEmpty()) {
            imageUrl = fileUtils.saveFile(req.getHinhAnh(), "hoso");
        }

        BenhNhan benhNhan = null;
        if (req.getMaBenhNhan() != null) {
            benhNhan = benhNhanRepository.findById(req.getMaBenhNhan())
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy bệnh nhân có mã: " + req.getMaBenhNhan()));
        }

        BenhAnNoiTru benhAn = BenhAnNoiTru.builder()
                .nhapVien(NhapVien.builder().maNhapVien(req.getMaNhapVien()).build())
                .benhNhan(benhNhan)
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
        BenhAnNoiTru benhAn = benhAnNoiTruRepo.findByMaBenhAn(maBenhAn)
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
    public PageResponse<Map<String, Object>> search(String keyword, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("maBenhAn").descending());
        Page<BenhAnNoiTru> ds = benhAnNoiTruRepo.findAll(BenhAnNoiTruSpec.hasKeyword(keyword), pageable);

        List<Map<String, Object>> mappedList = ds.getContent().stream()
                .map(bn -> {
                    Map<String, Object> map = new LinkedHashMap<>();
                    map.put("MaNhapVien", bn.getNhapVien().getMaNhapVien());
                    map.put("HoTen", bn.getNhapVien().getBenhNhan().getHoTen());
                    map.put("Phong", bn.getNhapVien().getPhong());
                    map.put("KhoaDieuTri", bn.getNhapVien().getKhoaDieuTri());
                    map.put("TrangThai", bn.getTrangThai());
                    return map;
                })
                .collect(Collectors.toList());
        return new PageResponse<>(
                mappedList,
                ds.getNumber(),
                ds.getTotalPages(),
                ds.getTotalElements()
        );
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
                .map(ba -> {
                    String hoTen = null;

                    if (ba.getNhapVien() != null
                            && ba.getNhapVien().getBenhNhan() != null
                            && ba.getNhapVien().getBenhNhan().getHoTen() != null) {
                        hoTen = ba.getNhapVien().getBenhNhan().getHoTen();
                    }
                    else if (ba.getBenhNhan() != null && ba.getBenhNhan().getHoTen() != null) {
                        hoTen = ba.getBenhNhan().getHoTen();
                    }

                    return BenhAnNoiTruRes.builder()
                            .maBenhAn(ba.getMaBenhAn())
                            .maNhapVien(ba.getNhapVien() != null ? ba.getNhapVien().getMaNhapVien() : null)
                            .hoTen(hoTen)
                            .ngayLap(ba.getNgayLap())
                            .tomTatBenhAn(ba.getTomTatBenhAn())
                            .tienSuBenh(ba.getTienSuBenh())
                            .ketQuaDieuTri(ba.getKetQuaDieuTri())
                            .trangThai(ba.getTrangThai())
                            .hinhAnhUrl(ba.getHinhAnhUrl())
                            .build();
                })
                .collect(Collectors.toList());
    }


    private BenhAnNoiTruRes toRes(BenhAnNoiTru entity) {
        return BenhAnNoiTruRes.builder()
                .maBenhAn(entity.getMaBenhAn())
                .maBenhNhan(entity.getBenhNhan().getMaBenhNhan())
                .hoTen(entity.getBenhNhan().getHoTen())
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
