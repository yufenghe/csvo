<%@ page language="java" pageEncoding="UTF-8" %>
<link rel="stylesheet" type="text/css" href="${ctx}/static/bootstrap/2.3.2/css/bootstrap-responsive.min.css">
<link href="${ctx}/static/bootstrap/2.3.2/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
<script src="${ctx}/static/jquery/1.8.3/jquery-1.8.3.min.js" type="text/javascript"></script>
<script src="${ctx}/static/jquery-validation/1.10.0/jquery.validate.min.js" type="text/javascript"></script>
<script src="${ctx}/static/jquery-validation/1.10.0/messages_bs_zh.js" type="text/javascript"></script>
<style>

/* 公共样式 S */
body{margin:20px 20px 20px 20px}
h1, h2, h3, .masthead p, .subhead p, .marketing h2, .lead {font-family: "Helvetica Neue",Helvetica,Arial,"Microsoft Yahei UI","Microsoft YaHei",SimHei,"宋体",simsun,sans-serif;font-weight: normal;}

input[type="text"],input[type="password"]{height:30px;}

/* 公共样式 E */
/* 用于栅格系统示例的样式 S */
.show-grid [class*="span"] {
    background-color: #EEEEEE;
    border-radius: 3px 3px 3px 3px;
    line-height: 40px;
    min-height: 40px;
    text-align: center;
}
.show-grid {
    margin-bottom: 20px;
    margin-top: 10px;
}
.show-grid [class*="span"]:hover {
    background-color: #DDDDDD;
}
.show-grid [class*="span"] [class*="span"] {
    background-color: #CCCCCC;
}
.show-grid .show-grid [class*="span"] {
    margin-top: 5px;
}
.show-grid [class*="span"] [class*="span"] [class*="span"] {
    background-color: #999999;
}
/* 用于栅格系统示例的样式 E */
/* 用于表达的样式 S */

form.bs-docs-example {
    padding-bottom: 19px;
}
.bs-docs-example {
    background-color: #FFFFFF;
    border: 1px solid #DDDDDD;
    border-radius: 4px 4px 4px 4px;
    margin: 15px 0;
    padding: 39px 19px 14px;
    position: relative;
}
.bs-docs-example-submenus .dropup > .dropdown-menu, .bs-docs-example-submenus .dropdown > .dropdown-menu {
    display: block;
    margin-bottom: 5px;
    position: static;
}
.bs-docs-example-submenus {
    min-height: 180px;
}
/* 用于表达的样式 E */

</style>
    <h4>&nbsp;&nbsp;运营活动系统-模拟器</h4><span style="color:red;">&nbsp;&nbsp;注：本模拟器页面中有token字时，需要先登录然后再进行操作，该值不需要手动处理，模拟器会自动获取。</span>
<div class="navbar">
    <div class="navbar-inner">
        <div class="container">
            <ul class="nav nav-pills">
            	<li><a href="${ctx}/login/init">登录</a></li>
            	<li><a href="${ctx}/openbet/init">开通投注账号</a></li>
            	 <li><a href="${ctx}/bet/init">即开投注</a></li>
            	 <li><a href="${ctx}/digitalbet/init">数字彩传统足彩投注</a></li>
            	 <li><a href="${ctx}/jcbet/init">竞彩投注</a></li>
                <li><a href="${ctx}/bonus/init">红包派发</a></li>
                <li><a href="${ctx}/query/init">红包查询</a></li>
                <li><a href="${ctx}/showmsg/init">消息查询</a></li>
            </ul>
        </div>
    </div>
</div>