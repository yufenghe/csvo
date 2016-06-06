<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">
<title>My WebSocket</title>
</head>

<body>
	Welcome
	<br />
	<input id="userid" type="text" />
	<input id="text" type="text" />
	<button onclick="send()">Send</button>
	<button onclick="closeWebSocket()">Close</button>
	<div id="message"></div>
</body>

<script type="text/javascript">
	window.websocket = function() {
		var _websocket = null;

		var init = function() {
			if ('WebSocket' in window) {
				_websocket = new WebSocket("ws://localhost:9898/csvo/websocket/" + document.getElementById('userid').value);
				window._websocket = _websocket;
			} else {
				alert('Not support websocket');
			}

			//连接发生错误的回调方法
			_websocket.onerror = function() {
				setMessageInnerHTML("error");
			};

			//连接成功建立的回调方法
			_websocket.onopen = function(event) {
				setMessageInnerHTML("open");
			};

			//接收到消息的回调方法
			_websocket.onmessage = function() {
				setMessageInnerHTML(event.data);
			};

			//连接关闭的回调方法
			_websocket.onclose = function() {
				setMessageInnerHTML("close");
			};

			//监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
			window.onbeforeunload = function() {
				_websocket.close();
			};
		};

		var setMessageInnerHTML = function(innerHTML) {
			document.getElementById('message').innerHTML += innerHTML + '<br/>';
		};

		return init;
	}();
	//关闭连接
	function closeWebSocket() {
		window._websocket.close();
		delete window._websocket;
	};

	//发送消息
	function send() {
		if (!window._websocket) {
			window.websocket();
		}
		
		waitForSocketConnection(window._websocket, function(){
	        console.log("message sent!!!");
			var message = document.getElementById('text').value;
			window._websocket.send(message);
	    });
	};
	
	// Make the function wait until the connection is made...
	function waitForSocketConnection(socket, callback){
	    setTimeout(
	        function () {
	            if (socket.readyState === 1) {
	                console.log("Connection is made");
	                if(callback != null){
	                    callback();
	                }
	                return;

	            } else {
	                console.log("wait for connection...");
	                waitForSocketConnection(socket, callback);
	            }

	        }, 5); // wait 5 milisecond for the connection...
	}
</script>
</html>