package edu.njupt.springmvc.settings.login.bean;

import java.io.Serializable;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class LoginBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;					//主键
	private String account;				//账号
	private String password;			//密码（MD5加密）
	private String tele;				//电话
	private String realName;			//账号姓名
	private Integer authorGroupId;		//权限编号
	private String createTime;			//创建时间
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
	 * @return account
	 */
	public String getAccount() {
		return account;
	}
	/**
	 * @param account 要设置的 account
	 */
	public void setAccount(String account) {
		this.account = account;
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
	 * @return tele
	 */
	public String getTele() {
		return tele;
	}
	/**
	 * @param tele 要设置的 tele
	 */
	public void setTele(String tele) {
		this.tele = tele;
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
		result = prime * result + ((account == null) ? 0 : account.hashCode());
		result = prime * result + ((authorGroupId == null) ? 0 : authorGroupId.hashCode());
		result = prime * result + ((createTime == null) ? 0 : createTime.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((realName == null) ? 0 : realName.hashCode());
		result = prime * result + ((tele == null) ? 0 : tele.hashCode());
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
		LoginBean other = (LoginBean) obj;
		if (account == null) {
			if (other.account != null)
				return false;
		} else if (!account.equals(other.account))
			return false;
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
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (realName == null) {
			if (other.realName != null)
				return false;
		} else if (!realName.equals(other.realName))
			return false;
		if (tele == null) {
			if (other.tele != null)
				return false;
		} else if (!tele.equals(other.tele))
			return false;
		return true;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("LoginBean [id=");
		builder.append(id);
		builder.append(", account=");
		builder.append(account);
		builder.append(", password=");
		builder.append(password);
		builder.append(", tele=");
		builder.append(tele);
		builder.append(", realName=");
		builder.append(realName);
		builder.append(", authorGroupId=");
		builder.append(authorGroupId);
		builder.append(", createTime=");
		builder.append(createTime);
		builder.append("]");
		return builder.toString();
	}
	public LoginBean() {
		super();
	}
	
	
}
