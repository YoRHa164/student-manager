package edu.njupt.springmvc.settings.admin.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import edu.njupt.springmvc.settings.admin.bean.AuthorGroupBean;

@Repository
@Scope("prototype")
public interface AuthorDao {
	/**
	 * 按照groupName查询权限组信息 当groupName 为null时，查询所有权限组信息
	 * @return
	 */
	List<AuthorGroupBean> queryAuthorGroupInfo(@Param("groupName") String groupName);
	/**
	 * 通过GuoupId查询所拥有的权限
	 * @param gid
	 * @return
	 */
	List<Map<String, Object>> queryAuthorGroupAccessByAuthorGroupId(Integer gid);
	/**
	 * 
	 * @param m
	 * @return
	 */
	int updateAuthorGroupInfoByMap(Map<String, Object> m);
	/**
	 * 
	 * @param id
	 * @return
	 */
	int deleteAuthorGroupInfoById(Integer id);
	/**
	 * 
	 * @param gid
	 * @return
	 */
	int deleteAuthorGroupRelationById(Integer gid);
	/**
	 * 删除指定权限组指定权限
	 * @param gid
	 * @param authorIds
	 * @return
	 */
	int deleteAuthorGroupAccess(@Param("gid") Integer gid, @Param("authorIds") Integer[] authorIds);
	/**
	 * 查询指定权限组权限数量
	 * @param gid
	 * @return
	 */
	int totalCountOfAuthorOfId(Integer gid);
	
	/**
	 * 查询相应权限组不拥有的权限
	 * @param gid
	 * @return
	 */
	List<Map<String, Object>> queryGroupAuthorNotHaveByGid(Integer gid);
	/**
	 * 查询不拥有权限的数量
	 * @param gid
	 * @return
	 */
	int countOfGroupAuthorNotHaveByGid(Integer gid);
	/**
	 * 给指定权限组添加未拥有的权限
	 * @param gid
	 * @param authorIds
	 * @return
	 */
	int addAuthorGroupAccess(@Param("gid") Integer gid, @Param("authorIds") Integer[] authorIds);
	/**
	 * 查询所有权限信息 包括displayName和authorId
	 * @return
	 */
	List<Map<String, Object>> queryAllAuthorAccess();
	/**
	 * 通过AuthorGroupBean方式添加新权限组
	 * @param b
	 * @return
	 */
	int insertNewAuthorGroupByAuthorGroupBean(AuthorGroupBean b);
}
