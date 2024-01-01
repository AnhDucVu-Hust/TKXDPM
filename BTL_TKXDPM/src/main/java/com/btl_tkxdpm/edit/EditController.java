package com.btl_tkxdpm.edit;

import com.btl_tkxdpm.AttendanceDB.IAttendanceDB;
import com.btl_tkxdpm.entity.NhanVienAttendance;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class EditController implements Initializable {
    public NhanVienAttendance getLogChamCong() {
        return logChamCong;
    }

    public void setLogChamCong(NhanVienAttendance logChamCong) {
        this.logChamCong = logChamCong;
    }

    private NhanVienAttendance logChamCong;
    @FXML
    private Label chucDanh;

    @FXML
    private Button clickQuayLai;

    @FXML
    private Button clickXacNhan;

    @FXML
    private Label donVI;

    @FXML
    private TextField gio;

    @FXML
    private Label hoTen;

    @FXML
    private ChoiceBox<String> loaiChamCong;

    @FXML
    private Label maNV;

    @FXML
    private DatePicker ngay;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Platform.runLater(() -> {
            maNV.setText(logChamCong.getNhanVien().getMaNhanVien());
            chucDanh.setText(logChamCong.getNhanVien().getChucDanh());
            donVI.setText(logChamCong.getNhanVien().getDonVi());
            hoTen.setText(logChamCong.getNhanVien().getHoTen());
            loaiChamCong.setItems(FXCollections.observableArrayList(
                    "CHECKIN","CHECKOUT"
            ));
            loaiChamCong.setValue(logChamCong.getLoaiChamCong());
            ngay.setValue(logChamCong.getDay());
            gio.setText(logChamCong.getGioVao().toString());
        });
    }
}
