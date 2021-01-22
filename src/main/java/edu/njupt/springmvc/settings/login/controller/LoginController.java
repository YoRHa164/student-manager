package edu.njupt.springmvc.settings.login.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.njupt.springmvc.settings.login.bean.LoginBean;
import edu.njupt.springmvc.settings.login.exception.LoginException;
import edu.njupt.springmvc.settings.login.service.LoginService;
import edu.njupt.springmvc.util.DataBaseUtil;
import edu.njupt.springmvc.web.interceptor.LoginAccessInterceptor;

@Controller
@RequestMapping("/api/Admin")
public class LoginController {
	private static final Logger logger = Logger.getLogger(LoginController.class);
	
	@Resource(type = LoginService.class)
	private LoginService loginService;
	

	/**
	 * 完成登录操作
	 * 根据请求前端api，接收参数，完成请求。若登录失败，则响应以下格式 {"code":1, "msg":${msg}}
	 * 若登录成功，则将 {@link LoginBean} 对象放入 {@link HttpSession session} 域，以供以后进行{@link LoginAccessInterceptor } 登录权限验证
	 * @param req
	 * @param acc
	 * @param pwd
	 * @return
	 */
	@ResponseBody
	@RequestMapping(path = "/login", method = RequestMethod.POST)
	public Map<String, Object> loginByAccountAndPassword(
			HttpServletRequest req,
			@RequestParam("loginName") String acc, @RequestParam("password") String pwd) {
		
		logger.info(String.format("[account=%s, password=%s, ip=%s] try login", acc, pwd, req.getRemoteAddr()));
		
		Map<String, Object> result = new HashMap<>(10);
		try {
			req.getSession().setAttribute("login", loginService.login(acc, pwd));
			
			logger.info(String.format("account=%s login success: %s", acc, DataBaseUtil.currentTimeByDefault()));
			result.put("code", 0);
		} catch (LoginException e) {
			logger.info(String.format("account=%s login failed: %s", acc, DataBaseUtil.currentTimeByDefault()));
			result.put("code", 1);
			result.put("msg", e.getMessage());
		}
		
		return result;
	}
	/**
	 * 添加退出操作，暂时只在{@link HttpSession session} 域移除相关对象，实际可能检验更多东西
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(path = "/logout", method = RequestMethod.POST)
	public Map<String, Object> logout(HttpSession session){
		LoginBean login = (LoginBean) session.getAttribute("login");
		session.removeAttribute("login");
		
		logger.debug(String.format("admin [ %s ] logout", login.getAccount()));
		return Map.of("msg", "退出成功");
	}
}
