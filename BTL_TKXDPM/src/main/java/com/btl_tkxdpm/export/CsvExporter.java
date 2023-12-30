package com.btl_tkxdpm.export;

import com.btl_tkxdpm.entity.NhanVienAttendance;
import javafx.collections.ObservableList;
import java.nio.file.*;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

public class CsvExporter {
    public static void exportToCsv(ObservableList<NhanVienAttendance> NhanVienAttendanceAttendanceList, String outputPath) {

        Path path = Paths.get(outputPath);

        if (!Files.exists(path)) {
            try {
                // Create the directory
                Files.createFile(path);
                System.out.println("Directory created successfully");
            } catch (Exception e) {
                System.err.println("Failed to create directory: " + e.getMessage());
            }
        } else {
            System.out.println("Directory already exists");
        }
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputPath),"UTF-8"))) {
            // Ghi tiêu đề
            writer.write("Tên,Mã Nhân Viên,Ngày,Giờ ra, Giờ vào, Chức danh");
            writer.newLine();

            // Ghi dữ liệu
            for (NhanVienAttendance nhanVienAttendance : NhanVienAttendanceAttendanceList) {
                writer.write(String.format("%s,%s,%s,%s,%s,%s",
                        nhanVienAttendance.getNhanVien().getHoTen(),
                        nhanVienAttendance.getNhanVien().getMaNhanVien(),
                        nhanVienAttendance.getDay().toString(),
                        nhanVienAttendance.getLoaiChamCong().toString(),
                        nhanVienAttendance.getGioVao().toString(),
                        nhanVienAttendance.getNhanVien().getChucDanh()));
                writer.newLine();
            }
            System.out.println("Xuất CSV thành công!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
