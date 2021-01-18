package edu.njupt.springmvc.settings.student.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.njupt.springmvc.settings.student.bean.StudentBean;
import edu.njupt.springmvc.settings.student.exception.StudentException;
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
	/**
	 * 以分页查询形式查找学生
	 * @param page
	 * @param limit
	 * @return
	 */
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
	/**
	 * 通过realName方式查找用户 若存在用户，返回{"code":0} 否则返回{"code":1}
	 * @param realName
	 * @return
	 */
	@ResponseBody
	@RequestMapping(path = "/queryByRealName", method = RequestMethod.GET)
	public Map<String, Object> queryStudentExistsByRealName(String realName){
		int exists = studentService.queryStudentExistsByRealName(realName);
		
		return Map.of("code", exists);
	}
	/**
	 * 添加学生。通过实体类方式进行添加
	 * @param req
	 * @param s
	 * @return
	 */
	@ResponseBody
	@RequestMapping(path = "/add", method = RequestMethod.POST)
	public Map<String, Object> addStudentByStudentBean(HttpServletRequest req, StudentBean s){
		Map<String, Object> result = new HashMap<>(10);
		try {
			studentService.insertStudentByStudentBean(s);
			
			logger.info(String.format("ip [ %s ] insert data. realName [ %s ]", req.getRemoteAddr(), s.getRealName()));
			result.put("code", 0);
			result.put("msg", "添加成功");
		} catch (StudentException e) {
			
			result.put("code", 1);
			result.put("msg", e.getMessage());
		}
		return result;
	}
	/**
	 * 通过id删除学生
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(path = "/deleteById", method = RequestMethod.POST)
	public Map<String, Object> deleteStudentById(String id){
		Map<String, Object> result = new HashMap<>(10);
		try {
			studentService.deleteStudentById(id);
			
			logger.info(String.format("id [ %s ] delete success ", id));
			result.put("code", 0);
			result.put("msg", "删除成功");
		} catch (StudentException e) {
			
			result.put("code", 1);
			result.put("msg", e.getMessage());
		}
		
		return result;
	}
}
