package com.btl_tkxdpm.export;

import com.btl_tkxdpm.entity.NhanVien;
import com.btl_tkxdpm.entity.NhanVienAttendance;
import javafx.collections.ObservableList;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelExporter {
    public static void exportToExcel(ObservableList<NhanVienAttendance> attendanceList, String outputPath) {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("DanhSachNhanVien");

            // Tạo hàng tiêu đề
            Row headerRow = sheet.createRow(0);
            String[] columns = {"Tên,Mã Nhân Viên,Ngày,Giờ ra, Giờ vào, Chức danh"};

            for (int i = 0; i < columns.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(columns[i]);
            }

            // Đổ dữ liệu vào bảng
            int rowNum = 1;

            for (NhanVienAttendance nhanVienAttendance : attendanceList) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(nhanVienAttendance.getNhanVien().getHoTen());
                row.createCell(1).setCellValue(nhanVienAttendance.getNhanVien().getMaNhanVien());
                row.createCell(2).setCellValue(nhanVienAttendance.getDay().toString());
                row.createCell(3).setCellValue(nhanVienAttendance.getGioRa().toString());
                row.createCell(5).setCellValue(nhanVienAttendance.getGioVao().toString());
                row.createCell(6).setCellValue(nhanVienAttendance.getNhanVien().getChucDanh());
            }

            // Ghi workbook ra file
            try (FileOutputStream outputStream = new FileOutputStream(outputPath)) {
                workbook.write(outputStream);
            }

            System.out.println("Xuất Excel thành công!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
