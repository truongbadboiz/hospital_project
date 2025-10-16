package com.example.project_hospital.service;

import com.example.project_hospital.dto.request.NhapVienReq;
import com.example.project_hospital.dto.response.BenhAnNoiTruRes;
import com.example.project_hospital.dto.response.BenhNhanRes;
import com.example.project_hospital.dto.response.NhapVienRes;
import com.example.project_hospital.entity.BenhNhan;
import com.example.project_hospital.entity.NhapVien;

import java.util.List;

public interface NhapVienService {
    NhapVienRes createNhapVienn(NhapVienReq request);
     List<BenhNhanRes> getAllBenhNhanRes();
    List<NhapVienRes> getAllNhapVienRes();
}
