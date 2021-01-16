package edu.njupt.springmvc.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * 拦截静态页面,进行权限检查
 * "/"请求会有不同情况： </br>
 * 		1、以前登录过，session未失效，直接跳转至首页</br>
 * 		2、以前登录过，session已失效，直接跳转至登录界面</br>
 * 		3、以前未登录过，直接跳转至登录界面</br>
 */
@WebFilter(urlPatterns = {"*.html", "*.jsp", "*.htm", "/"})
public class FrontRequestFilter implements Filter {
	private static final Logger logger = Logger.getLogger(FrontRequestFilter.class);
    /**
     * Default constructor. 
     */
    public FrontRequestFilter() {
    }


	/**
	 * 
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		String servletPath = req.getServletPath();
		logger.info(String.format("static resources path ==> %s", servletPath));
		
		if(!"/pages/login.html".equals(servletPath)) {
			if(req.getSession().getAttribute("login") == null) {
				logger.warn("force jumping to login page...");
				
				resp.sendRedirect(req.getContextPath() + "/pages/login.html");
				return;
			}else if("/".equals(servletPath)) {
				resp.sendRedirect(req.getContextPath() + "/pages/index.html");
				return;
			}
		}
		chain.doFilter(request, response);
	}
}
