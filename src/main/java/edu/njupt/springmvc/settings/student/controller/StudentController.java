package edu.njupt.springmvc.settings.student.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.njupt.springmvc.settings.student.bean.StudentBean;
import edu.njupt.springmvc.settings.student.service.StudentService;

@Controller
@RequestMapping("/api/StuInfo")
public class StudentController {
	private static final Logger logger = Logger.getLogger(StudentController.class);
	
	@Resource(type = StudentService.class)
	private StudentService studentService;
	
	
	
	/**
	 * @param studentService 要设置的 studentService
	 */
	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}
	
	@ResponseBody
	@RequestMapping(path = "/query", method = RequestMethod.GET)
	public Map<String, Object> queryStudentInfo(Integer page, Integer limit){
		logger.info(String.format("query student info page=%s, limit=%s", page, limit));
		Map<String, Object> res = new HashMap<>(10);
		List<StudentBean> result = null;
		try {
			result = studentService.queryStudentByLimitOrderById(page, limit);
			
			res.put("code", 0);
			res.put("msg", "success");
			res.put("data", result);
			res.put("count", studentService.totalCountOfStudent());
		} catch (Exception e) {
			res.put("code", 1);
			res.put("msg", "error");
			res.put("data", result);
		}
		
		return res;
	}
}
