package edu.njupt.springmvc.settings.admin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.njupt.springmvc.settings.admin.bean.AuthorGroupBean;
import edu.njupt.springmvc.settings.admin.service.AuthorService;

@Controller
@RequestMapping("/api/AuthorGroup")
public class AuthorGroupController {
	@Resource(type = AuthorService.class)
	private AuthorService authorService;
	
	/**
	 * 查询所有权限分组
	 * @return
	 */
	@ResponseBody
	@RequestMapping(path = "/all", method = RequestMethod.GET)
	public Map<String, Object> queryAuthorGroupInfo(){
		Map<String, Object> result = new HashMap<>();
		try {
			List<AuthorGroupBean> info = authorService.queryAuthorGroupInfo();
			
			result.put("data", info);
			result.put("code", 0);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("code", 1);
			result.put("msg", e.getMessage());
		}
		
		return result;
	}
	/**
	 * 请求所有权限组信息 <br>
	 * {
	 * 		"code":0/1,
	 * 		"data":[
	 * 					{
	 * 						"id":${authorGroupId},			//当前权限组id
	 * 						"displayName":${displayName},	//当前权限组对外公布的名字
	 * 						"descs":${descs}				//当前权限组描述
	 * 						"authorList":[					//当前权限组拥有的权限
	 * 										{
	 * 											"authorId":${authorId},
	 * 											"displayName":${displayName}	//当前权限的名字
	 * 										},
	 * 									    ...
	 * 									 ]
	 * 					},
	 * 					...
	 * 			   ]
	 * 	}
	 * @return
	 */
	@ResponseBody
	@RequestMapping(path = "/page", method = {RequestMethod.GET})
	public Map<String, Object> queryAuthorGroupInfoWithAccess(){
		Map<String, Object> result = new HashMap<>();
		
		try {
			List<Map<String,Object>> list = authorService.queryAuthorGroupInfoWithAccess();
			
			result.put("data", list);
			result.put("code", 0);
		} catch (Exception e) {
			result.put("code", 1);
			result.put("msg", e.getMessage());
		}
		
		return result;
	}
	/**
	 * 修改权限组信息
	 * @param id
	 * @param displayName
	 * @param descs
	 * @return
	 */
	@ResponseBody
	@RequestMapping(path = "/update", method = RequestMethod.POST)
	public Map<String, Object> updateAuthorGroupInfoByMap(Integer id, String displayName, String descs){
		Map<String, Object> temp = new HashMap<>(6);
		
		temp.put("id", id);
		temp.put("displayName", displayName);
		temp.put("descs", descs);
		
		try {
			authorService.updateAuthorGroupInfoByMap(temp);
			
			temp.clear();
			temp.put("code", 0);
			temp.put("msg", "修改成功");
		} catch (Exception e) {
			temp.clear();
			temp.put("code", 1);
			temp.put("msg", e.getMessage());
		}
		
		return temp;
	}
	/**
	 * 删除权限组
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(path = "/delete", method = RequestMethod.POST)
	public Map<String, Object> deleteAuthorGroupById(Integer id){
		Map<String, Object> result = new HashMap<>(6);
		
		try {
			authorService.deleteAuthorGroupById(id);
			
			result.put("code", 0);
			result.put("msg", "删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			result.put("code", 1);
			result.put("msg", e.getMessage());
		}
		
		return result;
	}
	/**
	 * 删除权限组一些权限
	 * @param groupId
	 * @param authorIds
	 * @return
	 */
	@ResponseBody
	@RequestMapping(path = "/deleteAuthors", method = RequestMethod.POST)
	public Map<String, Object> deleteAuthorGroupAccess(Integer groupId, String authorIds) {
		Map<String, Object> result = new HashMap<>(6);
		try {
			authorService.deleteAuthorGroupAccess(groupId, authorIds.split(" *, *"));
			
			result.put("code", 0);
			result.put("msg", "删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			result.put("code", 1);
			result.put("msg", e.getMessage());
		}
		return result;
	}
	
	/**
	 * 通过groupId查询相应权限
	 * @param gid
	 * @return
	 */
	@ResponseBody
	@RequestMapping(path = "/queryAuthorByGid", method = {RequestMethod.GET, RequestMethod.POST})
	public Map<String, Object> queryGroupAuthorByGroupId(@RequestParam("groupId") Integer gid){
		Map<String, Object> result = new HashMap<>();
		
		result.put("code", 0);
		result.put("data", authorService.queryAuthorGroupAccessByAuthorGroupId(gid));
		
		return result;
	}
	
	/**
	 * 需要响应格式
	 * 	{
	 * 		"code":0/1,
	 * 		"data":[
	 * 					{
	 * 						"authorId":${authorId},
	 * 						"displayName":${displayName}
	 * 					},...
	 * 			   ]
	 * 	}
	 * @param groupId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(path = "/queryhasNoAuthor", method = {RequestMethod.GET, RequestMethod.POST})
	public Map<String, Object> queryGroupAuthorNotHave(Integer groupId){
		Map<String, Object> result = new HashMap<>();
		
		try {
			List<Map<String, Object>> data = authorService.queryGroupAuthorNotHaveByGid(groupId);
			
			result.put("data", data);
			result.put("code", 0);
		} catch (Exception e) {
			result.put("code", 1);
			result.put("msg", e.getMessage());
		}
		
		return result;
	}
	/**
	 * 给指定权限组添加权限
	 * @param groupId
	 * @param authorIds
	 * @return
	 */
	@ResponseBody
	@RequestMapping(path = "/addAuthor", method = RequestMethod.POST)
	public Map<String, Object> addAuthorGroupAccess(Integer groupId, String authorIds){
		Map<String, Object> result = new HashMap<>(6);
		System.out.println(groupId);
		System.out.println(authorIds);
		try {
			authorService.addAuthorGroupAccess(groupId, authorIds.split(" *, *"));
			result.put("code", 0);
			result.put("msg", "添加成功");
		} catch (Exception e) {
			result.put("code", 1);
			result.put("msg", e.getMessage());
		}
		
		return result;
	}
	/**
	 * 查询所有权限组应该拥有的权限
	 * @return
	 */
	@ResponseBody
	@RequestMapping(path = "/allAccess", method = RequestMethod.GET)
	public Map<String, Object> queryAllAuthorAccess(){
		Map<String, Object> result = new HashMap<>(6);
		try {
			List<Map<String, Object>> data = authorService.queryAllAuthorAccess();
			
			result.put("code", 0);
			result.put("data", data);
		} catch (Exception e) {
			result.put("code", 1);
			result.put("msg", e.getMessage());
		}
		return result;
	}
	/**
	 * 向权限组中添加新权限组
	 * @param b 要添加的权限组信息
	 * @param authorIds	新权限组拥有的权限
	 * @return
	 */
	@ResponseBody
	@RequestMapping(path = "/add", method = RequestMethod.POST)
	public Map<String, Object> insertNewAuthorGroup(AuthorGroupBean b, String authorIds) {
		Map<String, Object> result = new HashMap<>(6);
		try {
			authorService.insertNewAuthorGroup(b, authorIds.split(" *, *"));
			
			result.put("code", 0);
			result.put("msg", "添加成功");
		} catch (Exception e) {
			e.printStackTrace();
			result.put("code", 1);
			result.put("msg", e.getMessage());
		}
		return result;
	}
}
