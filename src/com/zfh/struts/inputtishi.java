package com.zfh.struts;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.jws.soap.SOAPBinding.Use;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.zfh.Po.User;
import com.zfh.SessionFactory.HibernateSessionFactory;

public class inputtishi implements ServletRequestAware, ServletResponseAware {
	private Session session;
	  private Transaction transaction;
	  private HttpServletRequest request;
	  private HttpServletResponse response;
	  private String shuru;
	  

	public String getShuru() {
		return shuru;
	}

	public void setShuru(String shuru) {
		this.shuru = shuru;
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
   
	 public void fanhui(JSONArray json){
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
				System.out.println("deleteemployee.action    发生异常信息:IOException e");
				e.printStackTrace();
			}
	 }
	 
	
	
	public void getjson(){
		
		 try {
			 JSONArray jsonarry=new JSONArray();
			 if((shuru=="")||(shuru.length()==0)){
				  JSONObject json=new JSONObject();
				  json.put("weikong", true);
				  jsonarry.add(json);
				  fanhui(jsonarry);
			 }else{
			session=HibernateSessionFactory.getSession();
			  transaction=session.beginTransaction();
			  System.out.println(shuru);
			  
			 String hql="from User user where user.name like '%"+shuru+"%'";
			 System.out.println(hql);
			 List<User> user=new ArrayList<User>();
			 
			 user=(List<User>)session.createQuery(hql).list();
			 
			 request.getSession().setAttribute("shishi",user);
			 
			  if((user==null)||(user.size()==0)){
				  JSONObject json=new JSONObject();
				  json.put("weikong", true);
				  jsonarry.add(json);
			  }else{
				  JSONObject json1=new JSONObject();
				  json1.put("weikong", false);
				  jsonarry.add(json1);
				  for(User u:user){
						 JSONObject json=new JSONObject();
					   json.put("name", u.getName());
					   json.put("personid",u.getPersonId());
					   json.put("sex", u.getSex());
					   jsonarry.add(json);
					 }
			  }
			  
			  transaction.commit();
			  HibernateSessionFactory.closeSession();
			  fanhui(jsonarry);}
		} catch (HibernateException e) {
			System.out.println("出现异常");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  
		
		 
	}
}
