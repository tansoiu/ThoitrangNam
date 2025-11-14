<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="include/header.jsp" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Đăng nhập tài khoản</title>

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://unpkg.com/bootstrap@5.3.3/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://unpkg.com/bs-brain@2.0.4/components/logins/login-12/assets/css/login-12.css">
    <link rel="stylesheet" href="css/style.css">
</head>
<body>

<section class="py-3 py-md-5 py-xl-8">
  <div class="container">
    <div class="row">
      <div class="col-12">
        <div class="mb-5 text-center">
          <h2 class="display-5 fw-bold">Đăng nhập</h2>
          <p>Bạn chưa có tài khoản? <a href="register.jsp">Đăng ký</a></p>
        </div>
      </div>
    </div>

    <div class="row justify-content-center">
      <div class="col-12 col-lg-10 col-xl-8">
        <div class="row gy-5 justify-content-center">
          <div class="col-12 col-lg-5">
            <!-- Form đăng nhập (liên kết với servlet cũ) -->
            <form action="user" method="post">
              <!-- Hidden để Servlet nhận biết action -->
              <input type="hidden" name="action" value="login"/>

              <div class="row gy-3 overflow-hidden">

                <!-- Ô nhập tên đăng nhập -->
                <div class="col-12">
                  <div class="form-floating mb-3">
                    <input type="text" class="form-control border-0 border-bottom rounded-0"
                           name="username" id="username"
                           placeholder="Tên đăng nhập" required>
                    <label for="username" class="form-label">Tên đăng nhập</label>
                  </div>
                </div>

                <!-- ✅ Ô nhập mật khẩu -->
                <div class="col-12">
                  <div class="form-floating mb-3">
                    <input type="password" class="form-control border-0 border-bottom rounded-0"
                           name="password" id="password" placeholder="Mật khẩu" required>
                    <label for="password" class="form-label">Mật khẩu</label>
                  </div>
                </div>

                <!-- Ghi nhớ & quên mật khẩu -->
                <div class="col-12">
                  <div class="row justify-content-between">
                    <div class="col-6">
                      <div class="form-check">
                        <input class="form-check-input" type="checkbox" name="remember_me" id="remember_me">
                        <label class="form-check-label text-secondary" for="remember_me">Ghi nhớ tôi</label>
                      </div>
                    </div>
                    <div class="col-6 text-end">
                      <a href="#!" class="link-secondary text-decoration-none">Quên mật khẩu?</a>
                    </div>
                  </div>
                </div>

                <!-- ✅ Nút đăng nhập -->
                <div class="col-12">
                  <div class="d-grid">
                    <button class="btn btn-lg btn-dark rounded-0 fs-6" type="submit">Đăng nhập</button>
                  </div>
                </div>

                <!-- ✅ Thông báo lỗi từ Servlet -->
                <div class="col-12 text-danger text-center mt-3">
                  <%= request.getAttribute("msg") != null ? request.getAttribute("msg") : "" %>
                </div>
              </div>
            </form>
          </div>

          <!-- Divider -->
          <div class="col-12 col-lg-2 d-flex align-items-center justify-content-center gap-3 flex-lg-column">
            <div class="bg-dark h-100 d-none d-lg-block" style="width: 1px; --bs-bg-opacity: .1;"></div>
            <div class="bg-dark w-100 d-lg-none" style="height: 1px; --bs-bg-opacity: .1;"></div>
            <div>hoặc</div>
            <div class="bg-dark h-100 d-none d-lg-block" style="width: 1px; --bs-bg-opacity: .1;"></div>
            <div class="bg-dark w-100 d-lg-none" style="height: 1px; --bs-bg-opacity: .1;"></div>
          </div>

          <!-- Đăng nhập mạng xã hội -->
          <div class="col-12 col-lg-5 d-flex align-items-center">
            <div class="d-flex gap-3 flex-column w-100">
              <a href="#!" class="btn bsb-btn-2xl btn-outline-dark rounded-0 d-flex align-items-center">
                <i class="bi bi-google text-danger fs-5 me-2"></i> Đăng nhập bằng Google
              </a>
              <a href="#!" class="btn bsb-btn-2xl btn-outline-dark rounded-0 d-flex align-items-center">
                <i class="bi bi-facebook text-primary fs-5 me-2"></i> Đăng nhập bằng Facebook
              </a>
            </div>
          </div>

        </div>
      </div>
    </div>
  </div>
</section>

<script src="https://unpkg.com/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
<%@ include file="include/footer.jsp" %>
