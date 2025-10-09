package com.example.project_hospital.controller;

import com.example.project_hospital.dto.request.BenhAnNoiTruReq;
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

    @GetMapping("/danhsachbenhannoitru")
    public ResponseEntity<List<BenhAnNoiTruRes>> getBenhNhanDangDieuTri() {
        return ResponseEntity.ok(benhAnNoiTruService.getBenhNhanDangDieuTri());
    }
    @PostMapping("/benhannoitru")
    public ResponseEntity<BenhAnNoiTruRes> createBenhnhanNoiTru(@RequestBody BenhAnNoiTruReq req)
    {
       BenhAnNoiTruRes response = benhAnNoiTruService.createBenhNhan(req);
        return ResponseEntity.ok(response);
    }
    @PutMapping("/updatebenhannoitru/{maBenhAn}")
    public ResponseEntity<BenhAnNoiTruRes> updateBenhAnNoiTru(
            @PathVariable Long maBenhAn,
            @RequestBody BenhAnNoiTruReq req
    ) {
        BenhAnNoiTruRes response = benhAnNoiTruService.updateBenhAn(maBenhAn, req);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/xoabenhannoitru/{maBenhAn}")
    public ResponseEntity<?>delete(@PathVariable Long maBenhAn)
        {
        benhAnNoiTruService.delete(maBenhAn);
        return ResponseEntity.ok("Đã xóa bệnh án");
        }
}
