package com.example.project_hospital.service;

import com.example.project_hospital.dto.request.XuatVienReq;
import com.example.project_hospital.dto.response.XuatVienRes;


import java.util.List;
import java.util.Optional;

public interface XuatVienService {
    List<XuatVienRes> getAllXuatVien();
    Optional<XuatVienRes> getXuatVienById(Long maXuatVien);
    XuatVienRes createXuatVien(XuatVienReq req);


}
