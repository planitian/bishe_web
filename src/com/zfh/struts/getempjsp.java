package com.zfh.struts;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.zfh.Po.Time;
import com.zfh.dao.adminDao;

public class getempjsp extends ActionSupport {
   private String employeeid;
   private List<Time> peremptime;
	public List<Time> getPeremptime() {
	return peremptime;
}
public void setPeremptime(List<Time> peremptime) {
	this.peremptime = peremptime;
}
	public String getEmployeeid() {
	return employeeid;
}
public void setEmployeeid(String employeeid) {
	this.employeeid = employeeid;
}
	/**
	 * @return
	 */
	public String execute() {
		
		System.out.println(employeeid);
		adminDao admin=new adminDao();
		peremptime=admin.getemptime(employeeid);
		return SUCCESS;
		
		
	}
}