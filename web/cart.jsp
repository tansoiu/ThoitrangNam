<%@ page import="java.util.*, Model.Product, Model.ProductDAO" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="include/header.jsp" %>
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
      <table border="1" cellpadding="6">
        <tr><th>Ảnh</th><th>Tên</th><th>Giá</th><th>Số lượng</th><th>Thành tiền</th><th>Hành động</th></tr>
        <%
          for (Map.Entry<Integer,Integer> e : cart.entrySet()) {
            int pid = e.getKey();
            int qty = e.getValue();
            Product p = dao.getProductById(pid);
            double line = p.getGia() * qty;
            total += line;
        %>
        <tr>
          <td><img src="images/<%=p.getAnh()%>" style="width:80px;height:80px;object-fit:cover;"/></td>
          <td><%=p.getTenSanPham()%></td>
          <td><%= String.format("%,.0f", p.getGia()) %></td>
          <td>
            <input type="hidden" name="productId" value="<%=p.getMaSanPham()%>"/>
            <input type="number" name="qty_<%=p.getMaSanPham()%>" value="<%=qty%>" min="0" style="width:60px;"/>
          </td>
          <td><%= String.format("%,.0f", line) %></td>
          <td><a href="cart?action=remove&productID=<%=p.getMaSanPham()%>">Xóa</a></td>
        </tr>
        <% } %>
      </table>
      <p>Tổng cộng: <b><%= String.format("%,.0f", total) %> đ</b></p>
      <button type="submit">Cập nhật giỏ hàng</button>
    </form>
  <% } %>
</div>
<%@ include file="include/footer.jsp" %>
