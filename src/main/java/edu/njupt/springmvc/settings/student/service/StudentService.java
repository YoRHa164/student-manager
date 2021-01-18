package edu.njupt.springmvc.settings.student.service;

import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.njupt.springmvc.settings.student.bean.StudentBean;
import edu.njupt.springmvc.settings.student.dao.StudentDao;
import edu.njupt.springmvc.settings.student.exception.StudentException;
import edu.njupt.springmvc.util.DataBaseUtil;

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
	
	/**
	 * 通过realName查询学生是否存在。 若存在，则返回0， 否则返回非0值
	 * @param realName
	 * @return
	 */
	public int queryStudentExistsByRealName(String realName) {
		StudentBean s = studentDao.queryStudentByRealName(realName);
		
		return s == null? 1: 0;
	}
	/**
	 * 添加新学生 成功无异常抛出，失败抛出{@link StudentException }
	 * @param s
	 * @return
	 * @throws StudentException
	 */
	@Transactional(rollbackFor = {StudentException.class})
	public void insertStudentByStudentBean(StudentBean s) throws StudentException {
		int ret = 0;
		//添加时间戳
		s.setRegTime(DataBaseUtil.currentTimeByDefault());
		try {
			ret = studentDao.addStudentByStudent(s);
			
		} catch (Exception e) {
			throw new StudentException("添加失败", e);
		}
		if(ret != 1) {
			throw new StudentException("添加失败");
		}
	}
	/**
	 * 删除操作 若成功删除，不扔出异常；删除失败扔出 {@link StudentException}
	 * @param id
	 * @throws StudentException
	 */
	@Transactional(rollbackFor = {StudentException.class})
	public void deleteStudentById(String id) throws StudentException {
		int i = 0;
		try {
			i = studentDao.deleteStudentById(id);	
			
		} catch (Exception e) {
			throw new StudentException("删除失败", e);
		}
		if(i == 0) {
			throw new StudentException("删除失败");
		}
	}
}
