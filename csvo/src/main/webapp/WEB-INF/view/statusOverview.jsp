<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<c:set var="ctx" value="<%=request.getContextPath()%>"/>  
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>查询状态一览</title>
<link rel="stylesheet" href="${ctx}/static/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet" href="${ctx}/static/bootstrap/table/css/bootstrap-table.min.css">
<link rel="stylesheet" href="${ctx}/static/css/main.css">
<script type="text/javascript" src="${ctx}/static/jquery/1.9.1/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="${ctx}/static/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script src="${ctx}/static/bootstrap/table/js/bootstrap-table.min.js" type="text/javascript"></script>
<script src="${ctx}/static/bootstrap/table/local/bootstrap-table-zh-CN.min.js" type="text/javascript"></script>
<script type="text/javascript">
$(function() {
	
});

</script>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-12">
			<table id="smsTable" data-toggle="table" data-cache="true" class="table-striped table-bordered">
				<thead>
					<tr>
						<th data-halign="center">序号</th>
						<th data-halign="center">错误码</th>
						<th data-halign="center">错误侧</th>
						<th data-halign="center">错误描述</th>
					</tr>
				</thead>
				<tbody>
					<tr><td data-align="center">1</td><td>0或DELIVRD</td><td>服务端</td><td>成功</td></tr>
					<tr><td data-align="center">2</td><td>-2</td><td>客户端</td><td>客户端异常</td></tr>
					<tr><td data-align="center">3</td><td>-9000</td><td></td><td>数据格式错误,数据超出数据库允许范围</td></tr>
					<tr><td data-align="center">4</td><td>-9001</td><td>客户端,所有业务</td><td>序列号格式错误</td></tr>
					<tr><td data-align="center">5</td><td>-9002</td><td>客户端,所有业务</td><td>密码格式错误</td></tr>
					<tr><td data-align="center">6</td><td>-9003</td><td>客户端,所有业务</td><td>客户端Key格式错误</td></tr>
					<tr><td data-align="center">7</td><td>-9004</td><td>客户端,转发业务</td><td>设置转发格式错误</td></tr>
					<tr><td data-align="center">8</td><td>-9005</td><td>客户端,企业注册业务</td><td>公司地址格式错误</td></tr>
					<tr><td data-align="center">9</td><td>-9006</td><td>客户端,企业注册业务</td><td>企业中文名格式错误</td></tr>
					<tr><td data-align="center">10</td><td>-9007</td><td>客户端,企业注册业务</td><td>企业中文名简称格式错误</td></tr>
					<tr><td data-align="center">11</td><td>-9008</td><td>客户端,企业注册业务</td><td>邮件地址格式错误</td></tr>
					<tr><td data-align="center">12</td><td>-9009</td><td>客户端,企业注册业务</td><td>企业英文名格式错误</td></tr>
					<tr><td data-align="center">13</td><td>-9010</td><td>客户端,企业注册业务</td><td>企业英文名简称格式错误</td></tr>
					<tr><td data-align="center">14</td><td>-9011</td><td>客户端,企业注册业务</td><td>传真格式错误</td></tr>
					<tr><td data-align="center">15</td><td>-9012</td><td>客户端,企业注册业务</td><td>联系人格式错误</td></tr>
					<tr><td data-align="center">16</td><td>-9013</td><td>客户端,企业注册业务</td><td>联系电话</td></tr>
					<tr><td data-align="center">17</td><td>-9014</td><td>客户端,企业注册业务</td><td>邮编格式错误</td></tr>
					<tr><td data-align="center">18</td><td>-9015</td><td>客户端,密码修改业务</td><td>新密码格式错误</td></tr>
					<tr><td data-align="center">19</td><td>-9016</td><td>客户端,发送业务</td><td>发送短信包大小超出范围</td></tr>
					<tr><td data-align="center">20</td><td>-9017</td><td>客户端,发送业务</td><td>发送短信内容格式错误</td></tr>
					<tr><td data-align="center">21</td><td>-9018</td><td>客户端,发送业务</td><td>发送短信扩展号格式错误</td></tr>
					<tr><td data-align="center">22</td><td>-9019</td><td>客户端,发送业务</td><td>发送短信优先级格式错误</td></tr>
					<tr><td data-align="center">23</td><td>-9020</td><td>客户端,发送业务</td><td>发送短信手机号格式错误</td></tr>
					<tr><td data-align="center">24</td><td>-9021</td><td>客户端,发送业务</td><td>发送短信定时时间格式错误</td></tr>
					<tr><td data-align="center">25</td><td>-9022</td><td>客户端,发送业务</td><td>发送短信唯一序列值错误</td></tr>
					<tr><td data-align="center">26</td><td>-9023</td><td>客户端,充值业务</td><td>充值卡号格式错误</td></tr>
					<tr><td data-align="center">27</td><td>-9024</td><td>客户端,充值业务</td><td>充值密码格式错误</td></tr>
					<tr><td data-align="center">28</td><td>-1</td><td>服务器端</td><td>系统异常</td></tr>
					<tr><td data-align="center">29</td><td>-101</td><td>服务器端</td><td>命令不被支持</td></tr>
					<tr><td data-align="center">30</td><td>-102</td><td>服务器端</td><td>RegistryTransInfo删除信息失败</td></tr>
					<tr><td data-align="center">31</td><td>-103</td><td>服务器端</td><td>RegistryInfo更新信息失败</td></tr>
					<tr><td data-align="center">32</td><td>-104</td><td>服务器端</td><td>请求超过限制</td></tr>
					<tr><td data-align="center">34</td><td>-111</td><td>服务器端</td><td>企业注册失败</td></tr>
					<tr><td data-align="center">35</td><td>-117</td><td>服务器端</td><td>发送短信失败</td></tr>
					<tr><td data-align="center">36</td><td>-118</td><td>服务器端</td><td>接收MO失败</td></tr>
					<tr><td data-align="center">37</td><td>-119</td><td>服务器端</td><td>接收Report失败</td></tr>
					<tr><td data-align="center">38</td><td>-120</td><td>服务器端</td><td>修改密码失败</td></tr>
					<tr><td data-align="center">39</td><td>-122</td><td>服务器端</td><td>号码注销激活失败</td></tr>
					<tr><td data-align="center">40</td><td>-110</td><td>服务器端</td><td>号码注册激活失败</td></tr>
					<tr><td data-align="center">41</td><td>-123</td><td>服务器端</td><td>查询单价失败</td></tr>
					<tr><td data-align="center">42</td><td>-124</td><td>服务器端</td><td>查询余额失败</td></tr>
					<tr><td data-align="center">43</td><td>-125</td><td>服务器端</td><td>设置MO转发失败</td></tr>
					<tr><td data-align="center">44</td><td>-126</td><td>服务器端</td><td>路由信息失败</td></tr>
					<tr><td data-align="center">45</td><td>-127</td><td>服务器端</td><td>计费失败0余额</td></tr>
					<tr><td data-align="center">46</td><td>-128</td><td>服务器端</td><td>计费失败余额不足</td></tr>
					<tr><td data-align="center">47</td><td>-1100</td><td>服务器端</td><td>序列号错误,序列号不存在内存中,或尝试攻击的用户</td></tr>
					<tr><td data-align="center">48</td><td>-1103</td><td>服务器端</td><td>序列号Key错误</td></tr>
					<tr><td data-align="center">49</td><td>-1102</td><td>服务器端</td><td>序列号密码错误</td></tr>
					<tr><td data-align="center">50</td><td>-1104</td><td>服务器端</td><td>路由失败，请联系系统管理员</td></tr>
					<tr><td data-align="center">51</td><td>-1105</td><td>服务器端</td><td>注册号状态异常, 未用 1</td></tr>
					<tr><td data-align="center">52</td><td>-1107</td><td>服务器端</td><td>注册号状态异常, 停用 3</td></tr>
					<tr><td data-align="center">53</td><td>-1108</td><td>服务器端</td><td>注册号状态异常, 停止 5</td></tr>
					<tr><td data-align="center">54</td><td>-113</td><td>服务器端</td><td>充值失败</td></tr>
					<tr><td data-align="center">55</td><td>-1131</td><td>服务器端</td><td>充值卡无效</td></tr>
					<tr><td data-align="center">56</td><td>-1132</td><td>服务器端</td><td>充值密码无效</td></tr>
					<tr><td data-align="center">57</td><td>-1133</td><td>服务器端</td><td>充值卡绑定异常</td></tr>
					<tr><td data-align="center">58</td><td>-1134</td><td>服务器端</td><td>充值状态无效</td></tr>
					<tr><td data-align="center">59</td><td>-1135</td><td>服务器端</td><td>充值金额无效</td></tr>
					<tr><td data-align="center">60</td><td>-190</td><td>服务器端</td><td>数据操作失败</td></tr>
					<tr><td data-align="center">61</td><td>-1901</td><td>服务器端</td><td>数据库插入操作失败</td></tr>
					<tr><td data-align="center">62</td><td>-1902</td><td>服务器端</td><td>数据库更新操作失败</td></tr>
					<tr><td data-align="center">63</td><td>-1903</td><td>服务器端</td><td>数据库删除操作失败</td></tr>
				</tbody>
			</table>
			</div>
		</div>
	</div>
</body>
</html>