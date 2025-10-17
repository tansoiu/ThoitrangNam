<%@ page import="java.util.*, Model.Product, Model.ProductDAO" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="include/header.jsp" %>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
<div style="padding:20px;">
  <h2 class="title">Giỏ hàng</h2>
  <%
    Map<Integer,Integer> cart = (Map<Integer,Integer>) session.getAttribute("cart");
    if (cart == null || cart.isEmpty()) {
  %>
    <p>Giỏ hàng trống</p>
  <%
    } else {
      ProductDAO dao = new ProductDAO();
      double total = 0;
  %>
    <form action="cart" method="post">
  <input type="hidden" name="action" value="update"/>
  <table class="cart-table">
    <tr>
      <th>Ảnh</th>
      <th>Tên</th>
      <th>Giá</th>
      <th>Số lượng</th>
      <th>Thành tiền</th>
      <th>Hành động</th>
    </tr>
    <% for (Map.Entry<Integer,Integer> e : cart.entrySet()) {
         int pid = e.getKey();
         int qty = e.getValue();
         Product p = dao.getProductById(pid);
         double line = p.getGia() * qty;
         total += line;
    %>
    <tr>
      <td><img src="images/<%=p.getAnh()%>" width="80" height="80"/></td>
      <td><%=p.getTenSanPham()%></td>
      <td><%= String.format("%,.0f", p.getGia()) %> đ</td>
      <td>
        <input type="hidden" name="productId" value="<%=p.getMaSanPham()%>"/>
        <input type="number" name="qty_<%=p.getMaSanPham()%>" value="<%=qty%>" min="0"/>
      </td>
      <td><%= String.format("%,.0f", line) %> đ</td>
      <td><a href="cart?action=remove&productID=<%=p.getMaSanPham()%>">Xóa</a></td>
    </tr>
    <% } %>
  </table>

  <div class="cart-summary">
    Tổng cộng: <%= String.format("%,.0f", total) %> đ
  </div>

  <div class="cart-buttons">
    <button type="submit">Cập nhật giỏ hàng</button>
  </div>
</form>
  <% } %>
</div>
<%@ include file="include/footer.jsp" %>
