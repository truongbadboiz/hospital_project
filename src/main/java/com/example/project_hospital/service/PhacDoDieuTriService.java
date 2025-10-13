package com.example.project_hospital.service;

import com.example.project_hospital.dto.request.PhacDoDieuTriReq;
import com.example.project_hospital.dto.response.PhacDoDieuTriRes;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface PhacDoDieuTriService {
    PhacDoDieuTriRes addPhacDo(PhacDoDieuTriReq req, MultipartFile file) throws IOException;
    PhacDoDieuTriRes updatePhacDo(Long id, PhacDoDieuTriReq req, MultipartFile file) throws IOException;
    void deletePhacDo(Long id);
    PhacDoDieuTriRes getPhacDo(Long id);
    List<PhacDoDieuTriRes> getAllPhacDo();



}
