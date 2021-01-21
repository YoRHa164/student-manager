package edu.njupt.springmvc.settings.admin.service;

import java.util.Map;

import javax.annotation.Resource;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import edu.njupt.springmvc.settings.admin.dao.AuthorDao;

@Service
@Scope("prototype")
public class AuthorService {
	@Resource(type = AuthorDao.class)
	private AuthorDao authorDao;
	
	/**
	 * 该方法查询各管理员对应的权限级别信息
	 * @return
	 */
	public List<Map<String, Object>> queryAuthroInfo(){
		return authorDao.queryAuthorGroupInfo();
	}
}
