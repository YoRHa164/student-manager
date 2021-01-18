package edu.njupt.springmvc.settings.student.service;

import org.apache.log4j.Logger;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import edu.njupt.springmvc.config.SpringContextConfig;

public class StudentServiceTest {
	private static final Logger logger = Logger.getLogger(StudentServiceTest.class);
	@Test
	@Ignore
	public void testQueryStudentByLimitOrderById() {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(SpringContextConfig.class);
		
		StudentService bean = ctx.getBean(StudentService.class);
		bean.queryStudentByLimitOrderById(1, 5).forEach(s -> {
			logger.info(String.format("id ==> %s", s.getId()));
		});
		
		ctx.close();
	}
}
