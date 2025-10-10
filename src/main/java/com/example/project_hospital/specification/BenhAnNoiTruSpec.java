package com.example.project_hospital.specification;

import com.example.project_hospital.entity.BenhAnNoiTru;
import org.springframework.data.jpa.domain.Specification;

public class BenhAnNoiTruSpec {

    public static Specification<BenhAnNoiTru> hasKeyword(String keyword) {
        return (root, query, cb) -> {
            if (keyword == null || keyword.trim().isEmpty()) {
                return cb.conjunction();
            }

            var nhapVienJoin = root.join("nhapVien");
            var benhNhanJoin = nhapVienJoin.join("benhNhan");
            String likePattern = "%" + keyword.trim().toLowerCase() + "%";
            return cb.or(
                    cb.like(cb.lower(benhNhanJoin.get("hoTen")), likePattern),
                    cb.like(cb.lower(nhapVienJoin.get("phong")), likePattern),
                    cb.like(cb.lower(nhapVienJoin.get("khoaDieuTri")), likePattern),
                    cb.like(cb.lower(root.get("trangThai")), likePattern)
            );
        };
    }
}
