package com.btl_tkxdpm;

import com.btl_tkxdpm.AttendanceDB.IAttendanceDB;
import com.btl_tkxdpm.entity.NhanVienVanPhongThongKe;
import com.btl_tkxdpm.export.BangChamCongCongNhan;
import com.btl_tkxdpm.statistic.StatisticController;
import javafx.application.Platform;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import com.btl_tkxdpm.entity.NhanVienVanPhongThongKe;
import com.btl_tkxdpm.export.CsvExporter;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class StatisticControllerTest {
    static {
        // Khởi tạo JavaFX Toolkit
        Platform.startup(() -> {});
    }
    @Test
    public void testSearch() {
        // Tạo một TableView giả lập và thêm dữ liệu giả lập
        TableView<NhanVienVanPhongThongKe> tableView = new TableView<>();
        TableColumn<NhanVienVanPhongThongKe, String> col1 = new TableColumn<>("Tên");
        TableColumn<NhanVienVanPhongThongKe, String> col2 = new TableColumn<>("Mã Nhân Viên");
        TableColumn<NhanVienVanPhongThongKe, String> col3 = new TableColumn<>("Tổng số buổi làm");
        TableColumn<NhanVienVanPhongThongKe, String> col4 = new TableColumn<>("Tổng số giờ đi muộn/về sớm");
        col1.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMaNhanVien()));
        col2.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getHoTen()));
        col3.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getSoBuoiDiLam())));
        col4.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getSoGioDiMuonVeSom())));


        // Thêm các cột vào TableView
        tableView.getColumns().addAll(col1, col2,col3,col4);

        // Tạo dữ liệu giả lập
        ObservableList<NhanVienVanPhongThongKe> data = FXCollections.observableArrayList(
                new NhanVienVanPhongThongKe("l1","abc","nv","5",5,2),
                new NhanVienVanPhongThongKe("l4","xyz","nv","5",5,2),

                new NhanVienVanPhongThongKe("l6","xyz","nv","6",5,2)
        );
        // Thêm dữ liệu vào TableView
        tableView.setItems(data);
       List<String> name=StatisticController.trySearchName(data,"5");
       for (int i=0;i<name.size();i++){
           assertEquals(name.get(i),data.get(i).getHoTen());
       }
    }
}