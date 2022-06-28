<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="rapid" uri="http://www.rapid-framework.org.cn/rapid"%>

<rapid:override name="pageTitle">
	Quản lý người dùng
</rapid:override>

<rapid:override name="navTabId">1</rapid:override>

<rapid:override name="userContent">
	<div class="table-responsive">
		<table class="table table-striped table-hover">
			<thead>
				<tr>
					<th>Id</th>
					<th>Tên</th>
					<th>Username</th>
					<th>Quyền</th>
					<th>Hoạt động</th>
					<th></th>
				</tr>
			</thead>
			<tbody id="tbUser">
				<!-- 
				<c:forEach items="${usersList}" var="user">
					<tr>
						<td><c:out value="${user.id}"></c:out></td>
						<td><c:out value="${user.fullName}"></c:out></td>
						<td><c:out value="${user.username}"></c:out></td>
						<td><c:out value="${user.role.name}"></c:out></td>
						<td><c:choose>
								<c:when test="${user.enable == true}">
									<a
										href="${pageContext.request.contextPath}/user/disable?id=${user.id}"><i
										class="fa fa-check-square" style="font-size: 23px; color: green"></i></a>
								</c:when>
								<c:otherwise>
									<a
										href="${pageContext.request.contextPath}/user/enable?id=${user.id}"><i
										class="fa fa-window-close" style="font-size: 23px; color: red"></i></a>
								</c:otherwise>
							</c:choose></td>

						<td><a class="btn btn-primary"
							href="${pageContext.request.contextPath}/user/edit?id=${user.id}">Sửa</a>
							<a class="btn btn-danger"
							href="${pageContext.request.contextPath}/user/delete?id=${user.id}">Xóa</a></td>
					</tr>
				</c:forEach>  -->

			</tbody>
		</table>
		
		<script type="text/javascript">
			loadUserTable();
		</script>
	</div>
</rapid:override>

<%@ include file="baseUser.jsp"%>