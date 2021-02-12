$(document).ready(function() {

    let duplicate = false;

    $(".duplicatie-btn").on('click', function(e) {
        const email = jQuery('input[name=email]').val();
        console.log("clicked");
        duplicateCheck(email);
    })

    $("form").on("submit", function(e){
        e.preventDefault();
        const email = jQuery('input[name=email]').val();
        const account = jQuery('input[name=account]').val();
        const password1 = jQuery('input[name=password1]').val();
        const password2 = jQuery('input[name=password2]').val();
        const phoneNumber = jQuery('input[name=phone_number]').val();

        const token = $("meta[name='_csrf']").attr("content");
        const header = $("meta[name='_csrf_header']").attr("content");

        const data= {};

        if(!email) {
            alert('메일을 다시 확인해주세요.');
            return false;
        }

        if(!validatePassword(password1) || !validatePassword(password2)) {
            alert('7 to 15 characters which contain only characters, numeric digits, underscore and first character must be a letter');
            return false;
        }

        if(password1 !== password2) {
            alert('패스워드가 일치하지 않습니다.');
            return false;
        }

        // if(!duplicate) {
        //     alert('메일중복을 확인해주세요.');
        //     return false;
        // }

        data.email = email;
        data.account = account;
        data.password = password1;
        data.phone_number = phoneNumber;
        data.auth = "ROLE_USER"; // auth Check

        $.ajax( {

            type: "POST",
            contentType: "application/json",
            url: "/signup",
            data: JSON.stringify(data),

            beforeSend: function(xhr) {
                xhr.setRequestHeader(header, token);
            },

            success: function(data) {
                if(data) {
                    console.log(data);
                    alert('성공적으로 추가되었습니다.');
                    window.location.href="/login";
                }
            },
            error: function(e) {
                console.log("ERROR : ", e);
            }
        })
    })
});

function validateEmail(email) {
    const pattern = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return pattern.test(email.toLowerCase());
}

function validatePassword(password) {
    const pattern = /^[A-Za-z]\w{7,14}$/;
    return pattern.test(password.toLowerCase());
}

function duplicateCheck(email) {
    // 메일 중복 Check 함수
    if(!email) {
        alert("이메일을 입력해주세요.");
        return false;
    }

    $.ajax({
        type: "GET",
        url: "/emailCheck",
        data: {
            "email": email
        },
        success: function(data) {
            if(data === "JOIN") {
                console.log(data);
                alert('사용 가능한 email 입니다.');
                duplicate = true;
                return true;
            } else {
                alert('이미 사용중인 email입니다. 다른 email를 입력하세요.');
                duplicate = false;
                return false;
            }
        }

    })
}