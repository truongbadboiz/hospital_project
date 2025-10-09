package com.example.project_hospital.service;

import com.example.project_hospital.dto.request.BenhAnNoiTruReq;
import com.example.project_hospital.dto.response.BenhAnNoiTruRes;

import java.util.List;

public interface BenhAnNoiTruService {
    List<BenhAnNoiTruRes> getBenhNhanDangDieuTri();
    BenhAnNoiTruRes createBenhNhan(BenhAnNoiTruReq req);
    BenhAnNoiTruRes updateBenhAn(Long maBenhAn, BenhAnNoiTruReq req);

    //    BenhNhanNoiTruRes getById(Long id);
    void delete(Long maBenhAn);
}
