<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="include/header.jsp" %>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"/>

<div class="container mt-5">
  <div class="row justify-content-center">
    <div class="col-md-6">
      <div class="card shadow-lg border-0 rounded-4">
        <div class="card-header bg-dark text-white text-center rounded-top-4">
          <h3 class="mb-0">Đăng ký tài khoản</h3>
        </div>
        <div class="card-body p-4">
          <form action="user" method="post">
            <input type="hidden" name="action" value="register"/>

            <div class="mb-3">
              <label class="form-label fw-bold">Tên đăng nhập</label>
              <input type="text" name="username" class="form-control" required>
            </div>

            <div class="mb-3">
              <label class="form-label fw-bold">Họ tên</label>
              <input type="text" name="fullname" class="form-control" required>
            </div>

            <div class="mb-3">
              <label class="form-label fw-bold">Email</label>
              <input type="email" name="email" class="form-control" required>
            </div>

            <div class="mb-3">
              <label class="form-label fw-bold">Số điện thoại</label>
              <input type="text" name="phone" class="form-control">
            </div>

            <div class="mb-3">
              <label class="form-label fw-bold">Địa chỉ</label>
              <input type="text" name="address" class="form-control">
            </div>

            <div class="mb-3">
              <label class="form-label fw-bold">Mật khẩu</label>
              <input type="password" name="password" class="form-control" required>
            </div>

            <div class="mb-3">
              <label class="form-label fw-bold">Nhập lại mật khẩu</label>
              <input type="password" name="repassword" class="form-control" required>
            </div>

            <div class="d-grid">
              <button type="submit" class="btn btn-dark btn-lg rounded-pill">Đăng ký</button>
            </div>
          </form>

          <div class="text-danger mt-3 text-center">
            <%= request.getAttribute("msg") != null ? request.getAttribute("msg") : "" %>
          </div>

          <div class="text-center mt-3">
            <span>Đã có tài khoản?</span>
            <a href="login.jsp" class="text-decoration-none fw-bold">Đăng nhập</a>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>

<%@ include file="include/footer.jsp" %>
