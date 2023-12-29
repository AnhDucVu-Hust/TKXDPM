package com.btl_tkxdpm;

import com.btl_tkxdpm.entity.NhanVien;
import com.btl_tkxdpm.entity.NhanVienAttendance;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Iterator;

public class Services {

    static Connection connection = DBConnection.conDB();
    public static ObservableList<NhanVienAttendance> queryChamCong(){
        ObservableList<NhanVienAttendance> listAttendance = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM ChamCong";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String maNhanVien = resultSet.getString("maNhanVien");
                Date ngay = resultSet.getDate("ngay");
                Time thoiGian = resultSet.getTime("thoiGian");
                String loaiChamCong = resultSet.getString("loaiChamCong");
                NhanVien nhanVien = queryNhanVienBangMa(maNhanVien);

                listAttendance.add(new NhanVienAttendance(nhanVien,ngay.toLocalDate(),thoiGian.toLocalTime(),loaiChamCong));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listAttendance;
    }
    public static ObservableList<NhanVienAttendance> queryChamCong(int month,int year){
        ObservableList<NhanVienAttendance> listAttendance = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM ChamCong WHERE MONTH(Ngay) = ? AND YEAR(Ngay) = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,month);
            preparedStatement.setInt(2,month);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String maNhanVien = resultSet.getString("maNhanVien");
                Date ngay = resultSet.getDate("ngay");
                Time thoiGian = resultSet.getTime("thoiGian");
                String loaiChamCong = resultSet.getString("loaiChamCong");
                NhanVien nhanVien = queryNhanVienBangMa(maNhanVien);

                listAttendance.add(new NhanVienAttendance(nhanVien,ngay.toLocalDate(),thoiGian.toLocalTime(),loaiChamCong));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listAttendance;
    }
    public static NhanVien queryNhanVienBangMa(String maNhanVien){
        try {
            Connection connection = DBConnection.conDB();
            String sql = "SELECT * FROM NhanVien WHERE maNhanVien=" + maNhanVien;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            NhanVien nhanVien = null;
            while (resultSet.next()) {
                String hoTen = resultSet.getString("hoTen");
                String donVi = resultSet.getString("donVi");
                String chucDanh = resultSet.getString("chucDanh");

                nhanVien = new NhanVien(hoTen,maNhanVien,chucDanh,donVi);
            }
            return nhanVien;

        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;

    }
     public static ObservableList<NhanVienAttendance> queryChamCongCongNhan(int month,int year){
        ObservableList<NhanVienAttendance> fullChamCong = queryChamCong(month,year);
        Iterator<NhanVienAttendance> iterator = fullChamCong.iterator();
        while (iterator.hasNext()) {
            NhanVienAttendance chamCong = iterator.next();
            if ("Nhân viên văn phòng".equals(chamCong.getNhanVien().getChucDanh())) {
                iterator.remove(); // Remove the element safely during iteration
            }
        }
        return fullChamCong;
    }
    public static ObservableList<NhanVienAttendance> queryChamCongVanPhong(int month,int year){
        ObservableList<NhanVienAttendance> fullChamCong = queryChamCong(month,year);
        Iterator<NhanVienAttendance> iterator = fullChamCong.iterator();
        while (iterator.hasNext()) {
            NhanVienAttendance chamCong = iterator.next();
            if ("Công nhân".equals(chamCong.getNhanVien().getChucDanh())) {
                iterator.remove(); // Remove the element safely during iteration
            }
        }
        return fullChamCong;
    }
    public static float SubtractLocalTime(LocalTime t1, LocalTime t2){
        Duration duration = Duration.between(t1,t2);
        float durationInHours = duration.toMinutes()/60.0f;
        return durationInHours;
    }
}
