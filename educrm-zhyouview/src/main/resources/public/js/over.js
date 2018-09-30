$(function() {
    $(".con1 a").click(function() {
        $(".con1 a").removeClass("active");
        $(this).addClass("active");
    });
    $(".con2 a").click(function() {
        $(".con2 a").removeClass("active");
        $(this).addClass("active");
    });
    $(".card a").mouseenter(function(){
        $(".show-con a").removeClass("opt");
        $(this).addClass("opt");
    });
    $(".card a").mouseleave(function(){
        $(this).removeClass("opt");
    });


    // $('a.nav-link').each(function(){
    //     var href = $(this).attr('href');
    //     console.log(href);
    // });

    var path = location.pathname;

    //alert(path);
    $(".nav-link").removeClass("active");
    if(path == $("#coursenav").attr('href')){
        $("#coursenav").addClass("active");
    }else if(path == $("#questionsnav").attr('href')){
        $("#questionsnav").addClass("active");
    } else if(path == $("#teachernav").attr('href')){
        $("#teachernav").addClass("active");
    } else {
        $("#aboutnav").addClass("active");
    }






})
