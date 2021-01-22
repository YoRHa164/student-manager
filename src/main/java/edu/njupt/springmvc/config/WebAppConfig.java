package edu.njupt.springmvc.config;

import javax.annotation.Resource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import edu.njupt.springmvc.settings.admin.service.AdminService;
import edu.njupt.springmvc.web.interceptor.AccessInterceptor;
import edu.njupt.springmvc.web.interceptor.LoginAccessInterceptor;

@EnableWebMvc
@Configuration
@ComponentScan(basePackages = { 
		"edu.njupt.springmvc.settings.admin.controller",
		"edu.njupt.springmvc.settings.login.controller",
		"edu.njupt.springmvc.settings.student.controller",
		"edu.njupt.springmvc.web.controller"
		})
public class WebAppConfig implements WebMvcConfigurer {
	@Resource(type = AdminService.class)
	private AdminService adminService;
	
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**").
				 addResourceLocations("/WEB-INF/static/").
				 setCachePeriod(30 * 1024 * 1024);
	}
	/**
	 * 添加拦截器
	 * 
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LoginAccessInterceptor()).
				 addPathPatterns("/**/*.html", "/api/**", "/").
				 excludePathPatterns("/api/Admin/login");
		registry.addInterceptor(new AccessInterceptor(adminService)).
				 addPathPatterns("/api/**/add*", "/api/**/delete*", "/api/**/update*");
	}
	@Bean
	public ViewResolver getViewResolver() {
		InternalResourceViewResolver vr = new InternalResourceViewResolver();
		vr.setPrefix(null);
		vr.setSuffix(null);
		return vr;
	}
}
