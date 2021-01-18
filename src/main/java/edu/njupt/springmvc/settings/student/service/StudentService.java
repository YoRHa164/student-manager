package edu.njupt.springmvc.settings.student.service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
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
	private static final Logger logger = Logger.getLogger(StudentService.class);
	/**
	 * 按照页数、每页数量查询 <br>
	 * 该方法认为，每页查询结果数量一致，且通过id排序
	 * @param page 页数&nbsp;[1, +inf)
	 * @param limit 每页数量
	 * @return 
	 */
	@Transactional
	public Map<String, Object> queryStudentByLimitOrderById(Integer page, Integer limit){
		Map<String, Object> result = new HashMap<>(10);
		//检查数据合法性
		page = page == null? 1: ((page < 1)? 1: page);
		limit = limit == null? 0:((limit < 0)? 0: limit);
		
		result.put("data", studentDao.queryByLimitOrderById((page - 1) * limit, limit));
		result.put("count", studentDao.totalCountOfStudent());
		logger.info(String.format("total count of query is %d", result.get("count")));
		
		return result;
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
	
	@Transactional(rollbackFor = {StudentException.class})
	public void updateStudentByStudentBean(StudentBean s) throws StudentException {
		int update = 0;
		try {
			update = studentDao.updateStudentByStudentBean(s);
			
		} catch (Exception e) {
			throw new StudentException("修改失败", e);
		}
		if(update != 1) {
			throw new StudentException("修改失败");
		}
	}
	/**
	 * 通过关键字查询
	 * 	
	 * @param keyWord
	 * @param page
	 * @param limit
	 * @return	返回可变Map集合 包括count:${该模糊查询一共的记录} data:${根据页数限制查询的结果集}
	 */
	@Transactional
	public Map<String, Object> fuzzyQueryByKeyWord(String keyWord, Integer page, Integer limit){
		Map<String, Object> result = new HashMap<>(10);
		
		//page为空或 小于1都非法，全部默认变成1
		page = page != null? ((page < 1)? 1: page): 1;
		//limit为空或 小于0都非法，全部默认变成0
		limit = limit == null? 0: ((limit < 0)? 0: limit);
		
		logger.info(String.format("fuzzy query: key word [ %s ], page [ %d ], limit [ %d ]", keyWord, page, limit));
		
		result.put("count", studentDao.totalCountOfQueryStudentByKeyWord(keyWord));
		result.put("data", studentDao.queryStudentByKeyWord(keyWord, (page - 1) * limit, limit));
		return result;
	}
}
