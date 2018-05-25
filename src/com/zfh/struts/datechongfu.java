package com.zfh.struts;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.opensymphony.xwork2.ActionSupport;
import com.zfh.Po.Employee;
import com.zfh.Po.Time;
import com.zfh.SessionFactory.HibernateSessionFactory;

public class datechongfu  extends ActionSupport  implements ServletRequestAware, ServletResponseAware {
	private HttpServletRequest request;
	private HttpServletResponse response;
	private String date;
	private String employeeid;
	private Session session;
	private Transaction transaction;

	public String getEmployeeid() {
		return employeeid;
	}

	public void setEmployeeid(String employeeid) {
		this.employeeid = employeeid;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
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

	public void getjson() {
		try {
			System.out.println(this.employeeid);
			System.out.println(this.date);
			session = HibernateSessionFactory.getSession();
			transaction = session.beginTransaction();
			String hql = "from Time emp where emp.employee=?";
			List<Time> emps = new ArrayList<Time>();
			emps = (List<Time>) session.createQuery(hql).setString(0, employeeid)
					.list();
			System.out.println(emps.size()+"hhhhhhh");
			System.out.println(emps.isEmpty());
			JSONObject json = new JSONObject();
			if (emps.isEmpty()) {
				json.put("chongfu", false);
			} else {
				System.out.println("进入for循环");
				for (Time ti : emps) {
					System.out.println("amstat:"+ti.getAmstart() == null);
					if (ti.getAmstart() == null) {
						System.out.println("进入amstat");
						Timestamp jilu = ti.getPmstart();
						SimpleDateFormat ts = new SimpleDateFormat("yyyy-MM-dd");
						String jilu1 = ts.format(jilu);
						if (jilu1.equals(date)) {
							json.put("chongfu", true);
							break;
						}else{
							json.put("chongfu", false);
						}
					}else{
						System.out.println("进入pmstart");
						Timestamp jilu = ti.getAmstart();
						SimpleDateFormat ts = new SimpleDateFormat("yyyy-MM-dd");
						String jilu1 = ts.format(jilu);
						System.out.println(jilu1);
						System.out.println(jilu1.equals(date));
						if (jilu1.equals(date)) {
							json.put("chongfu", true);
							break;
						}else{
							json.put("chongfu", false);
						}
					}
				}
			}
			System.out.println(json.toString());
			this.response.setHeader("Access-Control-Allow-Origin", "*");
			this.response.setContentType("text/json;charset=utf-8");
			this.response.setCharacterEncoding("UTF-8");
			PrintWriter resulit=this.response.getWriter();
			resulit.print(json.toString());
			resulit.flush();
			resulit.close();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public String test(){
		return SUCCESS;
	}

}
