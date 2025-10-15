<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Đăng ký tài khoản</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

    <style>
        /* Giảm chiều cao và kích thước chữ của input */
        .form-control {
            height: 42px !important;
            padding: 0.3rem 0.75rem !important;
            font-size: 0.9rem !important;
        }

        .form-floating > label {
            font-size: 0.85rem;
            padding: 0.3rem 0.75rem;
        }

        .btn-lg {
            padding: 0.4rem 1rem;
            font-size: 0.95rem;
        }

        .card-body {
            padding: 1.5rem !important;
        }

        h2, h3 {
            margin-bottom: 0.5rem;
        }

        .small {
            font-size: 0.85rem !important;
        }
    </style>
</head>
<body>
<section class="bg-light py-4">
  <div class="container">
    <div class="row justify-content-center">
      <div class="col-12 col-md-8 col-lg-6 col-xl-5 col-xxl-4">
        <div class="card border border-light-subtle rounded-4 shadow-sm">
          <div class="card-body">
            <div class="text-center mb-3">
              <h2 class="h5 mb-1">Đăng ký</h2>
              <p class="text-secondary small mb-3">Nhập thông tin của bạn để đăng ký bằng Email hoặc SĐT</p>
            </div>

            <form action="RegisterServlet" method="post">
              <div class="row gy-2 overflow-hidden">

                <div class="col-12">
                  <div class="form-floating mb-2">
                    <input type="text" class="form-control" name="firstName" id="firstName" placeholder="Họ" required>
                    <label for="firstName">Họ</label>
                  </div>
                </div>

                <div class="col-12">
                  <div class="form-floating mb-2">
                    <input type="text" class="form-control" name="lastName" id="lastName" placeholder="Tên" required>
                    <label for="lastName">Tên</label>
                  </div>
                </div>

                <div class="col-12">
                  <div class="form-floating mb-2">
                    <input type="email" class="form-control" name="email" id="email" placeholder="name@example.com">
                    <label for="email">Email</label>
                  </div>
                </div>

                <div class="col-12">
                  <div class="form-floating mb-2">
                    <input type="text" class="form-control" name="phone" id="phone" placeholder="Số điện thoại">
                    <label for="phone">Số điện thoại</label>
                  </div>
                </div>

                <div class="col-12">
                  <div class="form-floating mb-2">
                    <input type="password" class="form-control" name="password" id="password" placeholder="Mật khẩu" required>
                    <label for="password">Mật khẩu</label>
                  </div>
                </div>

                <div class="col-12">
                  <div class="form-check small">
                    <input class="form-check-input" type="checkbox" name="iAgree" id="iAgree" required>
                    <label class="form-check-label text-secondary" for="iAgree">
                      Tôi đồng ý với <a href="#" class="link-primary text-decoration-none">điều khoản</a>
                    </label>
                  </div>
                </div>

                <div class="col-12">
                  <div class="d-grid mt-2">
                    <button class="btn btn-primary btn-lg" type="submit">Đăng ký</button>
                  </div>
                </div>
              </div>
            </form>

            <hr class="mt-4 mb-3 border-secondary-subtle">
            <p class="text-center text-secondary small m-0">
              Đã có tài khoản? 
              <a href="login.jsp" class="link-primary text-decoration-none">Đăng nhập</a>
            </p>

            <div class="mt-4 text-center">
              <p class="mb-3 text-secondary small">Hoặc tiếp tục với</p>
              <div class="d-flex gap-2 justify-content-center">
                <a href="#" class="btn btn-outline-danger p-2 lh-1"><i class="bi bi-google fs-6"></i></a>
                <a href="#" class="btn btn-outline-primary p-2 lh-1"><i class="bi bi-facebook fs-6"></i></a>
                <a href="#" class="btn btn-outline-info p-2 lh-1"><i class="bi bi-twitter fs-6"></i></a>
                <a href="#" class="btn btn-outline-dark p-2 lh-1"><i class="bi bi-apple fs-6"></i></a>
              </div>
            </div>

          </div>
        </div>
      </div>
    </div>
  </div>
</section>

<!-- Bootstrap + Icons -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
