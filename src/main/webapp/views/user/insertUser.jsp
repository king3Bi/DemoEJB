<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="rapid" uri="http://www.rapid-framework.org.cn/rapid"%>

<rapid:override name="pageTitle">
	Thêm người dùng
</rapid:override>

<rapid:override name="navTabId">2</rapid:override>

<rapid:override name="userContent">
	<form action="" method="post">
		<rapid:block name="firstForm">
		</rapid:block>
		<div class="form-group">
			<label for="fullName">Họ tên:</label> <input type="text"
				class="form-control" placeholder="Nhập họ tên" id="fullName"
				name="fullName" />
		</div>
		<div class="form-group">
			<label for="username">Tên đăng nhập:</label> <input type="text"
				class="form-control" placeholder="Nhập đăng nhập" id="username"
				name="username" />
		</div>
		<div class="form-group">
			<label for="password">Mật khẩu:</label> <input type="password"
				class="form-control" placeholder="Nhập mật khẩu" id="password"
				name="password" />
		</div>
		<div class="form-group">
			<label for="roleId">Quyền:</label> <select class="form-control"
				id="roleId" name="roleId">
				<c:forEach items="${rolesList}" var="role">
					<option value="<c:out value="${role.id}">
			  		</c:out>"><c:out
							value="${role.name}"></c:out>
					</option>
				</c:forEach>
			</select>
		</div>
		<div class="custom-control custom-switch">
			<input type="checkbox" class="custom-control-input" id="enable"
				name="enable"> <label class="custom-control-label"
				for="enable">Hoạt động</label>
		</div>
		<rapid:block name="lastForm">
		</rapid:block>
		<button type="submit" class="btn btn-primary">Lưu</button>
	</form>
</rapid:override>

<%@ include file="baseUser.jsp"%>