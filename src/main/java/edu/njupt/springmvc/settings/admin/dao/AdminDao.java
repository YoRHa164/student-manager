package edu.njupt.springmvc.settings.admin.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import edu.njupt.springmvc.settings.login.bean.LoginBean;

@Repository
public interface AdminDao {
	/**
	 * 检查给定账号密码是否存在。 若存在则返回非null 否则返回null
	 * @param account
	 * @param password 密码应被MD5加密
	 * @return
	 */
	LoginBean checkAdminAccountOldPasswordCurrect(@Param("loginName") String account, @Param("password") String password);
	/**
	 * 修改相应密码
	 * @param account
	 * @param password 密码应被MD5加密
	 * @return
	 */
	int updateAdminPassword(@Param("loginName") String account, @Param("password") String password);
}
