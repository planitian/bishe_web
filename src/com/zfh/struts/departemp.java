package com.zfh.struts;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.zfh.Po.Employee;
import com.zfh.dao.adminDao;

public class departemp extends ActionSupport {
	private String departmentid;
    public String getDepartmentid() {
		return departmentid;
	}

	public void setDepartmentid(String departmentid) {
		this.departmentid = departmentid;
	}

	private String employeeid;
    private List<Employee> employees;
    
	public String getEmployeeid() {
		return employeeid;
	}

	public void setEmployeeid(String employeeid) {
		this.employeeid = employeeid;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	/**
	 * @return
	 */
	public String execute() {
		// TODO Auto-generated method stub
		try {
			if(departmentid!=null){
			System.out.println("departemp  departmentid 参数 为"+departmentid );
			}
			adminDao admin=new adminDao();
			employees=admin.getemployees(departmentid);
			System.out.println("departemp  employees:"+employees);
			return SUCCESS;
		} catch (Exception e) {
			System.out.println("departemp  出现异常 Exception e" );
			// TODO Auto-generated catch block
			e.printStackTrace();
			return SUCCESS;
		}
	}
}