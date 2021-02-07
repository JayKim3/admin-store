$(document).ready(function(){
    const activeCtx = document.getElementById('activeChart').getContext('2d');
    const timeCtx = document.getElementById('timePieChart').getContext('2d');
    const followerCtx = document.getElementById('followerChart').getContext('2d');
    const newUserCtx = document.getElementById('newUserChart').getContext('2d');
    const yearlyCtx = document.getElementById('YearlyChart').getContext('2d');

    const activeChart = new Chart(activeCtx, {
        type: 'line',
        data: {
            labels: ['Feb 3', 'Feb 10' , 'Feb17'],
            datasets: [{
                label: 'Active Users',
                data: [12, 19, 3],
                pointBackgroundColor: 'rgba(238, 140, 203, 1.0)',
                borderColor: [
                    'rgba(121, 94, 241, 1.0)',
                ],
                borderWidth: 1
            }]
        },
    });

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
        }
    })

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
        }
    })

    const yearlyChart = new Chart(yearlyCtx, {
        type: 'bar',
        data: {
            labels: ["Jan", "Feb", "Mar", "Apr", "Jun", "Jul", "Aug"],
            datasets: [
                {
                    label: "Yearly Users",
                    backgroundColor: '#36A2EB',
                    data: [43,105,76,50,33,97,52],
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