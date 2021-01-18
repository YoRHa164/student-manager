package edu.njupt.springmvc.settings.login.service;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import edu.njupt.springmvc.config.SpringContextConfig;
import edu.njupt.springmvc.settings.login.bean.LoginBean;
import edu.njupt.springmvc.settings.login.exception.LoginException;

public class LoginServiceTest {
	private static final Logger logger = Logger.getLogger(LoginServiceTest.class);
	@Test
	public void testLoginService() {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(SpringContextConfig.class);
		logger.info(ctx);
		
		LoginService loginService = ctx.getBean(LoginService.class);
		logger.info(loginService);
		
		try {
			LoginBean login = loginService.login("admin01", "123");
			logger.info(login);
			
		} catch (LoginException e) {
			logger.info(e.getMessage(), e);
		}
		
		ctx.close();
	}
}
