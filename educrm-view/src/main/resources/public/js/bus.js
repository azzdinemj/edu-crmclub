
/*-------------------------- +
 获取id, class, tagName
 +-------------------------- */
var get = {
    byId: function(id) {
        return typeof id === "string" ? document.getElementById(id) : id
    },
    byClass: function(sClass, oParent) {
        var aClass = [];
        var reClass = new RegExp("(^| )" + sClass + "( |$)");
        var aElem = this.byTagName("*", oParent);
        for (var i = 0; i < aElem.length; i++) reClass.test(aElem[i].className) && aClass.push(aElem[i]);
        return aClass
    },
    byTagName: function(elem, obj) {
        return (obj || document).getElementsByTagName(elem)
    }
};
/*-------------------------- +
 事件绑定, 删除
 +-------------------------- */
var EventUtil = {
    addHandler: function (oElement, sEvent, fnHandler) {
        // alert(oElement.addEventListener);
        oElement.addEventListener ? oElement.addEventListener(sEvent, fnHandler, false) : (oElement["_" + sEvent + fnHandler] = fnHandler, oElement[sEvent + fnHandler] = function () {oElement["_" + sEvent + fnHandler]()}, oElement.attachEvent("on" + sEvent, oElement[sEvent + fnHandler]))
    },
    removeHandler: function (oElement, sEvent, fnHandler) {
        oElement.removeEventListener ? oElement.removeEventListener(sEvent, fnHandler, false) : oElement.detachEvent("on" + sEvent, oElement[sEvent + fnHandler])
    },
    addLoadHandler: function (fnHandler) {
        this.addHandler(window, "load", fnHandler)
    }
};

EventUtil.addLoadHandler(function ()
{
    var oMsgBox = get.byId("msgBox");
    var oUserName = get.byId("userName");
    var oConBox = get.byId("conBox");
    var oSendBtn = get.byId("sendBtn");
    var oBlue = get.byId("oBlue");
    var oList = get.byClass("list")[0];
    var oUl = get.byTagName("ul", oList)[0];
    var aLi = get.byTagName("li", oList);
    //var aFtxt = get.byClass("f-text", oMsgBox);
    var aImg = get.byClass("addreschan");
    //为按钮绑定发送事件
    EventUtil.addHandler(oSendBtn, "click", fnSend);
    //EventUtil.addHandler(oBlue, "mouseover", function () {this.className = "addbtn"});
    //EventUtil.addHandler(aImg, "mouseover", del);
    //发送函数
    function fnSend () {
        {
            var oLi = document.createElement("li");
            //var oDate = new Date();
            oLi.innerHTML = "<div class=\"addreschan\">"+
							 	"<b class=\"userName\">" + oUserName.value + "</b>"+
								"<a data-toggle=\"modal\" data-target=\"#myModal\" class=\"msginfo blue\">" + oConBox.value.replace(/<[^>]*>|&nbsp;/ig, "") + "</a>"+
								"<img class=\"del\" id=\'DEL\' src=\"../../images/delete.png\">"+
				            "</div>";
            //插入元素
            aLi.length ? oUl.insertBefore(oLi, aLi[0]) : oUl.appendChild(oLi);

            //liHover();

            //delLi()
        }

    }
    function del(){

        //$(this).next(".del").addClass("shows").removeClass("del");
    }

});
