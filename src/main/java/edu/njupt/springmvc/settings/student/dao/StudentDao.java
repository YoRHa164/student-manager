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
	/**
	 * 通过bean方式进行更新
	 * @param s
	 * @return
	 */
	int updateStudentByStudentBean(StudentBean s);
	
	/**
	 * 通过关键字 {@link String keyWord} 进行模糊查询
	 * @param keyWord
	 * @param startIndex
	 * @param limit
	 * @return
	 */
	List<StudentBean> queryStudentByKeyWord(
			@Param("keyWord") String keyWord, 
			@Param("startIndex") Integer startIndex, 
			@Param("limit") Integer limit);
	/**
	 * 与 queryStudentByKeyWord() 搭配使用。
	 * 该方法获取 queryStudentByKeyWord 下模糊查询具体数量,用于分页
	 * @param keyWord
	 * @return
	 */
	int totalCountOfQueryStudentByKeyWord(@Param("keyWord") String keyWord);
}
