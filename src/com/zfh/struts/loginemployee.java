package com.zfh.struts;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.opensymphony.xwork2.ActionSupport;
import com.zfh.Po.Employee;
import com.zfh.SessionFactory.HibernateSessionFactory;

public class loginemployee extends ActionSupport {
   private String EmployeeID;
   private String password;
   private Employee employee;
	public String getEmployeeID() {
	return EmployeeID;
}
public void setEmployeeID(String employeeID) {
	EmployeeID = employeeID;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
	/**
	 * @return
	 */
	public String execute() {
		// TODO Auto-generated method stub
		Session session=HibernateSessionFactory.getSession();
		Transaction transaction=session.beginTransaction();
		String hql="from Employee employee  where employee.employeeId=? and employee.password=?";
		employee=(Employee)session.createQuery(hql).setString(0, EmployeeID).setString(1, password).uniqueResult();
		
		transaction.commit();
		HibernateSessionFactory.closeSession();
		
		if(employee==null){
			addActionError("用户名或密码错误");	
			return ERROR;
		}else{
		HttpSession request=ServletActionContext.getRequest().getSession();
		request.setAttribute("emplo", employee.getEmployeeId());
		request.setAttribute("empname", employee.getName());
		request.setAttribute("empimgurl",employee.getImgurl());
		return SUCCESS;
		}
	}
	
}