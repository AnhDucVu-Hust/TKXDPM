package com.btl_tkxdpm.export;

import com.btl_tkxdpm.entity.NhanVienAttendance;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.*;
import java.nio.file.*;
import java.util.List;

public class CsvExporter {

    public static <T> void exportToCSV(TableView<T> tableView, String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            // Write header
            ObservableList<TableColumn<T, ?>> columns;
            columns = tableView.getColumns();
            for (int i = 0; i < columns.size(); i++) {
                writer.append(columns.get(i).getText());
                if (i < columns.size() - 1) {
                    writer.append(',');
                }
            }
            writer.append('\n');

            // Write data
            ObservableList<T> items = tableView.getItems();
            for (T item : items) {
                for (int j = 0; j < columns.size(); j++) {
                    TableColumn<T, ?> column = columns.get(j);
                    writer.append(String.valueOf(column.getCellData(item)));
                    if (j < columns.size() - 1) {
                        writer.append(',');
                    }
                }
                writer.append('\n');
            }

            System.out.println("Exported successfully to: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
}
}
