
//交卷
function assignment(){
    $(".testCon h4").css("color","#555");
    var _temp_tip = "yes";
    var tall = 0;

    $(".testCon").each(function(i){
        var type = $(this).attr("data-type");
        if(type==1||type==2){
            if($(this).find('input[type="radio"]:checked').val() == undefined){
                _temp_tip = "no";
                $(this).find("h4").css("color","#00B895");
            }
        }
        tall++;
    });

    if(_temp_tip == "no"){
        alert("还有题目没做完");

        return false;
    }else if(_temp_tip == "yes"){
        $('button.active').hide();
        $('a.other').show();
    }
    var err = 0;
    $(".testCon").each(function(i){
        var type = $(this).attr("data-type").trim();
        var aw = $(this).attr("data-answer");
        var set_answer = '';
        var set_answer2='';
        if(type==1){
            var rd = $(this).find('input[type="radio"]:checked').val().trim();

            if(rd != aw){
                $(this).find("h4").css("color","red");
                err++;
            }
            set_answer = rd;
            set_answer2 = rd;

        }else if(type==2){

            var rpd = $(this).find('input[type="radio"]:checked').val();
           // rpd = rpd==0 ? "错误" : (rpd==1 ? "正确" : "");
            if(rpd != aw){
                $(this).find("h4").css("color","red");
                err++;
            }
            rpd = rpd==0 ? "错误" : (rpd==1 ? "正确" : "");


            var rpd2='';
            if(rpd =='错误'){
                rpd2='0'
            }else{
                rpd2='1'
            }
            set_answer2=rpd2;
            set_answer = rpd;

        }
        $(this).find("input.select").val( $(this).find("input.select").val()+","+set_answer2+","+$(this).find("input.trueanswer").val());
        $(this).find("span.userAnswer").text(set_answer);

    });
     $(".topic-answer").show();
     $("#formId").submit();


}
//数字转换成大写字母
/*
function convert(num){
    num = num + 1;
    return num <= 26 ? String.fromCharCode(num + 64) : convert(~~((num - 1) / 26)) + convert(num % 26 || 26);
}*/
