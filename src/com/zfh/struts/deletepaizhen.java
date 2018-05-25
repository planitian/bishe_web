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

import com.zfh.Po.Time;
import com.zfh.SessionFactory.HibernateSessionFactory;

public class deletepaizhen implements ServletRequestAware, ServletResponseAware {
	  private Session session;
	  private Transaction transaction;
	  private HttpServletRequest request;
	  private HttpServletResponse response;
	  private String id;
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
	public void outstream(JSONObject json){
		try {
			this.response.setHeader("Access-Control-Allow-Origin", "*");
			  this.response.setContentType("text/json;charset=utf-8");
			  this.response.setCharacterEncoding("UTF-8");
			  PrintWriter out=this.response.getWriter();
			  out.print(json.toString());
			  out.flush();
			  out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
  public void getjson(){
	  System.out.println("Ω¯»Î¡Àdeletepaizhen.action");
	  try {
		System.out.println(id);
		Integer dd=Integer.valueOf(id);
		System.out.println(dd);
		  session=HibernateSessionFactory.getSession();
		  transaction=session.beginTransaction();
		  Time delepaizhen=(Time) session.get(Time.class, dd);
		  session.delete(delepaizhen);
		  transaction.commit();
		  HibernateSessionFactory.closeSession();
		  JSONObject json=new JSONObject();
		  json.put("dele", true);
		  outstream(json);
	} catch (HibernateException e) {
		// TODO Auto-generated catch block
		 JSONObject json=new JSONObject();
		  json.put("dele", false);
		  outstream(json);
		e.printStackTrace();
		
	}
	  
	  
  }
}
