package edu.njupt.springmvc.settings.login.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import edu.njupt.springmvc.settings.login.bean.LoginBean;
import edu.njupt.springmvc.settings.login.dao.LoginDao;
import edu.njupt.springmvc.settings.login.exception.LoginException;
import edu.njupt.springmvc.util.DataBaseUtil;

/**
 * 完成登录操作的服务
 * @author Administrator
 *
 */
@Service
public class LoginService {
	@Resource(type = LoginDao.class)
	private LoginDao loginDao;
	
	/**
	 * @param loginDao 要设置的 loginDao
	 */
	public void setLoginDao(LoginDao loginDao) {
		this.loginDao = loginDao;
	}
	/**
	 * 检查登录
	 * @param account 	要被检查的账号
	 * @param password	要被检查的密码（未被加密）
	 * @return
	 * @throws LoginException 当不能查找出结果时，即无法登录时，扔出该异常，请进行相应处理
	 */
	public LoginBean login(String account, String password) throws LoginException {
		LoginBean login = loginDao.login(account, DataBaseUtil.getMD5Digest(password));
		if(login == null) {
			throw new LoginException(String.format("账号: %s 账号密码错误，请联系管理员!", account));
		}
		return login;
	}
}
