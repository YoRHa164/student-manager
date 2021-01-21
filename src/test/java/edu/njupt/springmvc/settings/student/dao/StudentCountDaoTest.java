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
	@Test
	@Ignore
	public void testQueryStudentScoreBySbujectOnScoreRange() {
		List<Map<String, String>> res = scd.queryStudentScoreBySbujectOnScoreRange(
				Map.of(
				"subject", "sql", 
				"startIndex", 0, 
				"limit", 5, 
				"maxScore", 100, 
				"minScore", 60));
		res.forEach(e -> {
			System.out.println(e);
		});
	}
	@Test
	@Ignore
	public void testQueryStudentTotalScoreOnScoreRange() {
		List<Map<String, String>> res = scd.queryStudentTotalScoreOnScoreRange(Map.of("maxScore", 400, "minScore", 350, "startIndex", 0, "limit", 5));
		res.forEach(e -> {
			System.out.println(e);
		});
	}
	@Test
	@Ignore
	public void testTotalCountOfQueryStudentTotalScoreOnScoreRange() {
		Integer countOf = scd.totalCountOfQueryStudentTotalScoreOnScoreRange(Map.of("maxScore", 400, "minScore", 0, "startIndex", 0, "limit", 5));
		System.out.println(countOf);
	}
}
