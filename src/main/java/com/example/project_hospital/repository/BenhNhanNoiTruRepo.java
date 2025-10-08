package com.example.project_hospital.repository;


import com.example.project_hospital.entity.BenhNhanNoiTru;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
    public interface BenhNhanNoiTruRepo extends JpaRepository<BenhNhanNoiTru, Long> {
        List<BenhNhanNoiTru> findByTrangThai(String trangThai);
    }

