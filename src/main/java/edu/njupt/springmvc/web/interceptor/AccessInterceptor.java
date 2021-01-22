package edu.njupt.springmvc.web.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.njupt.springmvc.settings.admin.service.AdminService;
import edu.njupt.springmvc.settings.login.bean.LoginBean;

public class AccessInterceptor implements HandlerInterceptor {
	private final ObjectMapper om = new ObjectMapper();
	
	private AdminService adminService;
	
	
	public AccessInterceptor(AdminService adminService) {
		super();
		this.adminService = adminService;
	}



	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		LoginBean loginBean = (LoginBean) request.getSession().getAttribute("login");
		String servletPath = request.getServletPath();
		
		if(loginBean == null) {
			response.sendRedirect("/pages/login.html");
			return false;
		}
		
		if(!adminService .checkAccessExistsByGroupId(loginBean.getAuthorGroupId(), servletPath)) {
			response.setContentType("application/json;charset=utf-8");
			om.writeValue(
					response.getOutputStream(), 
					Map.of(
							"code", 1, 
							"msg", "您无权修改此项。若需要修改，请联系管理员"));
			return false;
		}
		return true;
	}
	
}
