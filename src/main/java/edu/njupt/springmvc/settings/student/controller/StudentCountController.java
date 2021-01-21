package edu.njupt.springmvc.settings.student.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.njupt.springmvc.settings.student.service.StudentCountService;

@Controller
@RequestMapping("/api/StuCount")
public class StudentCountController {
	@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(StudentCountController.class);
	@Resource(type = StudentCountService.class)
	private StudentCountService studentCountService;
	
	/**
	 * 响应格式:  {"code":0/1, "data":[{"regTime":${regTime}, "regNum":${regNum}},....]}
	 * @param s
	 * @param e
	 * @return
	 */
	@ResponseBody
	@RequestMapping(path = "/regEveryDayByTimeZone", method = RequestMethod.GET)
	public Map<String, Object> queryRegisteDateByInterval(
			@RequestParam("startDate") String s, 
			@RequestParam("endDate") String e)
	{
		Map<String, Object> result = new HashMap<>(5);
		try {
			List<Map<String, String>> res = studentCountService.queryRegisteDateByInterval(s, e);
			
			result.put("data", res);
			result.put("code", 0);
		} catch (Exception e2) {
			result.put("code", 1);
			result.put("msg", e2.getMessage());
		}
		
		return result;
	}
	/**
	 * 按照学生成绩分布进行区间查询
	 * 响应格式:<br>
	 * {
	 * 		"code":0/1,
	 * 		"data":[
	 * 					{
	 * 						"name":${interval name},
	 * 						"value":${count of student}
	 * 					},...
	 * 				],<br>
	 * 		"msg":${error message}
	 * }
	 * @param subject
	 * @return
	 */
	@ResponseBody
	@RequestMapping(path = "/subjDefaultCount", method = RequestMethod.GET)
	public Map<String, Object> queryDefaultScoreIntervalBySubject(@RequestParam("subjectName") String subject){
		Map<String, Object> result = new HashMap<>(10);
		
		try {
			List<Map<String, Object>> res = studentCountService.queryScoreInDefaultIntervalBySubject(subject);
			
			result.put("data", res);
			result.put("code", 0);
		} catch (Exception e) {
			result.put("code", 1);
			result.put("msg", e.getMessage());
		}
		
		return result;
	}
	/**
	 * 按照学生总成绩分布进行区间查询
	 * 响应格式: <br>
	 * {
	 * 		"code":0/1, 
	 * 		"data":[
	 * 					{
	 * 						"name":${interval name},
	 * 						"value":${count of student}
	 * 					},...
	 * 				],<br>
	 * 		"msg":${error message}
	 * }
	 * @return
	 */
	@ResponseBody
	@RequestMapping(path = "/sumScoreCount", method = RequestMethod.GET)
	public Map<String, Object> queryTotalScoreInDefaultInterval(){
		Map<String, Object> result = new HashMap<>(6);
		try {
			List<Map<String, Object>> res = studentCountService.queryTotalScoreInDefaultInterval();
			
			result.put("data", res);
			result.put("code", 1);
		} catch (Exception e) {
			result.put("code", 1);
			result.put("msg", e.getMessage());
			
		}
		return result;
	}
	/**
	 * 按照学科、分数范围、分页查找学生信息
	 * @param subject
	 * @param page
	 * @param limit
	 * @param maxScore
	 * @param minScore
	 * @return
	 */
	@ResponseBody
	@RequestMapping(path = "/querySubjOnZoneStu", method = RequestMethod.GET)
	public Map<String, Object> queryStudentScoreBySbujectOnScoreRange(
			@RequestParam("subjectName") 	String subject,
			@RequestParam("page") 			Integer page,
			@RequestParam("limit") 			Integer limit,
			@RequestParam("maxScore") 		Integer maxScore,
			@RequestParam("minScore") 		Integer minScore
			)
	{
		Map<String, Object> result = new HashMap<>(6);
		try {
			Map<String, Object> res = studentCountService.queryStudentScoreBySbujectOnScoreRange(Map.of(
					"subjectName", subject, 
					"page", page, 
					"limit", limit, 
					"maxScore", maxScore, 
					"minScore", minScore));
			
			result.put("data", res.get("data"));
			result.put("count", res.get("count"));
			result.put("code", 0);
		} catch (Exception e) {
			result.put("code", 1);
			result.put("msg", e.getMessage());
		}
		
		return result;
	}
	/**
	 * 按照分数范围、分页查找学生总成绩信息
	 * @param page
	 * @param limit
	 * @param maxScore
	 * @param minScore
	 * @return
	 */
	@ResponseBody
	@RequestMapping(path = "/querySumScoreOnZoneStu", method = RequestMethod.GET)
	public Map<String, Object> queryStudentTotalScoreOnScoreRange(
			@RequestParam("page") 			Integer page,
			@RequestParam("limit") 			Integer limit,
			@RequestParam("maxScore") 		Integer maxScore,
			@RequestParam("minScore") 		Integer minScore
			)
	{
		Map<String, Object> result = new HashMap<>();
		try {
			Map<String, Object> res = studentCountService.queryStudentTotalScoreOnScoreRange(Map.of(
					"page", page, 
					"limit", limit, 
					"maxScore", maxScore,
					"minScore", minScore));
			
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
