package com.example.project_hospital.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.sql.Date;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "thuocsudung")
public class ThuocSuDung {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maSuDung;

    @ManyToOne
    @JoinColumn(name = "maYLenh", referencedColumnName = "maYLenh")
    private YLenhDieuTri yLenh;

    @Column(length = 10)
    private String maThuoc;

    private Integer soLuong;

    @Column(length = 20)
    private String donVi;

    private Date ngayCapPhat;

    @Column(length = 50)
    private String trangThai;
}
