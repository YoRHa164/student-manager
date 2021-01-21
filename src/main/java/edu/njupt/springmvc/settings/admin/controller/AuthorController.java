package edu.njupt.springmvc.settings.admin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.njupt.springmvc.settings.admin.service.AuthorService;

@Controller
@RequestMapping("/api/AuthorGroup")
public class AuthorController {
	@Resource(type = AuthorService.class)
	private AuthorService authorService;
	
	@ResponseBody
	@RequestMapping(path = "/all", method = RequestMethod.GET)
	public Map<String, Object> queryAuthorGroupInfo(){
		Map<String, Object> result = new HashMap<>();
		try {
			List<Map<String,Object>> info = authorService.queryAuthroInfo();
			
			result.put("data", info);
			result.put("code", 0);
		} catch (Exception e) {
			result.put("code", 1);
			result.put("msg", e.getMessage());
		}
		
		return result;
	}
//	@ResponseBody
//	@RequestMapping(path = "/all", method = RequestMethod.GET)
	public Map<String, Object> testAPI(){
		Map<String, Object> result = new HashMap<>();
		
		result.put("code", 0);
		result.put("data", List.of(Map.of("id", 1, "displayName", "test1"), Map.of("id", 2, "displayName", "test2")));
		
		return result;
	}
}
