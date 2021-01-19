package edu.njupt.springmvc.settings.admin.service;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.njupt.springmvc.settings.admin.AdminException;
import edu.njupt.springmvc.settings.admin.dao.AdminDao;
import edu.njupt.springmvc.settings.login.bean.LoginBean;
import edu.njupt.springmvc.util.DataBaseUtil;

@Service
@Scope("prototype")
public class AdminService {
	@Resource(type = AdminDao.class)
	private AdminDao adminDao;
	
	
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
		admin = adminDao.checkAdminAccountOldPasswordCurrect(account, oldPassword);
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
}
