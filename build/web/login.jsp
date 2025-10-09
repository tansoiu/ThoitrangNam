<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="include/header.jsp" %>
<h2 class="title" style="padding:20px;">Đăng nhập</h2>
<div style="padding:20px;">
  <form action="user" method="post">
    <input type="hidden" name="action" value="login"/>
    Tên đăng nhập: <input type="text" name="username"/><br/>
    Mật khẩu: <input type="password" name="password"/><br/>
    <button type="submit">Đăng nhập</button>
  </form>
  <div style="color:red;"><%= request.getAttribute("msg")!=null?request.getAttribute("msg"):"" %></div>
</div>
<%@ include file="include/footer.jsp" %>
