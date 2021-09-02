<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
%>
<!DOCTYPE html>
<html>
	<head>

		<meta charset="UTF-8">
		<base href="<%=basePath%>">
		<link rel="stylesheet" href="css/bootstrap.css" />
		<link rel="stylesheet" href="css/login.css" />
		<script type="text/javascript" src="js/jquery-3.3.1.js"></script>
		<script type="text/javascript" src="js/bootstrap.js"></script>
		<title></title>
		
	</head>

	<body>
		<div id="login">
			<div id="top">
				<img src="images/cloud.jpg" /><span>LOGIN</span>
			</div>
			<div id="bottom">
					<table border="0px" id="table">
						<tr>
							<td class="td1">用户名：</td>
							<td><input type="text" id="loginName" value="admin" placeholder="Username" class="td2" name="name"></td>
						</tr>
						<tr>
							<td></td>
							<td><span id="nameerr"></span></td>
						</tr>
						<tr>
							<td class="td1">密码：</td>
							<td><input type="password" id="loginPwd" value="000000" placeholder="Password" class="td2" name="pwd"></td>
						</tr>
						<tr>
							<td></td>
							<td><span id="pwderr"></span></td>
						</tr>
						<tr>
							<td></td>
							<td><button id="submitBtn" value="登录" class="td3">登录</button>
								<a href="admin/regist.jsp"><input type="button" value="注册" class="td3	"></a>
							</td>
						</tr>
					</table>
				<span id="errmsg" style="color: red"></span>

		</div>
	</body>
<script>

	$(function () {
		if (window.top != window)
		{
			window.top.location = window.location;
		}
		// 当页面加载完毕过后，将用户账号输入框的历史记录清空
		$("#loginAct").val("");
		// 当页面加载完成过后，将用户输入框获取焦点
		$("#loginAct").focus();
		// 为登录按钮绑定事件，执行登录操作
		$("#submitBtn").click(function () {
			login();
		});
		// 为当前登录窗口设置敲击键盘事件
		$(window).keydown(function (event) {
			// 如果取得的码值为13，表示敲击的是回车
			if (event.keyCode == 13){
				login();
			}
		})
	});
	function login() {
		// 验证账号密码不能为空
		// 取得账号密码进行判断
		var loginAct = $.trim($("#loginName").val());
		var loginPwd = $.trim($("#loginPwd").val());

		if (loginAct == "" || loginPwd == ""){
			alert("账号密码不能为空，请输入");
			return false;
		}

		// 向后端发送Ajax请求来验证登录信息
		$.ajax({
			url:"admin/login.do",
			type:"post",
			data:{
				"name":loginAct,
				"pwd":loginPwd,
			},
			dataType:"json",
			success: function (data) {
				if (data){
					// 跳转到网站的首页面
					window.location.href = "admin/main.jsp"
				}else {
					$("#errmsg").html("用户名或者密码不正确");
				}
			}
		})
	}

</script>
</html>