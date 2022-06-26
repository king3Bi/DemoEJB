<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="rapid" uri="http://www.rapid-framework.org.cn/rapid"%>

<rapid:override name="pageTitle">
	Thêm quyền
</rapid:override>

<rapid:override name="navTabId">2</rapid:override>

<rapid:override name="roleContent">
	<h4>
		<c:out value="${msg}"></c:out>
	</h4>
	<form action="" method="post">
		<rapid:block name="firstForm">
		</rapid:block>
		<div class="form-group">
			<label for="name">Tên:</label> <input type="text"
				class="form-control" placeholder="Nhập tên quyền" id="name"
				name="name" />
		</div>
		<div class="form-group">
			<label for="description">Mô tả:</label> <input type="text"
				class="form-control" placeholder="Nhập mô tả quyền" id="description"
				name="description" />
		</div>
		<rapid:block name="lastForm">
		</rapid:block>
		<button type="submit" class="btn btn-primary">Lưu</button>
	</form>
</rapid:override>

<%@ include file="baseRole.jsp"%>