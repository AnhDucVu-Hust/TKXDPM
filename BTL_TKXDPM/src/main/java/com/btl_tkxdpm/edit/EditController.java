package com.btl_tkxdpm.edit;

import com.btl_tkxdpm.AttendanceDB.IAttendanceDB;
import com.btl_tkxdpm.SwitchScreener;
import com.btl_tkxdpm.entity.NhanVienAttendance;
import com.btl_tkxdpm.export.ExportController;
import com.btl_tkxdpm.home.HomeController;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class EditController implements Initializable {
    public IAttendanceDB getAttendanceDB() {
        return attendanceDB;
    }

    public void setAttendanceDB(IAttendanceDB attendanceDB) {
        this.attendanceDB = attendanceDB;
    }

    private IAttendanceDB attendanceDB;
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
    void clickXacNhan(MouseEvent event){
        LocalDate ngayXacNhan = ngay.getValue();
        LocalTime gioXacNhan = LocalTime.parse(gio.getText().toString());
        String loaiChamCongXacNhan = loaiChamCong.getValue();
        logChamCong.setLoaiChamCong(loaiChamCongXacNhan);
        logChamCong.setGioVao(gioXacNhan);
        logChamCong.setDay(ngayXacNhan);
        attendanceDB.editAttendance(logChamCong);
        try {
            FXMLLoader loader = new FXMLLoader(SwitchScreener.class.getResource("/com/btl_tkxdpm/manHinhChinh.fxml"));
            Parent root = loader.load();
            HomeController controller = loader.getController();
            controller.setAttendanceDB(attendanceDB);
            Scene scene = new Scene(root);
            SwitchScreener.primaryStage.setScene(scene);
            SwitchScreener.primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    @FXML
    void clickQuayLai(MouseEvent event){
        SwitchScreener.switchScreen("/com/btl_tkxdpm/manHinhChinh.fxml");
    }


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
