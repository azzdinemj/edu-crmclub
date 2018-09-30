
<#assign staticPath=''>

<#include "${staticPath}/commons/top.ftl" />
<#include "${staticPath}/commons/left.ftl" />
<form class="look form-ajax" id="formid" method="post" action="/heyun/hyLinkman/updateStatusOrderDetail" data-target="/heyun/hyLinkman/query">
    <div class="contentpanel">
        <div class="pageheader">
            <h2><i class="fa fa-bookmark"></i>Pedestrian management<span>...</span></h2>
            <div class="breadcrumb-wrapper">
                <div class="btn-group fr title-btn">
                    <a class="btn btn-sm btn-newblue" onClick="history.back(-1);">return</a>
                    <input class="btn btn-sm btn-newblue" type="submit" value="save">
                    <#--<a class="btn btn-newblue btn-sm" >-->
                        <#--save-->
                    <#--</a>-->
                </div>
            </div>
        </div>
        <div class="panel panel-default">

            <div class="row">

                <div class="col-xs-12 col-md-4">
                    <div class="form-group">
                        <label class="control-label col-xs-3 col-md-3">The order no.：</label>
                        <input class="form-control" type="text"  readonly value="${(HyOrder.id)!}">
                    </div>
                </div>

                <div class="col-xs-12 col-md-4">
                    <div class="form-group">
                        <label class="control-label col-xs-3 col-md-3">The order status：</label>
                        <select  class="orderStatus">
                            <option <#if HyOrder??&& HyOrder.status??&&HyOrder.status='0'> selected=""</#if>value="0">ongoing</option>
                            <option <#if HyOrder??&& HyOrder.status??&&HyOrder.status='1'> selected=""</#if>value="1">Has been completed</option>
                            <option <#if HyOrder??&& HyOrder.status??&&HyOrder.status='2'> selected=""</#if>value="2">Have a refund</option>
                        </select>
                    </div>
                </div>

            </div>

            <#if HyOrderDetailList??>
                <#list HyOrderDetailList as v>

                <div class="row">
                    <div class="col-xs-12 col-md-4">
                        <div class="form-group">
                            <label class="control-label col-xs-3 col-md-3">ChuHangRen：${(v.map.HyUser.name)!}</label>
                            <select name="HyUser[]">
                               <option <#if v??&&v.status??&&v.status='0'>selected="selected"</#if> value="0,${(v.id)!}">Dealt with in</option>
                               <option <#if v??&&v.status??&&v.status='1'>selected="selected"</#if> value="1,${(v.id)!}">Has the sign</option>
                               <option <#if v??&&v.status??&&v.status='2'>selected="selected"</#if> value="2,${(v.id)!}">Have a visa</option>
                            </select>
                        </div>
                    </div>
                </div>

                </#list>
            </#if>



        </div>




        </div>
    </div>
</form>




<#include "${staticPath}/commons/footer.ftl"/>

<script type="text/javascript">


   $(function () {
       $(".orderStatus").change(function () {

           var status=$(".orderStatus").val()
           $.ajax({
               url: '/heyun/hyLinkman/updateStatusOrder',
               type: 'POST',
               data: {
                   id: ${(HyOrder.id)!},
                   status :status
               },
               dataType: "json",
               success: function (data) {
                   if (data.code==0) {
                       alert(data.code+data.message);
                   }else{
                      alert(data.message);
                   }
               },


           })


       })

   })
    

</script>
