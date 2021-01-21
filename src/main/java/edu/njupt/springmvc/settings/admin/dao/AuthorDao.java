package edu.njupt.springmvc.settings.admin.dao;

import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Repository
@Scope("prototype")
public interface AuthorDao {
	/**
	 * 查询权限信息 包含id和对应的displayName
	 * @return
	 */
	List<Map<String, Object>> queryAuthorGroupInfo();
}
