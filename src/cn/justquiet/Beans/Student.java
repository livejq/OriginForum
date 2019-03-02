package cn.justquiet.Beans;

import java.io.Serializable;

public class Student implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String sid;
	private String spassword;
	private String sname;
	private String snickname;
	private int cid;
	private int slevel;
	private String sexperience;
	private int sexpvalue;//只要此值“变化”（意味着凡是加经验值的事件，都设置方法），就调用方法查询是否满级，若满级则重新设置
	private int svalue;
	private int sactive;
	
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getSpassword() {
		return spassword;
	}
	public void setSpassword(String spassword) {
		this.spassword = spassword;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getSnickname() {
		return snickname;
	}
	public void setSnickname(String snickname) {
		this.snickname = snickname;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public int getSlevel() {
		return slevel;
	}
	public void setSlevel(int slevel) {
		this.slevel = slevel;
	}
	public String getSexperience() {
		return sexperience;
	}
	public void setSexperience(String sexperience) {
		this.sexperience = sexperience;
	}
	public int getSexpvalue() {
		return sexpvalue;
	}
	public void setSexpvalue(int sexpvalue) {
		this.sexpvalue = sexpvalue;
	}
	public int getLvalue() {
		return svalue;
	}
	public void setLvalue(int svalue) {
		this.svalue = svalue;
	}
	public int getSactive() {
		return sactive;
	}
	public void setSactive(int sactive) {
		this.sactive = sactive;
	}
	
}
