package com.btl_tkxdpm.AttendanceDB;

import com.btl_tkxdpm.DBConnection;
import com.btl_tkxdpm.entity.NhanVienAttendance;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RemoteAttendanceDB implements  IAttendanceDB {

    @Override
    public ObservableList<NhanVienAttendance> getListAttendance() {
        try {
            Connection connection = DBConnection.conDB();
            String sql = "SELECT * FROM ChamCong";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int maNhanVien = resultSet.getInt("maNhanVien");
                String name = resultSet.getString("gioVao");
                String department = resultSet.getString("department");
                String title = resultSet.getString("title");


            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
