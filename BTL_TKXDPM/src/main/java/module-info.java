module com.example.btl_tkxdpm {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires org.apache.poi.poi;
    requires org.apache.poi.ooxml;

    opens com.btl_tkxdpm to javafx.fxml;
    exports com.btl_tkxdpm;

    opens com.btl_tkxdpm.home to javafx.fxml;
    exports com.btl_tkxdpm.home;

    opens com.btl_tkxdpm.export to javafx.fxml;
    exports com.btl_tkxdpm.export;

    opens com.btl_tkxdpm.employee to javafx.fxml;
    exports  com.btl_tkxdpm.employee;

    opens com.btl_tkxdpm.edit to javafx.fxml;
    exports com.btl_tkxdpm.edit;

    opens  com.btl_tkxdpm.statistic to javafx.fxml;
    exports  com.btl_tkxdpm.statistic;
    exports com.btl_tkxdpm.importdata;
    opens com.btl_tkxdpm.importdata to javafx.fxml;
}