package edu.njupt.springmvc.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;

import edu.njupt.springmvc.settings.login.bean.LoginBean;

/**
 * 进行权限拦截
 * @author Administrator
 *
 */
public class AccessInterceptor implements HandlerInterceptor{
	private static final Logger logger = Logger.getLogger(AccessInterceptor.class);
	
	/**
	 * 完成对请求权限控制 </br>
	 * 拦截规则： </br>
	 * 1、若请求未曾登录过，即请求session域中无 login 属性请求被强制跳转至登录界面
	 * 2、若请求曾登录过，但session失效，即请求session域中无 login 属性请求被强制跳转至登录界面
	 * 3、若请求曾登陆过并且session域未失效，不进行拦截，即前往所请求资源
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String servletPath = request.getServletPath();
		logger.info(String.format("access intercepor invoking... path ===> %s", servletPath));
		
		if(!"/pages/login.html".equals(servletPath)) {
			/*
			 * 此处进行权限判断 
			 */
			LoginBean login = (LoginBean) request.getSession().getAttribute("login");
			if(login != null) {
				return true;
			}
			response.sendRedirect(request.getContextPath() + "/pages/login.html");
			return false;
		}
		
		return true;
	}
}
