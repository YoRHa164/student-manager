package edu.njupt.springmvc.settings.admin.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import edu.njupt.springmvc.config.SpringContextConfig;
import edu.njupt.springmvc.settings.admin.bean.AdminBean;
import edu.njupt.springmvc.settings.login.bean.LoginBean;
import edu.njupt.springmvc.util.DataBaseUtil;

public class AdminDaoTest {
	private AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(SpringContextConfig.class);
	private AdminDao adminDao = ctx.getBean(AdminDao.class);
	private Logger logger = Logger.getLogger(AdminDaoTest.class);
	
	@Test
	@Ignore
	public void testCheckAdminAccountExPasswordCurrect() {
		LoginBean check = adminDao.checkAdminAccountExists("admin01", DataBaseUtil.getMD5Digest("123456789"));
		logger.info(check != null? "存在": "不存在");
	}
	@Test
	@Ignore
	public void testUpdateAdminPassword() {
		int res = adminDao.updateAdminPassword("admin01", DataBaseUtil.getMD5Digest("123456789"));
		logger.info(res == 1? "修改成功": "修改失败");
	}
	@Test
	@Ignore
	public void testQueryCompleteAdminList() {
		List<AdminBean> list = adminDao.queryCompleteAdminList(0, 3);
		list.forEach(e -> {
		});
	}
	@Test
	@Ignore
	public void testTotalCountOfCompleteAdminList() {
		int totalCount = adminDao.totalCountOfCompleteAdminList();
		System.out.println(totalCount);
	}
	@Test
	@Ignore
	public void testUpdateAdminByAdminBean() {
		AdminBean b = new AdminBean();
		b.setId("10");
		b.setLoginName("admin07");
		b.setRealName("测试修改");
		
		int i = adminDao.updateAdminByAdminBean(b);
		System.out.println(i);
		
	}
	
	@Test
	@Ignore
	public void testInsertAdminByAdminBean() {
		AdminBean b = new AdminBean();
		b.setLoginName("admin07");
		b.setPassword(DataBaseUtil.getMD5Digest("123456789"));
		b.setPhone("12345678912");
		b.setRealName("测试添加");
		b.setAuthorGroupId(7);
		b.setCreateTime(DataBaseUtil.currentTimeByDefault());
		adminDao.insertAdminByAdminBean(b);
	}
}
