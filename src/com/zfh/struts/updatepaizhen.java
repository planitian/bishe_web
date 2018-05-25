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

import com.zfh.SessionFactory.HibernateSessionFactory;
import com.zfh.dao.adminDao;

public class updatepaizhen implements ServletRequestAware, ServletResponseAware {
	private HttpServletRequest request;
	private HttpServletResponse response;
	private Session session;
	private Transaction transaction;
	private Integer id;
	private String employeename;
	private String date;
	private String amstart;
	private String amend;
	private String pmstart;
	private String pmend;
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmployeename() {
		return employeename;
	}

	public void setEmployeename(String employeename) {
		this.employeename = employeename;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getAmstart() {
		return amstart;
	}

	public void setAmstart(String amstart) {
		this.amstart = amstart;
	}

	public String getAmend() {
		return amend;
	}

	public void setAmend(String amend) {
		this.amend = amend;
	}

	public String getPmstart() {
		return pmstart;
	}

	public void setPmstart(String pmstart) {
		this.pmstart = pmstart;
	}

	public String getPmend() {
		return pmend;
	}

	public void setPmend(String pmend) {
		this.pmend = pmend;
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
			session = HibernateSessionFactory.getSession();
			transaction = session.beginTransaction();
			adminDao admin = new adminDao();
			Boolean que = false;
			que = admin.updatepaizhen(id, employeename, date, amstart, amend,
					pmstart, pmend);
			JSONObject json = new JSONObject();
			if (que) {
				json.put("updatasucc", que);
			} else {
				json.put("updatasucc", false);
			}
			this.response.setHeader("Access-Control-Allow-Origin", "*");
			this.response.setContentType("text/json;charset=utf-8");
			this.response.setCharacterEncoding("UTF-8");
			System.out.println("json"+json.toString());
			PrintWriter outs = this.response.getWriter();
			outs.print(json.toString());
			outs.flush();
			outs.close();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			System.out.println("发生异常 请检查   updatepaizhen");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("发生异常 请检查   updatepaizhen");
			e.printStackTrace();
		}

	}
}
