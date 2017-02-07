package entity;

import java.util.Date;

public class Celebrity {
	
	private int celebrityId;
	private String nameZh;
	private String nameEn;
	private int sex;
	private Date birthday;
	
	public int getCelebrityId() {
		return celebrityId;
	}
	public void setCelebrityId(int celebrityId) {
		this.celebrityId = celebrityId;
	}
	public String getNameZh() {
		return nameZh;
	}
	public void setNameZh(String nameZh) {
		this.nameZh = nameZh;
	}
	public String getNameEn() {
		return nameEn;
	}
	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	

}
