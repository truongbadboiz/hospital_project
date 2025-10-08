package com.example.project_hospital.controller;

import com.example.project_hospital.dto.response.BenhNhanNoiTruRes;
import com.example.project_hospital.service.BenhNhanNoiTruService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class BenhNhanNoiTruController {
    private final BenhNhanNoiTruService benhNhanNoiTruService;

    @GetMapping("/benhnhannoitru")
    public ResponseEntity<List<BenhNhanNoiTruRes>> getBenhNhanDangDieuTri() {
        return ResponseEntity.ok(benhNhanNoiTruService.getBenhNhanDangDieuTri());
    }
}
