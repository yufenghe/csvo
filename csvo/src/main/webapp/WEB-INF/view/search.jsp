<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="<%=request.getContextPath()%>" />
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta content="always" name="referrer">
<title>搜索页面模板</title>
<link rel="stylesheet" href="${ctx}/static/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet" href="${ctx}/static/css/search.css">
<script type="text/javascript" src="${ctx}/static/jquery/1.9.1/jquery-1.9.1.min.js"></script>
<script type="text/javascript">
	$(function() {
		$('#search').on('click', function() {
			$('div.overlay').addClass('open');
			$.post('${ctx}/searchResult.uz',{key:$('#searchContext').val()}, function(data) {
				$('div.overlay').removeClass('open');
				$('#content_left').append(data);
			});
		});
	});
	
</script>
</head>
<body>
	<div id="content_left">
	<div id="head" class="s_down">
		<div class="head_wrapper">
			<div class="s_form">
				<div class="" style="padding:4px;">
					<form class="bs-example bs-example-form" role="form">
				      <div class="row">
				         <div class="col-md-4">
				            <div class="input-group">
				               <input type="text" id="searchContext" class="form-control">
				               <span class="input-group-btn">
				                  <button  id="search" class="btn btn-default" type="button">搜一搜</button>
				               </span>
				            </div><!-- /input-group -->
				         </div><!-- /.col-lg-6 -->
				      </div><!-- /.row -->
				   </form>
				</div>
			</div>
		</div>
	</div>
		<div class="leftBlock">
			<div class="hint_toprq_tips f13 se_common_hint" data-id="28300"
				data-tpl="hint_toprq_tips" style="display: block;">
				<span class="hint_toprq_icon"><i
					class="c-icon c-icon-bear-circle c-gap-right-small res-queryext-pos"></i>为您推荐:</span>
				<span class="hint_toprq_tips_items">
					<div>
						<a target="_blank" href="">搜索引擎页面html模板</a>
					</div>
					<div>
						<a target="_blank" href="">搜索html模板</a>
					</div>
					<div>
						<a target="_blank" href="">搜索结果页面html模板</a>
					</div>
					<div>
						<a target="_blank" href="">登陆页面模板 html</a>
					</div>
				</span>
			</div>
		</div>
	</div>
	<div class="overlay overlay-corner">
	<div class="loading">
		<img alt="加载中..." src="${ctx }/static/css/loading.gif">
	</div>
</div>
</body>

</html>
