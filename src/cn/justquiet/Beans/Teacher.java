package cn.justquiet.Beans;

import java.io.Serializable;

public class Teacher implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int tid;
	private String taccount;
	private String tpassword;
	private String tname;
	private String tnickname;
	private String tlevel;
	private String texperience;
	private int texpvalue;//只要此值“变化”（意味着凡是加经验值的事件，都设置方法），就调用方法查询是否可获得头衔称号，若满级则重新设置
	private int tvalue;
	private int tactive;
	
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	public String getTaccount() {
		return taccount;
	}
	public void setTaccount(String taccount) {
		this.taccount = taccount;
	}
	public String getTpassword() {
		return tpassword;
	}
	public void setTpassword(String tpassword) {
		this.tpassword = tpassword;
	}
	public String getTname() {
		return tname;
	}
	public void setTname(String tname) {
		this.tname = tname;
	}
	public String getTnickname() {
		return tnickname;
	}
	public void setTnickname(String tnickname) {
		this.tnickname = tnickname;
	}
	public String getTlevel() {
		return tlevel;
	}
	public void setTlevel(String tlevel) {
		this.tlevel = tlevel;
	}
	public String getTexperience() {
		return texperience;
	}
	public void setTexperience(String texperience) {
		this.texperience = texperience;
	}
	public int getTexpvalue() {
		return texpvalue;
	}
	public void setTexpvalue(int texpvalue) {
		this.texpvalue = texpvalue;
	}
	public int getLvalue() {
		return tvalue;
	}
	public void setLvalue(int lvalue) {
		this.tvalue = lvalue;
	}
	public int getTactive() {
		return tactive;
	}
	public void setTactive(int tactive) {
		this.tactive = tactive;
	}
}
