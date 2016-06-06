<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<c:set var="ctx" value="<%=request.getContextPath()%>"/>  
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>短信查询</title>
<link rel="stylesheet" href="${ctx}/static/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet" href="${ctx}/static/bootstrap/table/css/bootstrap-table.min.css">
<link rel="stylesheet" href="${ctx}/static/bootstrap/3.3.5/css/bootstrap-datepicker3.css">
<!-- <link rel="stylesheet" href="http://cdn.bootcss.com/x-editable/1.5.1/bootstrap3-editable/css/bootstrap-editable.css"> -->
<link rel="stylesheet" href="${ctx}/static/bootstrap/x-editable/css/bootstrap-editable.css">
<link rel="stylesheet" href="${ctx}/static/css/main.css">
<script type="text/javascript">
	var _ctx = '${ctx}';
</script>
<script data-main="${ctx}/static/js/main" src="${ctx}/static/js/require.js"></script>
<script type="text/javascript" src="${ctx}/static/bootstrap/3.3.5/js/respond.min.js"></script>
</head>
<body>
<!-- 	<div class=container-fluid> -->
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="query-region">
				<form class="form-inline">
					<div class="control-group col-md-8 text-left" style="margin:10px 0px;">
					    <label for="start">请求时间</label>
		                <div class="input-group date form_date col-md-3" data-date="" data-date-format="yyyy-mm-dd" data-link-field="start" data-link-format="yyyy-mm-dd">
		                    <input class="form-control" size="16" type="text" value="" readonly>
		                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
							<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
		                </div>
						<input type="hidden" id="start" value="" />
					    <label for="end">至</label>
		                <div class="input-group date form_date col-md-3" data-date="" data-date-format="yyyy-mm-dd" data-link-field="end" data-link-format="yyyy-mm-dd">
		                    <input class="form-control" size="16" type="text" value="" readonly>
		                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
							<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
		                </div>
						<input type="hidden" id="end" value="" />
						
						<span style="width:10px;">    </span>
						<label for="phone" class="control-label">手机号</label>
						<input class="form-control x-amount" size="16" type="text" id="phone" name="phone">
				     	<button type="button" class="btn btn-info" onclick="query()">查 询</button>
				    </div>
				    <div class="col-md-4 text-right" style="margin-top:20px;vertical-align:bottom;">
				     	<label for="end" style="display:block;font-size:10px;"><a href="javascript:openStatusWindow();" data-toggle="modal" style="color:red;cursor:pointer;" target="_blank">注：发送状态(0或DELIVRD为成功,其他均为发送失败)</a></label>
				    </div>
				</form>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">
			<table id="smsTable" class="table-striped table-bordered">
			</table>
			</div>
		</div>
		
	</div>
<%-- <script type="text/javascript" src="${ctx}/static/jquery/1.9.1/jquery-1.9.1.min.js"></script> --%>
<%-- <script type="text/javascript" src="${ctx}/static/bootstrap/3.3.5/js/bootstrap.min.js"></script> --%>
<%-- <script src="${ctx}/static/bootstrap/table/js/bootstrap-table.min.js" type="text/javascript"></script> --%>
<%-- <script src="${ctx}/static/bootstrap/table/local/bootstrap-table-zh-CN.min.js" type="text/javascript"></script> --%>
<%-- <script type="text/javascript" src="${ctx}/static/bootstrap/3.3.5/js/bootstrap-datetimepicker.min.js"></script> --%>
<%-- <script type="text/javascript" src="${ctx}/static/bootstrap/3.3.5/local/bootstrap-datetimepicker.zh-CN.js"></script> --%>
<!-- <script type="text/javascript" src="http://cdn.bootcss.com/x-editable/1.5.1/bootstrap3-editable/js/bootstrap-editable.min.js"></script> -->
<%-- <script type="text/javascript" src="${ctx}/static/bootstrap/x-editable/js/bootstrap-editable.min.js"></script> --%>
<script type="text/javascript">

</script>
</body>
</html>