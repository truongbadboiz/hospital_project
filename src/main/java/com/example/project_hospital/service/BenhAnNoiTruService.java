package com.example.project_hospital.service;

import com.example.project_hospital.dto.request.BenhNhanNoiTruReq;
import com.example.project_hospital.dto.response.BenhAnNoiTruRes;

import java.util.List;

public interface BenhAnNoiTruService {
    List<BenhAnNoiTruRes> getBenhNhanDangDieuTri();
    BenhAnNoiTruRes createBenhNhan(BenhNhanNoiTruReq req);
    //    BenhNhanNoiTruRes update(Long id, BenhNhanNoiTruReq req);
//    BenhNhanNoiTruRes getById(Long id);
//    void delete(Long id);
}
