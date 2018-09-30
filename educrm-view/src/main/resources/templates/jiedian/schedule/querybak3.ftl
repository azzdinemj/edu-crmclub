<#include "../../commons/top.ftl" />
<#include "../../commons/left.ftl" />

<link href="${staticPath}/plus/fullcalendar-3.9.0/fullcalendar.min.css" type="text/css" rel="stylesheet">
<div class="contentpanel">
    <div class="pageheader">
        <h2><i class="fa fa-bookmark"></i>排课列表 <span>...</span></h2>
        <div class="breadcrumb-wrapper">
            <div class="btn-group fr title-btn">
                <a class="btn btn-sm btn-newblue addScheaule" href="/jiedian/schedule/create?key=1">新建循环排课</a>
            <a class="btn btn-sm btn-newblue addScheaule" href="/jiedian/schedule/create?<#if pkEmployee??>pkEmployee=${(pkEmployee)!}</#if><#if pkStudent??>pkStudent=${(pkStudent)!}</#if>">新建</a>
            <#--<a href="" class="btn btn-sm btn-newblue" onclick="del()"> 删除 </a>-->
            <#-- <button title="更多操作" type="button" class="btn btn-newblue btn-sm dropdown-toggle" data-toggle="dropdown">
                 <span class="caret"></span>
                 <span class="sr-only"></span>
             </button>
             <ul class="dropdown-menu" role="menu">
                 <li><a href="" data-toggle="modal" onclick="del()"> 删除 </a></li>
             </ul>-->
            </div>
        </div>
        <input type="hidden" id="defaultView" <#if defaultView??>value="${(defaultView)!}" <#else >value="month"</#if>>
        <input type="hidden" id="viewType" value="1">
		<div class="fac-right">
			<button class="fc-button fc-state-default fc-corner-left <#if defaultView?? && defaultView=="agendaDay">fc-state-active</#if>" id="day">日</button>
            <button class="fc-button fc-state-default fa-corner <#if defaultView?? && defaultView=="month">fc-state-active</#if>" id="month">月</button>
            <button class="fc-button fc-state-default fc-corner-right <#if defaultView?? && defaultView=="agendaWeek">fc-state-active</#if>" id="week">周</button>
		</div>
        <div id='calendar' style="margin-top: 25px;"></div>
    </div>
   
        
       

    </div><!-- panel -->
</div><!-- contentpanel -->

<#include "../../commons/footer.ftl" />
<script type="text/javascript" src="${staticPath}/plus/fullcalendar-scheduler-1.9.4/lib/moment.min.js"></script>
<script type="text/javascript" src="${staticPath}/plus/fullcalendar-3.9.0/fullcalendar.min.js"></script>
<script type="text/javascript" src="${staticPath}/plus/fullcalendar-3.9.0/locale-all.js"></script>
<script>

  $(function() { // document ready
//      var view = $('#calendar').fullCalendar('getView');
//      alert(view.changeView);


	  $("#day").on("click",function(){
          $("#month").removeClass("fc-state-active");
          $("#week").removeClass("fc-state-active");
          $("#day").addClass("fc-state-active");
          $('#calendar').fullCalendar('changeView',"agendaDay");
		  $("#defaultView").val("agendaDay");
	  });

      $("#week").on("click",function(){
          $("#month").removeClass("fc-state-active");
          $("#week").addClass("fc-state-active");
          $("#day").removeClass("fc-state-active");
          $('#calendar').fullCalendar('changeView',"agendaWeek");
          $("#defaultView").val("agendaWeek");
      });

      $("#month").on("click",function(){
          $("#month").addClass("fc-state-active");
          $("#week").removeClass("fc-state-active");
          $("#day").removeClass("fc-state-active");
          $('#calendar').fullCalendar('changeView',"month");
          $("#defaultView").val("month");
      });

      $('#calendar').fullCalendar({
    	 locale: 'zh-cn',
    	 now: '${.now}',
         editable: false,
         aspectRatio: 2.3,
		 defaultView: $("#defaultView").val(),
         header: {
           /*  left: 'prev,next today',
             center: 'title'*/
          /*   right: 'agendaDay,agendaWeek,month'*/
           },
		//悬浮框
        eventMouseover: function (calEvent, jsEvent, view) {
//            $.ajax({
//                url: '/jiedian/schedule/getsch',
//                dataType: 'json',
//                type: 'POST',
//                data: {"pkSchedule":calEvent.id},
//                success: function(data) {
//                    var title = "老师信息:"+ data.map.employeeobj + "     项目信息:"+ data.map.productobj + "      教室信息:" + data.map.classroomobj + "     学生信息:" + data.map.studentobj
                    $(this).attr('title',calEvent.title);
                    $(this).css('font-weight', 'normal');
//                }
//            });
        },
         events: function(start, end, timezone, callback) {
     	    $.ajax({
     	      url: '/jiedian/schedule/getschedule',
     	      dataType: 'json',
     	      type: 'POST',
     	      data: {
     	        // our hypothetical feed requires UNIX timestamps
     	        startTime: start.format('YYYY-MM-DD HH:mm:ss'),
     	        endTime: end.format('YYYY-MM-DD HH:mm:ss'),
     	        <#if pkEmployee??>
     	    pkEmployee: '${(pkEmployee)!}',
     	   	    </#if>
 	    	   	 <#if pkStudent??>
 	    	   	pkStudent: '${(pkStudent)!}',
 	 	   	    </#if>
     	      },
     	      success: function(data) {
     	    	 var t;
     	        var events = [];
     	        var color = "";
     	        for(i=0;i<data.length;i++){
     	            if(data[i].status == 0){
                        color = "#"+data[i].pkEmployee.toString(16);
                    }else if(data[i].status == 1){
     	                if(data[i].isfeedback == 1){
                            color = "#cacaca";
                        }else if(data[i].isfeedback == 0){
                            color = "#efac56";
                        }

                    }

     	        	events.push({
     	        		id   : data[i].pkSchedule,
     	        		
         	            title: data[i].map.employeeobj+"/"+data[i].map.productobj+"/"+data[i].map.classroomobj+"/"+data[i].map.studentobj,
         	            start: new Date(data[i].startTime), // will be parsed
         	            end:   new Date(data[i].endTime), // will be parsed
         	            url:   "/jiedian/schedule/edit?pkSchedule="+data[i].pkSchedule+"<#if pkEmployee??>&pkEmployee=${(pkEmployee)!}</#if><#if pkStudent??>&pkStudent=${(pkStudent)!}</#if>", // will be parsed
                        color: color,
         	          });
     	        }
     	       
     	        callback(events);
     	      }
     	    });
         },eventClick: function(event, jsEvent, view) {
              $(this).attr("href",$(this).attr("href")+"&defaultView="+$("#defaultView").val()+"&viewType="+$("#viewType").val());
          }
    });

      $(".addScheaule").on("click",function(){
		$(this).attr("href",$(this).attr("href")+"&defaultView="+$("#defaultView").val()+"&viewType="+$("#viewType").val());
	  })
  
  });

</script>