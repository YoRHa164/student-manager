package edu.njupt.springmvc.settings.student.bean;

import java.io.Serializable;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

@Component
@Scope( value = "prototype")
public class StudentScoreBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;	                //主键 id
	@JsonProperty("realName")
	private String name;                //真实姓名
	private Integer python;             //python成绩
	private Integer linux;              //linux成绩
	private Integer sql;                //sql成绩
	private Integer java;               //java成绩
	@JsonProperty("sum")
	private Integer total;              //总和成绩
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
	 * @return name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name 要设置的 name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return python
	 */
	public Integer getPython() {
		return python;
	}
	/**
	 * @param python 要设置的 python
	 */
	public void setPython(Integer python) {
		this.python = python;
	}
	/**
	 * @return linux
	 */
	public Integer getLinux() {
		return linux;
	}
	/**
	 * @param linux 要设置的 linux
	 */
	public void setLinux(Integer linux) {
		this.linux = linux;
	}
	/**
	 * @return sql
	 */
	public Integer getSql() {
		return sql;
	}
	/**
	 * @param sql 要设置的 sql
	 */
	public void setSql(Integer sql) {
		this.sql = sql;
	}
	/**
	 * @return java
	 */
	public Integer getJava() {
		return java;
	}
	/**
	 * @param java 要设置的 java
	 */
	public void setJava(Integer java) {
		this.java = java;
	}
	/**
	 * @return total
	 */
	public Integer getTotal() {
		return total;
	}
	/**
	 * @param total 要设置的 total
	 */
	public void setTotal(Integer total) {
		this.total = total;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((java == null) ? 0 : java.hashCode());
		result = prime * result + ((linux == null) ? 0 : linux.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((python == null) ? 0 : python.hashCode());
		result = prime * result + ((sql == null) ? 0 : sql.hashCode());
		result = prime * result + ((total == null) ? 0 : total.hashCode());
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
		StudentScoreBean other = (StudentScoreBean) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (java == null) {
			if (other.java != null)
				return false;
		} else if (!java.equals(other.java))
			return false;
		if (linux == null) {
			if (other.linux != null)
				return false;
		} else if (!linux.equals(other.linux))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (python == null) {
			if (other.python != null)
				return false;
		} else if (!python.equals(other.python))
			return false;
		if (sql == null) {
			if (other.sql != null)
				return false;
		} else if (!sql.equals(other.sql))
			return false;
		if (total == null) {
			if (other.total != null)
				return false;
		} else if (!total.equals(other.total))
			return false;
		return true;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("StudentScoreBean [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", python=");
		builder.append(python);
		builder.append(", linux=");
		builder.append(linux);
		builder.append(", sql=");
		builder.append(sql);
		builder.append(", java=");
		builder.append(java);
		builder.append(", total=");
		builder.append(total);
		builder.append("]");
		return builder.toString();
	}
	public StudentScoreBean() {
		super();
	}
	
	
}
