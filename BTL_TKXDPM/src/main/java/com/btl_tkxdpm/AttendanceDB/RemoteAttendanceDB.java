package com.btl_tkxdpm.AttendanceDB;

import com.btl_tkxdpm.DBConnection;
import com.btl_tkxdpm.Services;
import com.btl_tkxdpm.entity.NhanVien;
import com.btl_tkxdpm.entity.NhanVienAttendance;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDate;

public class RemoteAttendanceDB implements  IAttendanceDB {
    private ObservableList<NhanVienAttendance> listAttendance;
    @Override
    public ObservableList<NhanVienAttendance> getListAttendance() {
        return Services.queryChamCong();
    }

    @Override
    public ObservableList<NhanVienAttendance> getListCongNhanAttendance() {
       return null;
    }

    @Override
    public ObservableList<NhanVienAttendance> getListNhanVienAttendace() {
        return null;
    }

    @Override
    public void addAttendance(ObservableList<NhanVienAttendance> listChamCong) {

    }
}
