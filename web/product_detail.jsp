<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${product.tenSanPham}</title>
    <style>
        .variant-box { display: flex; flex-wrap: wrap; gap: 10px; margin: 15px 0; }
        .variant-item {
            padding: 10px 18px;
            border: 2px solid #ddd;
            border-radius: 8px;
            cursor: pointer;
            transition: all 0.3s;
            text-align: center;
            min-width: 60px;
        }
        .variant-item:hover { border-color: #999; }
        .variant-item.selected {
            background: #1976d2;
            color: white;
            border-color: #1976d2;
        }
        .variant-item.disabled {
            background: #f5f5f5;
            color: #aaa;
            cursor: not-allowed;
            text-decoration: line-through;
        }
        .stock-info { margin: 20px 0; font-size: 15px; }
        .out-of-stock { color: red; font-weight: bold; }
    </style>
</head>
<body>

<%@ include file="include/header.jsp" %>
<link rel="stylesheet" href="css/style.css"/>

<div class="content" style="padding:20px; max-width:1200px; margin:0 auto;">

    <h2 style="color:#333;">${product.tenSanPham}</h2>
    
    <div style="display:flex; gap:40px; flex-wrap: wrap;">
        <!-- Ảnh sản phẩm -->
        <div>
            <img src="images/${product.anh}" style="width:400px; height:500px; object-fit:cover; border-radius:10px;" 
                 alt="${product.tenSanPham}"/>
        </div>

        <!-- Thông tin + chọn màu size -->
        <div style="flex:1; min-width:300px;">
            <h3>Chọn màu sắc:</h3>
            <div class="variant-box" id="colorBox">
                <c:forEach var="detail" items="${details}" varStatus="loop">
                    <c:if test="${loop.index == 0 || detail.mauSac != details[loop.index - 1].mauSac}">
                        <div class="variant-item color-item" 
                             data-color="${detail.mauSac}"
                             onclick="selectColor('${detail.mauSac}')">
                            ${detail.mauSac}
                        </div>
                    </c:if>
                </c:forEach>
            </div>

            <h3>Chọn kích cỡ:</h3>
            <div class="variant-box" id="sizeBox">
                <!-- Size sẽ được load bằng JS theo màu đã chọn -->
            </div>

            <div class="stock-info" id="stockInfo">
                Vui lòng chọn màu và kích cỡ
            </div>

            <div style="margin-top:30px;">
                <p style="font-size:24px; color:#d32f2f; font-weight:bold;">
                    Giá: ${fn:replace(fn:replace(product.gia, ',', '.'), '.000', '')} đ
                </p>
                <p style="line-height:1.8; color:#555;">${product.moTa}</p>

                <form action="cart" method="post" id="addToCartForm">
                    <input type="hidden" name="action" value="add"/>
                    <input type="hidden" name="maChiTiet" id="maChiTietInput" value=""/>
                    
                    <div style="margin:20px 0;">
                        Số lượng: 
                        <input type="number" name="quantity" value="1" min="1" max="50" 
                               style="width:80px; padding:8px; margin-left:10px;"/>
                    </div>

                    <button type="submit" id="addBtn" disabled
                            style="padding:14px 30px; background:#1976d2; color:white; border:none; border-radius:8px; font-size:16px; cursor:not-allowed;">
                        Thêm vào giỏ hàng
                    </button>
                </form>
            </div>
        </div>
    </div>
</div>

<script>
// Danh sách chi tiết sản phẩm từ server
const details = [
    <c:forEach var="d" items="${details}">
    {maChiTiet: ${d.maChiTiet}, mauSac: "${d.mauSac}", size: "${d.size}", soLuong: ${d.soLuong}},
    </c:forEach>
];

// Lưu lựa chọn hiện tại
let selectedColor = null;
let selectedSize = null;
let selectedMaChiTiet = null;

function selectColor(color) {
    selectedColor = color;
    selectedSize = null;
    selectedMaChiTiet = null;
    document.querySelectorAll('.color-item').forEach(el => {
        el.classList.remove('selected');
        if (el.getAttribute('data-color') === color) el.classList.add('selected');
    });

    // Load size theo màu
    renderSizes(color);
    updateStockInfo();
}

function selectSize(size, maChiTiet, soLuong) {
    selectedSize = size;
    selectedMaChiTiet = maChiTiet;
    
    document.querySelectorAll('.size-item').forEach(el => {
        el.classList.remove('selected');
    });
    event.target.classList.add('selected');

    updateStockInfo(soLuong);
    document.getElementById('maChiTietInput').value = maChiTiet;
    document.getElementById('addBtn').disabled = false;
    document.getElementById('addBtn').style.background = '#1976d2';
    document.getElementById('addBtn').style.cursor = 'pointer';
}

function renderSizes(color) {
    const sizeBox = document.getElementById('sizeBox');
    const sizes = details.filter(d => d.mauSac === color);
    
    sizeBox.innerHTML = '';
    if (sizes.length === 0) {
        sizeBox.innerHTML = '<p>Không có size nào cho màu này</p>';
        return;
    }

    sizes.forEach(d => {
        const item = document.createElement('div');
        item.className = 'variant-item size-item';
        if (d.soLuong <= 0) {
            item.className += ' disabled';
            item.innerText = d.size + ' (Hết)';
        } else {
            item.innerText = d.size;
            item.onclick = () => selectSize(d.size, d.maChiTiet, d.soLuong);
        }
        sizeBox.appendChild(item);
    });
}

function updateStockInfo(soLuong = null) {
    const info = document.getElementById('stockInfo');
    if (!selectedColor) {
        info.innerHTML = 'Vui lòng chọn màu sắc';
    } else if (!selectedSize) {
        info.innerHTML = `Đã chọn màu: <strong>${selectedColor}</strong> → Vui lòng chọn kích cỡ`;
    } else {
        const sl = soLuong !== null ? soLuong : details.find(d => d.maChiTiet == selectedMaChiTiet).soLuong;
        if (sl > 0) {
            info.innerHTML = `Còn <strong>${sl}</strong> sản phẩm (Màu: ${selectedColor} - Size: ${selectedSize})`;
            info.style.color = 'green';
        } else {
            info.innerHTML = '<span class="out-of-stock">Hết hàng</span>';
        }
    }
}

// Tự động chọn màu đầu tiên khi load trang (nếu có)
window.onload = function() {
    const firstColor = document.querySelector('.color-item');
    if (firstColor) {
        selectColor(firstColor.getAttribute('data-color'));
        firstColor.classList.add('selected');
    }
}
</script>

<%@ include file="include/footer.jsp" %>
</body>
</html>