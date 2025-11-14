<%@ page import="java.util.*, Model.Product, Model.ProductDAO, Model.ProductDetailDAO, Model.ProductDetail, Model.User" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="include/header.jsp" %>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css?v=4">

<div style="padding:20px; max-width:1100px; margin:0 auto;">
  <h2 class="title">Xác nhận đơn hàng</h2>

  <%
    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("login.jsp?return=checkout.jsp");
        return;
    }

    Map<Integer, Integer> cart = (Map<Integer, Integer>) session.getAttribute("cart");
    if (cart == null || cart.isEmpty()) {
  %>
    <div style="text-align:center; padding:50px; background:#f9f9f9; border-radius:10px;">
      <h3>Giỏ hàng trống!</h3>
      <a href="index.jsp" style="color:#1976d2;">Quay lại mua sắm</a>
    </div>
  <%
      return;
    }

    ProductDAO productDAO = new ProductDAO();
    ProductDetailDAO detailDAO = new ProductDetailDAO();
    double totalAmount = 0;
  %>

  <div style="display:flex; gap:30px; flex-wrap:wrap;">
    
    <!-- Cột trái: Danh sách sản phẩm -->
    <div style="flex:1; min-width:300px;">
      <h3 style="background:#f1f1f1; padding:15px; border-radius:8px;">Sản phẩm trong đơn hàng</h3>
      <table class="cart-table" style="width:100%; margin-top:10px;">
        <tr>
          <th>Ảnh</th>
          <th>Sản phẩm</th>
          <th>Biến thể</th>
          <th>SL</th>
          <th>Giá</th>
          <th>Thành tiền</th>
        </tr>
        <%
          for (Map.Entry<Integer, Integer> entry : cart.entrySet()) {
            int maChiTiet = entry.getKey();
            int qty = entry.getValue();

            ProductDetail detail = detailDAO.getProductDetailByMaChiTiet(maChiTiet);
            if (detail == null) continue;
            Product p = productDAO.getProductById(detail.getMaSanPham());
            if (p == null) continue;

            double lineTotal = p.getGia() * qty;
            totalAmount += lineTotal;
        %>
        <tr>
          <td><img src="images/<%= p.getAnh() %>" width="60" height="60" style="border-radius:6px; object-fit:cover;"></td>
          <td><strong><%= p.getTenSanPham() %></strong></td>
          <td style="color:#1976d2; font-weight:bold;">
            <%= detail.getMauSac() %> - <%= detail.getSize() %>
          </td>
          <td style="text-align:center;"><%= qty %></td>
          <td><%= String.format("%,.0f", p.getGia()) %> ₫</td>
          <td style="font-weight:bold;"><%= String.format("%,.0f", lineTotal) %> ₫</td>
        </tr>
        <% } %>
      </table>

      <div style="background:#f8f8f8; padding:20px; border-radius:8px; margin-top:20px; font-size:18px;">
        <div style="display:flex; justify-content:space-between;">
          <span>Tạm tính:</span>
          <strong><%= String.format("%,.0f", totalAmount) %> ₫</strong>
        </div>
        <div style="display:flex; justify-content:space-between; margin-top:10px;">
          <span>Phí vận chuyển:</span>
          <strong style="color:#d32f2f;">Miễn phí</strong>
        </div>
        <hr style="border:none; border-top:2px dashed #ddd; margin:15px 0;">
        <div style="display:flex; justify-content:space-between; font-size:24px; color:#d32f2f;">
          <span><strong>Tổng cộng:</strong></span>
          <strong><%= String.format("%,.0f", totalAmount) %> ₫</strong>
        </div>
      </div>
    </div>

    <!-- Cột phải: Thông tin giao hàng -->
    <div style="flex:1; min-width:300px;">
      <h3 style="background:#f1f1f1; padding:15px; border-radius:8px;">Thông tin nhận hàng</h3>
      
      <form action="order" method="post" style="background:#fff; padding:20px; border:1px solid #eee; border-radius:8px;">
        <input type="hidden" name="action" value="create"/>

        <div style="margin-bottom:15px;">
          <label><strong>Họ tên:</strong></label><br>
          <input type="text" name="hoTen" value="<%= user.getHoTen() != null ? user.getHoTen() : "" %>" 
                 required style="width:100%; padding:10px; margin-top:5px; border:1px solid #ddd; border-radius:6px;" />
        </div>

        <div style="margin-bottom:15px;">
          <label><strong>Số điện thoại:</strong></label><br>
          <input type="text" name="sdt" value="<%= user.getSoDienThoai() != null ? user.getSoDienThoai() : "" %>"
                 required pattern="[0-9]{10}" title="Số điện thoại 10 số"
                 style="width:100%; padding:10px; margin-top:5px; border:1px solid #ddd; border-radius:6px;" />
        </div>

        <div style="margin-bottom:15px;">
          <label><strong>Địa chỉ giao hàng:</strong></label><br>
          <textarea name="diaChi" required rows="3" 
                    style="width:100%; padding:10px; margin-top:5px; border:1px solid #ddd; border-radius:6px;"><%= 
                      user.getDiaChi() != null ? user.getDiaChi() : "" 
                    %></textarea>
        </div>

        <div style="margin-bottom:20px;">
          <label><strong>Ghi chú (tùy chọn):</strong></label><br>
          <textarea name="ghiChu" rows="3" placeholder="Ví dụ: Giao giờ hành chính, để trước cửa..."
                    style="width:100%; padding:10px; margin-top:5px; border:1px solid #ddd; border-radius:6px;"></textarea>
        </div>

        <div style="text-align:center;">
          <button type="submit" 
                  style="padding:15px 40px; background:#d32f2f; color:white; border:none; border-radius:8px; font-size:18px; font-weight:bold; cursor:pointer;">
            HOÀN TẤT ĐẶT HÀNG
          </button>
        </div>

        <div style="margin-top:20px; text-align:center; color:#666; font-size:14px;">
          <p>Sau khi đặt hàng, chúng tôi sẽ liên hệ xác nhận trong 5-10 phút.</p>
        </div>
      </form>
    </div>
  </div>

  <div style="text-align:center; margin-top:30px;">
    <a href="cart.jsp" style="color:#1976d2;">← Quay lại giỏ hàng</a>
  </div>
</div>

<%@ include file="include/footer.jsp" %>