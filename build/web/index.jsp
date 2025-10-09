<%@ page import="java.util.List" %>
<%@ page import="Model.Product" %>
<%@ page import="Model.ProductDAO" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="include/header.jsp" %>
<link rel="stylesheet" href="css/style.css"/>

<div class="content" style="display:flex; padding:20px; gap:20px;">
  
  <!-- ===== MENU DANH MỤC VÀ TÌM KIẾM ===== -->
<div class="sidebar" 
     style="width:250px; background:#fff; border:1px solid #ddd; border-radius:10px; 
            padding:20px; box-shadow:0 2px 8px rgba(0,0,0,0.1);">

  <h3 style="margin-bottom:15px; font-size:20px; color:#222; font-weight:bold;">Danh mục</h3>

  <!-- Ô tìm kiếm -->
  <form action="product" method="get" style="margin-bottom:20px;">
    <input type="hidden" name="action" value="search"/>
    <input type="text" name="keyword" placeholder="Tìm sản phẩm..." 
           style="width:100%; padding:8px 10px; border:1px solid #ccc; border-radius:6px;"/>
  </form>

  <!-- Danh sách danh mục -->
  <ul style="list-style:none; padding:0; margin:0;">
    <%
      String selectedCategory = (String) request.getAttribute("selectedCategory");
      String[] categories = {"Áo", "Quần", "Giày", "Phụ kiện"};
      for (String c : categories) {
          String activeStyle = (selectedCategory != null && selectedCategory.equalsIgnoreCase(c))
              ? "background:#007bff;color:white;font-weight:bold;"
              : "color:#333;";
    %>
        <li style="margin-bottom:8px;">
          <a href="product?action=filter&category=<%=c%>" 
             style="display:block;padding:8px 12px;border-radius:6px;
                    text-decoration:none;<%=activeStyle%>"
             onmouseover="this.style.background='#007bff';this.style.color='white';" 
             onmouseout="if(this.innerText!='<%=selectedCategory!=null?selectedCategory:""%>'){this.style.background='';this.style.color='#333';}">
             <%=c%>
          </a>
        </li>
    <% } %>
  </ul>
</div>


  <!-- ===== DANH SÁCH SẢN PHẨM BÊN PHẢI ===== -->
  <div class="main-content" style="flex:1;">
    <h2 class="title">Sản phẩm</h2>
    <div class="product-list" style="display:flex;flex-wrap:wrap;gap:20px;">
      <%
        List<Product> list = (List<Product>) request.getAttribute("products");
        if (list == null) {
          list = new ProductDAO().getAllProducts();
        }
        for (Product p: list) {
      %>
        <div class="product-item" style="width:200px;border:1px solid #ddd;padding:10px;background:#fff;">
          <img src="images/<%=p.getAnh()!=null?p.getAnh():"no-image.png"%>" 
               alt="" style="width:100%;height:160px;object-fit:cover;"/>
          <h3><%=p.getTenSanPham()%></h3>
          <p><strong><%= String.format("%,.0f", p.getGia()) %> đ</strong></p>
          <a href="product?action=detail&id=<%=p.getMaSanPham()%>">Chi tiết</a>
        </div>
      <% } %>
    </div>
  </div>
</div>

<%@ include file="include/footer.jsp" %>
