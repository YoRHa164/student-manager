package edu.njupt.springmvc.settings.admin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import edu.njupt.springmvc.settings.admin.bean.AdminBean;
import edu.njupt.springmvc.settings.login.bean.LoginBean;

@Repository
public interface AdminDao {
	/**
	 * 检查给定账号密码是否存在。 若存在则返回非null 否则返回null。 <br>
	 * 允许password为null值，当为null时，只检查账号是否存在，若账号也为null，则扔出异常
	 * 
	 * @param account
	 * @param password 密码应被MD5加密
	 * @return
	 */
	LoginBean checkAdminAccountExists(@Param("loginName") String account, @Param("password") String password);
	/**
	 * 修改相应密码
	 * @param account
	 * @param password 密码应被MD5加密
	 * @return
	 */
	int updateAdminPassword(@Param("loginName") String account, @Param("password") String password);
	
	/**
	 * 分页查询所有管理员名单
	 * @param startIndex
	 * @param limit
	 * @return
	 */
	List<AdminBean> queryCompleteAdminList(@Param("startIndex") Integer startIndex, @Param("limit") Integer limit);
	/**
	 * 查询管理员数量
	 * @return
	 */
	int totalCountOfCompleteAdminList();
	
	/**
	 * 通过主键删除管理员
	 * @param id
	 * @return
	 */
	int deleteAdminById(String id);
	/**
	 * 使用AdminBean方式修改管理员信息
	 * @param b
	 * @return
	 */
	int updateAdminByAdminBean(AdminBean b);
	/**
	 * 使用AdminBean方式增加管理员信息
	 * @param b
	 * @return
	 */
	int insertAdminByAdminBean(AdminBean b);
	/**
	 * 检查当前权限组是否有权限进行url请求
	 * @param gid
	 * @param url
	 * @return
	 */
	int checkAccessExistsByGroupId(@Param("gid") Integer gid, @Param("url") String url);
}
