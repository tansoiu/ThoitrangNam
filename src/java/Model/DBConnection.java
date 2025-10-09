/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author garen
 */
public class DBConnection {
    private static final String URL = "jdbc:sqlserver://TANSOIU;databaseName=BanHang_ThoiTrangNam;encrypt=true;trustServerCertificate=true";

    public static Connection getConnection() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            return DriverManager.getConnection(URL, "sa", "a");
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
