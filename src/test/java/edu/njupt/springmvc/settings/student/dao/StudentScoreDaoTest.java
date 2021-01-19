package edu.njupt.springmvc.settings.student.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import edu.njupt.springmvc.config.SpringContextConfig;
import edu.njupt.springmvc.settings.student.bean.StudentScoreBean;

public class StudentScoreDaoTest {
	private static final Logger logger = Logger.getLogger(StudentScoreDaoTest.class);
	private final AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(SpringContextConfig.class);
	private final StudentScoreDao ssd = ctx.getBean(StudentScoreDao.class);
	
	@Test
	@Ignore
	public void testQueryStudentScoreWithLimitOrderById() {
		List<StudentScoreBean> res = ssd.queryStudentScoreWithLimitOrderById(9, 5);
		res.forEach(s -> {
			logger.info(s);
		});
	}
	
	@Test
	@Ignore
	public void testTotalCountOfQueryStudentScoreWithLimitOrderById() {
		int totalCount = ssd.totalCountOfQueryStudentScoreWithLimitOrderById();
		logger.info(totalCount);
	}
	@Test
	@Ignore
	public void testQueryStudentScoreWithLimitByRealName() {
		List<StudentScoreBean> result = ssd.queryStudentScoreWithLimitByRealName("李小龙7", 0, 20);
		result.forEach(s -> {
			System.out.println(s);
		});
	}
	@Test
	@Ignore
	public void testTotalCountOfQueryStudentScoreWithLimitByRealName() {
		int totalCount = ssd.totalCountOfQueryStudentScoreWithLimitByRealName("小");
		logger.info(totalCount);
	}
	@Test
	@Ignore
	public void testQueryStudentScoreExistsById() {
		int exists = ssd.queryStudentScoreExistsById(".....");
		logger.info(exists);
	}
	@Test
	@Ignore
	public void testUpdateStudentScoreByStudentScoreBean() {
		StudentScoreBean b = ctx.getBean(StudentScoreBean.class);
		
		b.setId("10007");
		b.setPython(80);
		b.setLinux(85);
		b.setSql(85);
		b.setJava(81);

		int update = ssd.updateStudentScoreByStudentScoreBean(b);
		logger.info(update);
	}
	@Test
	@Ignore
	public void testInsertStudentScoreByStudentScoreBean() {
		StudentScoreBean b = ctx.getBean(StudentScoreBean.class);

		b.setId("10062");
		b.setPython(80);
		b.setLinux(85);
		b.setSql(86);
		b.setJava(81);
		
		ssd.insertStudentScoreByStudentScoreBean(b);
	}
}
