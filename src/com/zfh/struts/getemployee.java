package com.zfh.struts;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.zfh.Po.Employee;
import com.zfh.SessionFactory.HibernateSessionFactory;

public class getemployee implements ServletRequestAware, ServletResponseAware {
	  private HttpServletRequest request;
	  private HttpServletResponse response;
	  private String department;
	  private Session session;
	  private Transaction transaction;
	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	@Override
	public void setServletResponse(HttpServletResponse arg0) {
		// TODO Auto-generated method stub
         this.response=arg0;
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
       this.request=arg0;
	}
   @Test
	public void getjson(){
		try {
		     
			System.out.println(">>>>>fangwen");
			System.out.println(department+"hhhhhhhh");
			
			session=HibernateSessionFactory.getSession();
			transaction=session.beginTransaction();
			String hql="select new Employee(emp.employeeId,emp.name) from Employee emp where emp.department=?";
			ArrayList<Employee> emp=new ArrayList<Employee>();
			emp=(ArrayList<Employee>)session.createQuery(hql).setString(0,department).list();
			transaction.commit();
			HibernateSessionFactory.closeSession();
			JSONObject json=new JSONObject();
			JSONArray jsonarr=new JSONArray();
			for(Employee emp1:emp){
			json.put("employeeid", emp1.getEmployeeId());
			json.put("name", emp1.getName());
			jsonarr.add(json);
			
			}
			//System.out.print(json.toString());
			this.response.setHeader("Access-Control-Allow-Origin", "*");
			this.response.setContentType("text/json;charset=utf-8");
			this.response.setCharacterEncoding("UTF-8");
			System.out.println(jsonarr.toString());
			 PrintWriter outs=this.response.getWriter();
			 outs.print(jsonarr.toString());
			 outs.flush();
			 outs.close();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
