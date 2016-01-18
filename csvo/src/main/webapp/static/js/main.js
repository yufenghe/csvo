require.config({
//	baseUrl:'',
    paths: {
        jquery: '../jquery/1.9.1/jquery-1.9.1.min',
        bootstrap: '../bootstrap/3.3.5/js/bootstrap.min',
        bootstraptable:'../bootstrap/table/js/bootstrap-table.min',
        bootstraptablelocal:'../bootstrap/table/local/bootstrap-table-zh-CN.min',
        bootstrapdatetimepicker:'../bootstrap/3.3.5/js/bootstrap-datetimepicker.min',
        bootstrapdatetimepikcerlocal:'../bootstrap/3.3.5/local/bootstrap-datetimepicker.zh-CN',
        bootstrapeditable:'../bootstrap/x-editable/js/bootstrap-editable.min'
    },
    shim: {
    	bootstrap:['jquery'],
    	bootstraptable:['bootstrap'],
    	bootstraptablelocal:['bootstraptable'],
    	bootstrapdatetimepicker:['jquery', 'bootstrap'],
    	bootstrapdatetimepikcerlocal:['bootstrapdatetimepicker'],
    	bootstrapeditable:['bootstraptable']
    }
});
 
require(['jquery', 'bootstrap', 'bootstraptable', 'bootstraptablelocal', 
         'bootstrapdatetimepicker', 'bootstrapdatetimepikcerlocal', 'bootstrapeditable'], function($) {

	$(function () {
		$.fn.editable.defaults.mode = 'popup'; 
		
		$('.form_date').datetimepicker({
		    language:  'zh-CN',
		    weekStart: 1,
		    todayBtn:  1,
			autoclose: 1,
			todayHighlight: 1,
			startView: 2,
			minView: 2,
			forceParse: 0,
			pickerPosition:'bottom-left'
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
			  url: _ctx + '/query.uz',
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
	  
});
function queryParams(params) {
	var options = $('#smsTable').bootstrapTable('getOptions');
//	 	var currentPage = options.pageNumber ? options.pageNumber : 1;
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
	window.open(_ctx + '/showStatusList.uz', '发送状态一览表', '', true);
}

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