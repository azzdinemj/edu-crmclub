<#include "../commons/top.ftl" />
<#include "../commons/left.ftl" />

<link href="${staticPath}/css/notfound.css" rel="stylesheet">

<div class="auto">
    <div class="container">
        <div class="settings">
            <i class="icon"></i>
            <h4>I'm sorry！There is no page to find the page you want to access！</h4>
            <p><span id="num">5</span> After a second, it will automatically jump to the home page</p>
            <div>
                <a href="index.html" title="Return to the home page">Return to the home page</a>
                <a href="javascript:" title="The last step" id="reload-btn">The last step</a>
            </div>
        </div>
    </div>
</div>

<#include "../commons/footer.ftl" />
<script src="${staticPath}/js/notfound.js">
