package com.example.project_hospital.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "theodoidieutri")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TheoDoiDieuTri {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maTheoDoi;

    @ManyToOne
    @JoinColumn(name = "maYLenh", referencedColumnName = "maYLenh")
    private YLenhDieuTri yLenhDieuTri;

    private Long maDieuDuong;

    private LocalDateTime ngayGio;

    @Column(columnDefinition = "TEXT")
    private String ghiChu;

    @Column(columnDefinition = "TEXT")
    private String sinhHieu;
}
