package edu.njupt.springmvc.settings.admin.bean;

import java.io.Serializable;

import org.springframework.context.annotation.Scope;

import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Repository
@Scope("prototype")
public class AdminBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;              //主键 管理员序号
	private String loginName;       //账号名
	@JsonIgnore
	private String password;		//密码
	private String realName;        //真实姓名
	private String phone;           //电话号码
	@JsonProperty("authorGroup")
	private String displayName;     //管理员权限组
	private Integer authorGroupId;	//管理员权限所在分组
	private String createTime;      //注册时间
	/**
	 * @return id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id 要设置的 id
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return loginName
	 */
	public String getLoginName() {
		return loginName;
	}
	/**
	 * @param loginName 要设置的 loginName
	 */
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	/**
	 * @return password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password 要设置的 password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return realName
	 */
	public String getRealName() {
		return realName;
	}
	/**
	 * @param realName 要设置的 realName
	 */
	public void setRealName(String realName) {
		this.realName = realName;
	}
	/**
	 * @return phone
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * @param phone 要设置的 phone
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * @return displayName
	 */
	public String getDisplayName() {
		return displayName;
	}
	/**
	 * @param displayName 要设置的 displayName
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	/**
	 * @return authorGroupId
	 */
	public Integer getAuthorGroupId() {
		return authorGroupId;
	}
	/**
	 * @param authorGroupId 要设置的 authorGroupId
	 */
	public void setAuthorGroupId(Integer authorGroupId) {
		this.authorGroupId = authorGroupId;
	}
	/**
	 * @return createTime
	 */
	public String getCreateTime() {
		return createTime;
	}
	/**
	 * @param createTime 要设置的 createTime
	 */
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((authorGroupId == null) ? 0 : authorGroupId.hashCode());
		result = prime * result + ((createTime == null) ? 0 : createTime.hashCode());
		result = prime * result + ((displayName == null) ? 0 : displayName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((loginName == null) ? 0 : loginName.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result + ((realName == null) ? 0 : realName.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AdminBean other = (AdminBean) obj;
		if (authorGroupId == null) {
			if (other.authorGroupId != null)
				return false;
		} else if (!authorGroupId.equals(other.authorGroupId))
			return false;
		if (createTime == null) {
			if (other.createTime != null)
				return false;
		} else if (!createTime.equals(other.createTime))
			return false;
		if (displayName == null) {
			if (other.displayName != null)
				return false;
		} else if (!displayName.equals(other.displayName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (loginName == null) {
			if (other.loginName != null)
				return false;
		} else if (!loginName.equals(other.loginName))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		if (realName == null) {
			if (other.realName != null)
				return false;
		} else if (!realName.equals(other.realName))
			return false;
		return true;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AdminBean [id=");
		builder.append(id);
		builder.append(", loginName=");
		builder.append(loginName);
		builder.append(", password=");
		builder.append(password);
		builder.append(", realName=");
		builder.append(realName);
		builder.append(", phone=");
		builder.append(phone);
		builder.append(", displayName=");
		builder.append(displayName);
		builder.append(", authorGroupId=");
		builder.append(authorGroupId);
		builder.append(", createTime=");
		builder.append(createTime);
		builder.append("]");
		return builder.toString();
	}
	public AdminBean() {
		super();
	}
	
}
