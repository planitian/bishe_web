package com.zfh.struts;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.zfh.Po.Employee;
import com.zfh.SessionFactory.HibernateSessionFactory;

public class deleteemployee implements ServletRequestAware,
		ServletResponseAware {
	private Session session;
	  private Transaction transaction;
	  private HttpServletRequest request;
	  private HttpServletResponse response;
      private String employeeid;
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
		this.response=arg0;
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		this.request=arg0;
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
				System.out.println("deleteemployee.action    发生异常信息:IOException e");
				e.printStackTrace();
			}
	 }
	 
	 public void getjson(){
		   try {
			if((employeeid==null)||(employeeid.length()==0)){
				   System.out.println("deleteemployee.action   employeeid   没传进 参数为空");
				   json.put("deleteok",false);
					fanhui(json);
			   }
			 System.out.println("employeechongfu.action      employeeid:"+employeeid);
			 
			 session=HibernateSessionFactory.getSession();
			  transaction=session.beginTransaction();
			  Employee emp=new Employee();
			  emp=(Employee) session.get(Employee.class, employeeid);
			  String serverRealPath1=ServletActionContext.getServletContext().getRealPath(""); 
			  File targetFile=new File(serverRealPath1,emp.getImgurl());
			  if(targetFile.delete()){
				  System.out.println("图片删除成功");
			  }
			  session.delete(emp);
			  transaction.commit();
			  HibernateSessionFactory.closeSession();
			  json.put("deleteok",true);
			  fanhui(json);
		} catch (HibernateException e) {
			System.out.println("employeechongfu.action  出现异常:HibernateException e");
			json.put("deleteok",false);
			fanhui(json);
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (Exception e) {
			System.out.println("employeechongfu.action  出现异常:(Exception e");
			json.put("deleteok",false);
			fanhui(json);
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }

}
