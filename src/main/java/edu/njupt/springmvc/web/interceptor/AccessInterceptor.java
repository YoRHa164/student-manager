package edu.njupt.springmvc.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;

import edu.njupt.springmvc.settings.login.bean.LoginBean;
import edu.njupt.springmvc.web.filter.FrontRequestFilter;
/**
 * 用于对动态请求进行权限检查, 检查静态资源请求，请见{@link FrontRequestFilter} </br>
 * 	除去/login/access请求不检查以外，其余动态请求皆接受检查 </br>
 * 	判断条件：</br>
 * 		检测请求会话域中是否含有 "login": ${LoginBean} 对象 </br>
 * 		如果不为空，则放行请求 </br>
 * 		如果为空，强制跳转到/pages/login.html
 * @author Administrator
 *
 */
public class AccessInterceptor implements HandlerInterceptor {
	private static Logger logger = Logger.getLogger(AccessInterceptor.class);
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		logger.info(String.format("Access Interceptor path ==> %s", request.getServletPath()));
		
		LoginBean bean = (LoginBean) request.getSession().getAttribute("login");
		if(bean == null) {
			logger.warn("force jumping to login page...");
			response.sendRedirect(request.getContextPath() + "/pages/login.html");
			return false;
		}
		
		return true;
	}
}
