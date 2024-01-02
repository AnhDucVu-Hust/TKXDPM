package com.btl_tkxdpm.entity;

import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Set;

public class NhanVienAttendance {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;
    private NhanVien nhanVien;
    private  LocalDate day;
    private LocalTime gioVao;
    private String loaiChamCong;


    public NhanVienAttendance(int id,NhanVien nhanVien, LocalDate day, LocalTime gioVao, String loaiChamCong) {
        this.id =id;
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
    public void setDay(LocalDate day) {
        this.day=day;
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
