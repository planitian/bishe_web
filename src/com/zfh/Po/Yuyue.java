package com.zfh.Po;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * Yuyue entity. @author MyEclipse Persistence Tools
 */

public class Yuyue implements java.io.Serializable {

	// Fields

	private String yuyuema;
	private User user;
	private Employee employee;
	private Timestamp time;
	private Timestamp createtime=new Timestamp(System.currentTimeMillis());
	private String xingqi;

	// Constructors

	public String getXingqi() {
		Timestamp ts=this.getTime();
		SimpleDateFormat s=new SimpleDateFormat("E");
		return s.format(ts);
	}

	public void setXingqi(String xingqi) {
		this.xingqi = xingqi;
	}

	/** default constructor */
	public Yuyue() {
	}

	/** full constructor */
	public Yuyue(User user, Employee employee, Timestamp time
			) {
		this.user = user;
		this.employee = employee;
		this.time = time;
		
	}

	// Property accessors

	public String getYuyuema() {
		return this.yuyuema;
	}

	public void setYuyuema(String yuyuema) {
		this.yuyuema = yuyuema;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Employee getEmployee() {
		return this.employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Timestamp getTime() {
		return this.time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public Timestamp getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}
	
	public String getxingqi(){
		Timestamp ts=this.getTime();
		SimpleDateFormat s=new SimpleDateFormat("E");
		return s.format(ts);
	}

}