package com.zfh.struts;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
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

import sun.misc.BASE64Decoder;

import com.zfh.Po.Department;
import com.zfh.Po.Empjian;
import com.zfh.Po.Employee;
import com.zfh.SessionFactory.HibernateSessionFactory;

public class updateemployee implements ServletRequestAware,
		ServletResponseAware {
	private HttpServletRequest request;
	private HttpServletResponse response;
	private Session session;
	private Transaction transaction;
	private String employeeid;
	private String name;
	private String password;
	private String department; 
	private String imgbase;
	private String jiuemp;
	public String getJiuemp() {
		return jiuemp;
	}

	public void setJiuemp(String jiuemp) {
		this.jiuemp = jiuemp;
	}
	private JSONObject json=new JSONObject();

	public String getEmployeeid() {
		return employeeid;
	}

	public void setEmployeeid(String employeeid) {
		this.employeeid = employeeid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getImgbase() {
		return imgbase;
	}

	public void setImgbase(String imgbase) {
		this.imgbase = imgbase;
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
				System.out.println("updateemployee.action   发生异常信息:IOException e");
				e.printStackTrace();
			}	
	    }
	  

   public void getjson(){
	   try {
		session=HibernateSessionFactory.getSession();
		   transaction=session.beginTransaction();
		   Employee emp=new Employee();
		   Employee emp1=new Employee();
		   System.out.println("updateemployee.action   employeeid ："+employeeid+jiuemp);
		   emp=(Employee) session.get(Employee.class, jiuemp);
		   
		   emp1.setEmployeeId(employeeid);
		   emp1.setName(name);
		   emp1.setPassword(password);
		   Department de=new Department();
		   de=(Department) session.get(Department.class, department);
		   emp1.setDepartment(de);
		   String imgtitle=employeeid+".png";
		   String serverRealPath=ServletActionContext.getServletContext().getRealPath("/upload");
		   String serverRealPath1=ServletActionContext.getServletContext().getRealPath("");
		   File targetFile=new File(serverRealPath1,emp.getImgurl());
		   System.out.println(targetFile.toString());
		   System.out.println(targetFile.exists());
		   if((imgbase==null)||(imgbase.length()==0)||(imgbase=="")){
			   String shujuimgurl="upload/"+imgtitle;
			   emp1.setImgurl(shujuimgurl);
			   File targetFile1=new File(serverRealPath,imgtitle);
			   System.out.println(targetFile1.toString());
			   Boolean wenjjian=targetFile.renameTo(targetFile1);
			   System.out.println(wenjjian);
			   if(!wenjjian){
				   System.out.println("updateemployee.action   修改文件名出错");
				   json.put("updateok", false);
				   fanhui(json); 
			   }else{
				 String gg=emp.getEmpjian().getJianjie();
				 System.out.println(gg);
			  Empjian jinajie=new Empjian();
			jinajie.setEmployee(emp1);
			jinajie.setJianjie(gg);
			   session.delete(emp);
			   session.flush();
			   session.save(emp1);
			   session.save(jinajie);
			   transaction.commit();
			   HibernateSessionFactory.closeSession();
			   json.put("updateok", true);
			   fanhui(json);
			   }
		   }else{
			   targetFile.delete();//删除以前的照片
			   File targetFile1=new File(serverRealPath,imgtitle);
			   System.out.println(imgbase);
				imgbase=imgbase.replaceAll("data:image/png;base64,", "");
				System.out.println("第二次"+imgbase);
				BASE64Decoder base=new BASE64Decoder();
				byte[] b=base.decodeBuffer(imgbase);
				for (int i = 0; i < b.length; ++i) {      
				    if (b[i] < 0) {// 调整异常数据      
				        b[i] += 256;      
				    }      
				}
				OutputStream out=new FileOutputStream(targetFile1);
				out.write(b);
				out.flush();
				out.close();
				
				String shujuimgurl="upload/"+imgtitle;
				emp1.setImgurl(shujuimgurl);
				 String gg=emp.getEmpjian().getJianjie();
				 System.out.println(gg);
				  Empjian jinajie=new Empjian();
				jinajie.setEmployee(emp1);
				jinajie.setJianjie(gg);
				 session.delete(emp);
				 session.flush();
				 session.save(emp1);
				 session.save(jinajie);
				transaction.commit();
			     HibernateSessionFactory.closeSession();
			     json.put("updateok", true);
			     fanhui(json);
		   }
	} catch (HibernateException e) {
		
		 System.out.println("updateemployee.action  出现异常信息 :HibernateException e");
		json.put("updateok", false);
	     fanhui(json);
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (FileNotFoundException e) {
		 System.out.println("updateemployee.action  出现异常信息 :FileNotFoundException e");
			json.put("updateok", false);
		     fanhui(json);
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		 System.out.println("updateemployee.action  出现异常信息 :IOException e");
			json.put("updateok", false);
		     fanhui(json);
		// TODO Auto-generated catch block
		e.printStackTrace();
	}catch (Exception e) {
		 System.out.println("updateemployee.action  出现异常信息 :Exception e");
			json.put("updateok", false);
		     fanhui(json);
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

   }
}
