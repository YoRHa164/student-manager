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
	/**
	 * 通过姓名完成模糊查询&nbsp;
	 * @param realName 要模糊查找的姓名
	 * @param startInedx	起始下标
	 * @param limit			每页数量
	 * @return
	 */
	List<StudentScoreBean> queryStudentScoreWithLimitByRealName(
			@Param("realName") String realName, 
			@Param("startIndex") Integer startInedx, 
			@Param("limit") Integer limit);
	/**
	 * 与 <b>queryStudentScoreWithLimitByRealName()</b>方法一起使用。 <br>
	 * 该方法返回上述方法不使用分页的总条数
	 * @param realName
	 * @return
	 */
	int totalCountOfQueryStudentScoreWithLimitByRealName(@Param("realName") String realName);
	
	/**
	 * 通过stuId字段，查找学生成绩信息是否已经被录入，如果成绩已经被录入，则返回1，否则返回0
	 * @param id
	 * @return
	 */
	int queryStudentScoreExistsById(@Param("id") String id);
	
	/**
	 * 通过 {@link StudentScoreBean }方式对学生分数数据库进行更新 <b>update</b> 操作
	 * @param b
	 * @return
	 */
	int updateStudentScoreByStudentScoreBean(StudentScoreBean b);
	
	/**
	 * 
	 * 通过 {@link StudentScoreBean }方式对学生分数数据库进行插入 <b>insert</b> 操作
	 * @param b
	 * @return
	 */
	int insertStudentScoreByStudentScoreBean(StudentScoreBean b);
}
