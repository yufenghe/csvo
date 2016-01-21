<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<c:set var="ctx" value="<%=request.getContextPath()%>"/>  
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="Charisma, a fully featured, responsive, HTML5, Bootstrap admin template.">
<meta name="author" content="Muhammad Usman">
<title>redis查询</title>
<%-- <link href="${ctx}/static/css/bootstrap-responsive.css" rel="stylesheet"> --%>
<link rel="stylesheet" href="${ctx}/static/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet" href="${ctx}/static/bootstrap/table/css/bootstrap-table.min.css">
<%-- <link rel="stylesheet" href="${ctx}/static/bootstrap/x-editable/css/bootstrap-editable.css"> --%>
<link href="${ctx}/static/bootstrap/select/css/bootstrap-cerulean.min.css" rel="stylesheet">
<style type="text/css">
  body {
	padding-bottom: 40px;
  }
  .sidebar-nav {
	padding: 9px 0;
  }
</style>
<link href="${ctx}/static/bootstrap/select/css/charisma-app.css" rel="stylesheet">
<link href="${ctx}/static/bootstrap/select/css/jquery-ui-1.8.21.custom.css" rel="stylesheet">
<link href="${ctx}/static/bootstrap/select/css/bootstrap-select.min.css" rel="stylesheet">
<%-- <link rel="stylesheet" href="${ctx}/static/css/main.css"> --%>
</head>
<body>
	<div class="ch-container">
    <div class="row">
        
        <!-- left menu starts -->
        <div class="col-sm-2 col-lg-2">
            <div class="sidebar-nav">
                <div class="nav-canvas">
                    <div class="nav-sm nav nav-stacked">
                    </div>
                    <ul class="nav nav-pills nav-stacked main-menu" id="dbview">
                        <li class="nav-header">
							<select id="selectServer" class="selectpicker" data-size="5">
							</select>
                        </li>
                        <li class="nav-header">DBS</li>
<!--                         <li><a class="ajax-link" href="index.html"><i class="glyphicon glyphicon-home"></i><span> Dashboard</span></a> -->
<!--                         </li> -->
<!--                         <li><a class="ajax-link" href="ui.html"><i class="glyphicon glyphicon-eye-open"></i><span> UI Features</span></a> -->
<!--                         </li> -->
<!--                         <li><a class="ajax-link" href="form.html"><i -->
<!--                                     class="glyphicon glyphicon-edit"></i><span> Forms</span></a></li> -->
<!--                         <li><a class="ajax-link" href="chart.html"><i class="glyphicon glyphicon-list-alt"></i><span> Charts</span></a> -->
<!--                         </li> -->
<!--                         <li><a class="ajax-link" href="typography.html"><i class="glyphicon glyphicon-font"></i><span> Typography</span></a> -->
<!--                         </li> -->
<!--                         <li><a class="ajax-link" href="gallery.html"><i class="glyphicon glyphicon-picture"></i><span> Gallery</span></a> -->
<!--                         </li> -->
<!--                         <li class="nav-header hidden-md">Sample Section</li> -->
<!--                         <li><a class="ajax-link" href="table.html"><i -->
<!--                                     class="glyphicon glyphicon-align-justify"></i><span> Tables</span></a></li> -->
<!--                         <li class="accordion"> -->
<!--                             <a href="#"><i class="glyphicon glyphicon-plus"></i><span> Accordion Menu</span></a> -->
<!--                             <ul class="nav nav-pills nav-stacked"> -->
<!--                                 <li><a href="#">Child Menu 1</a></li> -->
<!--                                 <li><a href="#">Child Menu 2</a></li> -->
<!--                             </ul> -->
<!--                         </li> -->
<!--                         <li><a class="ajax-link" href="calendar.html"><i class="glyphicon glyphicon-calendar"></i><span> Calendar</span></a> -->
<!--                         </li> -->
<!--                         <li><a class="ajax-link" href="grid.html"><i -->
<!--                                     class="glyphicon glyphicon-th"></i><span> Grid</span></a></li> -->
<!--                         <li><a href="tour.html"><i class="glyphicon glyphicon-globe"></i><span> Tour</span></a></li> -->
<!--                         <li><a class="ajax-link" href="icon.html"><i -->
<!--                                     class="glyphicon glyphicon-star"></i><span> Icons</span></a></li> -->
<!--                         <li><a href="error.html"><i class="glyphicon glyphicon-ban-circle"></i><span> Error Page</span></a> -->
<!--                         </li> -->
<!--                         <li><a href="login.html"><i class="glyphicon glyphicon-lock"></i><span> Login Page</span></a> -->
<!--                         </li> -->
                    </ul>
                    <label id="for-is-ajax" for="is-ajax"><input id="is-ajax" type="checkbox"> Ajax on menu</label>
                </div>
            </div>
        </div>
        <!--/span-->
        <!-- left menu ends -->
			<noscript>
				<div class="alert alert-block span10">
					<h4 class="alert-heading">Warning!</h4>
					<p>You need to have <a href="http://en.wikipedia.org/wiki/JavaScript" target="_blank">JavaScript</a> enabled to use this site.</p>
				</div>
			</noscript>
			
			<div id="content" class="col-lg-10 col-sm-10">
				<div>
					<ul class="breadcrumb">
						<li>
							<a href="#">Home</a> <span class="divider">/</span>
						</li>
						<li>
							<a href="#">Dashboard</a>
						</li>
					</ul>
				</div>
				<div class="row">
					 <div class="col-md-3 col-sm-3 col-xs-6">
				        <a data-toggle="tooltip" title="6 new members." class="well top-block" href="#">
				            <i class="glyphicon glyphicon-user blue"></i>
				
				            <div>Total Members</div>
				            <div>507</div>
				            <span class="notification">6</span>
				        </a>
				    </div>
				
				    <div class="col-md-3 col-sm-3 col-xs-6">
				        <a data-toggle="tooltip" title="4 new pro members." class="well top-block" href="#">
				            <i class="glyphicon glyphicon-star green"></i>
				
				            <div>Pro Members</div>
				            <div>228</div>
				            <span class="notification green">4</span>
				        </a>
				    </div>
	
<!-- 					<a data-rel="tooltip" title="$34 new sales." class="well span3 top-block" href="#"> -->
<!-- 						<span class="icon32 icon-color icon-cart"></span> -->
<!-- 						<div>Sales</div> -->
<!-- 						<div>$13320</div> -->
<!-- 						<span class="notification yellow">$34</span> -->
<!-- 					</a> -->
					
<!-- 					<a data-rel="tooltip" title="12 new messages." class="well span3 top-block" href="#"> -->
<!-- 						<span class="icon32 icon-color icon-envelope-closed"></span> -->
<!-- 						<div>Messages</div> -->
<!-- 						<div>25</div> -->
<!-- 						<span class="notification red">12</span> -->
<!-- 					</a> -->
				</div>
				<div class="row">
					<table id="smsTable" class="table-striped table-bordered">
					</table>
				</div>
			</div>
		</div>
	</div>
		
		
		
<script type="text/javascript" src="${ctx}/static/jquery/1.9.1/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="${ctx}/static/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${ctx}/static/bootstrap/treeview/js/bootstrap-treeview.min.js"></script>
<script src="${ctx}/static/bootstrap/table/js/bootstrap-table.min.js" type="text/javascript"></script>
<script src="${ctx}/static/bootstrap/table/local/bootstrap-table-zh-CN.min.js" type="text/javascript"></script>
<%-- <script type="text/javascript" src="${ctx}/static/bootstrap/x-editable/js/bootstrap-editable.min.js"></script> --%>
<script src="${ctx}/static/bootstrap/select/js/bootstrap-select.min.js" type="text/javascript"></script>
<script type="text/javascript">
function loadDB(id) {
	$.post('${ctx}/redis/showdb',{id:id}, function(data) {
		if(!data || data.length == 0) {
			return false;
		}
		
		var tempHtml = "";
		$.each(data, function(i, n) {
			tempHtml += '<li><a class="ajax-link" id="' + n.id + '" href="javascript:query(' + n.id + ')"><i class="glyphicon glyphicon-home"></i><span> ' + n.name + '</span></a>';
		});
		
		$('#dbview').append(tempHtml);
	});
}
$(function () {
// 	$.fn.editable.defaults.mode = 'popup'; 

	$('#selectServer').on('change', function () {
		var id = $('#selectServer').val();
		loadDB(id);
	});
	$.post('${ctx}/redis/serverInfo',{},function(data) {
		if(!data || data.length == 0) {
			$('#selectServer').selectpicker({noneSelectedText:'SELECT SERVER'});
			return false;
		}
		
		var tempAjax = "";
		$.each(data,function(i,n){
             tempAjax += "<option value='"+n.id +"'>"+n.name+"</option>";
        });
        $("#selectServer").append(tempAjax);    
        //更新内容刷新到相应的位置
        $('#selectServer').selectpicker('render');
        $('#selectServer').selectpicker('refresh');
        loadDB($('#selectServer').selectpicker('val'));
	});
	
	var columns = [
	   {field:'key', title:'KEY', width:100, halign:'center'},      
	   {field:'type', title:'类型', width:90, halign:'center'}          
    ];
	$('#smsTable').bootstrapTable({
		  method: 'get',
		  url: '${ctx}/redis/showkeys.uz',
		  dataType: "json",
		  striped: true,	 						//使表格带有条纹
		  columns:columns,
		  silent: true,
		  singleSelect: true,						//只能选择一条记录
		  cache:false,
		  queryParams: queryParams, 				//查询参数
		  queryParamsType: "limit", 				//参数格式,发送标准的RESTFul类型的参数请求
		  local:'zh-CN',
		  pagination: false,							//在表格底部显示分页工具栏
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
		  }
		});
  });
  
function queryParams(params) {
	var queryParam = {'id':'', 'db':''};
	return queryParam;
}

function query(db) {
	alert(db)
	var id = $('#selectServer').selectpicker('val');
	var queryParam = {id:id, db:db};
	$('#smsTable').bootstrapTable('refresh', {query:queryParam});
}

function openStatusWindow() {
	window.open('${ctx}/showStatusList.uz', '发送状态一览表', '', true);
}
</script>
</body>
</html>