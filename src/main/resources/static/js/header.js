$(document).ready(function(){

    getCurrentTime();
    setInterval(getCurrentTime, 1000);

})

function getCurrentTime() {
    const clockContainer = $('.main-header__right--clock');

    const date = new Date();
    const minutes = date.getMinutes();
    const hours = date.getHours();
    const seconds = date.getSeconds();

    const time = `Time : ${hours < 10 ? `0${hours}` : hours}:${minutes < 10 ?  `0${minutes}` : minutes}:${seconds < 10 ? `0${seconds}` : seconds}`

    clockContainer.text(time);
}