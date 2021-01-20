package edu.njupt.springmvc.settings.student.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Repository
@Scope("prototype")
public interface StudentCountDao {
	/**
	 * 该方法遵循与java规范一致的区间规则。即 <b>[startTime, endTime)</b>
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	List<Map<String, String>> queryRegisiteTimeByInterval(
			@Param("startTime") String startTime, 
			@Param("endTime")   String endTime);
	
	/**
	 * 使用默认方式进行分数分段查询
	 * 	默认分组方式：
	 * 		[90, 100], [80, 90), [70, 80), [60, 70), [0, 60) <br><br>
	 * <b>注意！</b> <br>
	 *  该方法由于使用指定字段查询，拼接SQL语句可能会引起 <b>SQL注入</b> 问题，请 <b>严格</b> 限制请求内容
	 * @param subject
	 * @return
	 */
	Map<String, Long> queryDefaultScoreIntervalBySubject(@Param("subject") String subject);
}
