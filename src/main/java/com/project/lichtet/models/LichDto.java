package com.project.lichtet.models;

import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LichDto {

    private String ten;

    private String maSanPham;

    private String kichThuoc;

    private String nhomLich;

    private String anh;

    private String gia;

    private String tinhTrang;

    private String congNghe;

    private String phuDieu;

    private boolean banChay;

    private boolean trangChu;

    private String id;
}
