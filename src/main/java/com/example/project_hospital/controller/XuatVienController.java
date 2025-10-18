package com.example.project_hospital.controller;

import com.example.project_hospital.dto.request.TinhTrangBenhReq;
import com.example.project_hospital.dto.request.XuatVienReq;
import com.example.project_hospital.dto.response.XuatVienRes;
import com.example.project_hospital.service.XuatVienService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/xuatvien")

public class XuatVienController {

    private final XuatVienService xuatVienService;

    @GetMapping("/danhsachxuatvien")
    public ResponseEntity<List<XuatVienRes>> getDanhsachxuatvien() {
        return ResponseEntity.ok(xuatVienService.getAllXuatVien());
    }

    @GetMapping("/{maXuatVien}")
    public ResponseEntity<Optional<XuatVienRes>> getMaXuatVien(@PathVariable Long maXuatVien) {
        return ResponseEntity.ok(xuatVienService.getXuatVienById(maXuatVien));
    }

    @PostMapping("/addxuatvien")
    public ResponseEntity<XuatVienRes> createXuatVien(@RequestBody XuatVienReq req) {
        return ResponseEntity.ok(xuatVienService.createXuatVien(req));
    }

}
