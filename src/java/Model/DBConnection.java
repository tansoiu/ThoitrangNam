package Model;
import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    private static final String URL = "jdbc:sqlserver://ANHQUAN\\SQLEXPRESS01:1433;"
            + "databaseName=BanHang_ThoiTrangNam;"
            + "encrypt=true;"
            + "trustServerCertificate=true;";
    private static final String USER = ""; // tài khoản SQL của bạn
    private static final String PASSWORD = ""; // mật khẩu tương ứng

    public static Connection getConnection() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("✅ Kết nối SQL Server thành công!");
            return conn;
        } catch (Exception ex) {
            System.out.println("❌ Lỗi kết nối SQL Server:");
            ex.printStackTrace();
            return null;
        }
    }
}
