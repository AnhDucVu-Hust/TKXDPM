package com.btl_tkxdpm.entity;

public class NhanVienVanPhongThongKe {
    private String maNhanVien;
    private String hoTen;
    private String donVi;
    private String thang;
    private int soBuoiDiLam;
    private double soGioDiMuonVeSom;

    public NhanVienVanPhongThongKe(String maNhanVien, String hoTen, String donVi, String thang, int soBuoiDiLam, double soGioDiMuonVeSom) {
        this.maNhanVien = maNhanVien;
        this.hoTen = hoTen;
        this.donVi = donVi;
        this.thang = thang;
        this.soBuoiDiLam = soBuoiDiLam;
        this.soGioDiMuonVeSom = soGioDiMuonVeSom;
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getDonVi() {
        return donVi;
    }

    public void setDonVi(String donVi) {
        this.donVi = donVi;
    }

    public String getThang() {
        return thang;
    }

    public void setThang(String thang) {
        this.thang = thang;
    }

    public int getSoBuoiDiLam() {
        return soBuoiDiLam;
    }

    public void setSoBuoiDiLam(int soBuoiDiLam) {
        this.soBuoiDiLam = soBuoiDiLam;
    }

    public double getSoGioDiMuonVeSom() {
        return soGioDiMuonVeSom;
    }

    public void setSoGioDiMuonVeSom(double soGioDiMuonVeSom) {
        this.soGioDiMuonVeSom = soGioDiMuonVeSom;
    }
}
