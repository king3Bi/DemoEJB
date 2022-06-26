package com.ejb.demo.servlet.user;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ejb.demo.model.User;
import com.ejb.demo.service.UserService;

@WebServlet("/user/enable")
public class EnableUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	UserService userService;

    public EnableUserServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idUserStr = request.getParameter("id");
		int idUser = Integer.valueOf(idUserStr);
		
		User user = userService.getUserById(idUser);
		
		String msg = "";
		if(userService.enableUser(user) != null) {
			msg = "Kích hoạt người " + user.getFullName() + " dùng thành công";
		} else {
			msg = "Đã có lỗi xảy ra, vui lòng thử lại";
		}
		
		request.setAttribute("msg", msg);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/views/user/baseUser.jsp");
		dispatcher.forward(request, response);
	}
}
