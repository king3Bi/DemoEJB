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

@WebServlet("/role/delete")
public class DeleteRoleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	RoleService roleService;
       
    public DeleteRoleServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idRoleStr = request.getParameter("id");
		int idRole = Integer.valueOf(idRoleStr);
		
		Role role = roleService.getRoleById(idRole);
		
		String msg = "";
		if(roleService.deleteRole(role)) {
			msg = "Xóa quyền " + role.getName() + " thành công";
		} else {
			msg = "Đã có lỗi xảy ra, vui lòng thử lại";
		}
		
		request.setAttribute("msg", msg);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/views/role/baseRole.jsp");
		dispatcher.forward(request, response);
	}
}
