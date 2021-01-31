$(document).ready(function() {

});

function signgupCheck() {
    const email = jQuery('input[name=email]').val();
    const account = jQuery('input[name=account]').val();
    const password1 = jQuery('input[name=password1]').val();
    const password2 = jQuery('input[name=password2]').val();
    const phoneNumber = jQuery('input[name=phone_number]').val();

    const data= {};

    console.log(email, account, password1, password2, phoneNumber);

    if(!email) {
        alert('이메일을 입력해주세요.');
        return false;
    }

    if(!account) {
        alert('계정을 입력해주세요.');
        return false;
    }

    if(!password1 || !password2) {
        alert('패스워드를 입력해주세요.');
        return false;
    }

    if(!phoneNumber) {
        alert('전화번호를 입력해주세요.');
        return false;
    }

    if(password1 !== password2) {
        alert('패스워드가 일치하지 않습니다.');
        return false;
    }

    data.email = email;
    data.account = account;
    data.password = password1;
    data.phone_number = phoneNumber;
    data.auth = "USER";

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/api/user/signup",
        data: JSON.stringify(data),
        dataType: 'json',
        success: function(data) {
            if(data) {
                alert('성공적으로 추가되었습니다.');
                window.location.href = "/";
            }
        },
        error: function(e) {
            console.log("ERROR : ", e);
        }
    })

}