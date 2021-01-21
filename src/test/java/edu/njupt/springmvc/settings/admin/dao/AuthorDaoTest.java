package edu.njupt.springmvc.settings.admin.dao;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import edu.njupt.springmvc.config.SpringContextConfig;

public class AuthorDaoTest {
	private AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(SpringContextConfig.class);
	private AuthorDao ad = ctx.getBean(AuthorDao.class);
	
	@Test
	public void testQueryAuthorInfo() {
		List<Map<String,Object>> list = ad.queryAuthorGroupInfo();
		list.forEach(e -> {
			System.out.println(e);
		});
	}
}
