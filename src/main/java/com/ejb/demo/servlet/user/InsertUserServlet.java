package com.ejb.demo.servlet.user;

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
import com.ejb.demo.model.User;
import com.ejb.demo.service.RoleService;
import com.ejb.demo.service.UserService;

@WebServlet("/user/insert")
public class InsertUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	RoleService roleService;
	
	@EJB
	UserService userService;

    public InsertUserServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		
		List<Role> rolesList = roleService.getAll();
		request.setAttribute("rolesList", rolesList);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/views/user/insertUser.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String fullName = request.getParameter("fullName");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String roleIdStr = request.getParameter("roleId");
		boolean enable = request.getParameter("enable") != null;
		
		int roleId = Integer.valueOf(roleIdStr);
		
		Role role = roleService.getRoleById(roleId);
		User newUser = new User(fullName, username, password, enable, role);
		
		String msg = "";
		if(userService.insertUser(newUser) != null) {
			msg = "Thêm người dùng thành công";
		} else {
			msg = "Đã có lỗi xảy ra, vui lòng thử lại";
		}
		
		request.setAttribute("msg", msg);
		doGet(request, response);
	}

}
