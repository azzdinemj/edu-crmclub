<#include "../commons/top.ftl" />
<#include "../commons/left.ftl" />
<link href="${staticPath}/css/notfound.css" rel="stylesheet">

<div class="content">
    <canvas class="snow" id="snow" width="1349" height="400"></canvas>
    <div class="main-text">
        <h1>系统故障请与软件供应商联系</h1>
        <h5 class="not2 "><span id="num">5</span> 秒后将自动跳转到首页</h5>
        <div class="main-text-a">
            <a href="#" class="main-text-abtns">返回首页</a>
            <a href="#" class="main-text-abtn">上一步</a>
        </div>


    </div>
    <div class="ground">
        <div class="mound">
            <div class="mound_text">404 </div>
            <div class="mound_spade"></div>
        </div>

    </div>
    <div class="footer">
        <h4>供应商电话：021-64518955</h4>
    </div>
</div>

<#include "../commons/footer.ftl" />
<script src="${staticPath}/js/notfound.js">