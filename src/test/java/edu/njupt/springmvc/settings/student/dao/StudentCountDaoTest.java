package edu.njupt.springmvc.settings.student.dao;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import edu.njupt.springmvc.config.SpringContextConfig;

public class StudentCountDaoTest {
	private static final Logger logger = Logger.getLogger(StudentCountDaoTest.class);
	private AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(SpringContextConfig.class);
	private StudentCountDao scd = ctx.getBean(StudentCountDao.class);
	
	@Test
	@Ignore
	public void testQueryRegisiteTimeByInterval() {
		List<Map<String, String>> res = scd.queryRegisiteTimeByInterval("2020-12-24", "2021-01-04");
		res.forEach(m -> {
			logger.info(m);
			
		});
	}
	@Test
	@Ignore
	public void testQueryDefaultScoreIntervalBySubject() {
		Map<String, Long> map = scd.queryScoreInDefaultIntervalBySubject("java");
		map.forEach((k, v) -> {
			logger.info(k + " == " + v);
		});
	}
	@Test
	@Ignore
	public void testQueryTotalScoreInDefaultInterval() {
		Map<String, Long> totalScore = scd.queryTotalScoreInDefaultInterval();
		totalScore.forEach((k, v) -> {
			logger.info(k + " = " + v);
		});
	}
}
