package edu.njupt.springmvc.settings.admin.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.njupt.springmvc.settings.admin.bean.AdminBean;
import edu.njupt.springmvc.settings.admin.dao.AdminDao;
import edu.njupt.springmvc.settings.admin.exception.AdminException;
import edu.njupt.springmvc.settings.login.bean.LoginBean;
import edu.njupt.springmvc.util.DataBaseUtil;

@Service
@Scope("prototype")
public class AdminService {
	@Resource(type = AdminDao.class)
	private AdminDao adminDao;
	
	
	public void setAdminDao(AdminDao adminDao) {
		this.adminDao = adminDao;
	}
	/**
	 * 通过账号密码修改管理员密码
	 * @param account
	 * @param oldPassword 未被MD5加密过的密码
	 * @return
	 * @throws AdminException 
	 */
	@Transactional(rollbackFor = {AdminException.class})
	public void updateAdminPassword(String account, String oldPassword, String newPassword) throws AdminException{
		oldPassword = DataBaseUtil.getMD5Digest(oldPassword);
		LoginBean admin = null;
		//检查原密码是否输入正确
		admin = adminDao.checkAdminAccountExists(account, oldPassword);
		if(admin == null) {
			throw new AdminException("原密码错误");
		}
		//修改新密码
		newPassword = DataBaseUtil.getMD5Digest(newPassword);
		int i = adminDao.updateAdminPassword(account, newPassword);
		if(i != 1) {
			throw new AdminException("出现异常，请重新尝试");
		}
	}
	/**
	 * 
	 * @param page
	 * @param limit
	 * @return
	 */
	@Transactional
	public Map<String, Object> queryCompleteAdminList(Integer page, Integer limit){
		Map<String, Object> result = new HashMap<>(6);
		page = page == null? 1:((page < 1)? 1: page);
		limit= limit == null? 0: ((limit < 0)? 0: limit);
		
		List<AdminBean> list = adminDao.queryCompleteAdminList((page - 1) * limit, limit);
		Integer totalCount = adminDao.totalCountOfCompleteAdminList();
		
		result.put("data", list);
		result.put("count", totalCount);
		
		return result;
	}
	@Transactional(rollbackFor = {AdminException.class})
	public void deleteAdminById(String id) throws AdminException{
		int i = adminDao.deleteAdminById(id);
		if(i != 1) {
			throw new AdminException("删除失败");
		}
	}
	/**
	 * 通过AdminBean 修改管理员信息 包括loginName、password、phone、realName、authorGroupId <br>
	 * 该方法若修改密码选项，则需要提供ensuPassowrd参数，否则无法通过。若不修改passwrod，则无需提供 <br>
	 * <b> 判断依据为是否为 null 值 </b> <br>
	 * 该方法密码皆为 <b>明文密码</b> ，会自动转换为MD5信息摘要
	 * @param b
	 * @param ensuPassword
	 * @throws AdminException
	 */
	@Transactional(rollbackFor = {AdminException.class})
	public void updateAdminByAdminBean(AdminBean b, String ensuPassword) throws AdminException {
		if(b.getPassword() != null) {
			if(b.getPassword().equals(ensuPassword)) {
				b.setPassword(DataBaseUtil.getMD5Digest(b.getPassword()));
			}else {
				throw new AdminException("修改失败");
			}
		}
		
		int i = adminDao.updateAdminByAdminBean(b);
		if(i != 1) {
			throw new AdminException("修改失败");
		}
	}
	/**
	 * 
	 * @param b
	 * @param ensuPassword
	 * @throws AdminException
	 */
	@Transactional(rollbackFor = {AdminException.class})
	public void insertAdminByAdminBean(AdminBean b, String ensuPassword) throws AdminException {
		if(
				b == null || 
				b.getPassword() == null || 
				"".equals(b.getPassword()) || 
				b.getLoginName() == null || 
				"".equals(b.getLoginName())) 
		{
			throw new AdminException("账号或密码非法");
		}else if(!b.getPassword().equals(ensuPassword)) {
			throw new AdminException("两次密码输入不一致");
		}else if(adminDao.checkAdminAccountExists(b.getLoginName(), null) != null) {
			throw new AdminException("该账号已经存在");
		}else{
			//进行MD5信息摘要
			b.setPassword(DataBaseUtil.getMD5Digest(b.getPassword()));
			//打上默认时间戳
			b.setCreateTime(DataBaseUtil.currentTimeByDefault());
		}
		
		int i = adminDao.insertAdminByAdminBean(b);
		if(i != 1) {
			throw new AdminException("添加失败");
		}
	}
	/**
	 * 检查指定权限组是否有指定url权限
	 * @param gid
	 * @param url
	 * @return 如果拥有权限，则返回true，否则反复false
	 */
	public boolean checkAccessExistsByGroupId(Integer gid, String url) {
		return adminDao.checkAccessExistsByGroupId(gid, url) != 0;
	}
}
