package com.example.project_hospital.service.impl;

import com.example.project_hospital.dto.request.BenhAnNoiTruReq;
import com.example.project_hospital.dto.response.BenhAnNoiTruRes;
import com.example.project_hospital.dto.response.BenhAnNoiTruRes2;
import com.example.project_hospital.dto.response.PageResponse;
import com.example.project_hospital.entity.BenhAnNoiTru;
import com.example.project_hospital.entity.BenhNhan;
import com.example.project_hospital.entity.NhapVien;
import com.example.project_hospital.repository.BenhAnNoiTruRepo;
import com.example.project_hospital.repository.BenhNhanRepo;
import com.example.project_hospital.repository.NhapVienRepo;
import com.example.project_hospital.service.BenhAnNoiTruService;
import com.example.project_hospital.specification.BenhAnNoiTruSpec;
import com.example.project_hospital.utils.FileUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BenhAnNoiTruImpl implements BenhAnNoiTruService {
    private final BenhAnNoiTruRepo benhAnNoiTruRepo;
    private final BenhNhanRepo benhNhanRepository;
    private final NhapVienRepo nhapVienRepo;
    private final FileUtils fileUtils;

    @Override
    @Transactional
    public BenhAnNoiTruRes2 createBenhNhan(BenhAnNoiTruReq req) {
        // 1. Validate mã nhập viện
        if (req.getMaNhapVien() == null) {
            throw new IllegalArgumentException("Mã nhập viện không được để trống");
        }

        // 2. Lấy thông tin nhập viện
        NhapVien nhapVien = nhapVienRepo.findById(req.getMaNhapVien())
                .orElseThrow(() -> new RuntimeException(
                        "Không tìm thấy thông tin nhập viện với mã: " + req.getMaNhapVien()
                ));

        if (nhapVien.getBenhNhan() == null) {
            throw new RuntimeException("Phiếu nhập viện không có thông tin bệnh nhân");
        }

        // 3. Kiểm tra mã nhập viện đã có bệnh án chưa
        if (benhAnNoiTruRepo.existsByNhapVien_MaNhapVien(req.getMaNhapVien())) {
            throw new RuntimeException(
                    "Mã nhập viện " + req.getMaNhapVien() + " đã có bệnh án"
            );
        }

        // 4. Lưu file nếu có
        String imageUrl = null;
        if (req.getHinhAnh() != null && !req.getHinhAnh().isEmpty()) {
            imageUrl = fileUtils.saveFile(req.getHinhAnh(), "hoso");
        }

        // 5. Tạo bệnh án mới
        BenhAnNoiTru benhAn = BenhAnNoiTru.builder()
                .nhapVien(nhapVien)
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
    public BenhAnNoiTruRes2 updateBenhAn(Long maBenhAn, BenhAnNoiTruReq req) {
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
                    BenhNhan benhNhan = null;
                    String hoTen = null;
                    String maBenhNhan = null;

                    if (ba.getNhapVien() != null && ba.getNhapVien().getBenhNhan() != null) {
                        benhNhan = ba.getNhapVien().getBenhNhan();
                    }

                    if (benhNhan == null && ba.getBenhNhan() != null) {
                        benhNhan = ba.getBenhNhan();
                    }

                    if (benhNhan != null) {
                        hoTen = benhNhan.getHoTen();
                        maBenhNhan = benhNhan.getMaBenhNhan();
                    }
                    return BenhAnNoiTruRes.builder()
                            .maBenhAn(ba.getMaBenhAn())
                            .maBenhNhan(maBenhNhan)
                            .hoTen(hoTen)
                            .maNhapVien(ba.getNhapVien() != null ? ba.getNhapVien().getMaNhapVien() : null)
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

    private BenhAnNoiTruRes2 toRes(BenhAnNoiTru entity) {
        return BenhAnNoiTruRes2.builder()
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
