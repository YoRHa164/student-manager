package edu.njupt.springmvc.settings.login.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.njupt.springmvc.exception.LoginException;
import edu.njupt.springmvc.settings.login.service.LoginService;

@Controller
@RequestMapping(path = { "/login" })
public class LoginController {
	private static final Logger logger = Logger.getLogger(LoginController.class);
	@Resource(type = LoginService.class)
	private LoginService loginService;
	@Resource(type = ServletContext.class)
	private ServletContext ctx;
	
	/**
	 * @param ctx 要设置的 ctx
	 */
	public void setCtx(ServletContext ctx) {
		this.ctx = ctx;
	}
	/**
	 * @param loginService 要设置的 loginService
	 */
	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}
	
	@ResponseBody
	@RequestMapping(path = {"/access"}, method = RequestMethod.POST)
	public Map<String, Object> login(
			HttpServletResponse resp,
			HttpSession session,
			@RequestParam(name = "account") String acc,
			@RequestParam(name = "password") String pwd) 
	{
		Map<String, Object> map = new HashMap<>(10); 
		logger.info(String.format("LoginController.login(account=%s, password=%s)", acc, pwd));
		try {
			session.setAttribute("login", loginService.login(acc, pwd));
			
			resp.sendRedirect(ctx.getContextPath() + "/pages/index.html");
			return map;
		} catch (LoginException e) {
			map.put("success", false);
			map.put("msg", e.getMessage());
			logger.warn(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return map;
	}
	@ResponseBody
	@RequestMapping(path = "/test", method = RequestMethod.GET)
	public Map<String, Object> hello(HttpServletRequest req){
		
		return Map.of("msg", "hello");
	}
}
