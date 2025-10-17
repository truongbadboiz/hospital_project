package com.example.project_hospital.service.impl;

import com.example.project_hospital.dto.request.PhacDoDieuTriReq;
import com.example.project_hospital.dto.response.PhacDoDieuTriRes;
import com.example.project_hospital.entity.BenhAnNoiTru;
import com.example.project_hospital.entity.YLenhDieuTri;
import com.example.project_hospital.repository.BenhAnNoiTruRepo;
import com.example.project_hospital.repository.PhacDoDieuTriRepo;
import com.example.project_hospital.service.PhacDoDieuTriService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PhacDoDieuTriImpl implements PhacDoDieuTriService {
    private final PhacDoDieuTriRepo yLenhDieuTriRepository;
    private final BenhAnNoiTruRepo benhAnNoiTruRepository;

    // CREATE
    @Override
    public PhacDoDieuTriRes createPhacDoDieuTri(PhacDoDieuTriReq request) throws IOException {
        BenhAnNoiTru benhAnNoiTru = benhAnNoiTruRepository.findById(request.getMaBenhAn())
                .orElseThrow(() -> new RuntimeException("Bệnh án không tồn tại với mã: " + request.getMaBenhAn()));

        YLenhDieuTri yLenh = new YLenhDieuTri();
        yLenh.setBenhAnNoiTru(benhAnNoiTru);
        yLenh.setMaBacSi(request.getMaBacSi());
        yLenh.setNgayGio(request.getNgayGio());
        yLenh.setNoiDung(request.getNoiDung());
        yLenh.setTrangThai(request.getTrangThai());

        saveFileToEntity(yLenh, request.getFile());

        YLenhDieuTri saved = yLenhDieuTriRepository.save(yLenh);
        return mapToResponse(saved);
    }


    @Override public List<PhacDoDieuTriRes> getAllPhacDoDieuTri() {
        return yLenhDieuTriRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors
                        .toList()
                );
    }

    @Override public Optional<PhacDoDieuTriRes> getPhacDoById(Long id) {
        return yLenhDieuTriRepository
                .findById(id)
                .map(this::mapToResponse);
    }
    // UPDATE
    @Override
    public PhacDoDieuTriRes updatePhacDoDieuTri(Long maYLenh, PhacDoDieuTriReq request) throws IOException {
        YLenhDieuTri yLenh = yLenhDieuTriRepository.findById(maYLenh)
                .orElseThrow(() -> new RuntimeException("Y lệnh không tồn tại với mã: " + maYLenh));

        BenhAnNoiTru benhAn = benhAnNoiTruRepository.findById(request.getMaBenhAn())
                .orElseThrow(() -> new RuntimeException("Bệnh án không tồn tại với mã: " + request.getMaBenhAn()));

        yLenh.setBenhAnNoiTru(benhAn);
        yLenh.setMaBacSi(request.getMaBacSi());
        yLenh.setNgayGio(request.getNgayGio());
        yLenh.setNoiDung(request.getNoiDung());
        yLenh.setTrangThai(request.getTrangThai());

        saveFileToEntity(yLenh, request.getFile());

        YLenhDieuTri updated = yLenhDieuTriRepository.save(yLenh);
        return mapToResponse(updated);
    }

    // DELETE
    @Override
    public void deletePhacDoDieuTri(Long maYLenh) {
        YLenhDieuTri yLenh = yLenhDieuTriRepository.findById(maYLenh)
                .orElseThrow(() -> new RuntimeException("Y lệnh không tồn tại với mã: " + maYLenh));
        yLenhDieuTriRepository.delete(yLenh);
    }

    // HELPER: lưu file vào entity
    private void saveFileToEntity(YLenhDieuTri entity, MultipartFile file) throws IOException {
        if (file != null && !file.isEmpty()) {
            entity.setFilePath(file.getOriginalFilename());
            entity.setFileData(file.getBytes());
        }
    }


    private PhacDoDieuTriRes mapToResponse(YLenhDieuTri entity) {
        var res = new PhacDoDieuTriRes();
        res.setMaYLenh(entity.getMaYLenh());
        res.setMaBacSi(entity.getMaBacSi());
        res.setNoiDung(entity.getNoiDung());
        res.setTrangThai(entity.getTrangThai());

        if (entity.getBenhAnNoiTru() != null
                && entity.getBenhAnNoiTru().getNhapVien() != null
                && entity.getBenhAnNoiTru().getNhapVien().getBenhNhan() != null) {
            res.setMaBenhAn(entity.getBenhAnNoiTru().getMaBenhAn());
            res.setHoTen(entity.getBenhAnNoiTru().getNhapVien().getBenhNhan().getHoTen());
            res.setPhong(entity.getBenhAnNoiTru().getNhapVien().getPhong());
            res.setGiuong(entity.getBenhAnNoiTru().getNhapVien().getGiuong());
        }

        return res;
    }
}
