package edu.njupt.springmvc.settings.student.service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import edu.njupt.springmvc.settings.student.dao.StudentCountDao;

@Service
@Scope("prototype")
public class StudentCountService {
	@Resource(type = StudentCountDao.class)
	private StudentCountDao studentCountDao;
	
	//白名单学科
	private static final Set<String> LEGAL_SUBJECTS = new CopyOnWriteArraySet<>();
	static {
		LEGAL_SUBJECTS.addAll(Set.of("java", "sql", "python", "linux"));
	}
	
	/**
	 * 通过 <b>startTime</b> 和 <b>endTime</b> 查询区间内所有注册学生的数量
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public List<Map<String, String>> queryRegisteDateByInterval(String startDate, String endDate){
		return studentCountDao.queryRegisiteTimeByInterval(startDate, endDate);
	}
	/**
	 * 通过给定学科查询分数分布。该方法通过白名单方式检查是否有SQL注入问题
	 * @param subject
	 * @return 返回
	 */
	public Map<String, Long> queryDefaultScoreIntervalBySubject(String subject){
		String lowerSubject = subject.toLowerCase();
		
		if(isIegalSubject(lowerSubject)) {
			return studentCountDao.queryDefaultScoreIntervalBySubject(lowerSubject);
		}else {
			return null;
		}
	}
	
	/**
	 * 检查请求科目是否是合法科目
	 * @param subject
	 * @return
	 */
	private static boolean isIegalSubject(String subject) {
		return LEGAL_SUBJECTS.contains(subject);
	}
}
