package com.btl_tkxdpm.entity;

import javafx.beans.property.StringProperty;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public class NhanVienAttendance {
    private NhanVien nhanVien;
    private final LocalDate day;
    private LocalTime gioVao;
    private String loaiChamCong;

    public NhanVienAttendance(NhanVien nhanVien, LocalDate day, LocalTime gioVao, String loaiChamCong) {
        this.nhanVien = nhanVien;
        this.day = day;
        this.gioVao = gioVao;
        this.loaiChamCong = loaiChamCong;
    }

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    public LocalDate getDay() {
        return day;
    }

    public LocalTime getGioVao() {
        return gioVao;
    }

    public void setGioVao(LocalTime gioVao) {
        this.gioVao = gioVao;
    }

    public String getLoaiChamCong() {
        return loaiChamCong;
    }

    public void setLoaiChamCong(String loaiChamCong) {
        this.loaiChamCong = loaiChamCong;
    }
}
