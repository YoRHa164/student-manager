package edu.njupt.springmvc.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WebUtil {
	/**
	 * 通过解析请求获取相应名字的Cookie
	 * @param req
	 * @param name
	 * @return
	 */
	public static Cookie getCookieByName(HttpServletRequest req, String name) {
		name = name == null? "": name;
		
		Cookie result = null;
		Cookie[] cookies = req.getCookies();
		for (Cookie cookie : cookies) {
			if(name.equals(cookie.getName())) {
				result = cookie;
				break;
			}
		}
		
		return result;
	}
	/**
	 * 通过名字删除Cookie
	 * @param req
	 * @param name
	 */
	public static void removeCookieByName(HttpServletRequest req, String name) {
		Cookie byName = getCookieByName(req, name);
		byName.setMaxAge(0);
	}
	/**
	 * 添加指定Cookie
	 * @param resp
	 * @param name
	 * @param value
	 * @param maxAge 最大生存时间 单位：秒
	 */
	public static void addCookie(HttpServletResponse resp, String name, String value, int maxAge) {
		Cookie c = new Cookie(name, value);
		maxAge = maxAge < 0? 0: maxAge;
		c.setMaxAge(maxAge);
		resp.addCookie(c);
	}
}
