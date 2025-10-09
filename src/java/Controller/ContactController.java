package Controller;

import Model.Contact;
import Model.ContactDAO;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/contact")
public class ContactController extends HttpServlet {
    private ContactDAO dao = new ContactDAO();

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        Contact c = new Contact();
        c.setHoTen(req.getParameter("name"));
        c.setEmail(req.getParameter("email"));
        c.setSoDienThoai(req.getParameter("phone"));
        c.setChuDe(req.getParameter("subject"));
        c.setNoiDung(req.getParameter("message"));

        // Basic validation
        if (c.getHoTen() == null || c.getEmail() == null || c.getNoiDung() == null || c.getHoTen().trim().isEmpty()) {
            req.setAttribute("msg", "Vui lòng điền đầy đủ thông tin");
            req.getRequestDispatcher("contact.jsp").forward(req, resp);
            return;
        }

        boolean ok = dao.save(c);
        if (ok) {
            req.setAttribute("msg", "Gửi liên hệ thành công");
        } else {
            req.setAttribute("msg", "Gửi liên hệ thất bại");
        }
        req.getRequestDispatcher("contact.jsp").forward(req, resp);
    }
}
