//顶部hover
$(document).ready(function(){
	if($(document).width()>1025){
		$(".hidden_btn").on({
			mouseenter:function(){
				$(this).addClass("current");
				
				 $(this).children("div.dropDiv").stop();
				 $(this).children("div.dropDiv").css("display","block")
				 $(this).children("div.dropDiv").queue(function(){
					$(this).animate({opacity:1.0},300);
				});
				 $(this).children("div.dropDiv").dequeue();
			},
			mouseleave:function(){
				$(this).removeClass("current");

				 $(this).children("div.dropDiv").stop();
				 $(this).children("div.dropDiv").css("display","none")
				 $(this).children("div.dropDiv").queue(function(){
					$(this).animate({opacity:0},300);
				});
				 $(this).children("div.dropDiv").dequeue();
			}
		});
	}else{
		$("a.find,.hidden_btn>em,.rgtInfo").on("mousedown", function(){
			if($(this).next("div").is(':hidden')){
				$(this).next("div.dropDiv").css({display:"block",opacity:"1"});
				return false;
			}
			else{
				$(this).next("div.dropDiv").css({display:"none",opacity:"0"});
			}
		})
	}
})



$(function(){   
	$(".showDiv").hover(function(){
			$(this).children("div").show();
		},function(){
			$(this).children("div").hide();
	})
	//右侧帮助
	$('#onlineAphone a').hover(function(){
		if($(this).attr("id")=="phoneCls"){
		$(this).stop(true,true).animate({'width':'130px'},200);
		}else{
		$(this).stop(true,true).animate({'width':'100px'},200);
		}
	},function(){
		$(this).stop(true,true).animate({'width':'34px'},200);
	});

	//input密码体验调整
	$(".passIpt").focus(function(){
		  if($(this).prev("em:contains('密码')")){ 
			$(this).prev("em").html("")
			$(this).prev("em").removeClass("cGray");
		  }
	}).blur(function(){
		 if ($(this).val() == '' && $(this).prev("em").hasClass("passTip")) {
			$(this).prev("em").html("密码");
			$(this).prev("em").addClass("cGray");
		 }else if($(this).val() == '' && $(this).prev("em").hasClass("confimCls")){
			 $(this).prev("em").html("确认密码");
			 $(this).prev("em").addClass("cGray");
		}else if($(this).val() == '' && $(this).prev("em").hasClass("nktips")){
			 $(this).prev("em").html("昵称");
			 $(this).prev("em").addClass("cGray");
		}
	})
});
$(window).load(function(){
	$("iframe[src^='http://x.adpro.cn/']").parent("div").css("display","none")//隐藏百度弹出广告
})
		$(window).scroll(function(){
		if ($("html").scrollTop()>100 ||$(window).scrollTop()>100 ){
		$("#back-to-top").fadeIn(100);
		}
		else
		{
		$("#back-to-top").fadeOut(100);
		}
		});
		//当点击跳转链接后，回到页面顶部位置
		$("#back-to-top").click(function(){
		$('body,html').animate({scrollTop:0},500);
		return false;
		});
//弹出层
function popup(id, canhide, size)
{
	if(!id)	return false;
	canhide = canhide || false;
	size = size || 'small'; //大或小的框
	var h_ght =  - $('#'+id).height()/2;
	if(size=='small')
	{
		$.blockUI({ 
			message: $('#'+id), 
			css: { 
				top:		'45%',
				left:		'50%',
				textAlign:	'left',
				marginLeft:     '-200px', 
				marginTop:      h_ght, 
				width: '400px',
				background:'none'
			}
		});
	}
	else
	{
		$.blockUI({ 
			message: $('#'+id),
			css: { 
				top:		'45%',
				left:		'50%',
				textAlign:	'left',
				marginLeft:     '-350px', 
				marginTop:      h_ght, 
				width: '700px',
				background:'none'
			} 
		}); 
	}
	$('#'+id).find('.close').on('click',function(){
		$.unblockUI();
	});
	$('#'+id).find('.close').click($.unblockUI);
	if( canhide )
	{
		setTimeout($.unblockUI,2000);
		$('#'+id).click(function(){
			return false;
		});
	}
}
jQuery.popup = popup;

//仿确认对话框
function myconfirm(message,fn)
{
	fn = fn || null;
	var t=Math.round( Math.random()*1000 );
	
	$('body').before('<div id="confirmbox'+t+'" class="floatDiv"><h1 class="tit1"></h1><div class="addPrice"><p>'+message+'</p><div class="btnWp"><input type="button" value="确定" class="btn btn-success btn-small" />&nbsp;&nbsp;&nbsp;<input type="button" value="取消" class="btn btn-small" /></div></div></div>');
	$.blockUI({
		message:$('#confirmbox'+t),
		css:{
			top:		'50%',
			left:		'50%',
			textAlign:	'left',
			marginLeft:	'-175px', 
			marginTop:	'-100px', 
			width:		'350px',
			background:	'none'
		}
	});
	var idDiv = '#confirmbox'+t;
	$(idDiv).find(':button').filter(':first').click(function(){
		$.unblockUI();
		$(idDiv).remove();
		if(fn)	fn(true);
	});
	$(idDiv).find(':button').filter(':last').click(function(){
		$.unblockUI();
		$(idDiv).remove();
		if(fn)	fn(false);
	});
	
	/*
	$('#confirmbox'+t).find(':button').filter(':first').click(function(){
		$.unblockUI();
		$('#confirmbox'+t).remove();
		if(fn)	fn(true);
	});
	$('#confirmbox'+t).find(':button').filter(':last').click(function(){
		$.unblockUI();
		$('#confirmbox'+t).remove();
		if(fn)	fn(false);
	});
	*/
}

//input框获取焦点操作
$(".iptFocus").focus(function(){
	  if($(this).val() ==this.defaultValue){ 
		$(this).val("");
		$(this).removeClass("cGray");
	  } 
}).blur(function(){
	 if ($(this).val() == '') {
		$(this).val(this.defaultValue);
		$(this).addClass("cGray");
	 }
});

$(".iptFocus2").focus(function(){
	  if($(this).val() !=""){ 
		$(this).removeClass("cGray");
	  } 
}).blur(function(){
	 if ($(this).val() == '') {
		$(this).addClass("cGray");
	 }
});
//弹出登录框
$("#loginId").click(function(){
		popup("loginDiv");
		$("#loginDiv").parent(".blockMsg").css({marginTop:"0",top:"45px"})	
})

//tab切换
var $div_li =$("div.tab_menu em");
$div_li.on("click",function(){
	$(this).addClass("current")
		   .siblings().removeClass("current");
	var index =  $div_li.index(this);
	$("div.tab_box > div").eq(index).show().siblings().hide();
})

//切换样式
var $div_li2 =$("div.click_menu em,div.click_menu a");
$div_li2.on("click",function(){
	$(this).addClass("current")
		   .siblings().removeClass("current");
})

//查看
$(".contDiv .lst").on("click",function(){
	$(this).children("div").fadeIn("fast").parent("li").siblings("li").children(".hideTr").hide();
})

//hover background
$(".hoverLst li").on({mouseenter:function(){
$(this).addClass("liBg");
},mouseleave:function(){$(this).removeClass("liBg")}})

//hover background del 并删除
$(".hoverLstDel li").on({mouseenter:function(){
$(this).addClass("liBg");
$(this).children(".del").show();
},mouseleave:function(){$(this).removeClass("liBg");$(this).children(".del").hide();}})



function subString(str, len, hasDot)
		{
			var newLength = 0;
			var newStr = "";
			var chineseRegex = /[^\x00-\xff]/g;
			var singleChar = "";
			var strLength = str.replace(chineseRegex,"**").length;
			for(var i = 0;i < strLength;i++){
				singleChar = str.charAt(i).toString();
				if(singleChar.match(chineseRegex) != null){
					newLength += 2;
				}else{
					newLength++;
				}
				if(newLength > len){
					break;
				}
				newStr += singleChar;
			}
			
			if(hasDot && strLength > len){
				newStr += "...";
			}
			return newStr;
		}

//转换时间
	/*function getLocalTime(nS){   
		return new Date(parseInt(nS) * 1000).toLocaleString().replace(/:\d{1,2}$/,' ');   
	}
	function getLocalTime(nS) { 

		return new Date(parseInt(nS) * 1000).toLocaleString().substr(0,17);

	} 

	function getLocalTime(nS) { 
		return new Date(parseInt(nS) * 1000).toLocaleString().replace(/年|月/g, "-").replace(/日/g, " "); 
	} 

	function getLocalTime(tm) { 
		
		var tt=new Date(parseInt(tm) * 1000).toLocaleString()
		return tt;

	} */
	

	function createComentlist(obj){
	var a='高顿学员';
	var b='';
	if(obj.sname==null || obj.sname==''){
		obj.sname=a;
	}
	if(obj.content==null){
		obj.content=b;
	}
	var	html="";		
		html+='<li class="uid_'+obj.student_id+'">';
		html+='<div class="topCont">';
		html+='<img src="'+obj.avatar+'" />';
		html+='<div>';
		html+='<p><em class="cGreen">'+obj.sname+'</em>　'+obj.regdate+'</p>';
		html+='<span class="small-rating">';
		html+='<span style="width:'+obj.type*20+'%"></span>';
		html+='</span>';
		html+='</div>';
		html+='</div>';
		html+='<p>'+obj.content+'</p>';
		html+='</li>';
	return html;
}
function loadingdiv(div_id){
	$("#"+div_id).html('<div class="dloading"></div>');
}
	


