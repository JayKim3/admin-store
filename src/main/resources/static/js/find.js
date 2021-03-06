$(document).ready(function() {
    $("form").on("submit", function(e){
        e.preventDefault();
        const email = jQuery('input[name=email]').val();

        // const token = $("meta[name='_csrf']").attr("content");
        // const header = $("meta[name='_csrf_header']").attr("content");

        if(!email) {
            swal('메일을 다시 확인해주세요.');
            return false;
        }

        $.ajax({
            type: "GET",
            url: "/emailCheck",
            data: {
                "email" : email
            },

            success: function(data) {
                if(data === "EXIST") {
                    swal('이메일이 확인되었습니다. 메일을 확인해주세요.').then((OK) => {
                        if(OK) {
                            $.ajax({
                                type: "POST",
                                url: "/find",
                                data: { email: email },
                                // beforeSend: function(xhr) {
                                //     xhr.setRequestHeader(header, token);
                                // },
                            })
                        }
                    });
                } else {
                    alert('존재하지 않는 이메일입니다. 다시 확인해주세요.');
                    return false;
                }
            }
        })
    })
})