package com.ejb.demo.apiv1;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Scanner;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ejb.demo.model.Message;
import com.ejb.demo.model.Role;
import com.ejb.demo.model.User;
import com.ejb.demo.service.UserService;
import com.ejb.demo.utils.GsonUtil;
import com.ejb.demo.utils.ServletUtill;

@WebServlet("/api/v1/user/*")
public class UserApi extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	UserService userService;

    public UserApi() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pathInfo = request.getPathInfo();
		if(pathInfo == null || "/".equals(pathInfo)) {
			Collection<User> users = userService.getAll();
			
			GsonUtil.sendAsJson(response, users);
			return;
		}
		
		String[] splits = pathInfo.split("/");
		
		if(splits.length != 2) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		
		String userIdStr = splits[1];
		int userId = Integer.valueOf(userIdStr);
		User user = userService.getUserById(userId);
		
		if(user == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
		
		GsonUtil.sendAsJson(response, user);
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String pathInfo = request.getPathInfo();
		if(pathInfo == null || "/".equals(pathInfo)) {
			String fullName = request.getParameter("fullName");
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			int roleId = Integer.valueOf(request.getParameter("roleId"));
			boolean enable = request.getParameter("enable") != null;
			
			Role role = new Role();
			role.setId(roleId);

			User newUser = new User(fullName, username, password, enable, role);
			newUser = userService.insertUser(newUser);
			if(newUser == null) {
				GsonUtil.sendAsJson(response, 
						new Message(500, "Đã có lỗi xảy ra, vui lòng thử lại"));
				return;
			}
			
			GsonUtil.sendAsJson(response, 
					new Message(200, "Thêm người dùng " + newUser.getFullName() + " thành công"));
			return;
		}
		else {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
	}
	
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String pathInfo = request.getPathInfo();
		if(pathInfo == null || "/".equals(pathInfo)) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		
		String[] splits = pathInfo.split("/");
		if(splits.length != 2) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		
		int idUser = Integer.valueOf(splits[1]);
		User user = userService.getUserById(idUser);
		
		if(user == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
		
		HashMap<String, String> data = null;
		try {
			data = ServletUtill.getDataRequest(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(data == null) {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return;
		}
		
		int roleId = Integer.valueOf(data.get("roleId"));
		boolean enable = data.get("enable") != null && !data.get("enable").equals("false");
		
		Role role = new Role();
		role.setId(roleId);
		
		user.setFullName(data.get("fullName"));
		user.setUsername(data.get("username"));
		user.setPassword(data.get("password"));
		user.setEnable(enable);
		user.setRole(role);
		
		user = userService.updateUser(user);
		if(user == null) {
			GsonUtil.sendAsJson(response, 
					new Message(500, "Đã có lỗi xảy ra, vui lòng thử lại"));
			return;
		}
		
		GsonUtil.sendAsJson(response, 
				new Message(200, "Cập nhật thông tin người dùng " + user.getFullName() + " thành công"));
		return;
	}
	
	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pathInfo = request.getPathInfo();
		if(pathInfo == null || "/".equals(pathInfo)) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		
		String[] splits = pathInfo.split("/");
		if(splits.length != 2) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		
		int idUser = Integer.valueOf(splits[1]);
		User user = userService.getUserById(idUser);
		
		if(user == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
		
		if(!userService.deleteUser(user)) {
			GsonUtil.sendAsJson(response, 
					new Message(500, "Đã có lỗi xảy ra, vui lòng thử lại"));
			return;
		}
		
		GsonUtil.sendAsJson(response, 
				new Message(200, "Xóa người dùng " + user.getFullName() + " thành công"));
		return;
	}
}
