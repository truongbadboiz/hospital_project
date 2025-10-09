package com.example.project_hospital.controller;

import com.example.project_hospital.dto.request.BenhNhanNoiTruReq;
import com.example.project_hospital.dto.response.BenhAnNoiTruRes;
import com.example.project_hospital.service.BenhAnNoiTruService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class BenhAnNoiTruController {
    private final BenhAnNoiTruService benhAnNoiTruService;

    @GetMapping("/benhnhannoitru")
    public ResponseEntity<List<BenhAnNoiTruRes>> getBenhNhanDangDieuTri() {
        return ResponseEntity.ok(benhAnNoiTruService.getBenhNhanDangDieuTri());
    }
    @PostMapping("/benhannoitru")
    public ResponseEntity<BenhAnNoiTruRes> createBenhnhanNoiTru(@RequestBody BenhNhanNoiTruReq req)
    {
       BenhAnNoiTruRes response = benhAnNoiTruService.createBenhNhan(req);
        return ResponseEntity.ok(response);
    }
}
