<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<nav
	class="navbar navbar-expand-sm bg-primary navbar-dark navbar-fixed-top">
	<a class="navbar-brand" href="${pageContext.request.contextPath}">Trang
		chủ</a>
	<ul class="navbar-nav mx-auto">
		<li class="nav-item active"><a class="nav-link"
			href="${pageContext.request.contextPath}/role">Quản lý quyền</a></li>
		<li class="nav-item active"><a class="nav-link"
			href="${pageContext.request.contextPath}/user">Quản lý người dùng</a></li>
	</ul>
</nav>