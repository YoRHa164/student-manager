package edu.njupt.springmvc.settings.student.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import edu.njupt.springmvc.config.SpringContextConfig;
import edu.njupt.springmvc.settings.student.bean.StudentBean;
import edu.njupt.springmvc.util.DataBaseUtil;

public class StudentDaoTest {
	private static final Logger logger = Logger.getLogger(StudentDao.class);
	private final AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(SpringContextConfig.class);
	private final StudentDao studentDao = ctx.getBean(StudentDao.class);
	@Test
	@Ignore
	public void testQueryByLimit() {
		StudentDao bean = ctx.getBean(StudentDao.class);
		logger.info(String.format("bean ==> %s", bean));
		List<StudentBean> queryByLimit = bean.queryByLimitOrderById(0, 5);
		queryByLimit.forEach(x -> {
			System.out.println(x.getId());
			
		});
	}
	@Test
	@Ignore
	public void testTotalCountOfStudent() {
		StudentDao bean = ctx.getBean(StudentDao.class);
		int i = bean.totalCountOfStudent();
		logger.info(String.format("total count of student is ==> %d", i));
	}
	@Test
	@Ignore
	public void testQueryStudentByRealName() {
		
		StudentDao bean = ctx.getBean(StudentDao.class);
		String realName = "abc";
		StudentBean s = bean.queryStudentByRealName(realName);
		logger.info(String.format("count of student named [ %s ] is [ %d ]", realName, s == null? 0: 1));
		
	}
	
	@Test
	@Ignore
	public void testInsertByStudent() {
		StudentDao bean = ctx.getBean(StudentDao.class);
		StudentBean s = ctx.getBean(StudentBean.class);
		
		s.setAddress("123");
		s.setAge(22);
		s.setCity("city");
		s.setGender("男");
		s.setIndentifyNo("123123123123123");
		s.setPhone("12312312312");
		s.setRealName("abcccccc");
		s.setRegTime(DataBaseUtil.currentTimeByDefault());
		
		int ret = bean.addStudentByStudent(s);
		
		
		logger.info(String.format("student bean realName = %s insert return %d", s.getRealName(), ret));
	}
	@Test
	@Ignore
	public void testDeleteById() {
		int i = studentDao.deleteStudentById("10079");
		logger.info(String.format("id [ %s ] 删除 ==> %s", "10079", i == 1? "成功": "失败"));
		
	}
	@Test
	@Ignore
	public void testUpdateByStudentBean() {
		StudentBean s = new StudentBean();
		s.setAddress("123");
		s.setAge(15);
		s.setCity("123");
		s.setGender("男");
		s.setId("10078");
		s.setIndentifyNo("456456456456456");
		s.setPhone("78978978978");
		s.setRealName("张三147");
		studentDao.updateStudentByStudentBean(s);
	}
	@Test
	@Ignore
	public void testFuzzyQuery() {
		List<StudentBean> keyWord = studentDao.queryStudentByKeyWord("1199", 0, 10);
		keyWord.forEach(s -> {
			System.out.println(s + " === " + s.getAddress());
			
		});
	}
	@Test
	@Ignore
	public void testCountOfFuzzyQuery() {
		String keyWord = "1199";
		int countOf = studentDao.totalCountOfQueryStudentByKeyWord(keyWord);
		logger.info(String.format("count of fuzzy keyWord = [ %s ] is %d", keyWord, countOf));
	}
	@Test
	@Ignore
	public void testQueryStudentByInterval() {
		List<StudentBean> interval = studentDao.queryStudentByInterval("2020-12-24", "2021-01-04", 0, 30);
		interval.forEach(s -> {
			System.out.println(s.getRegTime());
		});
	}
	@Test
	@Ignore
	public void testTotalCountOfQueryStudentByInteval() {
		int totalCount = studentDao.totalCountOfQueryStudentByInteval("2020-12-24", "2021-01-20");
		logger.info("total count of query is ==> " + totalCount);
	}
}
