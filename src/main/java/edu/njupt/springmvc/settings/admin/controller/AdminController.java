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

import edu.njupt.springmvc.settings.admin.bean.AdminBean;
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
	/**
	 * 修改当前管理员密码
	 * @param session
	 * @param oldPassword
	 * @param newPassword
	 * @return
	 */
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
		} catch (Exception e) {
			result.put("msg", "修改失败");
			result.put("code", 1);
		}
		
		return result;
	}
	/**
	 * 
	 * @param page
	 * @param limit
	 * @return
	 */
	@ResponseBody
	@RequestMapping(path = "/page", method = RequestMethod.GET)
	public Map<String, Object> queryCompleteAdminList(Integer page, Integer limit){
		Map<String, Object> result = new HashMap<>(6);
		try {
			Map<String, Object> res = adminService.queryCompleteAdminList(page, limit);
			
			result.put("data", res.get("data"));
			result.put("count", res.get("count"));
			result.put("code", 0);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("code", 1);
			result.put("msg", e.getMessage());
		}
		
		return result;
	}
	/**
	 * 通过主键ID删除管理员
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(path = "/delete", method = RequestMethod.POST)
	public Map<String, Object> deleteAdminById(String id){
		Map<String, Object> result = new HashMap<>(6);
		try {
			adminService.deleteAdminById(id);
			result.put("msg", "删除成功");
			result.put("code", 0);
		} catch (Exception e) {
			result.put("code", 1);
			result.put("msg", e.getMessage());
		}
		return result;
	}
	/**
	 * 修改管理员
	 * @param b
	 * @param surePassword
	 * @return
	 */
	@ResponseBody
	@RequestMapping(path = "/update", method = RequestMethod.POST)
	public Map<String, Object> updateAdminByAdminBean(AdminBean b, String surePassword){
		Map<String, Object> result = new HashMap<>(6);
		
		try {
			adminService.updateAdminByAdminBean(b, surePassword);
			
			result.put("code", 0);
			result.put("msg", "修改成功");
		} catch (Exception e) {
			e.printStackTrace();
			result.put("code", 1);
			result.put("msg", e.getMessage());
		}
		
		return result;
	}
	/**
	 * 添加新管理员
	 * @param b
	 * @param surePassword
	 * @return
	 */
	@ResponseBody
	@RequestMapping(path = "/add", method = RequestMethod.POST)
	public Map<String, Object> insertAdminByAdminBean(AdminBean b, String surePassword){
		Map<String, Object> result = new HashMap<>(6);
		
		try {
			adminService.insertAdminByAdminBean(b, surePassword);
			
			result.put("msg", "添加成功");
			result.put("code", 0);
		} catch (Exception e) {
			result.put("code", 1);
			result.put("msg", e.getMessage());
		}
		
		return result;
	}
}
