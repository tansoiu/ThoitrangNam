<%@ page contentType="text/html; charset=UTF-8" %>
<link rel="stylesheet" href="css/header.css">

<div id="topmenu">

  <div id="logo">
    <a href="product?action=list">TechMenSwear 🌿</a>
  </div>


  <div class="nav-links">
    <a href="product?action=list">Trang chủ</a>
    <a href="product?action=list&filter=new">Hàng mới</a>
    <a href="contact.jsp">Liên hệ</a>
    <a href="cart.jsp">Giỏ hàng</a>
  </div>


  <div class="user-area">
    <%
      Model.User u = (Model.User) session.getAttribute("user");
      if (u != null) {
    %>
      Xin chào <strong><%= u.getHoTen() == null ? u.getTenDangNhap() : u.getHoTen() %></strong> |
      <a href="user?action=logout">Đăng xuất</a>
    <% } else { %>
      <a href="login.jsp">Đăng nhập</a> | <a href="register.jsp">Đăng ký</a>
    <% } %>
  </div>
</div>
