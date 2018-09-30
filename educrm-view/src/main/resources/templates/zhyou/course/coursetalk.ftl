<#include "../../commons/top.ftl">
<#include "../../commons/left.ftl">

<div class="contentpanel">
    <div class="pageheader">
        <h2><i class="fa fa-bookmark"></i>学生姓名<span>...</span></h2>
        <div class="breadcrumb-wrapper">
            <div class="btn-group fr title-btn">
                <a class="btn btn-sm btn-newblue" onClick="javascript:history.back(-1);">返回</a>
            </div>
        </div>
    </div>

    <div class="panel panel-default">
        <div class="row">
            <div class="col-md-12">
                <div class="timeline-wrapper clearfix">
                    <div class="timeline-year">
                        2017
                    </div>
                    <div class="timeline-row alt">
                        <div class="timeline-item">
                            <div class="timeline-icon">
                                <i class="fa fa-comment"></i>
                            </div><!-- ./timeline-icon -->
                            <div class="timeline-item-inner">
                                <div class="timeline-body">
                                    <div class="timeline-avatar">
                                        <img src="${staticPath}/images/photos/blog3.jpg" alt="" class="img-circle" />
                                    </div>
                                    <div class="timeline-content">
                                        <div class="font-16 font-semi-bold"><a href="#">薛佳雯</a></div>
                                        <div class="timeline-text">
                                            <small class="block text-muted m-bottom-xs">08月14日, 15:37</small>
                                            老师讲的很棒，感受合作游戏的乐趣！
                                        </div>
                                        <div class="timeline-text">
                                            <small class="block text-muted m-bottom-xs">08月14日, 15:37</small>
                                            老师讲的很棒，感受合作游戏的乐趣！
                                        </div>
                                    </div>
                                </div><!-- ./timeline-body -->
                            </div><!-- ./timeline-item-inner -->

                        </div><!-- ./timeline-item -->
                    </div><!-- ./timeline-row -->


                </div><!-- ./timeline-wrapper -->
            </div><!-- ./col -->
        </div>
    </div>
</div>
<#include "../../commons/footer.ftl"/>
<script>

    KindEditor.ready(function(K) {
        window.editor = K.create('#editor_id');
    });
</script>
<script type="text/javascript">

    function clear(str) {
        str = str.replace(/,/g, "");//取消字符串中出现的所有逗号
        return str;
    }

</script>