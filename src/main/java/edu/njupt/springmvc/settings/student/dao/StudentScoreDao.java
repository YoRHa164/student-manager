package edu.njupt.springmvc.settings.student.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import edu.njupt.springmvc.settings.student.bean.StudentScoreBean;

@Repository
@Scope("prototype")
public interface StudentScoreDao {
	/**
	 * 分页查询学生分数信息 <br>
	 * 结果通过主键 <b>id</b> 进行升序排序
	 * @param startIndex
	 * @param limit
	 * @return
	 */
	List<StudentScoreBean> queryStudentScoreWithLimitOrderById(
			@Param("startIndex") Integer startIndex, 
			@Param("limit") Integer limit);
	/**
	 * 与 <b>queryStudentScoreWithLimitOrderById()</b>一起使用 <br>
	 * 该方法返回与上述方法结果不分页的查询总条数
	 * @return
	 */
	int totalCountOfQueryStudentScoreWithLimitOrderById();
	
	List<StudentScoreBean> queryStudentScoreWithLimitByRealName(
			@Param("realName") String realName, 
			@Param("startIndex") Integer startInedx, 
			@Param("limit") Integer limit);
	
	int totalCountOfQueryStudentScoreWithLimitByRealName(@Param("realName") String realName);
}
