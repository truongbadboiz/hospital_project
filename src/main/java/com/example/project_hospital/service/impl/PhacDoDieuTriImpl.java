package com.example.project_hospital.service.impl;

import com.example.project_hospital.dto.request.PhacDoDieuTriReq;
import com.example.project_hospital.dto.response.PhacDoDieuTriRes;
import com.example.project_hospital.entity.YLenhDieuTri;
import com.example.project_hospital.repository.BenhAnNoiTruRepo;
import com.example.project_hospital.repository.PhacDoDieuTriRepo;
import com.example.project_hospital.service.PhacDoDieuTriService;
import com.example.project_hospital.utils.UploadUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PhacDoDieuTriImpl implements PhacDoDieuTriService {

    private final PhacDoDieuTriRepo repo;
    private final BenhAnNoiTruRepo benhAnRepo;

    @Override
    public PhacDoDieuTriRes addPhacDo(PhacDoDieuTriReq req, MultipartFile file) throws IOException {
       YLenhDieuTri phacDo = new YLenhDieuTri();
        phacDo.setBenhAnNoiTru(benhAnRepo.findById(req.getMaBenhAn())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy bệnh án")));
        phacDo.setMaBacSi(req.getMaBacSi());
        phacDo.setNgayGio(req.getNgayGio());
        phacDo.setNoiDung(req.getNoiDung());
        phacDo.setTrangThai(req.getTrangThai());
        phacDo.setFileUrl(file != null ? UploadUtils.saveFile(file) : UploadUtils.saveBase64(req.getFileBase64()));
        return mapToRes(repo.save(phacDo));
    }

    @Override
    public PhacDoDieuTriRes updatePhacDo(Long id, PhacDoDieuTriReq req, MultipartFile file) throws IOException {
        YLenhDieuTri phacDo = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy phác đồ"));
        phacDo.setMaBacSi(req.getMaBacSi());
        phacDo.setNgayGio(req.getNgayGio());
        phacDo.setNoiDung(req.getNoiDung());
        phacDo.setTrangThai(req.getTrangThai());
        if (file != null || (req.getFileBase64() != null && !req.getFileBase64().isEmpty())) {
            phacDo.setFileUrl(file != null ? UploadUtils.saveFile(file) : UploadUtils.saveBase64(req.getFileBase64()));
        }
        return mapToRes(repo.save(phacDo));
    }

    @Override
    public void deletePhacDo(Long id) {
        if (!repo.existsById(id)) throw new RuntimeException("Không tìm thấy phác đồ");
        repo.deleteById(id);
    }

    // Lấy theo id
    public PhacDoDieuTriRes getPhacDo(Long id) {
       YLenhDieuTri phacDo = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy phác đồ"));
        return mapToRes(phacDo);
    }

    @Override
    public List<PhacDoDieuTriRes> getAllPhacDo() {
        return repo.findAll().stream().map(this::mapToRes).toList();
    }


    private PhacDoDieuTriRes mapToRes(YLenhDieuTri phacDo) {
        return new PhacDoDieuTriRes(
                phacDo.getMaYLenh(),
                phacDo.getBenhAnNoiTru().getMaBenhAn(),
                phacDo.getNoiDung(),
                phacDo.getTrangThai(),
                phacDo.getFileUrl()
        );
    }
}
