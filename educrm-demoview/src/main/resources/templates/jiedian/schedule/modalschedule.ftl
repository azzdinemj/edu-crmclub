<link href="/plus/fullcalendar-3.9.0/fullcalendar.min.css" type="text/css" rel="stylesheet">
<div  class="modal-dialog lg-modal">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close"
                    data-dismiss="modal" aria-hidden="true">
                &times;
            </button>
            <h4 class="modal-title" id="myModalLabel">
                排课列表
            </h4>
        </div>
        <div class="modal-body">
            <div id='calendar'></div>
        </div>
        <div class="modal-footer">
            <button type="button" class="btn btn-default btn-sm" data-dismiss="modal">关闭</button>
           
        </div>
    </div><!-- /.modal-content -->

</div><!-- /.modal -->

<script type="text/javascript" src="/plus/fullcalendar-scheduler-1.9.4/lib/moment.min.js"></script>
<script type="text/javascript" src="/plus/fullcalendar-3.9.0/fullcalendar.min.js"></script>
<script type="text/javascript" src="/plus/fullcalendar-3.9.0/locale-all.js"></script>
<script>

  $(function() { // document ready

    $('#calendar').fullCalendar({
    	 locale: 'zh-cn',
    	 now: '${.now}',
         editable: true,
         aspectRatio: 2.3,
         header: {
             left: 'prev,next today',
             center: 'title',
             right: 'agendaDay,agendaWeek,month'
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
     	        <#if schedule.pkEmployee??>
     	    pkEmployee: '${(schedule.pkEmployee)!}',
     	   	    </#if>
 	    	   	 <#if schedule.pkStudent??>
 	    	   	pkStudent: '${(schedule.pkStudent)!}',
 	 	   	    </#if>
 	    	   	<#if schedule.pkClassRoom??>
 	    	   	pkClassRoom: '${(schedule.pkClassRoom)!}',
 	 	   	    </#if>
     	      },
     	      success: function(data) {
     	    	 var t;
     	        var events = [];
     	        for(i=0;i<data.length;i++){
     	        	
     	        	events.push({
     	        		id   : data[i].pkSchedule,
     	        		
         	            title: data[i].map.employeeobj+"/"+data[i].map.productobj+"/"+data[i].map.classroomobj+"/"+data[i].map.studentobj,
         	            start: new Date(data[i].startTime), // will be parsed
         	            end:   new Date(data[i].endTime), // will be parsed
         	            url:   "/jiedian/schedule/edit?pkSchedule="+data[i].pkSchedule+"<#if pkEmployee??>&pkEmployee=${(pkEmployee)!}</#if><#if pkStudent??>&pkStudent=${(pkStudent)!}</#if>", // will be parsed
         	            color:	"#"+data[i].pkEmployee.toString(16),	
         	          });
     	        }
     	       
     	        callback(events);
     	      }
     	    });
         }
    });
  
  });

</script>