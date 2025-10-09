package com.example.project_hospital.service.impl;

import com.example.project_hospital.dto.request.TinhTrangBenhReq;
import com.example.project_hospital.dto.response.TinhTrangBenhRes;
import com.example.project_hospital.entity.BenhAnNoiTru;
import com.example.project_hospital.entity.TinhTrangBenh;
import com.example.project_hospital.repository.BenhNhanNoiTruRepo;
import com.example.project_hospital.repository.TinhTrangBenhRepo;
import com.example.project_hospital.service.TinhTrangBenhService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class TinhTrangBenhImpl implements TinhTrangBenhService {
    private final BenhNhanNoiTruRepo benhAnRepository;
    private final TinhTrangBenhRepo tinhTrangBenhRepo;

    @Override
    public void saveOrUpdate(TinhTrangBenhReq req) {
        BenhAnNoiTru benhAn = benhAnRepository.findById(req.getMaBenhAn())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy bệnh án"));

        TinhTrangBenh entity = tinhTrangBenhRepo
                .findByBenhNhanNoiTru_MaBenhAnAndNgay(req.getMaBenhAn(), req.getNgay())
                .orElse(TinhTrangBenh.builder()
                        .benhAnNoiTru(benhAn)
                        .ngay(req.getNgay())
                        .build());

        entity.setTinhTrang(req.getTinhTrang());
        entity.setGhiChu(req.getGhiChu());
        tinhTrangBenhRepo.save(entity);
    }

    @Override
    public List<TinhTrangBenhRes> getHistory(Long maBenhAn) {
        return tinhTrangBenhRepo.findByBenhNhanNoiTru_MaBenhAn(maBenhAn)
                .stream()
                .map(t -> {
                    TinhTrangBenhRes res = new TinhTrangBenhRes();
                    res.setNgay(t.getNgay());
                    res.setTinhTrang(t.getTinhTrang());
                    res.setGhiChu(t.getGhiChu());
                    return res;
                })
                .collect(Collectors.toList());
    }

    @Override
    public void deleteByNgay(Long maBenhAn, String ngayStr) {
        java.sql.Date ngay = java.sql.Date.valueOf(ngayStr);
        tinhTrangBenhRepo.findByBenhNhanNoiTru_MaBenhAnAndNgay(maBenhAn, ngay)
                .ifPresent(tinhTrangBenhRepo::delete);
    }
}
