package edu.njupt.springmvc.settings.admin.bean;

import java.io.Serializable;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Component
@Scope("prototype")
public class AuthorGroupBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	@JsonIgnore
	private String groupName;
	private String displayName;
	private String descs;
	/**
	 * @return id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id 要设置的 id
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return groupName
	 */
	public String getGroupName() {
		return groupName;
	}
	/**
	 * @param groupName 要设置的 groupName
	 */
	public void setGroupName(String groupName) {
		this.groupName = groupName;
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
	 * @return descs
	 */
	public String getDescs() {
		return descs;
	}
	/**
	 * @param descs 要设置的 descs
	 */
	public void setDescs(String descs) {
		this.descs = descs;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descs == null) ? 0 : descs.hashCode());
		result = prime * result + ((displayName == null) ? 0 : displayName.hashCode());
		result = prime * result + ((groupName == null) ? 0 : groupName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		AuthorGroupBean other = (AuthorGroupBean) obj;
		if (descs == null) {
			if (other.descs != null)
				return false;
		} else if (!descs.equals(other.descs))
			return false;
		if (displayName == null) {
			if (other.displayName != null)
				return false;
		} else if (!displayName.equals(other.displayName))
			return false;
		if (groupName == null) {
			if (other.groupName != null)
				return false;
		} else if (!groupName.equals(other.groupName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AuthorGroupBean [id=");
		builder.append(id);
		builder.append(", groupName=");
		builder.append(groupName);
		builder.append(", displayName=");
		builder.append(displayName);
		builder.append(", descs=");
		builder.append(descs);
		builder.append("]");
		return builder.toString();
	}
	public AuthorGroupBean() {
		super();
	}
	
	
}
