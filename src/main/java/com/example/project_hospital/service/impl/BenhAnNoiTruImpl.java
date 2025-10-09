package com.example.project_hospital.service.impl;

import com.example.project_hospital.dto.request.BenhNhanNoiTruReq;
import com.example.project_hospital.dto.response.BenhAnNoiTruRes;
import com.example.project_hospital.entity.BenhAnNoiTru;
import com.example.project_hospital.entity.NhapVien;
import com.example.project_hospital.repository.BenhNhanNoiTruRepo;
import com.example.project_hospital.service.BenhAnNoiTruService;
import com.example.project_hospital.utils.FileUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BenhAnNoiTruImpl implements BenhAnNoiTruService {
    private final BenhNhanNoiTruRepo benhNhanNoiTruRepo;
    private final FileUtils fileUtils;

    @Override
    public BenhAnNoiTruRes createBenhNhan(BenhNhanNoiTruReq req) {
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

      BenhAnNoiTru saved = benhNhanNoiTruRepo.save(benhAn);
      return new BenhAnNoiTruRes(
              saved.getMaBenhAn(),
              saved.getNhapVien().getMaNhapVien(),
              saved.getNgayLap(),
              saved.getTomTatBenhAn(),
              saved.getTienSuBenh(),
              saved.getKetQuaDieuTri(),
              saved.getTrangThai(),
              saved.getHinhAnhUrl()
      );
    }
    @Override
    public List<BenhAnNoiTruRes> getBenhNhanDangDieuTri() {
        List<BenhAnNoiTru> ds = benhNhanNoiTruRepo.findByTrangThai("Đang điều trị");

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
}
