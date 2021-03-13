$(document).ready(function () {

    const itemCategoryUl = $('.item-category ul');

    $('#create-btn').on('click', function (e) {
        $('.item-popup-wrapper').css('display', 'block');
    })

    $('.item-popup-wrapper .item-cancel-btn').on('click', function () {
        $('.item-popup-wrapper').css('display', 'none');
    })

    $('.selected-category-option').on('click', function () {
        if (itemCategoryUl.hasClass('hide')) {
            itemCategoryUl.removeClass('hide');
            itemCategoryUl.addClass('show');
        } else {
            itemCategoryUl.addClass('hide');
            itemCategoryUl.removeClass('show');
        }
    });

    $('.item-category ul li').on('click', function () {
        itemCategoryUl.removeClass('show');
        itemCategoryUl.addClass('hide');
        $('.selected-category-option').html($(this).text());
        $('.selected-category-option').val($(this).text());
    });

    $('#item_image_btn').on('change', function (e) {
        readImage(e.target);
    })
})

function readImage(input) {
    if (input.files && input.files[0]) {
        const reader = new FileReader()
        reader.onload = function (e) {
            $('.item_profile_image').attr('src', e.target.result);
        }
        reader.readAsDataURL(input.files[0]);
    }
}