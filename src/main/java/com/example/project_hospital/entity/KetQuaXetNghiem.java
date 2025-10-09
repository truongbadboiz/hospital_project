package com.example.project_hospital.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@Table(name = "ketquaxetnghiem")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class KetQuaXetNghiem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "maXetNghiem")
    private Long maXetNghiem;

    @ManyToOne
    @JoinColumn(name = "maBenhAn", referencedColumnName = "maBenhAn")
    private BenhAnNoiTru benhAnNoiTru;

    @Column(name = "loaiXetNghiem")
    private String loaiXetNghiem;

    @Column(name = "ketQua", columnDefinition = "TEXT")
    private String ketQua;

    @Column(name = "ngayThucHien")
    private Date ngayThucHien;

    @Column(name = "maBacSi")
    private String maBacSi;
}