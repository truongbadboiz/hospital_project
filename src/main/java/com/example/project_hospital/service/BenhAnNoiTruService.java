package com.example.project_hospital.service;

import com.example.project_hospital.dto.request.BenhAnNoiTruReq;
import com.example.project_hospital.dto.response.BenhAnNoiTruRes;
import com.example.project_hospital.entity.BenhAnNoiTru;

import java.util.List;
import java.util.Map;

public interface BenhAnNoiTruService {
    List<BenhAnNoiTruRes> getBenhNhanDangDieuTri();
    BenhAnNoiTruRes createBenhNhan(BenhAnNoiTruReq req);
    BenhAnNoiTruRes updateBenhAn(Long maBenhAn, BenhAnNoiTruReq req);
    List<Map<String, Object>> search(String keyword);
    //    BenhNhanNoiTruRes getById(Long id);
    void delete(Long maBenhAn);
}
