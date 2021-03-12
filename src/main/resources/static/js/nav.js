$(document).ready(function() {
    const email = jQuery('.nav-email-text').text();
    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "/nav",
        data: {
            "email": email
        },
        success: function (data) {
            // 셋팅
            if (data != "Not Found") {
                jQuery('.nav_profile_image').attr("src", `/images/${email}.${data}`);
            } else {
                jQuery('.nav_profile_image').attr("src", `/img/no-profile.jpg`);
            }
        },
        error: function (e) {
            console.log(e);
        }
    });
})