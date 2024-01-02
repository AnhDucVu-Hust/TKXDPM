package com.btl_tkxdpm.AttendanceDB;

import com.btl_tkxdpm.entity.NhanVien;
import com.btl_tkxdpm.entity.NhanVienAttendance;
import javafx.collections.ObservableList;

public interface IAttendanceDB {
    public ObservableList<NhanVienAttendance> getListAttendance();
    public ObservableList<NhanVienAttendance> getListCongNhanAttendance();
    public ObservableList<NhanVienAttendance> getListNhanVienAttendace();

    public void addAttendance(ObservableList<NhanVienAttendance> listChamCong);
    public void editAttendance(NhanVienAttendance editedAttendance);
    public ObservableList<NhanVienAttendance> queryByTenOrID(ObservableList<NhanVienAttendance> listChamCong,String query);
}
