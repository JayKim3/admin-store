$(document).ready(function() {
    $('#profile_image_btn').on('change', function(e) {
        readImage(e.target);
    });
})



function readImage(input) {
    if(input.files && input.files[0]) {
        const reader = new FileReader()
        reader.onload = function(e) {
            $('.profile_image').attr('src', e.target.result);
        }
        reader.readAsDataURL(input.files[0]);
    }
}