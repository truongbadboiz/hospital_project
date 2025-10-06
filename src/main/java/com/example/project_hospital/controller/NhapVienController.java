package com.example.project_hospital.controller;


import com.example.project_hospital.dto.request.NhapVienReq;
import com.example.project_hospital.dto.response.BenhNhanRes;
import com.example.project_hospital.dto.response.NhapVienRes;

import com.example.project_hospital.entity.BenhNhan;
import com.example.project_hospital.service.NhapVienService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class NhapVienController {

    private final NhapVienService nhapVienService;
    @PostMapping("/nhapvien")
    public ResponseEntity<NhapVienRes> createNhapVien(@RequestBody NhapVienReq request) {
        NhapVienRes response = nhapVienService.createNhapVienn(request);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/benhnhan")
    public ResponseEntity<List<BenhNhanRes>> getAllBenhNhan() {
        List<BenhNhan> list = nhapVienService.getAllBenhNhan();

        List<BenhNhanRes> res = list.stream().map(bn -> BenhNhanRes.builder()
                .maBenhNhan(bn.getMaBenhNhan())
                .hoTen(bn.getHoTen())
                .ngaySinh(bn.getNgaySinh())
                .gioiTinh(bn.getGioiTinh())
                .soCMND(bn.getSoCCCD())
                .diaChi(bn.getDiaChi())
                .build()).toList();

        return ResponseEntity.ok(res);
    }
}
