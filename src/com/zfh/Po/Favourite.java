package com.zfh.Po;

/**
 * Favourite entity. @author MyEclipse Persistence Tools
 */

public class Favourite implements java.io.Serializable {

	// Fields

	private Integer id;
	private User user;
	private Employee employee;

	// Constructors

	/** default constructor */
	public Favourite() {
	}

	/** full constructor */
	public Favourite(User user, Employee employee) {
		this.user = user;
		this.employee = employee;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
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

}