package com.example.project_hospital.service;

import com.example.project_hospital.dto.request.PhacDoDieuTriReq;
import com.example.project_hospital.dto.response.PhacDoDieuTriRes;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface PhacDoDieuTriService {
    Optional<PhacDoDieuTriRes> getPhacDoById(Long id);
    PhacDoDieuTriRes createPhacDoDieuTri(PhacDoDieuTriReq request) throws IOException;
     List<PhacDoDieuTriRes> getAllPhacDoDieuTri();
    PhacDoDieuTriRes updatePhacDoDieuTri(Long maYLenh, PhacDoDieuTriReq request) throws IOException;
    void deletePhacDoDieuTri(Long maYLenh);



}
