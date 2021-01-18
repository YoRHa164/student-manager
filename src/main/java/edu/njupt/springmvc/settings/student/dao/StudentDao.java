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
	/**
	 * 通过realName字段查找学生
	 * @param realName
	 * @return
	 */
	StudentBean queryStudentByRealName(@Param("realName") String realName);
	
	/**
	 * 通过学生实体类进行添加
	 * @param s
	 * @return 返回执行条数
	 */
	int addStudentByStudent(StudentBean s);
	
	/**
	 * 通过id进行删除操作
	 * @param id
	 * @return
	 */
	int deleteStudentById(String id);
}
