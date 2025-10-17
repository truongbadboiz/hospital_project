package com.example.project_hospital.service;

import com.example.project_hospital.dto.request.BenhAnNoiTruReq;
import com.example.project_hospital.dto.response.BenhAnNoiTruRes;
import com.example.project_hospital.dto.response.BenhAnNoiTruRes2;
import com.example.project_hospital.dto.response.PageResponse;
import org.springframework.data.domain.Page;


import java.util.List;
import java.util.Map;

public interface BenhAnNoiTruService {
    List<BenhAnNoiTruRes> getBenhNhanDangDieuTri();
    BenhAnNoiTruRes2 createBenhNhan(BenhAnNoiTruReq req);
    BenhAnNoiTruRes2 updateBenhAn(Long maBenhAn, BenhAnNoiTruReq req);
    PageResponse<Map<String, Object>> search(String keyword, int page, int size);
    void delete(Long maBenhAn);
}
