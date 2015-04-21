//Flot Line Chart
$(document).ready(function () {
    plot();
    function plot() {
        var options = {
            series: {
                lines: {
                    show: true
                },
                points: {
                    show: true
                }
            },
            grid: {
                hoverable: true //IMPORTANT! this is needed for tooltip to work
            },
            yaxis: {
                min: 0,
                tickDecimals: 0
            },
            xaxis: {
                mode: 'time',
                timeformat: "%d/%m/%y",
                minTickSize: [1, "day"]
            },
            tooltip: true,
            tooltipOpts: {
                content: "Дата: %x, Количество посещений: %y"
            }
        };

        $.ajax(mainContextPath + "/statistics/data/line").done(function (data) {
            var plotObj = $.plot($("#flot-line-chart"), [{
                    data: JSON.parse(data),
                    label: " Количество посещений"
                }],
                options);
        });
    }
});

//Flot Pie Chart
$(function () {
    $.ajax(mainContextPath + "/statistics/data/pie").done(function (data) {
        var plotObj = $.plot($("#flot-pie-chart"), JSON.parse(data), {
            series: {
                pie: {
                    show: true
                }
            },
            grid: {
                hoverable: true
            },
            tooltip: true,
            legend: {show: true, noColumns: 3},
            tooltipOpts: {
                content: "%p.0%, %s", // show percentages, rounding to 2 decimal places
                shifts: {
                    x: 20,
                    y: 0
                },
                defaultTheme: false
            }
        });
    });
});
