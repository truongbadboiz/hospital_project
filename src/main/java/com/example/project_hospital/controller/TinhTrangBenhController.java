package com.example.project_hospital.controller;


import com.example.project_hospital.dto.request.TinhTrangBenhReq;
import com.example.project_hospital.dto.response.TinhTrangBenhRes;
import com.example.project_hospital.service.TinhTrangBenhService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tinhtrangbenh")
@RequiredArgsConstructor
public class TinhTrangBenhController {

    private final TinhTrangBenhService tinhTrangBenhService;

    @PostMapping("capnhattinhtrang")
    public ResponseEntity<?> saveOrUpdate(@RequestBody TinhTrangBenhReq request) {
        tinhTrangBenhService.saveOrUpdate(request);
        return ResponseEntity.ok("Cập nhật tình trạng bệnh thành công");
    }

    @GetMapping("/{maBenhAn}")
    public ResponseEntity<List<TinhTrangBenhRes>> getHistory(@PathVariable Long maBenhAn) {
        return ResponseEntity.ok(tinhTrangBenhService.getHistory(maBenhAn));
    }

    @DeleteMapping("/{maBenhAn}/{ngay}")
    public ResponseEntity<?> deleteByNgay(@PathVariable Long maBenhAn, @PathVariable String ngay) {
        tinhTrangBenhService.deleteByNgay(maBenhAn, ngay);
        return ResponseEntity.ok("Đã xóa tình trạng bệnh ngày " + ngay);
    }
}
