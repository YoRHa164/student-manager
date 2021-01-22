package edu.njupt.springmvc.settings.admin.service;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import edu.njupt.springmvc.config.SpringContextConfig;
import edu.njupt.springmvc.settings.admin.bean.AuthorGroupBean;
import edu.njupt.springmvc.settings.admin.exception.AuthorException;

public class AuthorServiceTest {
	private AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(SpringContextConfig.class);
	private AuthorService authorService = ctx.getBean(AuthorService.class);
	
	@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(AdminServiceTest.class);

	@Test
	@Ignore
	public void testQueryAuthorGroupInfoWithAccess() {
		List<Map<String,Object>> list = authorService.queryAuthorGroupInfoWithAccess();
		list.forEach(e -> {
			System.out.println(e);
		});
	}
	@Test
	public void testDeleteAuthorGroupById() {
		try {
			authorService.deleteAuthorGroupById(8);
		} catch (AuthorException e) {
			System.out.println(e.getMessage());
		}
	}
	@Test
	public void testInsertAuthorGroup() {
		try {
			AuthorGroupBean b = new AuthorGroupBean();
			b.setGroupName("test insert group");
			b.setDisplayName("显示组名");
			b.setDescs("这是一段描述");
			authorService.insertNewAuthorGroup(b, new String[]{"1", "2", "3"});
		} catch (AuthorException e) {
			e.printStackTrace();
		}
		
	}
}
