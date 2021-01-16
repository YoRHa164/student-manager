package edu.njupt.springmvc.web.filter;

import javax.servlet.annotation.WebFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

/**
 * 使用过滤器，解决请求乱码问题
 */
@WebFilter(urlPatterns = "/*")
public class CharacterEncodingResolveFilter extends CharacterEncodingFilter {
	public CharacterEncodingResolveFilter() {
		setEncoding("utf-8");
		setForceEncoding(true);
	}
}
