package edu.njupt.springmvc.settings.student.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import edu.njupt.springmvc.settings.student.dao.StudentCountDao;
import edu.njupt.springmvc.settings.student.exception.StudentScoreQueryException;

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
	 * @return <b>[{"name":${name}, "value":${value}}, ...]</b>
	 * 	<br>返回一个有序 {@link java.util.List} 集合，顺序由字段名决定，即给定的{@link Comparator }决定 
	 * @throws StudentScoreQueryException 
	 */
	public List<Map<String, Object>> queryScoreInDefaultIntervalBySubject(String subject) throws StudentScoreQueryException{
		String lowerSubject = subject.toLowerCase();
		
		if(isIegalSubject(lowerSubject)) {
			//查询
			Map<String, Long> map = studentCountDao.queryScoreInDefaultIntervalBySubject(lowerSubject);
			//将查询结果再装配
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
			
			return temp;
		}else {
			throw new StudentScoreQueryException("使用非法学科名");
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
	/**
	 * 查询总成绩分布，结果装配进 {@link List} 集合
	 * <b> 注意 </b> 该方法会将查询结果为0值的剔除
	 * @return <b>[{"name":${name}, "value":${value}}, ...]</b>
	 */
	public List<Map<String,Object>> queryTotalScoreInDefaultInterval() {
		Map<String, Long> res = studentCountDao.queryTotalScoreInDefaultInterval();
		List<Map<String, Object>> temp = new LinkedList<>();
		res.forEach((k, v) -> {
			//将数量为0的值剔除
			if(v != 0L) {
				temp.add(Map.of("name", k, "value", v));
			}
//			temp.add(Map.of("name", k, "value", v));
		});
		
		Collections.sort(temp, new Comparator<Map<String, Object>>() {
			@Override
			public int compare(Map<String, Object> o1, Map<String, Object> o2) {
				return ((String) o2.get("name")).compareTo((String) (o1.get("name")));
			}
		});
		return temp;
	}
}
