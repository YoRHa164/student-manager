package edu.njupt.springmvc.settings.student.service;

import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import edu.njupt.springmvc.config.SpringContextConfig;
import edu.njupt.springmvc.settings.student.exception.StudentScoreQueryException;

public class StudentCountServiceTest {
	private static final Logger logger = Logger.getLogger(StudentCountServiceTest.class);
	private final AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(SpringContextConfig.class);
	private final StudentCountService scs = ctx.getBean(StudentCountService.class);
	
	@Test
	@Ignore
	public void testQueryStudentScoreBySbujectOnScoreRange() {
		try {
			@SuppressWarnings("unused")
			Map<String, Object> res = scs.queryStudentScoreBySbujectOnScoreRange(Map.of("subjectName", "java", "page", 1, "limit", 5, "minScore", 60, "maxScore", 100 ));
		} catch (StudentScoreQueryException e) {
			logger.warn(e);
		}
	}
	@Test
	@Ignore
	public void testQueryStudentTotalScoreOnScoreRange() {
		Map<String, Object> res = scs.queryStudentTotalScoreOnScoreRange(Map.of("maxScore", 400, "minScore", 0, "page", 1, "limit", 5));
		System.out.println(res.get("count"));
		System.out.println(res.get("data"));
	}
}
