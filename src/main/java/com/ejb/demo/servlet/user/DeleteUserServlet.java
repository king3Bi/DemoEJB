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

@WebServlet("/user/delete")
public class DeleteUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	UserService userService;

    public DeleteUserServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idUserStr = request.getParameter("id");
		int idUser = Integer.valueOf(idUserStr);
		
		User user = userService.getUserById(idUser);
		
		String msg = "";
		if(userService.deleteUser(user)) {
			msg = "Xóa người dùng " + user.getFullName() + " thành công";
		} else {
			msg = "Đã có lỗi xảy ra, vui lòng thử lại";
		}
		
		request.setAttribute("msg", msg);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/views/user/baseUser.jsp");
		dispatcher.forward(request, response);
	}
}
