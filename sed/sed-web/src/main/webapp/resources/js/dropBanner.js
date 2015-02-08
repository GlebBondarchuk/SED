function dropBanner() {
    for (var i = 1; i < 99999; i++)
        window.clearInterval(i);

    $('.carousel').carousel({
        interval: 5000 //changes the speed
    });
}

///*Delete Banner*/
//iframe {
//    display: none !important;
//    position: absolute;
//    height: 1px !important;
//    width: 1px !important;
//    overflow: hidden
//}
