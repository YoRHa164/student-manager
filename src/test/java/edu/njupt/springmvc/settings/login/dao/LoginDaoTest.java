package edu.njupt.springmvc.settings.login.dao;

import org.apache.log4j.Logger;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import edu.njupt.springmvc.config.SpringContextConfig;
import edu.njupt.springmvc.settings.login.bean.LoginBean;
import edu.njupt.springmvc.util.DataBaseUtil;

public class LoginDaoTest {
	private static final Logger logger = Logger.getLogger(LoginDaoTest.class);
	@Test
	@Ignore
	public void testLoginDao() {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(SpringContextConfig.class);
		logger.info(ctx);
		
		LoginDao loginDao = ctx.getBean(LoginDao.class);
		logger.info(loginDao);
		LoginBean login = loginDao.login("admin01", DataBaseUtil.getMD5Digest("123"));
		logger.info(login);
		
		ctx.close();
	}
}
