package com.zfh.Po;

import java.util.HashSet;
import java.util.Set;

/**
 * Employee entity. @author MyEclipse Persistence Tools
 */

public class Employee implements java.io.Serializable {

	// Fields

	private String employeeId;
	private Department department;
	private String name;
	private String password;
	private String imgurl;
    private Empjian empjian;
	private Set yuyues = new HashSet(0);
	private Set favourites = new HashSet(0);

	// Constructors

	public Empjian getEmpjian() {
		return empjian;
	}

	public void setEmpjian(Empjian empjian) {
		this.empjian = empjian;
	}

	/** default constructor */
	public Employee() {
	}

	/** minimal constructor */
	public Employee(String employeeId) {
		this.employeeId = employeeId;
	}
	
	public Employee(String employeeId,String name ){
		this.employeeId = employeeId;
		this.name = name;
	}

	/** full constructor */
	public Employee(String employeeId, Department department, String name,
			String password, String imgurl, Set yuyues, Set favourites) {
		this.employeeId = employeeId;
		this.department = department;
		this.name = name;
		this.password = password;
		this.imgurl = imgurl;
		this.yuyues = yuyues;
		this.favourites = favourites;
	}

	// Property accessors

	public String getEmployeeId() {
		return this.employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public Department getDepartment() {
		return this.department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getImgurl() {
		return this.imgurl;
	}

	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}

	public Set getYuyues() {
		return this.yuyues;
	}

	public void setYuyues(Set yuyues) {
		this.yuyues = yuyues;
	}

	public Set getFavourites() {
		return this.favourites;
	}

	public void setFavourites(Set favourites) {
		this.favourites = favourites;
	}

}