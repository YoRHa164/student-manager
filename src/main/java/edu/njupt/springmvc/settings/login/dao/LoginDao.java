package edu.njupt.springmvc.settings.login.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import edu.njupt.springmvc.settings.login.bean.LoginBean;

/**
 * 完成登录操作底层数据库访问
 * @author Administrator
 *
 */
@Repository
@Scope("prototype")
public interface LoginDao {
	LoginBean login(@Param(value = "account") String account, @Param(value = "password") String password);
}
