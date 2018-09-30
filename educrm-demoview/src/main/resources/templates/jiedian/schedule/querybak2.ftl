<#include "../../commons/top.ftl" />
<#include "../../commons/left.ftl" />

<link href="${staticPath}/plus/fullcalendar-3.9.0/fullcalendar.min.css" type="text/css" rel="stylesheet">
<link href="${staticPath}/plus/fullcalendar-scheduler-1.9.4/scheduler.min.css" type="text/css" rel="stylesheet">
<div class="contentpanel">
    <div class="pageheader">
        <h2><i class="fa fa-bookmark"></i>排课列表 <span>...</span></h2>
        <div class="breadcrumb-wrapper">
            <div class="btn-group fr title-btn">
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
        <input type="hidden" id="viewType" value="0">
        <div id='calendar'></div>
    </div>
   
        
       

    </div><!-- panel -->
</div><!-- contentpanel -->

<#include "../../commons/footer.ftl" />
<script type="text/javascript" src="${staticPath}/plus/fullcalendar-scheduler-1.9.4/lib/moment.min.js"></script>
<script type="text/javascript" src="${staticPath}/plus/fullcalendar-3.9.0/fullcalendar.min.js"></script>
<script type="text/javascript" src="${staticPath}/plus/fullcalendar-3.9.0/locale-all.js"></script>
<script type="text/javascript" src="${staticPath}/plus/fullcalendar-scheduler-1.9.4/scheduler.min.js"></script>
<script>

  $(function() { // document ready

    $('#calendar').fullCalendar({
    	 locale: 'zh-cn',
    	schedulerLicenseKey: 'CC-Attribution-NonCommercial-NoDerivatives',
      now: '${.now}',
      editable: true,
      aspectRatio: 2.3,
      scrollTime: '00:00',
    
      header: {
        left: 'today prev,next',
        center: 'title',
        right: 'timelineDay,timelineWeek,timelineMonth'
      },
      defaultView: 'timelineDay',
      views: {
        timelineDay: {
          buttonText: '15 分钟',
          slotDuration: '00:15'
        },
        timelineWeek: {
          type: 'timelineWeek',
          
        }
      },
      navLinks: true,
      resourceAreaWidth: '25%',
      resourceLabelText: '教师',
      resources: [
    	  <#if employee ??>
          <#list employee as s>
        { id: '${(s.pkEmployee)!}', title: '${(s.caption)!}' },
        </#list>
        </#if>
       
     
      ],
      
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
    	        var events = [];
    	        for(i=0;i<data.length;i++){
    	        	events.push({
    	        		id   : data[i].pkSchedule,
    	        		resourceId:data[i].pkEmployee,
        	            title: new Date(data[i].startTime).format("hh:mm")+"~"+new Date(data[i].endTime).format("hh:mm")+"/"+data[i].map.productobj+"/"+data[i].map.classroomobj+"/"+data[i].map.studentobj,
        	            start: data[i].startTime, // will be parsed
        	            end:   data[i].endTime, // will be parsed
        	            url:   "/jiedian/schedule/edit?pkSchedule="+data[i].pkSchedule+"<#if pkEmployee??>&pkEmployee=${(pkEmployee)!}</#if><#if pkStudent??>&pkStudent=${(pkStudent)!}</#if>", // will be parsed
        	          });
    	        }
    	       
    	        callback(events);
    	      }
    	    });
    	  }
    });

      $(".addScheaule").on("click",function(){
          $(this).attr("href",$(this).attr("href")+"&defaultView="+$("#defaultView").val()+"&viewType="+$("#viewType").val());
      })
  
  });

</script>