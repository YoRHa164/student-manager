package edu.njupt.springmvc.settings.student.service;

import org.apache.log4j.Logger;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import edu.njupt.springmvc.config.SpringContextConfig;
import edu.njupt.springmvc.settings.student.bean.StudentBean;
import edu.njupt.springmvc.settings.student.exception.StudentException;
import edu.njupt.springmvc.util.DataBaseUtil;

public class StudentServiceTest {
	private static final Logger logger = Logger.getLogger(StudentServiceTest.class);
	private final AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(SpringContextConfig.class);
	private final StudentService studentService = ctx.getBean(StudentService.class);
	@Test
	@Ignore
	public void testQueryStudentByLimitOrderById() {
		
		StudentService bean = ctx.getBean(StudentService.class);
		bean.queryStudentByLimitOrderById(1, 5).forEach(s -> {
			logger.info(String.format("id ==> %s", s.getId()));
		});
		
	}
	@Test
	@Ignore
	public void testQueryStudentByRealName() {
		StudentService bean = ctx.getBean(StudentService.class);
		logger.info(String.format("%s", bean));
		int byRealName = bean.queryStudentExistsByRealName("123");
		System.out.println(" ===> " + byRealName);
	}
	@Test
	@Ignore
	public void testAddStudentByStudentBean() {
		StudentService bean = ctx.getBean(StudentService.class);
		StudentBean s = ctx.getBean(StudentBean.class);

		s.setAddress("123");
		s.setAge(22);
		s.setCity("city");
		s.setGender("男");
		s.setIndentifyNo("123123123123123");
		s.setPhone("12312312312");
		s.setRealName("abcccccc");
		s.setRegTime(DataBaseUtil.currentTimeByDefault());
		
		try {
			bean.insertStudentByStudentBean(s);
		} catch (StudentException e) {
			logger.warn(e.getMessage());
		}
	}
	@Test
	@Ignore
	public void testDeleteStudentById() {
		try {
			studentService.deleteStudentById("10099");
			logger.info(String.format("id [ %s ] ==> 删除成功", "10099"));
		} catch (StudentException e) {
			logger.warn(e.getMessage());
		}
	}
}
