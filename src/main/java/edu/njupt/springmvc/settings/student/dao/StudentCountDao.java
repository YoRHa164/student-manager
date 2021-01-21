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
	Map<String, Long> queryScoreInDefaultIntervalBySubject(@Param("subject") String subject);
	
	/**
	 * 使用默认方式进行学生总成绩分布查询
	 * 	默认分组方式：
	 * 		[350, 400], [300, 350), [250, 300), [200, 250), [150, 200),[100, 150), [0, 100)  <br><br>
	 * @return
	 */
	Map<String, Long> queryTotalScoreInDefaultInterval();
	
	/**
	 * 按照Map方式查询指定科目指定范围内学生成绩和信息
	 * 	请求格式 <br>
	 * 	{
	 * 		"subject":${subject},
	 * 		"maxScore":${maxScore},
	 * 		"minScore":${minScore},
	 * 		"startIndex":${startIndex},
	 * 		"limit":${limit}
	 * 	}
	 * @param reqMap
	 * @return
	 * 	返回Map格式: <br>
	 * 	{
	 * 		"id":${id},
	 * 		"realName":${realName},
	 * 		"indentifyNo":${indentifyNo},
	 * 		"phone":${phone},
	 * 		"score":${score}
	 * 	}
	 */
	List<Map<String, String>> queryStudentScoreBySbujectOnScoreRange(Map<String, Object> reqMap);
	/**
	 * 与 <b>queryStudentScoreBySbujectOnScoreRange()</b> 方法一起使用。该方法返回不分页的结果数量
	 * @param reqMap
	 * @return
	 */
	Integer totalCountOfQueryStudentScoreBySbujectOnScoreRange(Map<String, Object> reqMap);
	
	/**
	 *  按照Map方式查询总科目指定范围内学生成绩和信息
	 * 	请求格式 <br>
	 * 	{
	 * 		"maxScore":${maxScore},
	 * 		"minScore":${minScore},
	 * 		"startIndex":${startIndex},
	 * 		"limit":${limit}
	 * 	}
	 * @param reqMap
	 * @return
	 * 返回Map格式: <br>
	 * 	{
	 * 		"id":${id},
	 * 		"realName":${realName},
	 * 		"indentifyNo":${indentifyNo},
	 * 		"phone":${phone},
	 * 		"score":${score}
	 * 	}
	 */
	List<Map<String, String>> queryStudentTotalScoreOnScoreRange(Map<String, Object> reqMap);
	
	/**
	 * 与 <b>queryStudentTotalScoreOnScoreRange()</b> 方法一起使用。该方法返回不分页的结果数量
	 * @param reqMap
	 * @return
	 */
	Integer totalCountOfQueryStudentTotalScoreOnScoreRange(Map<String, Object> reqMap);
}
