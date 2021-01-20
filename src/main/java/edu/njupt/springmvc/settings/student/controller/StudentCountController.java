package edu.njupt.springmvc.settings.student.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
	 * 响应格式:
	 * {
	 * 		"code":0/1,
	 * 		"data":[
	 * 					{
	 * 						"name":${interval name},
	 * 						"value":${count of student}
	 * 					},...
	 * 				],
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
			Map<String, Long> map = studentCountService.queryDefaultScoreIntervalBySubject(subject);
			List<Map<String, Object>> temp = new ArrayList<>();
			map.forEach((k, v) ->{
				temp.add(Map.of("name", k, "value", v));
			});
			
			//排序
			Collections.sort(temp, new Comparator<Map<String, Object>>() {
				@Override
				public int compare(Map<String, Object> o1, Map<String, Object> o2) {
					return ((String) o1.get("name")).compareTo((String) o2.get("name"));
				}
			});
			
			result.put("data", temp);
			result.put("code", 0);
		} catch (Exception e) {
			result.put("code", 1);
			result.put("msg", e.getMessage());
		}
		
		return result;
	}
	@ResponseBody
	@RequestMapping(path = "/sumScoreCount", method = RequestMethod.GET)
	public Map<String, Object> test1(){
		Map<String, Object> result = new HashMap<>(5);
		
		result.put("code", 0);
		result.put("data", 
				List.of(
						Map.of("name", "测试1", "value", 20),
						Map.of("name", "测试2", "value", 60),
						Map.of("name", "测试3", "value", 60),
						Map.of("name", "测试4", "value", 10),
						Map.of("name", "测试5", "value", 10)
						
						));
		
		return result;
	}
}
