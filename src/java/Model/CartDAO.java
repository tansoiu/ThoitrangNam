package Model;

import java.sql.*;
import java.util.*;

public class CartDAO {
    private Connection conn;

    public CartDAO() {
        conn = DBConnection.getConnection();
    }
    
    //  Lấy MaGioHang của user, nếu chưa có thì tạo mới
    public int getOrCreateCartID(int maTaiKhoan) {
        int maGioHang = -1;
        try {
            String sql = "SELECT MaGioHang FROM GioHang WHERE MaTaiKhoan = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, maTaiKhoan);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                maGioHang = rs.getInt("MaGioHang");
            } else {
                String insert = "INSERT INTO GioHang (MaTaiKhoan, NgayTao) VALUES (?, GETDATE())";
                PreparedStatement ps2 = conn.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
                ps2.setInt(1, maTaiKhoan);
                ps2.executeUpdate();
                ResultSet rs2 = ps2.getGeneratedKeys();
                if (rs2.next()) maGioHang = rs2.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return maGioHang;
    }

    // ✅ Lấy toàn bộ giỏ hàng (ProductID -> quantity)
    public Map<Integer, Integer> loadCart(int maTaiKhoan) {
        Map<Integer, Integer> cart = new HashMap<>();
        String sql = """
            SELECT cth.MaSanPham, cth.SoLuong 
            FROM ChiTietGioHang cth
            JOIN GioHang gh ON gh.MaGioHang = cth.MaGioHang
            WHERE gh.MaTaiKhoan = ?
        """;
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maTaiKhoan);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                cart.put(rs.getInt("MaSanPham"), rs.getInt("SoLuong"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cart;
    }

    // ✅ Thêm hoặc cập nhật số lượng sản phẩm trong giỏ
    public void updateCart(int maTaiKhoan, int maSanPham, int soLuong) {
        int maGioHang = getOrCreateCartID(maTaiKhoan);
        try {
            String check = "SELECT * FROM ChiTietGioHang WHERE MaGioHang = ? AND MaSanPham = ?";
            PreparedStatement ps = conn.prepareStatement(check);
            ps.setInt(1, maGioHang);
            ps.setInt(2, maSanPham);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String update = "UPDATE ChiTietGioHang SET SoLuong = ? WHERE MaGioHang = ? AND MaSanPham = ?";
                PreparedStatement ps2 = conn.prepareStatement(update);
                ps2.setInt(1, soLuong);
                ps2.setInt(2, maGioHang);
                ps2.setInt(3, maSanPham);
                ps2.executeUpdate();
            } else {
                String insert = "INSERT INTO ChiTietGioHang (MaGioHang, MaSanPham, SoLuong) VALUES (?, ?, ?)";
                PreparedStatement ps3 = conn.prepareStatement(insert);
                ps3.setInt(1, maGioHang);
                ps3.setInt(2, maSanPham);
                ps3.setInt(3, soLuong);
                ps3.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Xóa sản phẩm trong giỏ
    public void removeItem(int maTaiKhoan, int maSanPham) {
        int maGioHang = getOrCreateCartID(maTaiKhoan);
        String sql = "DELETE FROM ChiTietGioHang WHERE MaGioHang = ? AND MaSanPham = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maGioHang);
            ps.setInt(2, maSanPham);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void clearCart(int maTaiKhoan) {
    String sql = "DELETE FROM GioHang WHERE MaTaiKhoan = ?";
    try (Connection con = DBConnection.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {
        
        ps.setInt(1, maTaiKhoan);
        ps.executeUpdate();
        
    } catch (Exception e) {
        e.printStackTrace();
    }
}
}
