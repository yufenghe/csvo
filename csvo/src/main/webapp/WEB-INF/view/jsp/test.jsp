<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"> 
<script type="text/javascript"></script>
<%-- <link rel="stylesheet" href="${ctx}/static/bootstrap/2.3.2/css/bootstrap-table.css"> --%>
<%-- <link rel="stylesheet" href="${ctx}/static/bootstrap/2.3.2/css/bootstrap-editable.css"> --%>
<title>竞彩投注</title>
<script type="text/javascript">
function process() {
	$.post('${ctx}/test/submit', $('#inputForm').serialize(), function(data) {
		alert(111)
	});
// 	var data = $('#inputForm').serialize();
//     $.ajax({
//         url: '${ctx}/test/submit',
//         type: 'post',
//         data: data,
//         dataType: 'json',
//         timeout: 10000,
//         error: function (e) {
//             alert('处理超时或系统处理错误:' + e);
//         },
//         success: function (data) {
//         	$('#showResult').empty();
//         	$('#showResult').append('<span class="btn btn-success">响应头</span><br/>');
//         	$('#showResult').append(data.head);
//         	$('#showResult').append('<br/>');
//             $('#showResult').append('<span class="btn btn-danger">响应体</span><br/>');
//             $('#showResult').append(data.body);
//         }
//     });
}
</script>
<style>
.bs-docs-example:after {
	background-color: #F5F5F5;
	border: 1px solid #DDDDDD;
	border-radius: 4px 0 4px 0;
	color: #9DA0A4;
	content: "竞彩投注";
	font-size: 12px;
	font-weight: bold;
	left: -1px;
	padding: 3px 7px;
	position: absolute;
	top: -1px;
}
</style>
</head>

<body>
	<%@ include file="/WEB-INF/view/simu_memu.jsp"%>
	<form class="bs-docs-example form-horizontal" id="inputForm">
	<div class="row">
		<div class="span4 control-group">
			<label for="betNum" class="control-label">数量</label>
			<div class="controls">
				<input type="text" id="betNum" name="betNum" value="${sessionScope.agentNo}">
			</div>
		</div>
		<div class="span4 control-group">
			<label for="multiple" class="control-label">倍数</label>
			<div class="controls">
				<input type="text"  id="multiple" name="multiple" value="1">
			</div>
		</div>
	</div>	
	<div class="row">
		<div class="span4 control-group">
			<label for="serverAreaCode" class="control-label">接入区域代码</label>
			<div class="controls">
				<input type="text" id="serverAreaCode" name="serverAreaCode" value="370102">
			</div>
		</div>
		<div class="span4 control-group">
			<label for="date" class="control-label">日期</label>
			<div class="controls">
				<input type="text" id="date" name="date">
			</div>
		</div>
		
	</div>
	<div class="control-group">
		<div class="controls">
			<button class="btn btn-primary" type="button" onclick="process();">提   交</button>
		</div>
	</div>
		
	</form>
	<legend>
	    <div id="showResult"></div>
	</legend>
</body>
</html>
