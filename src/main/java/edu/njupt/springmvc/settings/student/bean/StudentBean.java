package edu.njupt.springmvc.settings.student.bean;

import java.io.Serializable;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
/**
 * 学生bean
 * @author Administrator
 *
 */
@Component
@Scope("prototype")
public class StudentBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;				//主键 编号
	private String realName;		//真实姓名
	private String gender;			//性别
	private Integer age;			//年龄
	private String indentifyNo;		//身份证号
	private String city;			//所在城市
	private String address;			//详细地址
	private String phone;			//电话号码
	private String regTime;			//注册时间
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
	 * @return gender
	 */
	public String getGender() {
		return gender;
	}
	/**
	 * @param gender 要设置的 gender
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}
	/**
	 * @return age
	 */
	public Integer getAge() {
		return age;
	}
	/**
	 * @param age 要设置的 age
	 */
	public void setAge(Integer age) {
		this.age = age;
	}
	/**
	 * @return indentifyNo
	 */
	public String getIndentifyNo() {
		return indentifyNo;
	}
	/**
	 * @param indentifyNo 要设置的 indentifyNo
	 */
	public void setIndentifyNo(String indentifyNo) {
		this.indentifyNo = indentifyNo;
	}
	/**
	 * @return city
	 */
	public String getCity() {
		return city;
	}
	/**
	 * @param city 要设置的 city
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * @return address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address 要设置的 address
	 */
	public void setAddress(String address) {
		this.address = address;
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
	 * @return regTime
	 */
	public String getRegTime() {
		return regTime;
	}
	/**
	 * @param regTime 要设置的 regTime
	 */
	public void setRegTime(String regTime) {
		this.regTime = regTime;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((age == null) ? 0 : age.hashCode());
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((gender == null) ? 0 : gender.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((indentifyNo == null) ? 0 : indentifyNo.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result + ((realName == null) ? 0 : realName.hashCode());
		result = prime * result + ((regTime == null) ? 0 : regTime.hashCode());
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
		StudentBean other = (StudentBean) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (age == null) {
			if (other.age != null)
				return false;
		} else if (!age.equals(other.age))
			return false;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (gender == null) {
			if (other.gender != null)
				return false;
		} else if (!gender.equals(other.gender))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (indentifyNo == null) {
			if (other.indentifyNo != null)
				return false;
		} else if (!indentifyNo.equals(other.indentifyNo))
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
		if (regTime == null) {
			if (other.regTime != null)
				return false;
		} else if (!regTime.equals(other.regTime))
			return false;
		return true;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("StudentBean [id=");
		builder.append(id);
		builder.append(", realName=");
		builder.append(realName);
		builder.append(", gender=");
		builder.append(gender);
		builder.append(", age=");
		builder.append(age);
		builder.append(", indentifyNo=");
		builder.append(indentifyNo);
		builder.append(", city=");
		builder.append(city);
		builder.append(", address=");
		builder.append(address);
		builder.append(", phone=");
		builder.append(phone);
		builder.append(", regTime=");
		builder.append(regTime);
		builder.append("]");
		return builder.toString();
	}
	public StudentBean() {
		super();
	}
	
	
}
