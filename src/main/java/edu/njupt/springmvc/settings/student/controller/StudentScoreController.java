package edu.njupt.springmvc.settings.student.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.njupt.springmvc.settings.student.service.StudentScoreService;

@Controller
@RequestMapping(path = "/api/StuScore")
public class StudentScoreController {
	private final Logger logger = Logger.getLogger(StudentScoreController.class);
	
	@Resource(type = StudentScoreService.class)
	private StudentScoreService studentScoreService;
	
	@ResponseBody
	@RequestMapping(path = "/queryByPage", method = RequestMethod.GET)
	public Map<String, Object> queryStudentScoreWithLimitOrderById(Integer page, Integer limit){
		logger.info("query student score");
		Map<String, Object> result = new HashMap<>(10);
		try {
			Map<String, Object> res = studentScoreService.queryStudentScoreWithLimitOrderById(page, limit);

			result.put("data", res.get("data"));
			result.put("count", res.get("count"));
			result.put("code", 0);
		} catch (Exception e) {
			result.put("code", 1);
			result.put("msg", e.getMessage());
		}
		return result;
	}
	@ResponseBody
	@RequestMapping(path = "/queryByLikeName", method = RequestMethod.GET)
	public Map<String, Object> queryStudentScoreWithLimitByRealName(String realName, Integer page, Integer limit){
		Map<String, Object> result = new HashMap<>();
		logger.info(String.format("query student score by real name [ %s ]", realName));
		try {
			Map<String, Object> res = studentScoreService.queryStudentScoreWithLimitByRealName(realName, page, limit);
			
			result.put("data", res.get("data"));
			result.put("count", res.get("count"));
			result.put("code", 0);
		} catch (Exception e) {
			result.put("code", 1);
			result.put("msg", e.getMessage());
		}
		
		return result;
	}
}
