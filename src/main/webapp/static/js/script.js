function convertObjToQueryStr(obj) {
	return Object.keys(obj)
		.map(key => `${key}=${obj[key]}`)
		.join('&');
}

function getAllUser() {
	let dataUsers;
	$.ajax({
		url: './api/v1/user',
		type: 'GET',
        async: false,
		success: function(data) {
			dataUsers = data;
		}
	});
	return dataUsers;
}

function getAllRole() {
	let dataRoles;
	$.ajax({
		url: './api/v1/role',
		type: 'GET',
        async: false,
		success: function(data) {
			dataRoles = data;
		}
	});
	return dataRoles;
}

function getUser(id) {
	let dataUser;
	$.ajax({
		url: `./api/v1/user/${id}`,
		type: 'GET',
        async: false,
		success: function(data) {
			dataUser = data;
		}
	});
	return dataUser;
}

function getRole(id) {
	let dataRole;
	$.ajax({
		url: `./api/v1/role/${id}`,
		type: 'GET',
        async: false,
		success: function(data) {
			dataRole = data;
		}
	});
	return dataRole;
}

function loadUserTable() {
	let table = document.getElementById("tbUser");
	table.innerHTML = "";
	
	let data = getAllUser();
	data.forEach(item => {
		let r = table.insertRow(table.rows.length);
		r.insertCell(0).innerText = item.id;
	    r.insertCell(1).innerText = item.fullName;
	    r.insertCell(2).innerText = item.username;
	    r.insertCell(3).innerText = item.role.name;
	    
	    let enableBtn = document.createElement("a");
		enableBtn.href = "javascript:;";
	    let iconEnable = document.createElement("a");
	    iconEnable.style.fontSize = "23px";
	    if(item.enable) {
			enableBtn.addEventListener("click", () => {
				enableUser(item.id, false);
			})
			iconEnable.classList.add("fa", "fa-check-square");
			iconEnable.style.color = "green";
		} else {
			enableBtn.addEventListener("click", () => {
				enableUser(item.id, true);
			})
			iconEnable.classList.add("fa", "fa-window-close");
			iconEnable.style.color = "red";
		}
		enableBtn.appendChild(iconEnable);
	    r.insertCell(4).appendChild(enableBtn);
	    
	    let editBtn = document.createElement("a");
	    editBtn.classList.add("btn", "btn-primary");
	    editBtn.innerText = "Sửa";
	    editBtn.href = "javascript:;";
	    editBtn.addEventListener("click", () => {
			showUpdateFormUser(item.id);
		});
		
	    let deleteBtn = document.createElement("a");
	    deleteBtn.classList.add("btn", "btn-danger");
	    deleteBtn.innerText = "Xóa";
	    deleteBtn.href = "javascript:;";
	    deleteBtn.addEventListener("click", () => {
			confirmDeleteUser(item.id);
		});
		
	    r.insertCell(5).append(editBtn, deleteBtn);
	})
}

function loadRoleTable() {
	let table = document.getElementById("tbRole");
	table.innerHTML = "";
	
	let data = getAllRole();
	data.forEach(item => {
		let r = table.insertRow(table.rows.length);
		r.insertCell(0).innerText = item.id;
	    r.insertCell(1).innerText = item.name;
	    r.insertCell(2).innerText = item.description;
	    
	    let editBtn = document.createElement("a");
	    editBtn.classList.add("btn", "btn-primary");
	    editBtn.innerText = "Sửa";
	    editBtn.href = "javascript:;";
	    editBtn.addEventListener("click", () => {
			showUpdateFormRole(item.id);
		})
	    
	    let deleteBtn = document.createElement("a");
	    deleteBtn.classList.add("btn", "btn-danger");
	    deleteBtn.innerText = "Xóa";
	    deleteBtn.href = "javascript:;";
	    deleteBtn.addEventListener("click", () => {
			confirmDeleteRole(item.id);
		})
	    r.insertCell(3).append(editBtn, deleteBtn);
	})
}

function insertUser() {
	var dataUser = $('#formUser').serialize();

	$.ajax({
		url: './api/v1/user',
		type: 'POST',
		data: dataUser,
		success: function(data) {
			console.log(data);
			document.querySelector("#notifyInsertUser").style.display = "block";
			
			if(data.statusCode == 200) {
				document.querySelector("#titleNotify").innerText = "Thành công!";
				document.querySelector("#contentNotify").innerText = data.message;
				$('#formUser')[0].reset();
			} else {
				document.querySelector("#titleNotify").innerText = "Thất bại!";
				document.querySelector("#contentNotify").innerText = data.message;
			}
			loadUserTable();
		}
	});
}

function updateUser(id) {
	var dataUser = $('#formUser').serialize();

	$.ajax({
		url: `./api/v1/user/${id}`,
		type: 'PUT',
		data: dataUser,
		success: function(data) {
			console.log(data);
			document.querySelector("#notifyInsertUser").style.display = "block";
			
			if(data.statusCode == 200) {
				document.querySelector("#titleNotify").innerText = "Thành công!";
				document.querySelector("#contentNotify").innerText = data.message;
			} else {
				document.querySelector("#titleNotify").innerText = "Thất bại!";
				document.querySelector("#contentNotify").innerText = data.message;
			}
			loadUserTable();
		}
	});
}

function insertRole() {
	var dataRole = $('#formRole').serialize();

	$.ajax({
		url: './api/v1/role',
		type: 'POST',
		data: dataRole,
		success: function(data) {
			console.log(data);
			document.querySelector("#notifyInsertRole").style.display = "block";
			
			if(data.statusCode == 200) {
				document.querySelector("#titleNotify").innerText = "Thành công!";
				document.querySelector("#contentNotify").innerText = data.message;
				$('#formRole')[0].reset();
			} else {
				document.querySelector("#titleNotify").innerText = "Thất bại!";
				document.querySelector("#contentNotify").innerText = data.message;
			}
			loadRoleTable();
		}
	});
}

function updateRole(id) {
	var dataRole = $('#formRole').serialize();
	
	fetch(`./api/v1/role/${id}`, {
        method: 'PUT',
        headers: {
	        'Content-type': 'application/json'
	    },
        body: dataRole
    }).then(response => response.json())
    	.then(data => {
			console.log(data);
			document.querySelector("#notifyInsertRole").style.display = "block";
			
			if(data.statusCode == 200) {
				document.querySelector("#titleNotify").innerText = "Thành công!";
				document.querySelector("#contentNotify").innerText = data.message;
			} else {
				document.querySelector("#titleNotify").innerText = "Thất bại!";
				document.querySelector("#contentNotify").innerText = data.message;
			}
			loadRoleTable();
		})
		.catch(error => error )
	/*
	$.ajax({
		url: `./api/v1/role/${id}`,
		type: 'PUT',
		data: formData,
		success: function(data) {
			console.log(data);
			document.querySelector("#notifyInsertUser").style.display = "block";
			
			if(data.statusCode == 200) {
				document.querySelector("#titleNotify").innerText = "Thành công!";
				document.querySelector("#contentNotify").innerText = data.message;
			} else {
				document.querySelector("#titleNotify").innerText = "Thất bại!";
				document.querySelector("#contentNotify").innerText = data.message;
			}
			loadRoleTable();
		}
	}); */
}

function showInsertFormRole() {
	document.querySelector('.modal-title').innerText = "Thêm quyền mới";
	
	$('#formRole')[0].reset();
	document.querySelector('#formRole')
		.setAttribute('onsubmit', `event.preventDefault(); insertRole();`);
	$("#insertRole").modal("show");
}

function showUpdateFormRole(id) {
	document.querySelector('.modal-title').innerText = "Chỉnh sửa quyền";
	
	data = getRole(id);
	document.querySelector('#name').value = data.name;
	document.querySelector('#description').value = data.description;
	document.querySelector('#formRole')
		.setAttribute('onsubmit', `event.preventDefault(); updateRole(${id});`);
	$("#insertRole").modal("show");
}

function showInsertFormUser() {
	document.querySelector('.modal-title').innerText = "Thêm người dùng mới";
	
	$('#formUser')[0].reset();
	document.querySelector('#formUser')
		.setAttribute('onsubmit', `event.preventDefault(); insertUser();`);
	$("#insertUser").modal("show");
}

function showUpdateFormUser(id) {
	document.querySelector('.modal-title').innerText = "Chỉnh sửa người dùng";
	
	data = getUser(id);
	document.querySelector('#fullName').value = data.fullName;
	document.querySelector('#username').value = data.username;
	document.querySelector('#password').value = data.description;
	document.querySelector('#roleId').value = data.role.id;
	document.querySelector('#enable').checked = data.enable;
	document.querySelector('#formUser')
		.setAttribute('onsubmit', `event.preventDefault(); updateUser(${id});`);
	$("#insertUser").modal("show");
}

function confirmDeleteRole(id) {
	console.log("confirm");
	$("#confirmDelete").modal("show");
	 
	$('#btnDeleteRole').unbind();
	$('#btnDeleteRole').on('click', function() {
		$("#confirmDelete").modal("hide");
		deleteRole(id);
	});
}
function deleteRole(id) {
	$.ajax({
		url: `./api/v1/role/${id}`,
		type: 'DELETE',
		success: function(data) {
			console.log(data);
			
			if(data.statusCode == 200) {
				alert(data.message);
			} else {
				alert(data.message);
			}
			loadRoleTable();
		}
	});
}

function confirmDeleteUser(id) {
	console.log("confirm");
	$("#confirmDelete").modal("show");
	 
	$('#btnDeleteUser').unbind();
	$('#btnDeleteUser').on('click', function() {
		$("#confirmDelete").modal("hide");
		deleteUser(id);
	});
}
function deleteUser(id) {
	$.ajax({
		url: `./api/v1/user/${id}`,
		type: 'DELETE',
		success: function(data) {
			console.log(data);
			
			if(data.statusCode == 200) {
				alert(data.message);
			} else {
				alert(data.message);
			}
			loadUserTable();
		}
	});
}

function enableUser(id, enable) {
	let userData = getUser(id);
	userData.enable = enable;
	userData["roleId"] = userData.role.id;
	delete userData.role;
	delete userData.id;
	$.ajax({
		url: `./api/v1/user/${id}`,
		type: 'PUT',
		data: encodeURIComponent(convertObjToQueryStr(userData)),
		success: function(data) {
			console.log(data);
			loadUserTable();
			if(data.statusCode == 200) {
				if(enable) {
					alert("Kích hoạt tài người dùng thành công");
				} else {
					alert("Vô hiệu hóa tài người dùng thành công");
				}
			} else {
				alert("Đã có lỗi xảy ra, vui lòng thực hiện lại");
			}
		}
	});
}