$('.cart_product-quantity-minus').click(function(e) {
    // Tìm ô input số lượng cụ thể cho sản phẩm
    const quantityInput = $(this).closest('tr').find('input[type="number"]');

    // Giảm giá trị của ô input số lượng nếu giá trị hiện tại lớn hơn 1
    quantityInput.val(parseInt(quantityInput.val()) - 1 > 0 ? parseInt(quantityInput.val()) - 1 : 1);
});

// Xử lý sự kiện khi click vào nút cộng
$('.cart_product-quantity-plus').click(function() {
    // Tìm ô input số lượng cụ thể cho sản phẩm
    const quantityInput = $(this).closest('tr').find('input[type="number"]');

    // Tăng giá trị của ô input số lượng
    quantityInput.val(parseInt(quantityInput.val()) + 1);
});