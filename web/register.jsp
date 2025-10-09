<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="include/header.jsp" %>
<h2 class="title" style="padding:20px;">Đăng ký tài khoản</h2>
<div style="padding:20px;">
  <form action="user" method="post">
    <input type="hidden" name="action" value="register"/>
    Tên đăng nhập: <input type="text" name="username"/><br/>
    Họ tên: <input type="text" name="fullname"/><br/>
    Email: <input type="email" name="email"/><br/>
    SĐT: <input type="text" name="phone"/><br/>
    Địa chỉ: <input type="text" name="address"/><br/>
    Mật khẩu: <input type="password" name="password"/><br/>
    Nhập lại mật khẩu: <input type="password" name="repassword"/><br/>
    <button type="submit">Đăng ký</button>
  </form>
  <div style="color:red;"><%= request.getAttribute("msg")!=null?request.getAttribute("msg"):"" %></div>
</div>
<%@ include file="include/footer.jsp" %>
