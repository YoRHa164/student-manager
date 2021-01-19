package edu.njupt.springmvc.settings.student.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.njupt.springmvc.settings.student.bean.StudentScoreBean;
import edu.njupt.springmvc.settings.student.dao.StudentScoreDao;

@Service
@Scope("prototype")
public class StudentScoreService {
	@Resource(type = StudentScoreDao.class)
	private StudentScoreDao studentScoreDao;
	@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(StudentScoreService.class);
	/**
	 * 
	 * @param page
	 * @param limit
	 * @return
	 */
	@Transactional
	public Map<String, Object> queryStudentScoreWithLimitOrderById(Integer page, Integer limit){
		Map<String, Object> result = new HashMap<>(10);
		
		page = page == null? 1:((page < 1)? 1: page);
		limit = limit == null? 0: ((limit < 0)? 0: limit);
		
		List<StudentScoreBean> query = studentScoreDao.queryStudentScoreWithLimitOrderById((page - 1) * limit, limit);
		int totalCount = studentScoreDao.totalCountOfQueryStudentScoreWithLimitOrderById();
		
		result.put("data", query);
		result.put("count", totalCount);
		
		return result;
	}
	
	@Transactional
	public Map<String, Object> queryStudentScoreWithLimitByRealName(
			String realName, 
			Integer page, 
			Integer limit)
	{
		Map<String, Object> result = new HashMap<>(10);
		page = page == null? 1:((page < 1)? 1: page);
		limit = limit == null? 0: ((limit < 0)? 0: limit);
		
		List<StudentScoreBean> list = studentScoreDao.queryStudentScoreWithLimitByRealName(realName, (page - 1) * limit, limit);
		int totalCount = studentScoreDao.totalCountOfQueryStudentScoreWithLimitByRealName(realName);
		
		result.put("data", list);
		result.put("count", totalCount);
		
		return result;
	}
}
