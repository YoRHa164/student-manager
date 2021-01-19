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
	
	/**
	 * 按照时间间隔方式查询 &nbsp;&nbsp; <b>查询区间为[startTime, endTime)</b>, 与jdk默认方式一致。 <br>
	 * 注意，该方法查询结果<b>有序</b> 结果按照时间顺序从小到大自然排列。
	 * @param startTime 起始时间
	 * @param endTime	结束时间
	 * @param startIndex	开始的索引
	 * @param limit			每页数量
	 * @return
	 */
	List<StudentBean> queryStudentByInterval(
			@Param("start") String startTime, 
			@Param("end") String endTime, 
			@Param("startIndex") Integer startIndex, 
			@Param("limit") Integer limit);
	
	/**
	 * 与 <b>queryStudentByInterval()</b>方法搭配使用。该方法返回上述方法结果不使用分页查询的所有结果数量，用于前端分页查询
	 * @param start
	 * @param end
	 * @return
	 */
	int totalCountOfQueryStudentByInteval(@Param("start") String start, @Param("end") String end);
}
