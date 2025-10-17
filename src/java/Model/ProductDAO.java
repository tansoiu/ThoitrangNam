/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    public List<Product> getAllProducts() {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM SanPham";
        try (Connection cn = DBConnection.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Product p = new Product();
                p.setMaSanPham(rs.getInt("MaSanPham"));
                p.setSku(rs.getString("SKU"));
                p.setTenSanPham(rs.getString("TenSanPham"));
                p.setMoTa(rs.getString("MoTa"));
                p.setGia(rs.getDouble("Gia"));
                p.setGiaGiam((rs.getObject("GiaGiam")!=null)?rs.getDouble("GiaGiam"):null);
                p.setSoLuong(rs.getInt("SoLuong"));
                p.setAnh(rs.getString("Anh"));
                p.setDanhMuc(rs.getString("DanhMuc"));
                list.add(p);
            }
        } catch (Exception ex) { ex.printStackTrace(); }
        return list;
    }

    public Product getProductById(int id) {
        String sql = "SELECT * FROM SanPham WHERE MaSanPham = ?";
        try (Connection cn = DBConnection.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Product p = new Product();
                    p.setMaSanPham(rs.getInt("MaSanPham"));
                    p.setSku(rs.getString("SKU"));
                    p.setTenSanPham(rs.getString("TenSanPham"));
                    p.setMoTa(rs.getString("MoTa"));
                    p.setGia(rs.getDouble("Gia"));
                    p.setGiaGiam((rs.getObject("GiaGiam")!=null)?rs.getDouble("GiaGiam"):null);
                    p.setSoLuong(rs.getInt("SoLuong"));
                    p.setAnh(rs.getString("Anh"));
                    p.setDanhMuc(rs.getString("DanhMuc"));
                    return p;
                }
            }
        } catch (Exception ex) { ex.printStackTrace(); }
        return null;
    }
    public List<Product> getProductsByCategory(String category) {
    List<Product> list = new ArrayList<>();
    try {
        Connection cn = DBConnection.getConnection();
        String sql = "SELECT * FROM SanPham WHERE DanhMuc = ?";
        PreparedStatement ps = cn.prepareStatement(sql);
        ps.setString(1, category);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Product p = new Product();
            p.setMaSanPham(rs.getInt("MaSanPham"));
            p.setSku(rs.getString("SKU"));
            p.setTenSanPham(rs.getString("TenSanPham"));
            p.setMoTa(rs.getString("MoTa"));
            p.setGia(rs.getDouble("Gia"));
            Object giam = rs.getObject("GiaGiam");
            p.setGiaGiam(giam != null ? rs.getDouble("GiaGiam") : null);
            p.setSoLuong(rs.getInt("SoLuong"));
            p.setAnh(rs.getString("Anh"));
            p.setDanhMuc(rs.getString("DanhMuc"));
            list.add(p);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
    }
    public List<Product> searchProductsByName(String keyword) {
    List<Product> list = new ArrayList<>();
    try {
        Connection cn = DBConnection.getConnection();
        String sql = "SELECT * FROM SanPham WHERE TenSanPham LIKE ?";
        PreparedStatement ps = cn.prepareStatement(sql);
        ps.setString(1, "%" + keyword + "%");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Product p = new Product();
            p.setMaSanPham(rs.getInt("MaSanPham"));
            p.setSku(rs.getString("SKU"));
            p.setTenSanPham(rs.getString("TenSanPham"));
            p.setMoTa(rs.getString("MoTa"));
            p.setGia(rs.getDouble("Gia"));
            Object giam = rs.getObject("GiaGiam");
            p.setGiaGiam(giam != null ? rs.getDouble("GiaGiam") : null);
            p.setSoLuong(rs.getInt("SoLuong"));
            p.setAnh(rs.getString("Anh"));
            p.setDanhMuc(rs.getString("DanhMuc"));
            list.add(p);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}

}
