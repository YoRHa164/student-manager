package edu.njupt.springmvc.settings.student.service;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import edu.njupt.springmvc.config.SpringContextConfig;
import edu.njupt.springmvc.settings.student.bean.StudentScoreBean;

public class StudentScoreServiceTest {
	private static final Logger logger = Logger.getLogger(StudentScoreServiceTest.class);
	private final AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(SpringContextConfig.class);
	private final StudentScoreService sss = ctx.getBean(StudentScoreService.class);
	
	@Test
	@Ignore
	@SuppressWarnings("unchecked")
	public void testQueryStudentScoreWithLimitOrderById() {
		Map<String, Object> res = sss.queryStudentScoreWithLimitOrderById(2, 10);
		
		logger.info("total count of query is " + res.get("count"));
		List<StudentScoreBean> list = (List<StudentScoreBean>) res.get("data");
		list.forEach(s -> {
			System.out.println(s);
		});
	}
}
