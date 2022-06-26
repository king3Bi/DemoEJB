package com.ejb.demo.servlet.role;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ejb.demo.model.Role;
import com.ejb.demo.service.RoleService;

@WebServlet("/role/edit")
public class UpdateRoleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	RoleService roleService;
	
    public UpdateRoleServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		
		String idRoleStr = request.getParameter("id");
		int idRole = Integer.valueOf(idRoleStr);
		
		Role role = roleService.getRoleById(idRole);
		request.setAttribute("role", role);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/views/role/updateRole.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String idRoleStr = request.getParameter("id");
		int idRole = Integer.valueOf(idRoleStr);
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		
		Role role = roleService.getRoleById(idRole);
		role.setName(name);
		role.setDescription(description);
		
		String msg = "";
		if(roleService.updateRole(role) != null) {
			msg = "Cập nhật thông tin quyền thành công";
		} else {
			msg = "Đã có lỗi xảy ra, vui lòng thử lại";
		}
		
		request.setAttribute("msg", msg);
		
		doGet(request, response);
	}

}
