package Controller;

import Model.*;
import java.io.IOException;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/cart")
public class CartController extends HttpServlet {

    private final CartDAO cartDAO = new CartDAO();
    private final ProductDetailDAO detailDAO = new ProductDetailDAO();

    @SuppressWarnings("unchecked")
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String action = req.getParameter("action");
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            resp.sendRedirect("login.jsp");
            return;
        }

        // FIX LỖI SESSION MAP: DÙNG RAW MAP + ÉP KIỂU
        Map<Integer, Integer> cart = (Map<Integer, Integer>) session.getAttribute("cart");
        if (cart == null) {
            cart = cartDAO.loadCart(user.getMaTaiKhoan());
            session.setAttribute("cart", cart);
        }

        if ("add".equals(action)) {
            addToCart(req, resp, session, cart, user);
        } 
        else if ("update".equals(action)) {
            updateCart(req, cart, user);
            session.setAttribute("cart", cart);
            resp.sendRedirect("cart.jsp");
        } 
        else if ("remove".equals(action)) {
            String idStr = req.getParameter("maChiTiet");
            if (idStr != null && !idStr.trim().isEmpty()) {
                int maChiTiet = Integer.parseInt(idStr.trim());
                cart.remove(maChiTiet);
                cartDAO.removeItem(user.getMaTaiKhoan(), maChiTiet);
            }
            session.setAttribute("cart", cart);
            resp.sendRedirect("cart.jsp");
        } 
        else {
            resp.sendRedirect("cart.jsp");
        }
    }

    private void addToCart(HttpServletRequest req, HttpServletResponse resp,
                           HttpSession session, Map<Integer, Integer> cart, User user) 
                           throws IOException {

        String maChiTietStr = req.getParameter("maChiTiet");

        // BẮT BUỘC CHỌN MÀU + SIZE
        if (maChiTietStr == null || maChiTietStr.trim().isEmpty()) {
            String id = req.getParameter("id");
            resp.sendRedirect("product_detail.jsp?id=" + (id != null ? id : "") + "&error=choose");
            return;
        }

        int maChiTiet;
        try {
            maChiTiet = Integer.parseInt(maChiTietStr.trim());
        } catch (NumberFormatException e) {
            resp.sendRedirect("cart.jsp?error=invalid");
            return;
        }

        int quantity = 1;
        try {
            String qtyStr = req.getParameter("quantity");
            if (qtyStr != null && !qtyStr.trim().isEmpty()) {
                quantity = Integer.parseInt(qtyStr.trim());
                if (quantity < 1) quantity = 1;
            }
        } catch (Exception ignored) {}

        // LẤY THÔNG TIN CHI TIẾT ĐỂ KIỂM TỒN KHO
        ProductDetail detail = detailDAO.getProductDetailByMaChiTiet(maChiTiet);
        if (detail == null || detail.getSoLuong() <= 0) {
            resp.sendRedirect("product_detail.jsp?id=" + detail.getMaSanPham() + "&error=outstock");
            return;
        }

        if (quantity > detail.getSoLuong()) {
            quantity = detail.getSoLuong(); // Không cho thêm quá tồn kho
        }

        // CỘNG DỒN SỐ LƯỢNG TRONG GIỎ
        int current = cart.getOrDefault(maChiTiet, 0);
        int newQty = current + quantity;

        if (newQty > detail.getSoLuong()) {
            newQty = detail.getSoLuong();
        }

        cart.put(maChiTiet, newQty);
        cartDAO.updateCart(user.getMaTaiKhoan(), maChiTiet, newQty);

        // FIX LỖI SESSION (ép kiểu an toàn)
        session.setAttribute("cart", (HashMap<Integer, Integer>) cart);

        resp.sendRedirect("cart.jsp?success=1");
    }

    private void updateCart(HttpServletRequest req, Map<Integer, Integer> cart, User user) {
        String[] ids = req.getParameterValues("maChiTiet");
        if (ids != null) {
            for (String idStr : ids) {
                int maChiTiet = Integer.parseInt(idStr);
                int qty;
                try {
                    qty = Integer.parseInt(req.getParameter("qty_" + maChiTiet));
                } catch (Exception e) {
                    continue;
                }

                ProductDetail d = detailDAO.getProductDetailByMaChiTiet(maChiTiet);
                if (qty <= 0 || d == null) {
                    cart.remove(maChiTiet);
                    cartDAO.removeItem(user.getMaTaiKhoan(), maChiTiet);
                } else if (qty <= d.getSoLuong()) {
                    cart.put(maChiTiet, qty);
                    cartDAO.updateCart(user.getMaTaiKhoan(), maChiTiet, qty);
                }
            }
        }
    }
}