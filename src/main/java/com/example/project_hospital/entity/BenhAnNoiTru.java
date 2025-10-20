package com.example.project_hospital.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="benhannoitru")
public class BenhAnNoiTru {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "maBenhAn")
    private Long maBenhAn;

    @ManyToOne
    @JoinColumn(name = "maBenhNhan")
    private BenhNhan benhNhan;

    @OneToOne
    @JoinColumn(name = "maNhapVien", unique = true)
    private NhapVien nhapVien;

    @Column(name="ngayLap")
    private Date ngayLap;

    @Column(name="tomTatBenhAn")
    private String tomTatBenhAn;

    @Column(name="tienSuBenh")
    private String tienSuBenh;

    @Column(name="ketQuaDieuTri")
    private String ketQuaDieuTri;

    @Column(name="trangThai")
    private String trangThai;

    @Column(name="hinhAnhUrl")
    private String hinhAnhUrl;

    @OneToMany(mappedBy = "benhAnNoiTru", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TinhTrangBenh> tinhTrangBenhs;

    // Các quan hệ khác
    @OneToMany(mappedBy = "benhAnNoiTru", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<KetQuaXetNghiem> ketQuaXetNghiem;

    @OneToMany(mappedBy = "benhAnNoiTru", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<YLenhDieuTri> yLenhDieuTri;
}
