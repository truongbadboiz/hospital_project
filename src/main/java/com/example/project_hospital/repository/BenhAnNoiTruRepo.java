package com.example.project_hospital.repository;


import com.example.project_hospital.entity.BenhAnNoiTru;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BenhAnNoiTruRepo extends JpaRepository<BenhAnNoiTru, Long>, JpaSpecificationExecutor<BenhAnNoiTru> {
    List<BenhAnNoiTru> findByTrangThai(String trangThai);
    Optional<BenhAnNoiTru> findByMaBenhAn(Long maBenhAn);
    boolean existsByNhapVien_MaNhapVien(Long maNhapVien);

}

