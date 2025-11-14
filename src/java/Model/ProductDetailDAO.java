/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author garen
 */
public class ProductDetailDAO {
    // Lấy tất cả chi tiết của 1 sản phẩm (bạn đang dùng rồi)
    public List<ProductDetail> getProductDetailsByProductId(int productId) {
        List<ProductDetail> list = new ArrayList<>();
        String sql = "SELECT c.MaChiTiet, c.MaSanPham, c.MaMau, m.MauSac, " +
                     "c.MaSize, s.Size, c.SoLuong " +
                     "FROM ChiTietSanPham c " +
                     "JOIN Mau_SP m ON c.MaMau = m.MaMau " +
                     "JOIN Size_SP s ON c.MaSize = s.MaSize " +
                     "WHERE c.MaSanPham = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, productId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ProductDetail ct = new ProductDetail();
                ct.setMaChiTiet(rs.getInt("MaChiTiet"));
                ct.setMaSanPham(rs.getInt("MaSanPham"));
                ct.setMaMau(rs.getInt("MaMau"));
                ct.setMauSac(rs.getString("MauSac"));
                ct.setMaSize(rs.getInt("MaSize"));
                ct.setSize(rs.getString("Size"));
                ct.setSoLuong(rs.getInt("SoLuong"));
                list.add(ct);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // Method quan trọng nhất – dùng để kiểm tra tồn kho khi thêm giỏ
    public ProductDetail getProductDetailByMaChiTiet(int maChiTiet) {
        String sql = "SELECT c.MaChiTiet, c.MaSanPham, c.MaMau, m.MauSac, " +
                     "c.MaSize, s.Size, c.SoLuong " +
                     "FROM ChiTietSanPham c " +
                     "JOIN Mau_SP m ON c.MaMau = m.MaMau " +
                     "JOIN Size_SP s ON c.MaSize = s.MaSize " +
                     "WHERE c.MaChiTiet = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, maChiTiet);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ProductDetail pd = new ProductDetail();
                pd.setMaChiTiet(rs.getInt("MaChiTiet"));
                pd.setMaSanPham(rs.getInt("MaSanPham"));
                pd.setMaMau(rs.getInt("MaMau"));
                pd.setMauSac(rs.getString("MauSac"));
                pd.setMaSize(rs.getInt("MaSize"));
                pd.setSize(rs.getString("Size"));
                pd.setSoLuong(rs.getInt("SoLuong"));
                return pd;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
