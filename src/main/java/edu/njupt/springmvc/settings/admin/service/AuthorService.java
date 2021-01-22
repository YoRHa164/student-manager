package edu.njupt.springmvc.settings.admin.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.njupt.springmvc.settings.admin.bean.AuthorGroupBean;
import edu.njupt.springmvc.settings.admin.dao.AuthorDao;
import edu.njupt.springmvc.settings.admin.exception.AuthorException;

@Service
@Scope("prototype")
public class AuthorService {
	@Resource(type = AuthorDao.class)
	private AuthorDao authorDao;
	private static final String LEGAL_NUMBER = "\\d+";
	/**
	 * 该方法查询各管理员对应的权限级别信息
	 * @return
	 */
	public List<AuthorGroupBean> queryAuthorGroupInfo(){
		return authorDao.queryAuthorGroupInfo(null);
	}
	@Transactional
	public List<Map<String, Object>> queryAuthorGroupInfoWithAccess(){
		List<AuthorGroupBean> list = queryAuthorGroupInfo();
		List<Map<String, Object>> result = new ArrayList<>();
		
		list.forEach(e -> {
			List<Map<String, Object>> accessInfo = queryAuthorGroupAccessByAuthorGroupId(e.getId());
			result.add(Map.of(
					"id", e.getId(), 
					"displayName", e.getDisplayName(), 
					"descs", e.getDescs(),
					"authorList", accessInfo));
		});
		
		return result;
	}
	/**
	 * 查询指定groupId下所有权限
	 * @param groupId
	 * @return
	 */
	@Transactional
	public List<Map<String, Object>> queryAuthorGroupAccessByAuthorGroupId(Integer groupId){
		return authorDao.queryAuthorGroupAccessByAuthorGroupId(groupId);
	}
	/**
	 * 通过Map进行权限组信息修改
	 * @param m
	 * @throws AuthorException
	 */
	@Transactional(rollbackFor = {AuthorException.class})
	public void updateAuthorGroupInfoByMap(Map<String, Object> m) throws AuthorException {
		int i = authorDao.updateAuthorGroupInfoByMap(m);
		
		if(i != 1) {
			throw new AuthorException("修改失败");
		}
		
	}
	/**
	 * 删除权限组。注意 删除权限组要一并删除权限组对应权限的所有记录
	 * @param gid
	 * @throws AuthorException
	 */
	@Transactional(rollbackFor = AuthorException.class)
	public void deleteAuthorGroupById(Integer gid) throws AuthorException {
		int i = authorDao.deleteAuthorGroupInfoById(gid);
		if(i != 1) {
			throw new AuthorException("删除失败");
		}
		int total = authorDao.totalCountOfAuthorOfId(gid);
		int count = authorDao.deleteAuthorGroupRelationById(gid);
		
		if(count != total) {
			throw new AuthorException("删除失败");
		}
	}
	/**
	 * 
	 * @param gid
	 * @param authorIds
	 * @throws AuthorException 
	 */
	@Transactional(rollbackFor = {AuthorException.class})
	public void deleteAuthorGroupAccess(Integer gid, String[] authorIds) throws AuthorException {
		if(!isLegalAccessParam(authorIds)) {
			throw new AuthorException("非法参数请求");
		}
		Integer[] ids = toIntegerArray(authorIds);
		
		int total = authorDao.totalCountOfAuthorOfId(gid);
		int i = authorDao.deleteAuthorGroupAccess(gid, ids);
		
		if(i > total) {
			throw new AuthorException("删除失败");
		}
	}
	/**
	 * 查询指定权限组不拥有的权限
	 * @param gid
	 * @return
	 */
	@Transactional
	public List<Map<String, Object>> queryGroupAuthorNotHaveByGid(Integer gid){
		return authorDao.queryGroupAuthorNotHaveByGid(gid);
	}
	/**
	 * 给指定权限组添加未拥有权限
	 * @param gid
	 * @param authorIds
	 * @throws AuthorException 
	 */
	@Transactional(rollbackFor = AuthorException.class)
	public void addAuthorGroupAccess(Integer gid, String[] authorIds) throws AuthorException {
		Integer[] is = toIntegerArray(authorIds);
		
		int notHave = authorDao.countOfGroupAuthorNotHaveByGid(gid);
		int i = authorDao.addAuthorGroupAccess(gid, is);
		if(i > notHave){
			throw new AuthorException("添加失败");
		}
	}
	
	private static Integer[] toIntegerArray(String[] arr) {
		if(arr == null) {
			return new Integer[] {};
		}
		Integer[] result = new Integer[arr.length];
		
		for (int i = 0; i < arr.length; i++) {
			System.out.println(arr[i]);
			result[i] = Integer.valueOf(arr[i]);
		}
		
		return result;
	}
	/**
	 * 查询所有权限信息
	 * @return
	 */
	public List<Map<String, Object>> queryAllAuthorAccess(){
		return authorDao.queryAllAuthorAccess();
	}
	/**
	 * 向权限组中，添加新权限组
	 * @param b 要添加的新权限组信息
	 * @param authorIds	新权限组中包含的权限
	 * @throws AuthorException 
	 */
	@Transactional(rollbackFor = AuthorException.class)
	public void insertNewAuthorGroup(AuthorGroupBean b, String[] authorIds) throws AuthorException {
		//验证请求合法性
		if(b.getGroupName() == null || "".equals(b.getGroupName())) {
			throw new AuthorException("非法请求格式");
		}
		
		int i = authorDao.insertNewAuthorGroupByAuthorGroupBean(b);
		if(i != 1) {
			throw new AuthorException("添加失败");
		}
		//通过groupName查询已经增加的权限组信息
		List<AuthorGroupBean> query = authorDao.queryAuthorGroupInfo(b.getGroupName());
		if(query.size() != 1) {
			throw new AuthorException("已经存在相同权限组，添加失败");
		}
		Integer gid = query.get(0).getId();
		
		//如果新增权限为0，则不进行添加操作
		if(isLegalAccessParam(authorIds)) {
			//为新增权限组添加权限
			int totalCount = authorDao.countOfGroupAuthorNotHaveByGid(gid);
			int insert = authorDao.addAuthorGroupAccess(gid, toIntegerArray(authorIds));
			if(insert > totalCount) {
				throw new AuthorException("添加权限失败");
			}
		}
	}
	/**
	 * 检查请求数组是否为合法全数字数组
	 * @param param
	 * @return
	 */
	private static boolean isLegalAccessParam(String[] param) {
		boolean flag = true;
		if(param == null || param.length == 0) {
			return false;
		}
		for (String str : param) {
			if(!str.matches(LEGAL_NUMBER)) {
				return false;
			}
		}
		
		return flag;
	}
}
