/**
 * Created by Administrator on 2018/3/7 0007.
 */
//��ʾ��ʾ��Ŀǰ��������(txt��Ҫ��ʾ���ı���time���Զ��رյ�ʱ�䣨�����õĻ�Ĭ��1500���룩��status��Ĭ��0Ϊ������ʾ��1Ϊ��ȷ��ʾ��)
function showTips(txt,time,status)
{
    var htmlCon = '';
    if(txt != ''){
        if(status != 0 && status != undefined){
            htmlCon = '<div class="tipsBox">'+txt+'</div>';
        }else{
            htmlCon = '<div class="tipsBox" >'+txt+'</div>';
        }
        $('body').prepend(htmlCon);
        if(time == '' || time == undefined){
            time = 1500;
        }
        setTimeout(function(){ $('.tipsBox').remove(); },time);
    }
}



function isPoneAvailable(str) {
    var myreg=/^[1][3,4,5,7,8][0-9]{9}$/;
    if (!myreg.test(str)) {
        return false;
    } else {
        return true;
    }
}
