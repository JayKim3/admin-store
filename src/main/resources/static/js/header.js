$(document).ready(function(){

    getCurrentTime();
    setInterval(getCurrentTime, 1000);

    $('.main-header__right--category-btn').on("click", function () {
        // $('.overlay').css({'backgroundColor': 'rgba(0,0,0,0.5)', 'zIndex': 99});
        $('.overlay').show();
        $('.category-popup-wrapper').css('display', 'block');
    })

    const parentCategoryUl = $('.parent-category ul');

    $('#category-add').on('click', function () {
        // Validation
        // 해당 카테고리 이미 존재한 카테고리인지 ?

        const parentType = $('.selected-category-option').val();
        const type = $('input[name=type]').val();
        const title = $('input[name=title]').val();

        const data = {};

        if (parentType === "" || type === "" || title === "") {
            alert("type or title input plz");
            return;
        } else {
            data.parent_type = parentType;
            data.type = type;
            data.title = title;
        }

        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "/category",
            data: JSON.stringify(data),

            // beforeSend: function (xhr) {
            //     xhr.setRequestHeader(header, token);
            // },

            success: function (data) {
                if (data) {
                    const parentType = data.parent_type;
                    const type = data.type;
                    alert(parentType + '[' + type + ']가 성공적으로 추가 되었습니다.');
                    // setTimeout 설정해주기
                    window.location.href = "/";
                }
            },
            error: function (e) {
                console.log("ERROR : ", e);
            }
        });
    });

    $('.selected-category-option').on('click', function () {
        if (parentCategoryUl.hasClass('hide')) {
            parentCategoryUl.removeClass('hide');
            parentCategoryUl.addClass('show');
        } else {
            parentCategoryUl.addClass('hide');
            parentCategoryUl.removeClass('show');
        }
    });

    $('.parent-category ul li').on('click', function () {
        parentCategoryUl.removeClass('show');
        parentCategoryUl.addClass('hide');
        $('.selected-category-option').html($(this).text());
        $('.selected-category-option').val($(this).text());
    });

    $('.cancel-btn, .top-cancel-btn').on("click", function () {
        $('.overlay').hide();
        $('.store-popup-wrapper').css('display', 'none');
        $('.category-popup-wrapper').css('display', 'none');
        $('.category-result-popup-wrapper').css('display', 'none');
    });
})

function getCurrentTime() {
    const clockContainer = $('.main-header__right--clock');

    const date = new Date();
    const minutes = date.getMinutes();
    const hours = date.getHours();
    const seconds = date.getSeconds();

    const time = `Time : ${hours < 10 ? `0${hours}` : hours}:${minutes < 10 ? `0${minutes}` : minutes}:${seconds < 10 ? `0${seconds}` : seconds}`

    clockContainer.text(time);
}

function categoryUpdate() {
    console.log("1234ß");
}

function categoryDelete(id) {
    $.ajax({
        type: "DELETE",
        url: "/category/" + id,
        success: function (data) {
            if (data) {
                alert("삭제되었습니다.");
                window.location.href = "/";
            }
        },
        error: function (e) {
            console.log("ERROR : ", e);
        }
    });
}