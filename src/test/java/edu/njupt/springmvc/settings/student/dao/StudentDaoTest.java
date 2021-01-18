package edu.njupt.springmvc.settings.student.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import edu.njupt.springmvc.config.SpringContextConfig;
import edu.njupt.springmvc.settings.student.bean.StudentBean;

public class StudentDaoTest {
	private static final Logger logger = Logger.getLogger(StudentDao.class);
	@Test
	@Ignore
	public void testQueryByLimit() {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(SpringContextConfig.class);
		StudentDao bean = ctx.getBean(StudentDao.class);
		logger.info(String.format("bean ==> %s", bean));
		List<StudentBean> queryByLimit = bean.queryByLimitOrderById(0, 5);
		queryByLimit.forEach(x -> {
			System.out.println(x.getId());
			
		});
		ctx.close();
	}
	@Test
	@Ignore
	public void testTotalCountOfStudent() {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(SpringContextConfig.class);
		StudentDao bean = ctx.getBean(StudentDao.class);
		int i = bean.totalCountOfStudent();
		logger.info(String.format("total count of student is ==> %d", i));
		ctx.close();
		
	}
}
