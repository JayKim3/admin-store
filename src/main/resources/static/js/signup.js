$(document).ready(function() {
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

        if(password1 !== password2) {
            alert('패스워드가 일치하지 않습니다.');
            return false;
        }

        data.email = email;
        data.account = account;
        data.password = password1;
        data.phone_number = phoneNumber;
        data.auth = "USER"; // auth Check

        $.ajax( {

            type: "POST",
            contentType: "application/json",
            url: "/user",
            data: JSON.stringify(data),

            beforeSend: function(xhr) {
                xhr.setRequestHeader(header, token);
            },

            success: function(data) {
                if(data) {
                    console.log(data);
                    alert('성공적으로 추가되었습니다.');
                    window.location.href="/";
                }
            },
            error: function(e) {
                console.log("ERROR : ", e);
            }
        })
    })
});