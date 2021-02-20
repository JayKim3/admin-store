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
})