package com.example.project_hospital.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "ylenhdieutri")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class YLenhDieuTri {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "maYLenh")
    private Long maYLenh;

    @ManyToOne
    @JoinColumn(name = "maBenhAn", referencedColumnName = "maBenhAn")
    private BenhAnNoiTru benhAnNoiTru;

    @Column(name = "maBacSi", length = 10)
    private String maBacSi;

    @Column(name = "ngayGio")
    private LocalDateTime ngayGio;

    @Column(name = "noiDung", columnDefinition = "TEXT")
    private String noiDung;

    @Column(name = "trangThai", length = 50)
    private String trangThai;

    @OneToMany(mappedBy = "yLenhDieuTri", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TheoDoiDieuTri> dsTheoDoiDieuTri;
    @OneToMany(mappedBy = "yLenh", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ThuocSuDung> thuocSuDungList;
}
