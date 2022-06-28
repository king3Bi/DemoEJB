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
		<li class="nav-item"><a class="nav-link" data-toggle="modal"
			href="javascript:;" onclick="showInsertFormRole();">Thêm</a></li>
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

	<!-- The Modal -->
	<div class="modal fade" id="insertRole">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">Thêm quyền mới</h4>
					<button type="button" class="close" data-dismiss="modal">×</button>
				</div>

				<form action="" method="post" id="formRole"
					onsubmit="event.preventDefault(); insertRole();">
					<!-- Modal body -->
					<div class="modal-body">

						<div class="alert alert-success alert-dismissible"
							id="notifyInsertRole" style="display: none;">
							<button type="button" class="close" id="closeAlert"
								data-dismiss="dispose">&times;</button>
							<strong id="titleNotify"></strong> <span id="contentNotify"></span>
						</div>
						<rapid:block name="firstForm">
						</rapid:block>
						<div class="form-group">
							<label for="name">Tên:</label> <input type="text"
								class="form-control" placeholder="Nhập tên quyền" id="name"
								name="name" required />
						</div>
						<div class="form-group">
							<label for="description">Mô tả:</label> <input type="text"
								class="form-control" placeholder="Nhập mô tả quyền"
								id="description" name="description" required />
						</div>
						<rapid:block name="lastForm">
						</rapid:block>
					</div>

					<!-- Modal footer -->
					<div class="modal-footer">
						<button type="submit" class="btn btn-primary">Lưu</button>
						<button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
					</div>
				</form>
			</div>
		</div>
	</div>

	<script>
		$('#closeAlert').unbind();
		$('#closeAlert').on('click', function() {
			document.querySelector("#notifyInsertRole").style.display = "none";
		});

		$("#insertRole").on('hide.bs.modal', function() {
			document.querySelector("#notifyInsertRole").style.display = "none";
		});
	</script>

	<div class="modal fade" id="confirmDelete" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLongTitle" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLongTitle">Bạn có muốn
						xóa quyền này?</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">Sau khi xóa, quyền này sẽ không khôi
					phục lại được!</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Không</button>
					<button type="button" class="btn btn-danger" id="btnDeleteRole">Xóa</button>
				</div>
			</div>
		</div>
	</div>

	<div class="modal fade bd-example-modal-sm" tabindex="-1" role="dialog"
		aria-labelledby="mySmallModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-sm modal-dialog-centered">
			<div class="modal-content">...</div>
		</div>
	</div>

</rapid:override>

<%@ include file="../layouts/base.jsp"%>