package com.btl_tkxdpm.entity;

import javafx.beans.property.StringProperty;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public class NhanVienAttendance {
    private NhanVien nhanVien;
    private final LocalDate day;
    private LocalTime gioVao;
    private LocalTime gioRa;

    public NhanVienAttendance(NhanVien nhanVien, LocalDate day, LocalTime gioVao, LocalTime gioRa) {
        this.nhanVien = nhanVien;
        this.day = day;
        this.gioVao = gioVao;
        this.gioRa = gioRa;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    public void setGioVao(LocalTime gioVao) {
        this.gioVao = gioVao;
    }

    public void setGioRa(LocalTime gioRa) {
        this.gioRa = gioRa;
    }

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public LocalDate getDay() {
        return day;
    }

    public LocalTime getGioVao() {
        return gioVao;
    }

    public LocalTime getGioRa() {
        return gioRa;
    }
}
