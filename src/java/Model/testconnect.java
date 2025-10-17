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
public class testconnect {
    public static void main(String[] args) {
        Connection conn = null;
        try {
            // Nạp driver SQL Server
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            // Chuỗi kết nối
            String url = "jdbc:sqlserver://TANSOIU;databaseName=BanHang_ThoiTrangNam;user=sa;password=a;encrypt=false;";

            // Kết nối
            conn = DriverManager.getConnection(url);

            if (conn != null) {
                System.out.println("✅ Kết nối SQL Server thành công!");
            } else {
                System.out.println("❌ Kết nối thất bại!");
            }

        } catch (Exception e) {
            System.out.println("❌ Lỗi khi kết nối: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
