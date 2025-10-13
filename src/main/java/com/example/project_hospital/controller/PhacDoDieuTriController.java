package com.example.project_hospital.controller;


import com.example.project_hospital.dto.request.PhacDoDieuTriReq;
import com.example.project_hospital.dto.response.PhacDoDieuTriRes;
import com.example.project_hospital.service.PhacDoDieuTriService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/phacdo")
public class PhacDoDieuTriController {

    @Autowired
    private PhacDoDieuTriService service;

    @PostMapping("/them")
    public ResponseEntity<PhacDoDieuTriRes> addPhacDo(
            @RequestPart PhacDoDieuTriReq req,
            @RequestPart(required = false) MultipartFile file) throws IOException {
        return ResponseEntity.ok(service.addPhacDo(req, file));
    }

    @PutMapping("/sua/{id}")
    public ResponseEntity<PhacDoDieuTriRes> updatePhacDo(
            @PathVariable Long id,
            @RequestPart PhacDoDieuTriReq req,
            @RequestPart(required = false) MultipartFile file) throws IOException {
        return ResponseEntity.ok(service.updatePhacDo(id, req, file));
    }

    @DeleteMapping("/xoa/{id}")
    public ResponseEntity<Void> deletePhacDo(@PathVariable Long id) {
        service.deletePhacDo(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PhacDoDieuTriRes> getPhacDo(@PathVariable Long id) {
        return ResponseEntity.ok(service.getPhacDo(id));
    }

    @GetMapping("/tatca")
    public ResponseEntity<List<PhacDoDieuTriRes>> getAllPhacDo() {
        return ResponseEntity.ok(service.getAllPhacDo());
    }
}
