<%@ page import="Model.Product" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="include/header.jsp" %>
<link rel="stylesheet" href="css/style.css"/>
<%
  Product p = (Product) request.getAttribute("product");
  if (p == null) {
      int id = Integer.parseInt(request.getParameter("id"));
      p = new Model.ProductDAO().getProductById(id);
  }
%>
<div class="content" style="padding:20px;">
  <h2 class="title"><%=p.getTenSanPham()%></h2>
  <div style="display:flex;gap:20px;">
    <div><img src="images/<%=p.getAnh()%>" style="width:300px;height:350px;object-fit:cover;"/></div>
    <div>
      <p>Giá: <b><%= String.format("%,.0f", p.getGia()) %> đ</b></p>
      <p><%=p.getMoTa()%></p>
      <form action="cart" method="post">
        <input type="hidden" name="action" value="add"/>
        <input type="hidden" name="productID" value="<%=p.getMaSanPham()%>"/>
        Số lượng: <input type="number" name="quantity" value="1" min="1" style="width:60px;"/>
        <button type="submit">Thêm vào giỏ hàng</button>
      </form>
    </div>
  </div>
</div>
<%@ include file="include/footer.jsp" %>
