package edu.njupt.springmvc.settings.admin.service;

import org.apache.log4j.Logger;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import edu.njupt.springmvc.config.SpringContextConfig;
import edu.njupt.springmvc.settings.admin.AdminException;

public class AdminServiceTest {
	private AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(SpringContextConfig.class);
	private AdminService adminService = ctx.getBean(AdminService.class);
	private  static final Logger logger = Logger.getLogger(AdminServiceTest.class);
	
	@Test
	@Ignore
	public void testUpdateAdminPassword() {
		try {
			adminService.updateAdminPassword("admin01", "123456789", "123456789*");
		} catch (AdminException e) {
			logger.warn(e.getMessage());
		}
	}
}
