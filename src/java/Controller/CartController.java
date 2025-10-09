package Controller;

import Model.Product;
import Model.ProductDAO;

import java.io.IOException;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/cart")
public class CartController extends HttpServlet {
    private ProductDAO dao = new ProductDAO();

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        HttpSession session = req.getSession();
        Map<Integer, Integer> cart = (Map<Integer, Integer>) session.getAttribute("cart");
        if (cart == null) {
            cart = new HashMap<>();
        }

        if ("add".equals(action)) {
            int pid = Integer.parseInt(req.getParameter("productID"));
            int qty = Integer.parseInt(req.getParameter("quantity"));
            cart.put(pid, cart.getOrDefault(pid,0) + qty);
            session.setAttribute("cart", cart);
            resp.sendRedirect("cart.jsp");
            return;
        } else if ("update".equals(action)) {
            // expect params like qty_pid
            String[] keys = req.getParameterValues("productId");
            if (keys != null) {
                for (String k : keys) {
                    int pid = Integer.parseInt(k);
                    int q = Integer.parseInt(req.getParameter("qty_" + k));
                    if (q <= 0) cart.remove(pid);
                    else cart.put(pid, q);
                }
                session.setAttribute("cart", cart);
            }
            resp.sendRedirect("cart.jsp");
            return;
        } else if ("remove".equals(action)) {
            int pid = Integer.parseInt(req.getParameter("productID"));
            cart.remove(pid);
            session.setAttribute("cart", cart);
            resp.sendRedirect("cart.jsp");
            return;
        }
    }
}
