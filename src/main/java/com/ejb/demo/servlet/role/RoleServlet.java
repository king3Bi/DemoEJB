package com.ejb.demo.servlet.role;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ejb.demo.model.Role;
import com.ejb.demo.service.RoleService;

@WebServlet("/role")
public class RoleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	RoleService roleService;

    public RoleServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		
		List<Role> rolesList = roleService.getAll();
		request.setAttribute("rolesList", rolesList);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/views/role/role.jsp");
		dispatcher.forward(request, response);
	}
}
