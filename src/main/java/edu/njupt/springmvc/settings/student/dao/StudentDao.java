package edu.njupt.springmvc.settings.student.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import edu.njupt.springmvc.settings.student.bean.StudentBean;

@Repository
@Scope("prototype")
public interface StudentDao {
	/**
	 * 分页查询 <br>
	 * 结果通过id排序
	 * @param startIndex 查询的下标起点
	 * @param limit		每页查询数量
	 * @return	查询结果集
	 */
	List<StudentBean> queryByLimitOrderById(@Param("start") Integer startIndex, @Param("limit") Integer limit);
	/**
	 * 查询当前数据库中学生数量
	 * @return
	 */
	int totalCountOfStudent();
}
