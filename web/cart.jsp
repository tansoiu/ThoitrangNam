<%@ page import="java.util.*, Model.Product, Model.ProductDAO, Model.ProductDetailDAO, Model.ProductDetail" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="include/header.jsp" %>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css?v=3">

<div style="padding:20px;">
  <h2 class="title">Giỏ hàng</h2>

  <%
    Map<Integer, Integer> cart = (Map<Integer, Integer>) session.getAttribute("cart");
    if (cart == null || cart.isEmpty()) {
  %>
    <p>Giỏ hàng trống. <a href="index.jsp">Tiếp tục mua sắm</a></p>
  <%
    } else {
      ProductDAO productDAO = new ProductDAO();
      ProductDetailDAO detailDAO = new ProductDetailDAO();
      double total = 0;
  %>
    <form action="cart" method="post">
      <input type="hidden" name="action" value="update"/>
      <table class="cart-table">
        <tr>
          <th>Ảnh</th>
          <th>Sản phẩm</th>
          <th>Biến thể</th>
          <th>Giá</th>
          <th>Số lượng</th>
          <th>Thành tiền</th>
          <th>Hành động</th>
        </tr>

        <%
          for (Map.Entry<Integer, Integer> entry : cart.entrySet()) {
            int maChiTiet = entry.getKey();
            int qty = entry.getValue();

            // Lấy thông tin chi tiết (màu + size + tồn kho)
            ProductDetail detail = detailDAO.getProductDetailByMaChiTiet(maChiTiet);
            if (detail == null) continue;

            // Lấy thông tin sản phẩm chính
            Product p = productDAO.getProductById(detail.getMaSanPham());
            if (p == null) continue;

            double lineTotal = p.getGia() * qty;
            total += lineTotal;
        %>
        <tr>
          <!-- Ảnh -->
          <td><img src="images/<%= p.getAnh() %>" width="80" height="80" style="object-fit:cover;border-radius:8px;"/></td>

          <!-- Tên sản phẩm -->
          <td><strong><%= p.getTenSanPham() %></strong></td>

          <!-- Màu + Size (mới thêm) -->
          <td style="color:#1976d2; font-weight:bold;">
            <%= detail.getMauSac() %> - <%= detail.getSize() %>
          </td>

          <!-- Giá -->
          <td><%= String.format("%,.0f", p.getGia()) %> ₫</td>

          <!-- Số lượng -->
          <td>
            <input type="hidden" name="maChiTiet" value="<%= maChiTiet %>"/>
            <input type="number"
                   name="qty_<%= maChiTiet %>"
                   value="<%= qty %>"
                   min="1"
                   max="<%= detail.getSoLuong() %>"
                   style="width:60px;padding:5px;text-align:center;"
                   <%= qty > detail.getSoLuong() ? "style='border:2px solid red;'" : "" %> />
            <% if (qty > detail.getSoLuong()) { %>
              <br><small style="color:red;">Chỉ còn <%= detail.getSoLuong() %> sản phẩm</small>
            <% } %>
          </td>

          <!-- Thành tiền -->
          <td><strong><%= String.format("%,.0f", lineTotal) %> ₫</strong></td>

          <!-- Xóa -->
          <td>
            <a href="cart?action=remove&maChiTiet=<%= maChiTiet %>"
               style="color:red;"
               onclick="return confirm('Xóa sản phẩm này khỏi giỏ hàng?')">
              Xóa
            </a>
          </td>
        </tr>
        <% } %>
      </table>

      <div class="cart-summary" style="font-size:20px; font-weight:bold; color:#d32f2f; text-align:right; margin:20px 0;">
        Tổng cộng: <%= String.format("%,.0f", total) %> ₫
      </div>

      <div class="cart-buttons" style="text-align:center;">
        <button type="submit" style="padding:12px 30px; background:#1976d2; color:white; border:none; border-radius:6px; font-size:16px; cursor:pointer;">
          Cập nhật giỏ hàng
        </button>
        &nbsp;&nbsp;
        <a href="checkout.jsp" 
           style="padding:12px 30px; background:#d32f2f; color:white; text-decoration:none; border-radius:6px; font-size:16px;">
          Thanh toán
        </a>
      </div>
    </form>

    <div style="text-align:center; margin-top:20px;">
      <a href="index.jsp">← Tiếp tục mua sắm</a>
    </div>
  <% } %>
</div>

<%@ include file="include/footer.jsp" %>