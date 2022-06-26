<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="rapid" uri="http://www.rapid-framework.org.cn/rapid"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<rapid:override name="content">

	<ul class="nav nav-tabs  mt-4">
		<rapid:block name="firstNavtab">
		</rapid:block>
		<li class="nav-item"><a class="nav-link"
			href="${pageContext.request.contextPath}/role">Danh sách</a></li>
		<li class="nav-item"><a class="nav-link"
			href="${pageContext.request.contextPath}/role/insert">Thêm</a></li>
		<rapid:block name="lastNavtab">
		</rapid:block>
	</ul>

	<script>
		document
				.querySelector("body > div.container > nav > ul > li:nth-child(2)").classList
				.add("active");

		let navTabId;
		navTabId = <rapid:block name="navTabId"></rapid:block>;
		document.querySelector("body > div.container > ul > li:nth-child("
				+ navTabId + ") > a").classList.add("active");
	</script>

	<c:if test="${msg != null}">
		<div class="alert alert-success alert-dismissible fade show">
			<button type="button" class="close" data-dismiss="alert">&times;</button>
			<c:out value="${msg}"></c:out>
		</div>
	</c:if>

	<rapid:block name="roleContent">
	</rapid:block>

</rapid:override>

<%@ include file="../layouts/base.jsp"%>