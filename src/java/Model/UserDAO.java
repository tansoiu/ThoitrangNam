package Model;

import java.sql.*;

public class UserDAO {
   private Connection conn;

    public UserDAO() {
        conn = DBConnection.getConnection();
    }

    // ✅ Kiểm tra username tồn tại
    public boolean existsUsername(String username) {
        try {
            String sql = "SELECT 1 FROM TaiKhoan WHERE TenDangNhap = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // ✅ Đăng ký
    public boolean register(User u) {
        String sql = "INSERT INTO TaiKhoan (TenDangNhap, MatKhau, HoTen, Email, SoDienThoai, DiaChi, Quyen) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, u.getTenDangNhap());
            ps.setString(2, u.getMatKhau());
            ps.setString(3, u.getHoTen());
            ps.setString(4, u.getEmail());
            ps.setString(5, u.getSoDienThoai());
            ps.setString(6, u.getDiaChi());
            ps.setString(7, u.getQuyen());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // ✅ Đăng nhập
    public User login(String username, String password) {
    String sql = "SELECT * FROM TaiKhoan WHERE TenDangNhap = ? AND MatKhau = ?";
    try (Connection conn = DBConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, username);
        ps.setString(2, password);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            User u = new User();
            u.setMaTaiKhoan(rs.getInt("MaTaiKhoan"));
            u.setTenDangNhap(rs.getString("TenDangNhap"));
            u.setMatKhau(rs.getString("MatKhau"));
            u.setHoTen(rs.getString("HoTen"));
            u.setEmail(rs.getString("Email"));
            u.setSoDienThoai(rs.getString("SoDienThoai"));
            u.setDiaChi(rs.getString("DiaChi"));
            u.setQuyen(rs.getString("Quyen"));
            return u;
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
}

}
