$(document).ready(function(){
    const monthNames = [ "Jan", "Feb", "Mar", "Apr", "May", "Jun",
        "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" ];

    loadUserActiveData(monthNames);
    loadUserYearlyData(monthNames);
    loadCountryData();

    const timeCtx = document.getElementById('timePieChart').getContext('2d');
    const followerCtx = document.getElementById('followerChart').getContext('2d');

    const timePieChart = new Chart(timeCtx, {
        type: 'pie',
        data: {
            labels: ["More than 1 hour", "Between 30 min and 1 hour", "Less than 30 min"],
            datasets: [
                {
                    data: [64, 21, 15],
                    backgroundColor: [
                        'rgba(121, 94, 241, 1.0)',
                        'rgba(238, 140, 203, 1.0)',
                        "#36A2EB"
                    ],
                }]
        },
        options: {
            title: {
                display: true,
                text: 'Time spent per day',
                fontSize: 20,
                fontColor: '#ffffff'
            },
            responsive: true,
            maintainAspectRatio: false,
        }
    })

    const followerChart = new Chart(followerCtx, {
        type: 'doughnut',
        data: {
            datasets: [{
                backgroundColor: ['#8d3ada', '#413f51'],
                borderColor: 'none',
                // Below I just pull out 1 number from the db
                data: [57, 43]
        }]
        },
        options: {
            cutoutPercentage: 90,
        },
        plugins: {
            beforeDraw: function(chart) {
                let width = chart.chart.width,
                    height = chart.chart.height,
                    ctx = chart.chart.ctx;

                ctx.font = "1.5em sans-serif";

                let textX = width / 2 - 10,
                    textY = height / 2 + 8;

                ctx.fillStyle = '#ffffff';
                ctx.fillText(12, textX, textY);
                ctx.save();
            }
        }
    })

})

function loadUserActiveData(monthNames) {
    const nowDate = new Date();
    const currentMonth = monthNames[nowDate.getMonth()];

    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "/admin/monthlyUser",
    }).done(function (data){
        const activeCtx = document.getElementById('activeChart').getContext('2d');
        const newUserCtx = document.getElementById('newUserChart').getContext('2d');
        // created_at 값이 1, 15 ,30 일때의 값 구하기;
        const activeUser = Array(0, 0, 0);

        $.each(data, function(index, user) {
            const day = user.created_at.substr(8,2);
            if(day === '01') {
                activeUser[0] += 1;
            }
            else if(day <= 15) {
                activeUser[1] += 1;
            } else if(day > 15 && day <= 30) {
                activeUser[2] += 1;
            }
        });

        new Chart(activeCtx, {
            type: 'line',
            data: {
                labels: [currentMonth + ' 1', currentMonth + ' 15', currentMonth + ' 30'],
                datasets: [{
                    label: 'Active Users',
                    data: [activeUser[0], activeUser[1], activeUser[2]],
                    pointBackgroundColor: 'rgba(238, 140, 203, 1.0)',
                    borderColor: [
                        'rgba(121, 94, 241, 1.0)',
                    ],
                    borderWidth: 1
                }]
            },
        });
        // 이 달의 새로운 유저
        $('.user-count').text(data.length + ' 명');
        const newUserChart = new Chart(newUserCtx, {
            type: 'doughnut',
            data: {
                datasets: [{
                    backgroundColor: ['#8d3ada', '#413f51'],
                    borderColor: 'none',
                    // Below I just pull out 1 number from the db
                    data: [57, 43]
                }]
            },
            options: {
                cutoutPercentage: 90,
            },
            plugins: {
                beforeDraw: function(chart) {
                    let width = chart.chart.width,
                        height = chart.chart.height,
                        ctx = chart.chart.ctx;

                    ctx.font = "1.5em sans-serif";

                    let textX = width / 2 - 10,
                        textY = height / 2 + 8;

                    ctx.fillStyle = '#ffffff';
                    ctx.fillText(data.length, textX, textY);
                    ctx.save();
                }
            }
        })
    })
}

function loadUserYearlyData(monthNames) {

    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "/admin/yearlyUser",
    }).done(function (data){
        const yearlyCtx = document.getElementById('YearlyChart').getContext('2d');
        const yearlyUser = Array(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);

        $.each(data, function(index, user) {
            const month = user.created_at.substr(5,2);

            // month 값이 01~12까지 있는데 해당 값에따라 yearlyUser에 넣어주기
            // if 말고 다르게 넣을 수 없을까 ?
            if(month == '01') {
                yearlyUser[0] += 1;
            } else if(month == '02') {
                yearlyUser[1] += 1;
            } else if(month == '03') {
                yearlyUser[2] += 1;
            } else if(month == '04') {
                yearlyUser[3] += 1;
            } else if(month == '05') {
                yearlyUser[4] += 1;
            } else if(month == '06') {
                yearlyUser[5] += 1;
            } else if(month == '07') {
                yearlyUser[6] += 1;
            } else if(month == '08') {
                yearlyUser[7] += 1;
            } else if(month == '09') {
                yearlyUser[8] += 1;
            } else if(month == '10') {
                yearlyUser[9] += 1;
            } else if(month == '11') {
                yearlyUser[10] += 1;
            } else if(month == '12') {
                yearlyUser[11] += 1;
            }
        });

        new Chart(yearlyCtx, {
            type: 'bar',
            data: {
                labels: monthNames,
                datasets: [
                    {
                        label: "Yearly Users",
                        backgroundColor: '#36A2EB',
                        data: [yearlyUser[0],yearlyUser[1],yearlyUser[2],yearlyUser[3],yearlyUser[4],yearlyUser[5],yearlyUser[6],
                            yearlyUser[7],yearlyUser[8],yearlyUser[9],yearlyUser[10],yearlyUser[11]],
                    }
                ]
            },
            options : {
                scales: {
                    yAxes: [{
                        scaleLabel: {
                            display: true,
                            labelString: 'Unit : K'
                        }
                    }],
                    scales: {
                        xAxes: [{
                            barThickness: 6,  // number (pixels) or 'flex'
                            maxBarThickness: 8 // number (pixels)
                        }]
                    }
                },
            }
        })
    })
}

function loadCountryData() {
    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "/admin/countryUser",
    }).done(function (data) {
        const CountryCtx = document.getElementById('countryChart').getContext('2d');
        const countryLabel = ['KOREA', 'US', 'JAPEN', 'CHINA', "OTHER"];
        const countryCount = [0, 0, 0, 0, 0];
        for(let i = 0; i < data.length; i++) {
            if(data[i] == 'Korea, Republic of') {
                countryCount[0] += 1;
            } else if(data[i] == 'United States of America') {
                countryCount[1] += 1;
            } else if(data[i] == 'Japen') {
                countryCount[2] += 1;
            } else if(data[i] == 'China') {
                countryCount[3] += 1;
            } else {
                countryCount[4] += 1;
            }
        }

        console.log(countryLabel, countryCount);

        new Chart(CountryCtx, {
            type: 'horizontalBar',
            data: {
                labels: countryLabel,
                datasets: [{
                    label: "Country Users",
                    data: countryCount,
                    backgroundColor: 'rgba(121, 94, 241, 1.0)',
                    fill: false,
                    borderWidth: 1
                }]
            },
            options: {
                responsive: true,
            }
        });
    })
}