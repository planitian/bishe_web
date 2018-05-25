package com.zfh.struts;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.opensymphony.xwork2.ActionSupport;
import com.zfh.Po.Admin;
import com.zfh.Po.Department;
import com.zfh.Po.Employee;
import com.zfh.SessionFactory.HibernateSessionFactory;

public class loginAdmin extends ActionSupport {
    private String username;
    private String password;
    private Admin admin;
    private List<Employee> emp=new ArrayList<Employee>();
    private List<Department> depar=new ArrayList<Department>();
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
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
		String hql="from Admin employee  where employee.name=? and employee.password=?";
		admin=(Admin)session.createQuery(hql).setString(0, username).setString(1, password).uniqueResult();
		String hql1="from Employee  ";
		emp=(List<Employee>)session.createQuery(hql1).list();
		String hql2="from Department";
		depar=(List<Department>)session.createQuery(hql2).list();
		transaction.commit();
		HibernateSessionFactory.closeSession();
		if(admin==null){
			addActionError("管理员用户名或密码错误");	
			return ERROR;
		}else{
			HttpSession request=ServletActionContext.getRequest().getSession();
			request.setAttribute("admin", admin.getName());
			request.setAttribute("allemployee", emp);
			request.setAttribute("alldepartment", depar);
			request.setAttribute("first", false);
			return SUCCESS;
		}
			
		
	}
}