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

    // GET ALL
    @Override
    public List<PhacDoDieuTriRes> getAllPhacDoDieuTri() {
        return yLenhDieuTriRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
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

    // HELPER: map entity → response
    private PhacDoDieuTriRes mapToResponse(YLenhDieuTri entity) {
        return PhacDoDieuTriRes.builder()
                .maYLenh(entity.getMaYLenh())
                .maBenhAn(entity.getBenhAnNoiTru() != null ? entity.getBenhAnNoiTru().getMaBenhAn() : null)
                .noiDung(entity.getNoiDung())
                .trangThai(entity.getTrangThai())
                .fileData(entity.getFileData())
                .build();
    }
}
