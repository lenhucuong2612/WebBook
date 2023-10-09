

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


  // Ẩn hiện khi click
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

$('.product_more-information-footer-review').click(function(e) {
  $('.product_more-information-footer-review').addClass('border_bottom');
  $('.product_more-information-footer-question').removeClass('border_bottom');
});

$('.product_more-information-footer-question').click(function(e) {
  $('.product_more-information-footer-question').addClass('border_bottom');
  $('.product_more-information-footer-review').removeClass('border_bottom');
});
