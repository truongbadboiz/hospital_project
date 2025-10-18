package com.example.project_hospital.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="tinhtrangbenh")
public class TinhTrangBenh {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maTinhTrang;

    @OneToOne
    @JoinColumn(name = "maBenhAn", unique = true)
    private BenhAnNoiTru benhAnNoiTru;

    @Column(name="ngay")
    private Date ngay;

    @Column(name="tinhTrang")
    private String tinhTrang;

    @Column(name = "ghiChu")
    private String ghiChu;

    @Column(name = "ngayTao")
    private Date ngayTao;
}
