package com.example.project_hospital.controller;

import com.example.project_hospital.dto.request.BenhAnNoiTruReq;
import com.example.project_hospital.dto.response.BenhAnNoiTruRes;
import com.example.project_hospital.dto.response.BenhAnNoiTruRes2;
import com.example.project_hospital.dto.response.PageResponse;
import com.example.project_hospital.service.BenhAnNoiTruService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
    public ResponseEntity<BenhAnNoiTruRes2> createBenhnhanNoiTru(@RequestBody BenhAnNoiTruReq req) {
        BenhAnNoiTruRes2 response = benhAnNoiTruService.createBenhNhan(req);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/updatebenhannoitru/{maBenhAn}")
    public ResponseEntity<BenhAnNoiTruRes2> updateBenhAnNoiTru(
            @PathVariable Long maBenhAn,
            @RequestBody BenhAnNoiTruReq req
    ) {
        BenhAnNoiTruRes2 response = benhAnNoiTruService.updateBenhAn(maBenhAn, req);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/xoabenhannoitru/{maBenhAn}")
    public ResponseEntity<?> delete(@PathVariable Long maBenhAn) {
        benhAnNoiTruService.delete(maBenhAn);
        return ResponseEntity.ok("Đã xóa bệnh án");
    }

    @PostMapping("/timkiem")
    public ResponseEntity<PageResponse<Map<String, Object>>> timKiem(@RequestBody Map<String, Object> body) {
        String keyword = (String) body.getOrDefault("keyword", "");

        int page = ((Number) body.getOrDefault("page", 0)).intValue();
        int size = ((Number) body.getOrDefault("size", 10)).intValue();

        PageResponse<Map<String, Object>> result = benhAnNoiTruService.search(keyword, page, size);
        return ResponseEntity.ok(result);
    }

}
