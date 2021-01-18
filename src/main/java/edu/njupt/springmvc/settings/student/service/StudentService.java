package edu.njupt.springmvc.settings.student.service;

import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import edu.njupt.springmvc.settings.student.bean.StudentBean;
import edu.njupt.springmvc.settings.student.dao.StudentDao;

@Service
@Scope("prototype")
public class StudentService {
	@Resource(type = StudentDao.class)
	private StudentDao studentDao;
	/**
	 * 按照页数、每页数量查询 <br>
	 * 该方法认为，每页查询结果数量一致，且通过id排序
	 * @param page 页数&nbsp;[1, +inf)
	 * @param limit 每页数量
	 * @return 若结果集为空，不返回null 而返回一个空集 []
	 */
	public List<StudentBean> queryStudentByLimitOrderById(Integer page, Integer limit){
		if(page == null || page < 1) {
			return Collections.emptyList();
		}
		if(limit == null || limit < 0) {
			return Collections.emptyList();
		}
		int startIndex = (page - 1) * limit;
		List<StudentBean> result = studentDao.queryByLimitOrderById(startIndex, limit);
		return result == null? Collections.emptyList(): result;
	}
	/**
	 * 查询当前数据库学生数量
	 * @return
	 */
	public int totalCountOfStudent() {
		return studentDao.totalCountOfStudent();
	}
}
