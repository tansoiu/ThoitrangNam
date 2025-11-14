package Controller;

import Model.*;
import java.io.IOException;
import java.sql.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/order")
public class OrderController extends HttpServlet {

    private final ProductDetailDAO detailDAO = new ProductDetailDAO();

    @SuppressWarnings("unchecked")
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            resp.sendRedirect("login.jsp");
            return;
        }

        Map<Integer, Integer> cart = (Map<Integer, Integer>) session.getAttribute("cart");
        if (cart == null || cart.isEmpty()) {
            resp.sendRedirect("cart.jsp");
            return;
        }

        String hoTen = req.getParameter("hoTen");
        String sdt = req.getParameter("sdt");
        String diaChi = req.getParameter("diaChi");
        String ghiChu = req.getParameter("ghiChu");
        if (ghiChu == null) ghiChu = "";

        // Tính tổng tiền
        double tongTien = 0;
        ProductDAO pDAO = new ProductDAO();
        for (Map.Entry<Integer, Integer> e : cart.entrySet()) {
            ProductDetail d = detailDAO.getProductDetailByMaChiTiet(e.getKey());
            if (d != null) {
                Product p = pDAO.getProductById(d.getMaSanPham());
                if (p != null) tongTien += p.getGia() * e.getValue();
            }
        }

        Connection con = null;
        try {
            con = DBConnection.getConnection();
            con.setAutoCommit(false);

            // 1. Thêm vào bảng DonHang
            String sqlDH = "INSERT INTO DonHang (MaTaiKhoan, HoTenNhan, SdtNhan, DiaChiNhan, GhiChu, TongTien) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement psDH = con.prepareStatement(sqlDH, PreparedStatement.RETURN_GENERATED_KEYS);
            psDH.setInt(1, user.getMaTaiKhoan());
            psDH.setString(2, hoTen);
            psDH.setString(3, sdt);
            psDH.setString(4, diaChi);
            psDH.setString(5, ghiChu);
            psDH.setDouble(6, tongTien);
            psDH.executeUpdate();

            int maDonHang = 0;
            ResultSet rs = psDH.getGeneratedKeys();
            if (rs.next()) maDonHang = rs.getInt(1);

            // 2. Thêm chi tiết + trừ tồn kho
            String sqlCT = "INSERT INTO ChiTietDonHang (MaDonHang, MaChiTiet, SoLuong, DonGia) VALUES (?, ?, ?, ?)";
            String sqlTruKho = "UPDATE ChiTietSanPham SET SoLuong = SoLuong - ? WHERE MaChiTiet = ? AND SoLuong >= ?";

            PreparedStatement psCT = con.prepareStatement(sqlCT);
            PreparedStatement psKho = con.prepareStatement(sqlTruKho);

            for (Map.Entry<Integer, Integer> e : cart.entrySet()) {
                int maChiTiet = e.getKey();
                int sl = e.getValue();

                ProductDetail detail = detailDAO.getProductDetailByMaChiTiet(maChiTiet);
                if (detail == null || detail.getSoLuong() < sl) {
                    con.rollback();
                    resp.sendRedirect("checkout.jsp?error=outstock");
                    return;
                }

                Product p = pDAO.getProductById(detail.getMaSanPham());
                double donGia = p != null ? p.getGia() : 0;

                // Thêm chi tiết đơn hàng
                psCT.setInt(1, maDonHang);
                psCT.setInt(2, maChiTiet);
                psCT.setInt(3, sl);
                psCT.setDouble(4, donGia);
                psCT.addBatch();

                // Trừ tồn kho
                psKho.setInt(1, sl);
                psKho.setInt(2, maChiTiet);
                psKho.setInt(3, sl);
                psKho.addBatch();
            }

            psCT.executeBatch();
            psKho.executeBatch();

            con.commit();

            // Xóa giỏ hàng
            cart.clear();
            session.removeAttribute("cart");
            new CartDAO().clearCart(user.getMaTaiKhoan()); // xóa cả trong DB

            resp.sendRedirect("order_success.jsp");

        } catch (Exception e) {
            e.printStackTrace();
            try { if (con != null) con.rollback(); } catch (Exception ex) {}
            resp.sendRedirect("checkout.jsp?error=1");
        }
    }
}