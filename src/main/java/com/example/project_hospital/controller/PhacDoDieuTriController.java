package com.example.project_hospital.controller;

import com.example.project_hospital.dto.request.PhacDoDieuTriReq;
import com.example.project_hospital.dto.response.PhacDoDieuTriRes;
import com.example.project_hospital.service.PhacDoDieuTriService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/phacdo")
public class PhacDoDieuTriController {

    private final PhacDoDieuTriService phacDoDieuTriService;

    @PostMapping("/add")
    public ResponseEntity<PhacDoDieuTriRes> createPhacDo(
            @RequestParam("maBenhAn") Long maBenhAn,
            @RequestParam("maBacSi") String maBacSi,
            @RequestParam("ngayGio") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate ngayGio,
            @RequestParam("noiDung") String noiDung,
            @RequestParam("trangThai") String trangThai,
            @RequestParam(value = "file", required = false) MultipartFile file
    ) throws IOException {

        PhacDoDieuTriReq request = new PhacDoDieuTriReq();
        request.setMaBenhAn(maBenhAn);
        request.setMaBacSi(maBacSi);
        request.setNgayGio(ngayGio.atStartOfDay()); // Convert LocalDate -> LocalDateTime
        request.setNoiDung(noiDung);
        request.setTrangThai(trangThai);
        request.setFile(file);

        return ResponseEntity.ok(phacDoDieuTriService.createPhacDoDieuTri(request));
    }

    // UPDATE
    @PutMapping("/update/{id}")
    public ResponseEntity<PhacDoDieuTriRes> updatePhacDo(
            @PathVariable Long id,
            @RequestParam("maBenhAn") Long maBenhAn,
            @RequestParam("maBacSi") String maBacSi,
            @RequestParam("ngayGio") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate ngayGio,
            @RequestParam("noiDung") String noiDung,
            @RequestParam("trangThai") String trangThai,
            @RequestParam(value = "file", required = false) MultipartFile file
    ) throws IOException {
        PhacDoDieuTriReq request = new PhacDoDieuTriReq();
        request.setMaBenhAn(maBenhAn);
        request.setMaBacSi(maBacSi);
        request.setNgayGio(ngayGio.atStartOfDay());
        request.setNoiDung(noiDung);
        request.setTrangThai(trangThai);
        request.setFile(file);

        return ResponseEntity.ok(phacDoDieuTriService.updatePhacDoDieuTri(id, request));
    }

    // DELETE
    @DeleteMapping("/del/{id}")
    public ResponseEntity<Void> deletePhacDo(@PathVariable Long id) {
        phacDoDieuTriService.deletePhacDoDieuTri(id);
        return ResponseEntity.noContent().build();
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<PhacDoDieuTriRes> getPhacDo(@PathVariable Long id) {
        PhacDoDieuTriRes res = phacDoDieuTriService.getPhacDoById(id);
        return ResponseEntity.ok(res);
    }

    // GET ALL
    @GetMapping("/getAll")
    public ResponseEntity<List<PhacDoDieuTriRes>> getAllPhacDo() {
        return ResponseEntity.ok(phacDoDieuTriService.getAllPhacDoDieuTri());
    }
}
