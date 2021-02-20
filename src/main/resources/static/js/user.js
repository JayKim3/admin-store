$(document).ready(function() {
    // pagination 만들기

    $('input[id=user_all_checkbox]').on('click', function(e){
        if($(this).prop("checked")) {
            $('input[name=user_checkbox]').prop('checked', true);
        } else {
            $('input[name=user_checkbox]').prop('checked', false);
        }
    })

    // 삭제 버튼 클릭 시 체크박스 개수 가져와서 ajax 통신
    $('#delete-btn').on('click', function() {
        const cnt = $('input[name=user_checkbox]:checked').length;
        if(cnt == 0) {
            swal('1개 이상 선택해주세요.');
            return;
        }
        const deleteUserArray = new Array();

        $('input[name=user_checkbox]').each(function(){
            if($(this).prop("checked")) {
                deleteUserArray.push(parseInt($(this).prop("id")));
            }
        });

        $.ajax({
            type: 'GET',
            url: "/admin/user/delete",
            data: {
                "deleteUserArray" : deleteUserArray
            },
            success: function(data) {
                if(data === 1 ) {
                    swal("성공적으로 삭제 되었습니다.");
                    setTimeout(function(){
                        window.location.href= "/admin/user/0"}, 2000);
                } else {
                    swal("예기치 못한 에러 발생");
                    window.location.href= "/"
                }
            },
            error: function(e) {
                console.log("ERROR: ", e);
            }
        })
    })

    $('#edit-btn').on('click', function() {
        const cnt = $('input[name=user_checkbox]:checked').length;

        if(cnt != 1) {
            swal("해당 유저 한 명만 선택 후 수정버튼을 클릭해주세요");
            return;
        }

        const id = $('input[name=user_checkbox]:checked').attr("id");
        $('.user-popup-wrapper').css('display', 'block');

        $.ajax({
            type: "GET",
            contentType: "application/json",
            url: "/admin/user/modify/" + id,
            success: function(data) {
                const user = data.body;
                // 전달받은 데이터 동적으로 화면 그려주기.
                $('.user-info-email').text(user.email);
                $('input[name=user-info-id]').val(user.id);
                $('input[name=user-info-password]').val(user.password);
                $('input[name=user-info-account]').val(user.account);
                $('input[name=user-info-auth]').val(user.auth);
                $('input[name=user-info-phoneNumber]').val(user.phone_number);
            },
            error: function(e) {
                console.log("ERROR : ", e);
            }
        });
    });

    $('.user-modifty-btn').on('click', function(e) {
        e.preventDefault();
        const data = {};
        const id = $('input[name=user-info-id]').val();
        const password = $('input[name=user-info-password]').val();
        const email = $('.user-info-email').text();
        const account = $('input[name=user-info-account]').val();
        const auth = $('input[name=user-info-auth]').val();
        const phoneNumber = $('input[name=user-info-phoneNumber]').val();

        // 방어코드 구현

        data.id = id;
        data.password = password;
        data.email = email;
        data.account = account;
        data.auth = auth;
        data.phone_number = phoneNumber;

        $.ajax({
            type: "PUT",
            contentType: "application/json",
            url: "/admin/user/modify/",
            data: JSON.stringify(data),
            success: function(data) {
                console.log(data);
                if(data) {
                    setTimeout(function (){
                        swal("성공적으로 변경하였습니다.");
                        $('.user-popup-wrapper').css('display', 'none');
                    }, 1000);
                }
            },
            error: function(e) {
                console.log("ERROR : ", e);
            }
        });
    });

    $('.user-popup-wrapper .user-cancel-btn').on("click", function(){
        $('.user-popup-wrapper').css('display', 'none');
    });
})