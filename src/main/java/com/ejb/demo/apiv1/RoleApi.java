package com.ejb.demo.apiv1;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ejb.demo.model.Message;
import com.ejb.demo.model.Role;
import com.ejb.demo.model.User;
import com.ejb.demo.service.RoleService;
import com.ejb.demo.utils.GsonUtil;
import com.ejb.demo.utils.ServletUtill;

@WebServlet("/api/v1/role/*")
public class RoleApi extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	RoleService roleService;
	
    public RoleApi() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pathInfo = request.getPathInfo();
		if(pathInfo == null || "/".equals(pathInfo)) {
			Collection<Role> roles = roleService.getAll();
			
			GsonUtil.sendAsJson(response, roles);
			return;
		}
		
		String[] splits = pathInfo.split("/");
		
		if(splits.length != 2) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		
		int roleId = Integer.valueOf(splits[1]);
		Role role = roleService.getRoleById(roleId);
		
		if(role == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
		
		GsonUtil.sendAsJson(response, role);
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String pathInfo = request.getPathInfo();
		if(pathInfo == null || "/".equals(pathInfo)) {
			String name = request.getParameter("name");
			String description = request.getParameter("description");
			
			Role newRole = new Role(name, description);

			newRole = roleService.insertRole(newRole);
			if(newRole == null) {
				GsonUtil.sendAsJson(response, 
						new Message(500, "Đã có lỗi xảy ra, vui lòng thử lại"));
				return;
			}
			
			GsonUtil.sendAsJson(response, 
					new Message(200, "Thêm quyền " + newRole.getName() + " thành công"));
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
		
		int idRole = Integer.valueOf(splits[1]);
		Role role = roleService.getRoleById(idRole);
		
		if(role == null) {
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

		role.setName(data.get("name"));
		role.setDescription(data.get("description"));
		
		role = roleService.updateRole(role);
		if(role == null) {
			GsonUtil.sendAsJson(response, 
					new Message(500, "Đã có lỗi xảy ra, vui lòng thử lại"));
			return;
		}
		
		GsonUtil.sendAsJson(response, 
				new Message(200, "Cập nhật thông quyền " + role.getName() + " thành công"));
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
		
		int idRole = Integer.valueOf(splits[1]);
		Role role = roleService.getRoleById(idRole);
		
		if(role == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
		
		if(!roleService.deleteRole(role)) {
			GsonUtil.sendAsJson(response, 
					new Message(500, "Đã có lỗi xảy ra, vui lòng thử lại"));
			return;
		}
		
		GsonUtil.sendAsJson(response, 
				new Message(200, "Xóa quyền " + role.getName() + " thành công"));
		return;
	}

}
