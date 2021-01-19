package edu.njupt.springmvc.settings.student.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.njupt.springmvc.settings.student.bean.StudentBean;
import edu.njupt.springmvc.settings.student.bean.StudentScoreBean;
import edu.njupt.springmvc.settings.student.dao.StudentDao;
import edu.njupt.springmvc.settings.student.dao.StudentScoreDao;
import edu.njupt.springmvc.settings.student.exception.StudentException;

@Service
@Scope("prototype")
public class StudentScoreService {
	@Resource(type = StudentScoreDao.class)
	private StudentScoreDao studentScoreDao;
	@Resource(type = StudentDao.class)
	private StudentDao studentDao;
	
	
	/**
	 * 查找所有学生成绩信息，并且通过id进行升序排序
	 * @param page
	 * @param limit
	 * @return
	 */
	@Transactional
	public Map<String, Object> queryStudentScoreWithLimitOrderById(Integer page, Integer limit){
		Map<String, Object> result = new HashMap<>(10);
		
		page = page == null? 1:((page < 1)? 1: page);
		limit = limit == null? 0: ((limit < 0)? 0: limit);
		
		List<StudentScoreBean> query = studentScoreDao.queryStudentScoreWithLimitOrderById((page - 1) * limit, limit);
		int totalCount = studentScoreDao.totalCountOfQueryStudentScoreWithLimitOrderById();
		
		result.put("data", query);
		result.put("count", totalCount);
		
		return result;
	}
	/**
	 * 按照学生姓名进行模糊查询
	 * @param realName
	 * @param page
	 * @param limit
	 * @return
	 */
	@Transactional
	public Map<String, Object> queryStudentScoreWithLimitByRealName(
			String realName, 
			Integer page, 
			Integer limit)
	{
		Map<String, Object> result = new HashMap<>(10);
		page = page == null? 1:((page < 1)? 1: page);
		limit = limit == null? 0: ((limit < 0)? 0: limit);
		
		List<StudentScoreBean> list = studentScoreDao.queryStudentScoreWithLimitByRealName(realName, (page - 1) * limit, limit);
		int totalCount = studentScoreDao.totalCountOfQueryStudentScoreWithLimitByRealName(realName);
		
		result.put("data", list);
		result.put("count", totalCount);
		
		return result;
	}
	/**
	 * 通过{@link StudentScoreBean}进行修改学生成绩操作 <br>
	 * 若学生存在，则进行更新 <b>update</b> 操作；若不存在则进行插入 <b>insert</b> 操作
	 * @param b
	 * @throws StudentException 
	 */
	@Transactional(rollbackFor = {StudentException.class})
	public void updateStudentScoreByStudentScoreBean(StudentScoreBean b) throws StudentException {
		StudentBean bean = studentDao.queryStudentById(b.getId());
		//业务层面检查外键约束。即检查学生是否注册过
		if(bean == null) {
			throw new StudentException(String.format("id:%s, name: %s 未注册，请先对该学生注册", b.getId(), b.getName()));
		}
		int exists = studentScoreDao.queryStudentScoreExistsById(bean.getId());
		int result = -1;
		
		if(exists == 1) {
			//学生曾经添加过分数信息，进行更新操作
			result = studentScoreDao.updateStudentScoreByStudentScoreBean(b);
		}else {
			//学生未曾添加过分数信息，进行添加操作
			result = studentScoreDao.insertStudentScoreByStudentScoreBean(b);
		}
		if(result != 1) {
			throw new StudentException("学生修改操作异常，请重新操作");
		}
	}
}
