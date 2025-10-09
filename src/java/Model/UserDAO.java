/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;
import java.sql.*;

public class UserDAO {
    public boolean register(User u) {
        String sql = "INSERT INTO TaiKhoan (TenDangNhap, MatKhau, HoTen, Email, SoDienThoai, DiaChi) VALUES (?,?,?,?,?,?)";
        try (Connection cn = DBConnection.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, u.getTenDangNhap());
            ps.setString(2, u.getMatKhau()); // TODO: hash password
            ps.setString(3, u.getHoTen());
            ps.setString(4, u.getEmail());
            ps.setString(5, u.getSoDienThoai());
            ps.setString(6, u.getDiaChi());
            return ps.executeUpdate() > 0;
        } catch (Exception ex) { ex.printStackTrace(); return false; }
    }

    public User login(String username, String password) {
        String sql = "SELECT * FROM TaiKhoan WHERE TenDangNhap = ? AND MatKhau = ?";
        try (Connection cn = DBConnection.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, password);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    User u = new User();
                    u.setMaTaiKhoan(rs.getInt("MaTaiKhoan"));
                    u.setTenDangNhap(rs.getString("TenDangNhap"));
                    u.setHoTen(rs.getString("HoTen"));
                    u.setEmail(rs.getString("Email"));
                    u.setSoDienThoai(rs.getString("SoDienThoai"));
                    u.setDiaChi(rs.getString("DiaChi"));
                    return u;
                }
            }
        } catch (Exception ex) { ex.printStackTrace(); }
        return null;
    }
}
