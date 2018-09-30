function subString(str, len, hasDot) {
    var newLength = 0;
    var newStr = "";
    var chineseRegex = /[^\x00-\xff]/g;
    var singleChar = "";
    var strLength = str.replace(chineseRegex, "**").length;
    for (var i = 0; i < strLength; i++) {
        singleChar = str.charAt(i).toString();
        if (singleChar.match(chineseRegex) != null) {
            newLength += 2;
        } else {
            newLength++;
        }
        if (newLength > len) {
            break;
        }
        newStr += singleChar;
    }

    if (hasDot && strLength > len) {
        newStr += "...";
    }
    return newStr;
}


//按钮-隐藏右侧和显示右侧
$("#j-hideRightBtn").bind("click", function () {
    /*
    $("#rgtDiv").stop();
    $("#rgtDiv").queue(function(){
        $(this).animate({width:0},300);
    });

    $("#rgtDiv").dequeue();
    */
    if ($("#lftDiv").css("right") == "320px") {
        $(this).addClass("u-hiderightbtn-hide");
        $("#rgtDiv,.coursebox").stop();
        $(".coursebox").queue(function () {
            $(this).animate({right: 0}, 300);
        });

        $("#rgtDiv").queue(function () {
            $(this).animate({width: 0}, 300);
        });
        $("#rgtDiv,.coursebox").dequeue();
    } else {
        $(this).removeClass("u-hiderightbtn-hide");
        $("#rgtDiv,.coursebox").stop();
        $(".coursebox").queue(function () {
            $(this).animate({right: 320}, 300);
        });

        $("#rgtDiv").queue(function () {
            $(this).animate({width: 320}, 300);
        });
        $("#rgtDiv,.coursebox").dequeue();
    }
})
//
//click
$(".clkBtn").on("click", function () {
    if ($(this).next("div").is(':hidden')) {
        $(this).next("div.clkDiv").slideDown("fast");
    }
    else {
        $(this).next("div.clkDiv").slideUp("fast");
    }
    return false;
})
$("body").on("click", function () {
    $("div.clkDiv").hide();
})

function changevideo(src) {

    var object = document.createElement("script");
    object.src = src;
    object.type = "text/javascript";
    object.charset = "utf-8";

    document.getElementById("learn-box").appendChild(object);

}

$(".capWrap .btn-success").on("click", function () {
    $(this).parent("div").prev("textarea")
})

$(".fomCont textarea").on("keydown keyup click", function () {
    $(".capWrap .cRed").remove();
})


