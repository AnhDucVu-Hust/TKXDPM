package com.btl_tkxdpm;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DBConnection {
    Connection conn = null;

    public static Connection conDB() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/TimeKeeping", "root", "922002");
            return con;
        } catch (ClassNotFoundException | SQLException ex) {
            System.err.println("Exception : " + ex.getMessage());
            return null;
        }
    }
}
