/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;
import java.sql.*;
public class ContactDAO {
    public boolean save(Contact c) {
        String sql = "INSERT INTO LienHe (HoTen, Email, SoDienThoai, ChuDe, NoiDung) VALUES (?,?,?,?,?)";
        try (Connection cn = DBConnection.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, c.getHoTen());
            ps.setString(2, c.getEmail());
            ps.setString(3, c.getSoDienThoai());
            ps.setString(4, c.getChuDe());
            ps.setString(5, c.getNoiDung());
            return ps.executeUpdate() > 0;
        } catch (Exception ex) { ex.printStackTrace(); return false; }
    }
}
