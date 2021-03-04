$(document).ready(function() {
    const weather = $(".setting__weather");

    const API_KEY = '504117424e458b4722a152467f38f333';
    const COORDS = 'coords';

    loadCoords();

    $('input[name=input-notification], input[name=input-privacy]').change(function(e){
        const toggleText = $(this).siblings(".slider-text");
        if($(this).is(":checked")) {
            toggleText.text('On');
            toggleText.animate({left: '6px'}, 600);
        } else {
            toggleText.text('Off');
            toggleText.animate({left: '32px'}, 600);
        }
    });

    $('input[name=input-mode]').change(function(e){
        const toggleText = $(this).siblings(".slider-text");
        if($(this).is(":checked")) {
            toggleText.text('On');
            $('html').attr('color-theme', 'dark');
            toggleText.animate({left: '6px'}, 600);
        } else {
            toggleText.text('Off');
            $('html').attr('color-theme', 'light');
            toggleText.animate({left: '32px'}, 600);
        }
    })

    $('input[name=input-language]').change(function (e){
        const toggleText = $(this).siblings(".slider-text");
        if($(this).is(":checked")) {
            toggleText.text('Ko');
            toggleText.animate({left: '6px'}, 600);
        } else {
            toggleText.text('En');
            toggleText.animate({left: '32px'}, 600);
        }
    })

    $('.help-btn').on('click', function () {
        alert("서비스 준비중입니다.");
    })

    function getWeather(lat, lng) {
        fetch(`https://api.openweathermap.org/data/2.5/weather?lat=${lat}&lon=${lng}&appid=${API_KEY}&units=metric`)
            .then(function(response){
                return response.json();
            }).then(function(json){
            const temperature = json.main.temp;
            const place = json.name;
            weather.text(`Temp : ${temperature} @ Place : ${place}`);
        });
    }

    function saveCoords(coordsObj) {
        localStorage.setItem(COORDS, JSON.stringify(coordsObj));
    }

    function handleGeoSuccess(position) {
        const latitude =  position.coords.latitude;
        const longitude = position.coords.longitude;

        const coordsObj =  {
            latitude : latitude,
            longitude : longitude
        };
        saveCoords(coordsObj);
        getWeather(latitude, longitude);
    }

    function handleGeoError() {
        console.log("Cant access geo location");
    }

    function askForCoords() {
        navigator.geolocation.getCurrentPosition(handleGeoSuccess, handleGeoError)
    }

    function loadCoords() {
        const loadedCords = localStorage.getItem(COORDS);

        if(loadedCords === null) {
            askForCoords();
        } else {
            const parseCoords = JSON.parse(loadedCords);
            getWeather(parseCoords.latitude, parseCoords.longitude);
        }
    }
});
