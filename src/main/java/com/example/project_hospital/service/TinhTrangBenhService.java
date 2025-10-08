package com.example.project_hospital.service;

import com.example.project_hospital.dto.request.TinhTrangBenhReq;
import com.example.project_hospital.dto.response.TinhTrangBenhRes;
import com.example.project_hospital.entity.TinhTrangBenh;

import java.sql.Date;
import java.util.List;


public interface TinhTrangBenhService {
    void saveOrUpdate(TinhTrangBenhReq request);
    List<TinhTrangBenhRes> getHistory(Long maBenhAn);
    void deleteByNgay(Long maBenhAn, String ngay);
}
