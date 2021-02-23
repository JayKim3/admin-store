$(document).ready(function() {
    $('input[name=input-notification]').change(function(e){
        const toggleText = $(this).siblings(".slider-text");
        if($(this).is(":checked")) {
            toggleText.text('On');
            toggleText.animate({left: '6px'}, 600);
        } else {
            toggleText.text('Off');
            toggleText.animate({left: '32px'}, 600);
        }
    })
})