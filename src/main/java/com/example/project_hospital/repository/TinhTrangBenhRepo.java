
package com.example.project_hospital.repository;

import com.example.project_hospital.entity.TinhTrangBenh;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface TinhTrangBenhRepo extends JpaRepository<TinhTrangBenh, Long> {

    List<TinhTrangBenh> findByBenhNhanNoiTru_MaBenhAn(Long maBenhAn);

    Optional<TinhTrangBenh> findByBenhNhanNoiTru_MaBenhAnAndNgay(Long maBenhAn, Date ngay);
}
