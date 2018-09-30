$(document).ready(function(){
	changewk();
	
	var hgtDiv = $("div.m-courselearn").height();
	$(window).resize(function () {
		hgtDiv = $("div.m-courselearn").height();
	});
	  max = $(".section").length;
	var cur = $("#cur").val()-1;
	
	btns = $(".section")
	.each(function(idx, el) {
		$(el).data({idx: idx});
	 });
	
	//prev
	$("#j-prev").on("click",function(){
		
		jPrev()
	});
	//next
	$("#j-next").on("click",function(){
		
		jNext();
	});
	//--------------------------prev
	
	function jPrev(){
		
		var t = parseInt(cur)-1 ;
		
		return dgo(t);
	}
	function changewk(){
		
		$(".section-cur a").each(function(){
			var lessonid=$(this).data("lessonid");
			var number=$(this).data("number");
			var chid=$(this).data("chid");
			
			$("#lid").val(lessonid);
			$("#cur").val(number);
			$(".cl-chapter em").html(chid);
			$(".j-lessonnum").html(number);
		});
		
		
	}
	//----------------------next
	function jNext(){
		var t = parseInt(cur)+1;
		
		return dgo(t);

	}
	 function onoff(on, off) {
	
		$(".section").removeClass("section-cur");
        (on !== -1) && btns.eq(on).addClass('section-cur');
        (off !== -1) && btns.eq(off).removeClass('section-cur');
    }

    function dgo(n) {
		
		
        // hack for slast
        var idx = n > max-1 ? 0 : n;
		
		btns.eq(idx).find("a").click();
        //onoff(idx, cur);
       
    }
	$("#lecture_play_finish_mark").on("click",function() {
		var lid=$("#lid").val();
		if ($(this).attr("class")=="j-finish unfinish") {
			var status=1;
		} else {
			var status=2;
		}
		
		SetStatusClick(lid,status);
	});
	
	
	$(".section  a").on("click",function(){
		
		var lessonid=$(this).data("lessonid");
		var num=$(this).data("number")-1;
		//任务介绍(点击之后两个都换)
        var strData = $(this).attr("data-data");
		if(strData==''||strData==null){
			$(".cdecls").addClass("showDiv");
		}else{
			$(".cdecls").removeClass("showDiv");
		}
		    $(".desc").html(strData);
			
			
		changepart(lessonid,num);
		});
	

	$("div.clkDiv a").on("click",function(){
		var lessonid=$("#lid").val();
		
		var txt= $(this).text();
		var clsdd = $(this).attr("class");
		var tag=$(this).attr("data-info");
		$.post(GLOBAL.path+"/class/setrecordtag",{lessonid : lessonid,tag:tag},function(data){
			var data=eval("("+data+")");
			if(data.status==1){
				$("#cli-sign").html("<b "+"class="+clsdd+"></b>"+txt+"<span></span>");
				setTag(lessonid,tag);
			}
		});

	});
	//tab切换
	
	$("div.tab_menu a").on("click",function(){
		var lid=$("#lid").val();
		var cid=$("#cid").val();
		$(this).addClass("tabitem-pos")
			   .siblings().removeClass("tabitem-pos");
		var index =  $("div.tab_menu a").index(this); 
		
		switch(index){
			case 0:
				break;
			case 1:
				getpartnotebook(lid);
				break;
			case 2:
				getasklist(lid);
				break;
			case 3:
				getmaterial(cid);
		}
		$("div.tab_box > div").eq(index).show().siblings().hide();
	});
	$(".btn-notebook").on("click",function(){
		var content=$("#text_notebook").val().replace(/^\s+|\s+$/g,'');
		if(content==""){
			$("#text_notebook").focus();
			if($("#contcRed").length>0){
					$("#contcRed").show();
				}else{
					$(this).before("<span id='contcRed' class='cRed'>内容不能为空！</span>");
				}
			return false;
		}

		var lid=$("#lid").val();
		var cid=$("#cid").val();
		
		$.post(GLOBAL.path+"/notebook/addnotebook",{lid : lid,cid:cid,content:content},function(data){
			var data=eval("("+data+")");

			if(data.status==1){
				var obj={"id":data.id,'content':content,'regdate':'刚刚'};
				$("#text_notebook").val('');
				var html=createNotebookList(obj);
				$("#noteCont").html(html+$("#noteCont").html());


			}

		});

	});
	//笔记
	$("#noteCont a").on("click",function(){
		var id=$(this).attr("data-info");
		
		$.post(GLOBAL.path+"/notebook/getnotebookdetail",{id:id},function(data){
			var data=eval("("+data+")");
			if(data.status==1){
				var html="<div>"+data.detail.content+"</div>";
				html+='<div class="clearfix">';
                html+='        	<span class="lft cGray">'+data.detail.regdate+' </span>';
                html+='            <span class="rgt del" data-info="'+data.detail.id+'">删除</span>';
                html+'        </div>';
				$("#noteRgt .detailCont").html(html);
				//$("#noteRgt .cGray").html("<div>"+data.detail.regdate+"</div>");
			}
		});
		$(this).parents(".tabbox2").queue(function(){
				$(this).animate({left:-320},300);
		});
		$(this).parents(".tabbox2").next(".tabbox3").queue(function(){
			$(this).animate({left:0},300);
		});
		$(".tabbox2,.tabbox3").dequeue();
	});
$("#noteRgt .detailCont .del").on("click",function(){
	var id=$(this).attr("data-info");
	 myconfirm('您确定要删除该笔记?', function(r){
	 if(r){
	$.post(GLOBAL.path+"/notebook/deletenotebook",{id:id},function(data){
		var data=eval("("+data+")");
		if(data.status==1){
			$(".backArrow").click();
			$("#noteCont a[data-info="+id+"]").hide();
		}
	});
	 }
	 });
});
	//笔记


$(".backArrow").on("click",function(){
	$(this).parents(".tabbox3").queue(function(){
		$(this).animate({left:320},300);
	});
		$(this).parents(".tabbox3").prev(".tabbox2").queue(function(){
			$(this).animate({left:0},300);
	});
	$(".tabbox2,.tabbox3").dequeue();
})
	//wenda
	$(".btn-ask").on("click",function(){
		var content=$("#text_ask").val().replace(/^\s+|\s+$/g,'');
		if(content==""){
			$("#text_ask").focus();
			if($("#answcRed").length>0){
				$("#answcRed").show();
			}else{
				$(this).before("<span id='answcRed' class='cRed'>内容不能为空！</span>");
			}
			return false;
		}
		var lid=$("#lid").val();
		var cid=$("#cid").val();
		
		$.post(GLOBAL.path+"/ask/addask",{lid : lid,cid:cid,content:content},function(data){
			var data=eval("("+data+")");

			if(data.status==1){
				var obj={"id":data.id,'content':content,'regdate':'刚刚'};
				$("#text_ask").val('');
				var html=createNotebookList(obj);
				$("#askCont").html(html+$("#askCont").html());


			}

		});

	});


	
	$(".alignR a.askdel").on("click",function(){
		var id=$(this).attr("data-info");
		 myconfirm('您确定要删除该问题？', function(r){
		 if(r){
		$.post(GLOBAL.path+"/Ask/deleteask",{id:id},function(data){
			var data=eval("("+data+")");
			if(data.status==1){
				$(".backArrow").click();
				$("#askCont a[data-info="+id+"]").hide();
			}
		});
		 }
		 });
	});
	
/************************** hanshu*******************/

function getpartnotebook(lid){
	loadingdiv("noteCont");

	$.post(GLOBAL.path+"/notebook/getPartnotebook",{lid : lid},function(data){
		var data=eval("("+data+")");
		if(data.status=="1"){
			var html='';
			for(var i=0;i<data.list.length;i++){
				html+=createNotebookList(data.list[i]);
			}
			$("#noteCont").html(html);
		}
	});
}
function getasklist(lid){
	loadingdiv("askCont");
		$.post(GLOBAL.path+"/ask/getpartasklist",{lid : lid},function(data){
		var data=eval("("+data+")");
		if(data.status=="1"){
			var html='';
			for(var i=0;i<data.list.length;i++){
				html+=createAskList(data.list[i]);
			}
			$("#askCont").html(html);
		}
	});
}
function getmaterial(cid){
loadingdiv("materialCont");
$.post(GLOBAL.path+"/class/getmaterial",{cid : cid},function(data){
		
		if(data.status=="1"){
			
			$("#materialCont").html(data.info);
		}
	},'json');
}

function createNotebookList(obj){
	var html="";
	html+="<a href='javascript:void()' data-info='"+obj.id+"'>"
	 html+='<li>';
     html+='<h4>'+subString(obj.content,36)+'</h4>';
     html+='<span class="cGray">'+obj.regdate+'</span>';
     html+='</li>';
	 html+='</a>';
	 return html;
}
function createAskList(obj){
	var html="";
	html+="<a href='"+GLOBAL.path+"/Ask/askdetail/id/"+obj.id+"' target='_blank' data-info='"+obj.id+"'>"
	 html+='<li>';
     html+='<h4>'+subString(obj.content,36)+'</h4>';
	 if(obj.status==1){
		html+='<span class="pastBtn">已回答</span>';
	 }
     html+='<span class="cGray">'+obj.regdate+'</span>';
     html+='</li>';
	 html+='</a>';
	 return html;
	
}


function setTag(lid,status){
	
	btns.eq(cur).find(".ksinfo .bjSpan").removeClass("ques impo");
	switch(status){
				case "1":
					btns.eq(cur).find(".ksinfo .bjSpan").addClass("ques");
					break;
				case "2":
					btns.eq(cur).find(".ksinfo .bjSpan").addClass("impo");
					break;
				default:
					break;

			}
	
}
function SetStatusClick(lessonid,status){
	
	var url=GLOBAL.path+"/class/setrecordstatus";
	
	$.post(url,{lessonid:lessonid,status:status},function(data){
			var data=eval("("+data+")");
			if(data.status==1){
				if(status==1){
					$("#lecture_play_finish_mark").attr("class", "j-finish finish");
				}else{
					$("#lecture_play_finish_mark").attr("class", "j-finish unfinish");
				}
				setStatus(lessonid,status);
				
			}
		});
}
function setStatus(lid,status){

	
	
	btns.eq(cur).find(".ksicon").removeClass("ksicon-0 ksicon-20 ksicon-30");
	switch(status){
				case 1:
					btns.eq(cur).find(".ksicon").addClass("ksicon-20");
				
					break;
				case 2:
					btns.eq(cur).find(".ksicon").addClass("ksicon-30");
					break;
				default:
					break;

			}
	
}


function changepart(lessonid,num){
	
	if(num==cur){
		return;
	}
	if(num>cur){
		
		 $('div.m-courselearn').stop().animate({top:-hgtDiv,bottom:hgtDiv},500,function(){
			$('div.m-courselearn').css({top:hgtDiv,bottom:-hgtDiv})
			$('div.m-courselearn').stop().animate({top:0,bottom:0},500)
		});
	}else{
		$('div.m-courselearn').stop().animate({top:hgtDiv,bottom:-hgtDiv},500,function(){
			$('div.m-courselearn').css({top:-hgtDiv,bottom:hgtDiv})
			$('div.m-courselearn').stop().animate({top:0,bottom:0},500)
			});
	}
	
	onoff(num,cur);
	
	cur=num;
	
	
	//$(".cl-chapter em").html(k);
	$(".j-lessonnum").html(num+1);

	$.post(GLOBAL.path+"/class/getpartdetail",{
		lessonid : lessonid
	},function(data){
			changewk();
			var data=eval('('+data+')');
			if(data.status==0){
				return;
			}
			if(data.status==-1){
				window.location.href=GLOBAL.path+"/User/login";
				return;
			}
			$("#lessonid").val(lessonid);
			$("#cid").val(data.lesson.cid);
			
			$(".j-jiangyi").html("");
			$(".j-keshi").html("");
			$(".j-lessonname").html(data.lesson.name);
			
			var html="";
			switch(data.lesson.tag){

				case "0":
					html='<b class=""></b>';
					html+='					没有标记';
					html+='					<span></span>';
					break;
				case "1":
					html='<b class="clis-qu j-signItem"></b>';
					html+='					有疑问';
					html+='					<span></span>';
					break;
				case "2":
					html='<b class="clis-im j-signItem"></b>';
					html+='					重要';
					html+='					<span></span>';
					break;
					$("#cli-sign").html('<b class="clis-qu j-signItem" ></b><span>有疑问</span>');
					break;
				default:
					html='<b class=""></b>';
					html+='					没有标记';
					html+='					<span></span>';
					break;
				

			}
			$("#cli-sign").html(html);
			$("#lecture_play_finish_mark").removeClass("unfinish").removeClass("finish");
			switch(data.lesson.status){
				case "0":
					$("#lecture_play_finish_mark").addClass("finish");
					break;
				case "1":
					$("#lecture_play_finish_mark").addClass("finish");
					break;
				case "2":
					$("#lecture_play_finish_mark").addClass("unfinish");
					break;
				default:
					$("#lecture_play_finish_mark").addClass("finish");
					break;

			}
			if(data.lesson.type!=4){
			$(".learn-box").html("");}
			switch(data.lesson.type){
				case "1":
					
					changevideo(data.lesson.partpath);
					
					break;
				
					

			}


	});

}

/************************** hanshu*******************/
});

(function(){
		   //hover 延迟
	   $.fn.hoverDelay = function(options){
        var defaults = {
            hoverDuring: 500,
            outDuring: 200,
            hoverEvent: function(){
                $.noop();
            },
            outEvent: function(){
                $.noop();    
            }
        };
        var sets = $.extend(defaults,options || {});
        var hoverTimer, outTimer, that = this;
        return $(this).each(function(){
			/*
            $(this).hover(function(){
                clearTimeout(outTimer);
                hoverTimer = setTimeout(function(){sets.hoverEvent.apply(that)}, sets.hoverDuring);
            },function(){
                clearTimeout(hoverTimer);
                outTimer = setTimeout(function(){sets.outEvent.apply(that)}, sets.outDuring);
            });  
			*/ 
			
			$(this).on({mouseenter:function(){
                clearTimeout(outTimer);
                hoverTimer = setTimeout(function(){sets.hoverEvent.apply(that)}, sets.hoverDuring);
            },mouseleave:function(){
                clearTimeout(hoverTimer);
                outTimer = setTimeout(function(){sets.outEvent.apply(that)}, sets.outDuring);
            }}); 
			
			
        });
    }   

})();





	//回复
	$(".seeDiv .addquestion").on("click",function(){
		var content=$("#ask_content").val();
		var ask_id=$("#ask_id").val();
		if(content!=""){
			$.post(GLOBAL.path+"/ask/addaskquestion",{content : content,ask_id:ask_id},function(data){
				var data=eval("("+data+")");
				if(data.status==1){
					var html=getaskquestionlist(data.data);
					$("#my_question_list").html($("#my_question_list").html()+html);
					$("#ask_content").val('');
				}
			});
		}
	});
	//删除回复
	$("a.askdel").on("click",function(){
		
		var id=$(this).attr("data-info");
		 var p = $(this).parents("li");
		 myconfirm('您确定要删除回复？', function(r){
			 if(r){
				$.post(GLOBAL.path+"/ask/deletequestion",{
				id : id
			},function(data){
					
					var data=eval("("+data+")");
					if(data.status==1){
						
						p.fadeOut(300, function(){ $(this).remove();});
					}
			
			});
			 }
		 });		

	});
	function getaskquestionlist(obj){
	var html="";
	html+='<li>'
    html+='                            <div class="clearfix"><span class="lft">';
    html+='                            <em class="cGreen">'+obj.nickname+'</em>';
    html+='                            <em class="cGray">'+obj.regdate+'</em>';
    html+='                            </span><a class="rgt askdel" data-info="'+obj.id+'">删除</a></div>';
    html+='                            <div class="cont">'+obj.content+'</div>';
    html+='                        </li>';
	return html;
}

//任务说明
$(".rwDiv").on({mouseenter:function(){
	$(this).children("div.rwIntro").show();
},mouseleave:function(){
	$(this).children("div.rwIntro").hide();

}})
$(".m-chapterList em").each(function(){
    var that = $(this);
	that.hoverDelay({
		hoverEvent: function(){
			var  $target=$(this);
			var str=that.attr("data-data");
			getTaskInfo(str);
			var hgt = $('#rwTips').height();
			var left = $target.offset().left + 0 +'px';
			var top =  $target.offset().top - 20 - hgt +'px';	
			$('#rwTips').addClass("tipsCls").css({
				left:left,
				top:top
			});
		}
	});
	that.mouseleave(function(){
		$('#rwTips').removeClass("tipsCls");
	})
});
	function getTaskInfo(str){
		if(str==''||str==null){
			$("#rwTips").addClass("showDiv");
		}else{
			$("#rwTips").removeClass("showDiv");
		}
		$("#desc").html(str);
	}



