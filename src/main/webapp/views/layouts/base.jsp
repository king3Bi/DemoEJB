<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="rapid" uri="http://www.rapid-framework.org.cn/rapid"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">

<title><rapid:block name="pageTitle">
		My title
	</rapid:block></title>

<rapid:block name="css">
	<link rel="stylesheet"
		href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
	<link rel="stylesheet"
		href="https://use.fontawesome.com/releases/v5.7.0/css/all.css"
		integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ"
		crossorigin="anonymous">
</rapid:block>

<rapid:block name="js">
	<script
		src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
</rapid:block>
</head>
<body>
	<div class="container">
		<rapid:block name="header">
			<%@ include file="header.jsp"%>
		</rapid:block>

		<rapid:block name="msg">
		</rapid:block>

		<rapid:block name="content">
			My content
		</rapid:block>

		<rapid:block name="footer">
			<%@ include file="footer.jsp"%>
		</rapid:block>
	</div>
</body>
</html>