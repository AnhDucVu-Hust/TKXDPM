package com.btl_tkxdpm.export;

import com.btl_tkxdpm.entity.NhanVien;
import com.btl_tkxdpm.entity.NhanVienAttendance;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelExporter {

    public static <T> void exportToExcel(TableView<T> tableView, String filePath) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Data");

        // Create header row
        Row headerRow = sheet.createRow(0);
        ObservableList<TableColumn<T, ?>> columns = tableView.getColumns();

        for (int i = 0; i < columns.size(); i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns.get(i).getText());
        }

        // Create data rows
        ObservableList<T> items = tableView.getItems();
        System.out.println(items.size());
        for (int i = 0; i < items.size(); i++) {
            Row dataRow = sheet.createRow(i + 1);
            T item = items.get(i);

            for (int j = 0; j < columns.size(); j++) {
                Cell cell = dataRow.createCell(j);
                TableColumn<T, ?> column = columns.get(j);
                cell.setCellValue(String.valueOf(column.getCellData(item)));
            }
        }

        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
            workbook.write(fileOut);
            System.out.println("Exported successfully to: " + filePath);
            Alert alert1 = new Alert(Alert.AlertType.WARNING);
            alert1.setTitle("Xuất báo cáo ");
            alert1.setHeaderText("Thành công");
            alert1.setContentText("Đã xuất báo cáo thành công ");
            // Show the alert
            alert1.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
