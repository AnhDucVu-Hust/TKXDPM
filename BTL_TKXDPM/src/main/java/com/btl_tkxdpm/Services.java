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
                NhanVien nhanVien = queryNhanVienBangMa(maNhanVien).get(0);

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
                NhanVien nhanVien = queryNhanVienBangMa(maNhanVien).get(0);

                listAttendance.add(new NhanVienAttendance(nhanVien,ngay.toLocalDate(),thoiGian.toLocalTime(),loaiChamCong));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listAttendance;
    }
    public static ObservableList<NhanVien> queryNhanVien(String sql) {
        try {
            ObservableList<NhanVien> listNhanVien = FXCollections.observableArrayList();
            Connection connection = DBConnection.conDB();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            NhanVien nhanVien = null;
            while (resultSet.next()) {
                String maNhanVien = resultSet.getString("maNhanVien");
                String hoTen = resultSet.getString("hoTen");
                String donVi = resultSet.getString("donVi");
                String chucDanh = resultSet.getString("chucDanh");

                nhanVien = new NhanVien(hoTen,maNhanVien,chucDanh,donVi);
                listNhanVien.add(nhanVien);
            }
            return listNhanVien;

        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    public static ObservableList<NhanVien> queryNhanVienBangMa(String maNhanVien){
        String sql = "SELECT * FROM NhanVien WHERE maNhanVien like %"+maNhanVien+"%";
        ObservableList<NhanVien> listNhanVien = queryNhanVien(sql);
        return listNhanVien;


    }
    public static ObservableList<NhanVien> queryNhanVienBangDonVi(String donVi) {
        String sql = "SELECT * FROM NhanVien WHERE donVi like %"+donVi+"%";
        ObservableList<NhanVien> listNhanVien = queryNhanVien(sql);
        return listNhanVien;
    }
    public static ObservableList<NhanVien> queryNhanVienBangTen(String hoTen) {
        String sql = "SELECT * FROM NhanVien WHERE hoTen like %"+hoTen+"%";
        ObservableList<NhanVien> listNhanVien = queryNhanVien(sql);
        return listNhanVien;
    }
    public static void importNhanVienFromExcel(String filepath){

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
    public static void addNhanVien(NhanVien nhanVien){
        try {
            String query = "INSERT INTO NhanVien(maNhanVien,ten,donVi,chucDanh) VALUES (?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,nhanVien.getMaNhanVien());
            preparedStatement.setString(2,nhanVien.getHoTen());
            preparedStatement.setString(3,nhanVien.getDonVi());
            preparedStatement.setString(4,nhanVien.getChucDanh());
            preparedStatement.execute();
            System.out.println("Thêm thành công");
        }
        catch (SQLException e){
            e.printStackTrace();
        }

    }

}
