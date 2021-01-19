package edu.njupt.springmvc.settings.admin.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.njupt.springmvc.settings.admin.AdminException;
import edu.njupt.springmvc.settings.admin.service.AdminService;
import edu.njupt.springmvc.settings.login.bean.LoginBean;

@Controller
@RequestMapping("/api/Admin")
public class AdminController {
	private static final Logger logger = Logger.getLogger(AdminController.class); 
	@Resource(type = AdminService.class)
	private AdminService adminService;
	/**
	 * 向前端相应管理员信息
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(path = "/currentAdminInfo", method = RequestMethod.GET)
	public Map<String, Object> currentAdminInfo(HttpSession session){
		LoginBean login = null;
		Map<String, Object> result = new HashMap<>(5);
		
		try {
			login = (LoginBean) session.getAttribute("login");
			result.put("code", 0);
			result.put("realName", login.getRealName());
		} catch (Exception e) {
			result.put("code", 1);
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping(path = "/updateCurrentAdminPwd", method = RequestMethod.POST)
	public Map<String, Object> updateAdminPassword(HttpSession session, String oldPassword, String newPassword){
		LoginBean bean = (LoginBean) session.getAttribute("login");
		Map<String, Object> result = new HashMap<>(10);
		
		logger.info(String.format("account [ %s ] try update password", bean.getAccount()));
		try {
			adminService.updateAdminPassword(bean.getAccount(), oldPassword, newPassword);
			
			result.put("msg", "修改成功");
			result.put("code", 0);
		} catch (AdminException e) {
			result.put("msg", e.getMessage());
			result.put("code", 1);
		}
		
		return result;
		
	}
}
