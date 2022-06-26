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

import com.ejb.demo.model.User;
import com.ejb.demo.service.UserService;

@WebServlet("/user")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	UserService userService;
	
       
    public UserServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		
		List<User> usersList = userService.getAll();
		request.setAttribute("usersList", usersList);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/views/user/user.jsp");
		dispatcher.forward(request, response);
	}
}
