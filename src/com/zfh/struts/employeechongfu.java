package com.zfh.struts;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.zfh.Po.Employee;
import com.zfh.SessionFactory.HibernateSessionFactory;

public class employeechongfu implements ServletRequestAware,
		ServletResponseAware {
	private HttpServletRequest request;
	private HttpServletResponse response;
	private String employeeid;
	private Session session;
	private Transaction transaction;
	private JSONObject json=new JSONObject();

	public String getEmployeeid() {
		return employeeid;
	}

	public void setEmployeeid(String employeeid) {
		this.employeeid = employeeid;
	}

	@Override
	public void setServletResponse(HttpServletResponse arg0) {
		// TODO Auto-generated method stub
		this.response = arg0;
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		this.request = arg0;
	}
	 public void fanhui(JSONObject json){
	    	try {
				this.response.setHeader("Access-Control-Allow-Origin", "*");
				this.response.setContentType("text/json;charset=utf-8");
				this.response.setCharacterEncoding("UTF-8");
				PrintWriter resulit=this.response.getWriter();
				resulit.print(json.toString());
				resulit.flush();
				resulit.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("employeechongfu.action    发生异常信息:IOException e");
				e.printStackTrace();
			}
	 }
	
	public void getjson(){
		
			try {
				System.out.println("employeechongfu.action      employee"+employeeid);
				if((employeeid==null)||(employeeid.length()==0)){
				System.out.println("employeechongfu.action      employee"+employeeid.length());
				json.put("chongfu", true);
				fanhui(json);
				}
				session=HibernateSessionFactory.getSession();
				transaction=session.beginTransaction();
				String hql="from Employee emp where emp.employeeId=?";
				Employee empl=new Employee();
				System.out.println("employeechongfu.action      这里执行了");
				empl=(Employee)session.createQuery(hql).setString(0, employeeid).uniqueResult();
				System.out.println("employeechongfu.action      查询结果"+empl);
				if((empl==null)||(empl.toString().length()==0)){
					json.put("chongfu", false);
				}else {
					json.put("chongfu", true);
				}
				fanhui(json);
			} catch (HibernateException e) {
				System.out.println("employeechongfu.action       发生异常信息:HibernateException e");
				json.put("chongfu", true);
				fanhui(json);
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (Exception e) {
				System.out.println("employeechongfu.action       发生异常信息:Exception e");
				json.put("chongfu", true);
				fanhui(json);
				// TODO: handle exception
				e.printStackTrace();
			}
		
		
	}

}
