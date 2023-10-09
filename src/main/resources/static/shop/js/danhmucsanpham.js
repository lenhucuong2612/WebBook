document.addEventListener('DOMContentLoaded', function() {
    const sortBySelect = document.getElementById('SortBy');
    const currentUrl = new URL(window.location.href);
    const categoryId = extractCategoryId(currentUrl.pathname);
    const page = extractPageNumber(currentUrl.pathname);

    // Kiểm tra nếu URL là `/shop/category/${categoryId}` và không có param `sort`
    const isDefaultUrl = currentUrl.pathname === `/shop/category/${categoryId}/${page}` && !currentUrl.searchParams.get('sort');

    // Khôi phục giá trị đã chọn từ localStorage (nếu có) hoặc giữ nguyên giá trị hiện tại
    const selectedValue = localStorage.getItem('selectedSortBy');
    if (selectedValue && !isDefaultUrl) {
        sortBySelect.value = selectedValue;
    }

    sortBySelect.addEventListener('change', function() {
        const selectedValue = sortBySelect.value;

        currentUrl.searchParams.set('sort', selectedValue);

        // Lưu giá trị đã chọn vào localStorage
        localStorage.setItem('selectedSortBy', selectedValue);

        // Giữ nguyên các tham số truy vấn khác
        const queryParams = currentUrl.searchParams.toString();
        let newUrl = '';
        if (categoryId != null) {
            newUrl = `/shop/category/${categoryId}/${page}/sort?${queryParams}`;
        } else {
            newUrl = `/shop/category/all/${page}/sort?${queryParams}`;
        }

        // Kiểm tra nếu URL là `/shop/category/${categoryId}` và không có param `sort`
        const isDefaultUrl = currentUrl.pathname === `/shop/category/${categoryId}/${page}` && !currentUrl.searchParams.get('sort');

        // Reset giá trị chọn về option đầu tiên nếu chuyển sang các đường dẫn không có param
        if (isDefaultUrl) {
            localStorage.removeItem('selectedSortBy');
        }

        window.location.href = newUrl;
    });

    function extractCategoryId(pathname) {
        const regex = /\/shop\/category\/(\d+)/;
        const match = pathname.match(regex);
        if (match && match.length > 1) {
            return match[1];
        }
        return null;
    }

    function extractPageNumber(pathname) {
        const regex = /\/shop\/category\/(?:\d+|all)\/(\d+)/;
        const match = pathname.match(regex);
        if (match && match.length > 1) {
            return match[1];
        }
        return null;
    }
});
//
// Lấy danh sách các ô input bộ lọc giá
const priceFilterInputs = document.querySelectorAll('input[name="filter-price"]');
const currentUrl = new URL(window.location.href);

priceFilterInputs.forEach(function(input) {
    input.addEventListener('change', function() {
        const filterPrice = input.value;
        const id = getCurrentIdFromUrl(currentUrl);
        const updatedUrl = buildUpdatedUrl(id, 1, filterPrice);
        navigateToUrl(updatedUrl);
    });
});

window.addEventListener('DOMContentLoaded', function() {
    const selectedValue = getUrlParameter(currentUrl, 'search');
    if (selectedValue) {
        const selectedRadio = document.querySelector(`input[value="${selectedValue}"]`);
        if (selectedRadio) {
            selectedRadio.checked = true;
        }
    }
});

function getCurrentIdFromUrl(url) {
    const pathSegments = url.pathname.split('/');
    const idIndex = pathSegments.indexOf('category') + 1;
    return pathSegments[idIndex];
}

function buildUpdatedUrl(id, pageNo, filterPrice) {
    let url = '';

    if (id === 'all') {
        url = `/shop/category/all/${pageNo}/search`;
    } else if (id && pageNo) {
        url = `/shop/category/${id}/${pageNo}/search`;
    } else {
        url = '/shop/category/all';
    }

    if (filterPrice) {
        if (url.includes('?')) {
            url += `&search=${filterPrice}`;
        } else {
            url += `?search=${filterPrice}`;
        }
    }

    return url;
}

function removeUrlParameter(url, paramName) {
    if (url.searchParams) {
        url.searchParams.delete(paramName);
    } else {
        const searchParams = new URLSearchParams(url.search);
        searchParams.delete(paramName);
        url.search = searchParams.toString();
    }
    return url;
}

function getUrlParameter(url, paramName) {
    if (url.searchParams) {
        return url.searchParams.get(paramName);
    } else {
        const searchParams = new URLSearchParams(url.search);
        return searchParams.get(paramName);
    }
}

function navigateToUrl(url) {
    window.location.href = url;
}
// slider
$(document).ready(function(){
    $('#related_product-list-1').slick({
        slidesToShow: 5,
        slidesToScroll: 1,
        arrows:true,
        autoplay: false,
        autoplaySpeed: 3000,
        infinite: true,
        dots:false,
        // <div class="banner_slider-icon"></div>
        prevArrow: `<button type='button' class='slick-prev '><i class=" banner_slider-icon-prev fa-solid fa-chevron-left"></i></button>`,
        nextArrow: `<button type='button' class='slick-next slick-arrow'><i class=" banner_slider-icon-next fa-solid fa-chevron-right"></i></button>`,
    });
});

$(document).ready(function(){
    $('#related_product-list-2').slick({
        slidesToShow: 5,
        slidesToScroll: 1,
        arrows:true,
        autoplay: false,
        autoplaySpeed: 3000,
        infinite: true,
        dots:false,
        // <div class="banner_slider-icon"></div>
        prevArrow: `<button type='button' class='slick-prev '><i class=" banner_slider-icon-prev fa-solid fa-chevron-left"></i></button>`,
        nextArrow: `<button type='button' class='slick-next slick-arrow'><i class=" banner_slider-icon-next fa-solid fa-chevron-right"></i></button>`,
    });
});

$(document).ready(function(){
    $('#seen_product-list').slick({
        slidesToShow: 4,
        slidesToScroll: 1,
        arrows:true,
        autoplay: false,
        autoplaySpeed: 3000,
        infinite: true,
        dots:false,
        // <div class="banner_slider-icon"></div>
        prevArrow: `<button type='button' class='slick-prev '><i class=" banner_slider-icon-prev fa-solid fa-chevron-left"></i></button>`,
        nextArrow: `<button type='button' class='slick-next slick-arrow'><i class=" banner_slider-icon-next fa-solid fa-chevron-right"></i></button>`,
    });
});
// Hết slider

// Ẩn hiện khi click
// Phần click nút review và bình luận để hiện lên để có thể bình luận
$('.comment').click(function(e) {
    e.preventDefault();
    $('.review_main').addClass('d-none');
    $('.comment_main').removeClass('d-none');
    $('.comment').addClass('active');
    $('.review').removeClass('active');
});

$('.review').click(function(e) {
    e.preventDefault();
    $('.comment_main').addClass('d-none');
    $('.review_main').removeClass('d-none');
    $('.review').addClass('active');
    $('.comment').removeClass('active');
});
// Hết Phần click nút review và bình luận để hiện lên

// Phần click để hiện form
var previewOpen = false;
var askQuestionOpen = false;

$('.write-preview').click(function(e) {
    e.preventDefault();

    if (!previewOpen) {
        $('.form_ask-question').addClass('d-none');
        $('.form_write-preview').removeClass('d-none');
        previewOpen = true;
        askQuestionOpen = false;
    } else {
        $('.form_write-preview').addClass('d-none');
        previewOpen = false;
    }
});

$('.ask-question').click(function(e) {
    e.preventDefault();

    if (!askQuestionOpen) {
        $('.form_write-preview').addClass('d-none');
        $('.form_ask-question').removeClass('d-none');
        askQuestionOpen = true;
        previewOpen = false;
    } else {
        $('.form_ask-question').addClass('d-none');
        askQuestionOpen = false;
    }
});
// Hết Phần click để hiện form


$('.product_more-information-footer-review').click(function(e) {
    $('.product_more-information-footer-review').addClass('border_bottom');
    $('.product_more-information-footer-question').removeClass('border_bottom');
});

$('.product_more-information-footer-question').click(function(e) {
    $('.product_more-information-footer-question').addClass('border_bottom');
    $('.product_more-information-footer-review').removeClass('border_bottom');
});

// Tăng giảm số lượng sản phẩm để thêm vào giỏ hàng
$('.product_infor-quantity-minus').click(function() {
    $('#product_quantity').val(parseInt( $('#product_quantity').val()) - 1 > 0 ? $('#product_quantity').val() - 1 : 1) ;
});

$('.product_infor-quantity-plus').click(function() {
    $('#product_quantity').val( parseInt( $('#product_quantity').val()) + 1 ); ;
});