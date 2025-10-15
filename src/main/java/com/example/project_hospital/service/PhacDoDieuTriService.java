package com.example.project_hospital.service;

import com.example.project_hospital.dto.request.PhacDoDieuTriReq;
import com.example.project_hospital.dto.response.PhacDoDieuTriRes;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface PhacDoDieuTriService {
    PhacDoDieuTriRes createPhacDoDieuTri(PhacDoDieuTriReq request) throws IOException;
    public List<PhacDoDieuTriRes> getAllPhacDoDieuTri();
    PhacDoDieuTriRes updatePhacDoDieuTri(Long maYLenh, PhacDoDieuTriReq request) throws IOException;
    void deletePhacDoDieuTri(Long maYLenh);



}
