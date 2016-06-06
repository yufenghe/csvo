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
<script type="text/javascript" src="${ctx}/static/jquery/1.9.1/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="${ctx}/static/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script src="${ctx}/static/bootstrap/table/js/bootstrap-table.min.js" type="text/javascript"></script>
<script src="${ctx}/static/bootstrap/table/local/bootstrap-table-zh-CN.min.js" type="text/javascript"></script>
<script type="text/javascript" src="${ctx}/static/bootstrap/3.3.5/js/bootstrap-datepicker.min.js"></script>
<script type="text/javascript" src="${ctx}/static/bootstrap/3.3.5/local/bootstrap-datepicker.zh-CN.min.js"></script>
<!-- <script type="text/javascript" src="http://cdn.bootcss.com/x-editable/1.5.1/bootstrap3-editable/js/bootstrap-editable.min.js"></script> -->
<script type="text/javascript" src="${ctx}/static/bootstrap/x-editable/js/bootstrap-editable.min.js"></script>
<script type="text/javascript" src="${ctx}/static/bootstrap/3.3.5/js/respond.min.js"></script>
<script type="text/javascript">
function fmtType(value, row, index) {
	if(!value) return value;
	if(value == 1) {
		return "用户主动获取";
	}
	else if(value == 2) {
		return "系统推送";
	}
}

function fmtSmsType(value, row, index) {
	if(!value) return value;
	if(value == 1) {
		return "短信";
	}
	else if(value == 2) {
		return "语音";
	}
}

function fmtSpId(value, row, index) {
	return "<a href='#' class='spId' data-type='text' data-pk='" + row.seqId + "' data-placement='right'>" + value + "</a>";
}

$(function () {
	$.fn.editable.defaults.mode = 'popup'; 
	
	$('.form_date').datepicker({
		language:'zh-CN',
		autoclose:1,
		todayBtn:'linked',
		todayHighlight: 1,
		weekStart: 1
	}).on('changeDate', function(ev) {
	});
	
	var columns = [
	   {field:'seqId', title:'编号', width:100, halign:'center'},      
	   {field:'sysCode', title:'业务系统代码', width:90, halign:'center'},            
	   {field:'spId', title:'服务提供商', width:90, halign:'center', formatter:fmtSpId},            
	   {field:'serverIp', title:'服务器IP', width:90, halign:'center'},            
	   {field:'mobile', title:'手机号', width:60, halign:'center'},            
	   {field:'content', title:'内容', halign:'center'},            
	   {field:'state', title:'发送状态', width:80, halign:'center'},            
	   {field:'type', title:'发送类型', width:80, halign:'center', formatter:fmtType},            
	   {field:'sendTime', title:'请求时间', width:100, halign:'center'},            
	   {field:'spSendTime', title:'发送时间', width:90, halign:'center'},            
	   {field:'spmtNumber', title:'下行特服号', width:90, halign:'center'},            
	   {field:'smsType', title:'短信类型', width:80, halign:'center', align:'center', formatter:fmtSmsType}           
    ];
	$('#smsTable').bootstrapTable({
		  method: 'get',
		  url: '${ctx}/query.uz',
		  dataType: "json",
		  striped: true,	 						//使表格带有条纹
		  pageSize: 10,
		  pageNumber: 1,
		  pageList: [10, 20, 50, 100, 200],
		  columns:columns,
		  silent: true,
		  singleSelect: true,						//只能选择一条记录
		  cache:false,
		  sidePagination: "server",					//表格分页的从服务端查询
		  queryParams: queryParams, 				//查询参数
		  queryParamsType: "limit", 				//参数格式,发送标准的RESTFul类型的参数请求
		  local:'zh-CN',
		  pagination: true,							//在表格底部显示分页工具栏
		  paginationVAlign:'bottom',
		  paginationHAlign:'right',
		  paginationDetailHAlign:'left',
		  formatLoadingMessage: function () {
		    return "请稍等，正在加载中...";
		  },
		  formatNoMatches: function () {  			//没有匹配的结果
		    return '<span style="text-align:center;width:100%;height:100%;">无符合条件的记录</span>';
		  },
		  onLoadError: function (data) {
		    $('#smsTable').bootstrapTable('removeAll');
		  },
		  onPostBody:function() {
			  $('.spId').editable({
			       url: '${ctx}/change.uz',
			       validate: function(value) {
			            if($.trim(value) == '') {
			                return 'This field is required';
			            }
			       },
			       send: 'auto', 
			       success: function(response, newValue) {
			            if(response.success) return response.result;
			       },
			       ajaxOptions: {
			            type: 'post',
			            dataType: 'json'
			       }
			  });
		  }
		});
  });
  
function queryParams(params) {
	var options = $('#smsTable').bootstrapTable('getOptions');
// 	var currentPage = options.pageNumber ? options.pageNumber : 1;
	var pageSize = options.pageSize;
	var queryParam = {'currentPage':1};
	if(params) {
		pageSize = params.limit;
		var start = $('#start').val();
		var end = $('#end').val();
		queryParam['start'] = start;
		queryParam['end'] = end;
		queryParam['phone'] = $('#phone').val();
	}
	
	queryParam['pageSize'] = pageSize;
	return queryParam;
}

function query() {
	var options = $('#smsTable').bootstrapTable('getOptions');
	var currentPage = options.pageNumber ? options.pageNumber : 1;
	var queryParam = {'currentPage':currentPage};
	var pageSize = options.pageSize;
	var start = $('#start').val();
	var end = $('#end').val();
	if(!start) {
		$('#start').focus();
		return false;
	}
	if(!end) {
		$('#end').focus();
		return false;
	}
	var phone = $('#phone').val();
	var reg = new RegExp(/^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})|(17[0-9]){1})+\d{8})$/); 
	if(phone && !reg.test(phone)) 
	{ 
		$('#phone').focus(); 
	    return false; 
	}
	
	queryParam['phone'] = phone;
	queryParam['start'] = start;
	queryParam['end'] = end;
	queryParam['pageSize'] = pageSize;
	$('#smsTable').bootstrapTable('refresh', {query:queryParam});
}

function openStatusWindow() {
	window.open('${ctx}/showStatusList.uz', '发送状态一览表', '', true);
}
</script>
</body>
</html>