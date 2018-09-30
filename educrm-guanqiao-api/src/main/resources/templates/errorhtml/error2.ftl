<#include "../commons/top.ftl" />
<#include "../commons/left.ftl" />

<link href="${staticPath}/css/notfound.css" rel="stylesheet">

<div class="auto">
    <div class="container">
        <div class="settings">
            <i class="icon"></i>
            <h4>很抱歉！没有找到您要访问的页面！</h4>
            <p><span id="num">5</span> 秒后将自动跳转到首页</p>
            <div>
                <a href="index.html" title="返回首页">返回首页</a>
                <a href="javascript:" title="上一步" id="reload-btn">上一步</a>
            </div>
        </div>
    </div>
</div>

<#include "../commons/footer.ftl" />
<script src="${staticPath}/js/notfound.js">
