package com.example.project_hospital.service;

import com.example.project_hospital.dto.response.BenhNhanNoiTruRes;
import com.example.project_hospital.dto.response.BenhNhanRes;

import java.util.List;

public interface BenhNhanNoiTruService {
    List<BenhNhanNoiTruRes> getBenhNhanDangDieuTri();
}
