package edu.njupt.springmvc.settings.admin.dao;

import org.apache.log4j.Logger;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import edu.njupt.springmvc.config.SpringContextConfig;
import edu.njupt.springmvc.settings.login.bean.LoginBean;
import edu.njupt.springmvc.util.DataBaseUtil;

public class AdminDaoTest {
	private AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(SpringContextConfig.class);
	private AdminDao adminDao = ctx.getBean(AdminDao.class);
	private Logger logger = Logger.getLogger(AdminDaoTest.class);
	
	@Test
	@Ignore
	public void testCheckAdminAccountExPasswordCurrect() {
		LoginBean check = adminDao.checkAdminAccountOldPasswordCurrect("admin01", DataBaseUtil.getMD5Digest("123456789"));
		logger.info(check != null? "存在": "不存在");
	}
	@Test
	@Ignore
	public void testUpdateAdminPassword() {
		int res = adminDao.updateAdminPassword("admin01", DataBaseUtil.getMD5Digest("123456789"));
		logger.info(res == 1? "修改成功": "修改失败");
	}
}
