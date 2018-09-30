var PaperData;
var paperid;
$(document).ready(function(){
	
	/*******exam start********/
		//单选
	
	
	$("div.click_menu a").live("click",function(){
		var answer=$(this).attr("value");
		var id=$(this).parents(".lstDiv").attr("id");
		var eid=id.substring(4);
		
		$(this).addClass("current")
			   .siblings().removeClass("current");
		$(this).parent("div").addClass("curtDiv");
		AddAnswer(eid,answer);
		
	});
	//duoxuan
	//多选
	$("div.multiLst a").live("click",function(){
			var id=$(this).parents(".lstDiv").attr("id");
			var eid=id.substring(4);
			var answer='';
			$(this).toggleClass("current");
			$(this).parent("div").find("a").each(function(){
					if($(this).attr("class")=="current"){
						
						answer+=(answer=="")?$(this).attr("value"):","+$(this).attr("value");
					}
				});
			AddAnswer(eid,answer);
			$(this).parent("div").removeClass("curtDiv");
			if(answer!=""){
				
				$(this).parent("div").addClass("curtDiv");
			}	
	});
	//答题卡选项卡
	$("#numCls a").live("click",function(){
		
		$(this).addClass("currentCs").siblings().removeClass("currentCs");
		var index =  $("#numCls a").index(this);
		$("div#subjectCont > div").eq(index).show().siblings().hide();
		$("div#subjectCont > div").eq(index).find(".subCls").show();
		$("div#subjectCont > div").eq(index).find(".ansCont").hide();
		

		

	});
	//提交
	$("a.subBtn").live("click", function(){
		var id=$(this).parents(".lstDiv").attr("id");
		var eid=id.substring(4);
		var data=getAnswer(eid);
		if(data.userAnswer==""){
			return;
		}
		$(this).parent(".subCls").next(".ansCont").find(".analyze .ansEm .answer").html(getTrueorFalse(data.userAnswer,data.type));
		if(data.answer==data.userAnswer){
			
			$(this).parent(".subCls").next(".ansCont").find(".analyze").removeClass("wrongDiv rightDiv").addClass("rightDiv").find(".ansEm .answer").removeClass("cGreen cRed").addClass("cGreen");
		}else{
			$(this).parent(".subCls").next(".ansCont").find(".analyze").removeClass("wrongDiv rightDiv").addClass("wrongDiv").find(".ansEm .answer").removeClass("cGreen cRed").addClass("cRed");
		}
		
		if($(this).prev("div").hasClass("curtDiv")){
		$(this).parent(".subCls").hide();
		$(this).parent(".subCls").next(".ansCont").show();
		}
		$("#numCls a").each(function(){
			
			var aeid=$(this).attr("data-info");
			if(id==aeid){
				if(data.answer==data.userAnswer){

					$(this).removeClass("rightCs wrongCs").addClass("rightCs");
				}else{
					$(this).removeClass("rightCs wrongCs").addClass("wrongCs");
				}
				return;
			}
		});
	});

	//next 
	$("a.finish").live("click",function(){
		TimeOut(1);
	});
	$("a.nextDiv").live("click",function(){
		var lghCls = $($("#numCls a")).length-1;
		var index =  $("a.nextDiv").index(this);
		$(".subCls").show();
		$(".ansCont").hide()
		if(index < lghCls){
			$("div#subjectCont > div").eq(index+1).show().siblings().hide();
			$("#numCls a").eq(index+1).addClass("currentCs").siblings().removeClass("currentCs");
		}else{
		}
	});
	/*******exam end********/

	/***********exam function start****************/
	function AddAnswer(eid,answer)
	{
		
		var hasflag = false;
		for(var i=0;i<PaperData.length;i++)
		{
			for(var j=0;j<PaperData[i].pdata.length;j++)
			{
				
				if(PaperData[i].pdata[j].ExamID-eid==0){
					PaperData[i].pdata[j].userAnswer=answer;
					hasflag=true;
					break;
					}
			}
			if(hasflag){
				break;
			}
		}
		
		
	}
	function getAnswer(eid){
		var hasflag = false;
		var obj={userAnswer:'',answer:''};
		for(var i=0;i<PaperData.length;i++)
		{
			for(var j=0;j<PaperData[i].pdata.length;j++)
			{
				
				if(PaperData[i].pdata[j].ExamID-eid==0){
					hasflag=true;
					obj['userAnswer']=PaperData[i].pdata[j].userAnswer;
					obj['answer']=PaperData[i].pdata[j].answer;
					obj['type']=PaperData[i].pdata[j].type;
					break;
					}
			}
			if(hasflag){
				break;
			}
		}
		return obj;
		
	}
	
	/***********exam function end****************/
});
	function getExam(courseid,paperid){
		
		$.post(GAODUNER.path+"/Exam/getPaper",{
			courseid : courseid,
			paperid:paperid
		},function(data){
			$(".learn-box").html(data);
			$("#subjectCont .lstDiv").last().find(".ansCont .nextDiv").addClass("finish").removeClass("nextDiv").html("完成");
			//PaperData=$("#PaperData").val();
		});
	}
	function goExam(courseid,paperid,partid){
		var url=GAODUNER.path+"/Task/getStr";
		$.get(url,{courseid:courseid},function(data){
			var data=eval("("+data+")");
			if(data.status==1){
				window.location.href=GAODUNER.tiku+'/'+data.str+'/Exam/createTaskPaper/pid/'+paperid+'/istk/1/partid/'+partid+'';
			}	
		});
	}
	function TimeOut(status)
	{	
		var paperid=$("#paperid").val();
		$.ajax({
					type: "POST",
					url:GAODUNER.path+"/Exam/saveitems",
					data: {PaperId:paperid,RunTime:0,Status:status,PaperData:$.toJSON(PaperData)},
					dataType: 'json',
					success: function (result) {
						if(result.ret==101){
							GAODUNER.userId="";
							if(!$.tools.dialog.isLogin())return!1;
						}
						if(result.ret==100){
							$("#j-next").click();
						}
					}
					,
					error: function (e) {}
						
					});	
	}
	function getTrueorFalse(answer,type){
		if(type=="3"){
			if(answer=="1"){
				answer="True";
			}
			if(answer=="0"){
				answer="False";
			}
			
		}
		return answer;
	}
