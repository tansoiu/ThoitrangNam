<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="include/header.jsp" %>
<h2 class="title" style="padding:20px;">Liên hệ</h2>
<div style="padding:20px;">
  <form action="contact" method="post">
    Họ tên: <input type="text" name="name"/><br/>
    Email: <input type="email" name="email"/><br/>
    SĐT: <input type="text" name="phone"/><br/>
    Chủ đề: <input type="text" name="subject"/><br/>
    Nội dung: <textarea name="message" rows="6" cols="50"></textarea><br/>
    <button type="submit">Gửi</button>
  </form>
  <div style="color:green;"><%= request.getAttribute("msg")!=null?request.getAttribute("msg"):"" %></div>
</div>
<%@ include file="include/footer.jsp" %>
