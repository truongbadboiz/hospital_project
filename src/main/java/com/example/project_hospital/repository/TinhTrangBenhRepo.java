
package com.example.project_hospital.repository;

import com.example.project_hospital.entity.BenhAnNoiTru;
import com.example.project_hospital.entity.TinhTrangBenh;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface TinhTrangBenhRepo extends JpaRepository<TinhTrangBenh, Long> {
    List<TinhTrangBenh> findByBenhAnNoiTru_MaBenhAn(Long maBenhAn);

    Optional<TinhTrangBenh> findByBenhAnNoiTru_MaBenhAnAndNgay(Long maBenhAn, Date ngay);

}
