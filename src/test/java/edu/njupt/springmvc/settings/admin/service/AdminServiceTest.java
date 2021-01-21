package edu.njupt.springmvc.settings.admin.service;

import org.apache.log4j.Logger;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import edu.njupt.springmvc.config.SpringContextConfig;
import edu.njupt.springmvc.settings.admin.AdminException;
import edu.njupt.springmvc.settings.admin.bean.AdminBean;

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
	@Test
	@Ignore
	public void testUpdateAdminByAdminBean() {
		
		try {
			AdminBean b = new AdminBean();
			b.setId("10");
			b.setLoginName("admin07");
			b.setRealName("管理员");
			b.setPassword("123456789");
			
			adminService.updateAdminByAdminBean(b, "123456789");
		} catch (AdminException e) {
			System.out.println(e.getMessage());
		}
		
	}
	@Test
	@Ignore
	public void testInsertAdminByAdminBean() {
		AdminBean b = new AdminBean();
		b.setLoginName("admin007");
		b.setPassword("12345678");
		b.setPhone("12345678912");
		b.setRealName("测试添加");
		b.setAuthorGroupId(7);
		try {
			adminService.insertAdminByAdminBean(b, "12345678");
		} catch (AdminException e) {
			e.printStackTrace();
		}
	}
}
