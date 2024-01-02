package com.btl_tkxdpm;

import static org.junit.jupiter.api.Assertions.*;

import com.btl_tkxdpm.entity.NhanVien;
import com.btl_tkxdpm.entity.NhanVienAttendance;
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
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class CsvExporterTest {
    static {
        // Khởi tạo JavaFX Toolkit
        Platform.startup(() -> {});
    }
    @Test
    public void testExportToCSV() {
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
        new NhanVienVanPhongThongKe("l1","abc","nv","5",5,2)
        );
        // Thêm dữ liệu vào TableView
        tableView.setItems(data);

        // Đặt đường dẫn tạm thời cho file CSV xuất
        String temporaryFilePath = "E:\\TKPM\\finalProject\\TKXDPM\\BTL_TKXDPM\\test_export.csv";

        // Thực hiện xuất CSV
        CsvExporter.exportToCSV(tableView, temporaryFilePath);

        // Đọc nội dung từ file CSV để kiểm tra
        try {
            Path path = Paths.get(temporaryFilePath);
            List<String> lines = Files.readAllLines(path);

            // Kiểm tra số dòng trong file
            assertEquals(data.size() + 1, lines.size()); // +1 để tính cả header

            // Kiểm tra dữ liệu từ file CSV có trùng khớp với dữ liệu trong TableView không
            for (int i = 0; i < data.size(); i++) {
                String[] columns = lines.get(i + 1).split(",");

                assertEquals(data.get(i).getMaNhanVien(), columns[0]);
                assertEquals(data.get(i).getHoTen(), columns[1]);
                assertEquals(String.valueOf(data.get(i).getSoBuoiDiLam()), columns[2]);
                assertEquals(String.valueOf(data.get(i).getSoGioDiMuonVeSom()), columns[3]);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
