package Controller;

import Model.ProductDAO;
import Model.Product;
import java.io.IOException;
import java.util.List;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/product")
public class ProductController extends HttpServlet {
    private ProductDAO dao = new ProductDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "list":  // Hiển thị toàn bộ sản phẩm
                listProducts(req, resp);
                break;

            case "detail": // Xem chi tiết sản phẩm
                showDetail(req, resp);
                break;

            case "filter": // Lọc theo danh mục
                filterByCategory(req, resp);
                break;
                
            case "search":
            searchProducts(req, resp);
            break;

            default:
                listProducts(req, resp);
                break;
        }
    }

    private void listProducts(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<Product> list = dao.getAllProducts();
        req.setAttribute("products", list);
        RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
        rd.forward(req, resp);
    }

    private void showDetail(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Product p = dao.getProductById(id);
        req.setAttribute("product", p);
        RequestDispatcher rd = req.getRequestDispatcher("product_detail.jsp");
        rd.forward(req, resp);
    }

    private void searchProducts(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
    String keyword = req.getParameter("keyword");
    List<Product> list = dao.searchProductsByName(keyword);
    req.setAttribute("products", list);
    req.setAttribute("searchKeyword", keyword);
    RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
    rd.forward(req, resp);
}

    private void filterByCategory(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String category = req.getParameter("category");
        List<Product> list = dao.getProductsByCategory(category);
        req.setAttribute("products", list);
        req.setAttribute("selectedCategory", category); // dùng để hiển thị danh mục đang chọn nếu cần
        RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doGet(req, resp);
    }
}
