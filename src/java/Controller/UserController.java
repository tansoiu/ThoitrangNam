/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;


import Model.User;
import Model.UserDAO;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/user")
public class UserController extends HttpServlet {
    private UserDAO dao = new UserDAO();

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        if ("register".equals(action)) {
            String username = req.getParameter("username");
            String fullname = req.getParameter("fullname");
            String email = req.getParameter("email");
            String phone = req.getParameter("phone");
            String address = req.getParameter("address");
            String password = req.getParameter("password");
            String repassword = req.getParameter("repassword");
            // Basic validation
            if (username==null || password==null || !password.equals(repassword)) {
                req.setAttribute("msg", "Dữ liệu không hợp lệ hoặc mật khẩu không khớp");
                req.getRequestDispatcher("register.jsp").forward(req, resp);
                return;
            }
            User u = new User();
            u.setTenDangNhap(username);
            u.setMatKhau(password); // TODO: hash trước khi lưu
            u.setHoTen(fullname);
            u.setEmail(email);
            u.setSoDienThoai(phone);
            u.setDiaChi(address);
            boolean ok = dao.register(u);
            if (ok) {
                resp.sendRedirect("login.jsp");
            } else {
                req.setAttribute("msg", "Lỗi khi đăng ký");
                req.getRequestDispatcher("register.jsp").forward(req, resp);
            }
        } else if ("login".equals(action)) {
            String username = req.getParameter("username");
            String password = req.getParameter("password");
            User u = dao.login(username, password);
            if (u != null) {
                HttpSession session = req.getSession();
                session.setAttribute("user", u);
                resp.sendRedirect("product?action=list");
            } else {
                req.setAttribute("msg", "Đăng nhập thất bại");
                req.getRequestDispatcher("login.jsp").forward(req, resp);
            }
        }
    }
}
