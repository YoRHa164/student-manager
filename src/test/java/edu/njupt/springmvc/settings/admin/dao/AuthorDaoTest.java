package edu.njupt.springmvc.settings.admin.dao;

import java.util.List;
import java.util.Map;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import edu.njupt.springmvc.config.SpringContextConfig;
import edu.njupt.springmvc.settings.admin.bean.AuthorGroupBean;

public class AuthorDaoTest {
	private AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(SpringContextConfig.class);
	private AuthorDao ad = ctx.getBean(AuthorDao.class);
	
	@Test
	@Ignore
	public void testQueryAuthorInfo() {
		List<AuthorGroupBean> list = ad.queryAuthorGroupInfo(null);
		list.forEach(e -> {
			System.out.println(e);
		});
	}
	@Test
	@Ignore
	public void testQueryAuthorGroupAccessByAuthorGroupId() {
		List<Map<String,Object>> list = ad.queryAuthorGroupAccessByAuthorGroupId(2);
		list.forEach(e -> {
			System.out.println(e);
		});
	}
	@Test
	@Ignore
	public void testUpdateAuthorGroupInfoByMap() {
		int res = ad.updateAuthorGroupInfoByMap(Map.of("id", 7, "displayName", "调试管理员1", "descs", ""));
		System.out.println(res);
	}
	@Test
	@Ignore
	public void testDleteAuthorGroupAccess() {
		ad.deleteAuthorGroupAccess(8, new Integer[] {1, 2});
	}
	@Test
	@Ignore
	public void testTotalCountOfAuthorOfId() {
		int i = ad.totalCountOfAuthorOfId(14);
		System.out.println(i);
		
	}
	@Test
	@Ignore
	public void testQueryGroupAuthorNotHaveByGid() {
		List<Map<String,Object>> list = ad.queryGroupAuthorNotHaveByGid(1);
		list.forEach(e -> {
			System.out.println(e);
		});
	}
	@Test
	@Ignore
	public void testAddAuthorGroupAccess() {
		int i = ad.addAuthorGroupAccess(8, new Integer[]{1, 2, 3});
		System.out.println(i);
	}
	@Test
	@Ignore
	public void testQueryAllAuthorAccess() {
		List<Map<String,Object>> list = ad.queryAllAuthorAccess();
		
		list.forEach(e -> {
			System.out.println(e);
		});
	}
	@Test
	@Ignore
	public void testInsertNewAuthorGroupByAuthorGroupBean() {
		AuthorGroupBean b = new AuthorGroupBean();
		b.setGroupName("test group");
		b.setDisplayName("测试组");
		b.setDescs("这是一个测试组数据");
		
		ad.insertNewAuthorGroupByAuthorGroupBean(b);
		AuthorGroupBean info = ad.queryAuthorGroupInfo(b.getGroupName()).get(0);
		System.out.println(info);
	}
}
