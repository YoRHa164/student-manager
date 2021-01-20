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
			throw new StudentException(String.format("id:%s, name: %s 未注册，请先对该学生注册", b.getId(), b.getRealName()));
		}
		int result = studentScoreDao.updateStudentScoreByStudentScoreBean(b);
		
		if(result == 0) {
			throw new StudentException("修改失败，请先对学生进行添加");
		}else if(result != 1) {
			throw new StudentException("修改失败，请检查输入是否正确");
		}
	}
	/**
	 * 通过 <b>map</b> 方式查找未被添加进学生成绩表 <b>stuscore</b> 的学生
	 * @return
	 */
	public List<Map<String, String>> queryStudentInfoWithoutScore(){
		return studentScoreDao.queryStudentInfoWithoutScore();
	}
	/**
	 * 通过学生实体类进行插入学生成绩信息
	 * @param b
	 * @throws StudentException
	 */
	@Transactional(rollbackFor = {StudentException.class})
	public void insertStudentScoreByStudentScoreBean(StudentScoreBean b) throws StudentException{
		//检查学生是否注册过
		StudentBean bean = studentDao.queryStudentByRealName(b.getRealName());
		if(bean == null) {
			throw new StudentException("该学生未注册，请先进行学生注册");
		}
		//前端API问题
		b.setId(bean.getId());
		int i = studentScoreDao.insertStudentScoreByStudentScoreBean(b);
		if(i != 1) {
			throw new StudentException("添加失败");
		}
	}
	/**
	 * 通过主键删除学生成绩信息
	 * @param id
	 * @throws StudentException
	 */
	@Transactional(rollbackFor = {StudentException.class})
	public void deleteStudentScoreById(String id) throws StudentException {
		//检查学生是否进行注册
		StudentBean bean = studentDao.queryStudentById(id);
		if(bean == null) {
			throw new StudentException("删除失败，学生未注册");
		}
		//删除
		int i = studentScoreDao.deleteStudentScoreById(id);
		if(i != 1) {
			throw new StudentException("删除失败");
		}
	}
}
