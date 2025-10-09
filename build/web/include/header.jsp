<%@ page contentType="text/html; charset=UTF-8" %>
<div id="topmenu" style="background:#222;color:#fff;padding:10px;">
  <a href="product?action=list" style="color:white;text-decoration:none;margin-right:15px;">Trang chủ</a>
  <a href="product?action=list&filter=new" style="color:white;text-decoration:none;margin-right:15px;">Hàng mới</a>
  <a href="contact.jsp" style="color:white;text-decoration:none;margin-right:15px;">Liên hệ</a>
  <a href="cart.jsp" style="color:white;text-decoration:none;margin-right:15px;">Giỏ hàng</a>
  <span style="float:right;">
    <% 
      Model.User u = (Model.User) session.getAttribute("user");
      if (u != null) {
    %>
      Xin chào <%=u.getHoTen()==null?u.getTenDangNhap():u.getHoTen()%> | <a href="logout.jsp">Đăng xuất</a>
    <% } else { %>
      <a href="login.jsp" style="color:white;">Đăng nhập</a> | <a href="register.jsp" style="color:white;">Đăng ký</a>
    <% } %>
  </span>
</div>
