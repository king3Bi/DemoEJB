<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="rapid" uri="http://www.rapid-framework.org.cn/rapid"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<rapid:override name="pageTitle">
	Quản lý quyền
</rapid:override>

<rapid:override name="navTabId">1</rapid:override>

<rapid:override name="roleContent">
	<div class="table-responsive">
		<table class="table table-striped table-hover">
			<thead>
				<tr>
					<th>Id</th>
					<th>Tên</th>
					<th>Mô tả</th>
					<th></th>
				</tr>
			</thead>
			<tbody id="tbRole">
			
				<!-- 
				<c:forEach items="${rolesList}" var="role">
					<tr>
						<td><c:out value="${role.id}"></c:out></td>
						<td><c:out value="${role.name}"></c:out></td>
						<td><c:out value="${role.description}"></c:out></td>

						<td><a class="btn btn-primary"
							href="${pageContext.request.contextPath}/role/edit?id=${role.id}">Sửa</a>
							<a class="btn btn-danger"
							href="${pageContext.request.contextPath}/role/delete?id=${role.id}">Xóa</a>
						</td>
					</tr>
				</c:forEach> -->

			</tbody>
		</table>
		
		<script type="text/javascript">
			loadRoleTable();
		</script>
	</div>
</rapid:override>

<%@ include file="baseRole.jsp"%>