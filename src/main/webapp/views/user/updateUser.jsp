<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="rapid" uri="http://www.rapid-framework.org.cn/rapid"%>

<rapid:override name="pageTitle">
	Chỉnh sửa thông tin người dùng
</rapid:override>

<rapid:override name="lastNavtab">
	<li class="nav-item">
	  	<a class="nav-link" href="#">Chỉnh sửa</a>
	</li>
</rapid:override>

<rapid:override name="navTabId">3</rapid:override>

<rapid:override name="lastForm">
	<script>
		document.querySelector("#fullName").value = "${user.fullName}";
		document.querySelector("#username").value = "${user.username}";
		document.querySelector("#password").value = "${user.password}";
		document.querySelector("#roleId").value = ${user.role.id};
		document.querySelector("#enable").checked = ${user.enable};
	</script>
</rapid:override>

<%@ include file="insertUser.jsp"%>