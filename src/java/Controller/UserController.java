package Controller;

import Model.User;
import Model.UserDAO;
import Model.CartDAO;
import java.io.IOException;
import java.util.Map;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/user")
public class UserController extends HttpServlet {
    private UserDAO dao = new UserDAO();
    private CartDAO cartDAO = new CartDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");

        // ==================== ĐĂNG KÝ ====================
        if ("register".equals(action)) {
            String username = req.getParameter("username");
            String fullname = req.getParameter("fullname");
            String email = req.getParameter("email");
            String phone = req.getParameter("phone");
            String address = req.getParameter("address");
            String password = req.getParameter("password");
            String repassword = req.getParameter("repassword");

            // Validation cơ bản
            if (username == null || username.isEmpty() ||
                password == null || password.isEmpty() ||
                !password.equals(repassword)) {

                req.setAttribute("msg", "❌ Dữ liệu không hợp lệ hoặc mật khẩu không khớp!");
                req.getRequestDispatcher("register.jsp").forward(req, resp);
                return;
            }

            // Kiểm tra trùng tên đăng nhập
            if (dao.existsUsername(username)) {
                req.setAttribute("msg", "❌ Tên đăng nhập đã tồn tại!");
                req.getRequestDispatcher("register.jsp").forward(req, resp);
                return;
            }

            // Tạo đối tượng User
            User u = new User();
            u.setTenDangNhap(username);
            u.setMatKhau(password);
            u.setHoTen(fullname);
            u.setEmail(email);
            u.setSoDienThoai(phone);
            u.setDiaChi(address);
            u.setQuyen("user"); // mặc định quyền user

            boolean ok = dao.register(u);
            if (ok) {
                req.setAttribute("msg", " Đăng ký thành công! Vui lòng đăng nhập.");
                req.getRequestDispatcher("login.jsp").forward(req, resp);
            } else {
                req.setAttribute("msg", " Lỗi khi đăng ký!");
                req.getRequestDispatcher("register.jsp").forward(req, resp);
            }

        // ==================== ĐĂNG NHẬP ====================
        } else if ("login".equals(action)) {
            String username = req.getParameter("username");
            String password = req.getParameter("password");

            User u = dao.login(username, password);
            if (u != null) {
                HttpSession session = req.getSession();
                session.setAttribute("user", u);

                // Tải giỏ hàng của tài khoản này từ database
                Map<Integer, Integer> cart = cartDAO.loadCart(u.getMaTaiKhoan());
                session.setAttribute("cart", cart);

                resp.sendRedirect("product?action=list");
            } else {
                req.setAttribute("msg", " Sai tên đăng nhập hoặc mật khẩu!");
                req.getRequestDispatcher("login.jsp").forward(req, resp);
            }
        }
    }

    // ==================== ĐĂNG XUẤT ====================
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");

        if ("logout".equals(action)) {
            HttpSession session = req.getSession(false);
            if (session != null) {
                session.invalidate();
            }
            resp.sendRedirect("index.jsp");
        }
    }
}
