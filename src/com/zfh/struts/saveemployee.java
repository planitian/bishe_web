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

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import sun.misc.BASE64Decoder;

import com.opensymphony.xwork2.ActionSupport;
import com.zfh.Po.Department;
import com.zfh.Po.Employee;
import com.zfh.SessionFactory.HibernateSessionFactory;

public class saveemployee extends ActionSupport  implements ServletRequestAware, ServletResponseAware  {
	private HttpServletRequest request;
	private HttpServletResponse response;
	private Session session;
	private Transaction transaction;
	private String employeeid;
	private String name;
	private String password;
	private String department; 
	private File touxiang;
	private String touxiangFileName;
	private String touxiangContentType;
	private String imgbase;
	private JSONObject json=new JSONObject();
	
	
	
	public String getImgbase() {
		return imgbase;
	}

	public void setImgbase(String imgbase) {
		this.imgbase = imgbase;
	}

	
	

	public File getTouxiang() {
		return touxiang;
	}

	public void setTouxiang(File touxiang) {
		this.touxiang = touxiang;
	}

	public String getTouxiangFileName() {
		return touxiangFileName;
	}

	public void setTouxiangFileName(String touxiangFileName) {
		this.touxiangFileName = touxiangFileName;
	}

	public String getTouxiangContentType() {
		return touxiangContentType;
	}

	public void setTouxiangContentType(String touxiangContentType) {
		this.touxiangContentType = touxiangContentType;
	}

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
			System.out.println("saveemployee.action   发生异常信息:IOException e");
			e.printStackTrace();
		}
    	
    }
	public void getjson(){
		System.out.println("进入了saveemployee.action");
		
			try {
				System.out.println("saveemployee.action   employeeid:"+employeeid);
				System.out.println("saveemployee.action   touxiangFileNameh:"+touxiangFileName);
				System.out.println("saveemployee.action   touxiangContentType:"+touxiangContentType);
				
				if(touxiang!= null){
					//项目在服务器中的地址
					String serverRealPath=ServletActionContext.getServletContext().getRealPath("/upload");
					//String serverRealPath="F:/tomatupian";
					System.out.println("saveemployee.action   serverRealPath:"+serverRealPath);
					File dir=new File(serverRealPath);
					if(!dir.exists()){
						dir.mkdir();
					}
					int beginIndex=touxiangFileName.lastIndexOf(".");
					if(beginIndex>0){
						String imgtitle=employeeid+touxiangFileName.substring(beginIndex);
						System.out.println("saveemployee.action   imgtitle:"+imgtitle);
						File targetFile=new File(serverRealPath,imgtitle);
						FileUtils.copyFile(touxiang, targetFile);
						File proimg=new File("/upload",imgtitle);
						FileUtils.copyFile(touxiang, proimg);
						String shujuimgurl="upload/"+imgtitle;
						Employee emp=new Employee();
						emp.setEmployeeId(employeeid);
						emp.setName(name);
						emp.setPassword(password);
						session=HibernateSessionFactory.getSession();
						transaction=session.beginTransaction();
						Department dep=(Department) session.get(Department.class, department);
						emp.setDepartment(dep);
						emp.setImgurl(shujuimgurl);
						System.out.println("saveemployee.action   Employee:"+emp.toString());
						session.save(emp);
						transaction.commit();
						HibernateSessionFactory.closeSession();
						
						json.put("succeetupian", true);
						fanhui(json);	
					}		
				}else{
					json.put("succeetupian", false);
					fanhui(json);	
				}
			} catch (HibernateException e) {
				// TODO Auto-generated catch block
				System.out.println("saveemployee.action   发生异常信息:HibernateException e");
				json.put("succeetupian", false);
				fanhui(json);	
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("saveemployee.action   发生异常信息:IOException e");
				json.put("succeetupian", false);
				fanhui(json);	
				e.printStackTrace();
			}
		 
	}
	
	public void test( ){
		try {
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
			String serverRealPath=ServletActionContext.getServletContext().getRealPath("/upload");
			System.out.println("saveemployee.action   serverRealPath:"+serverRealPath);
			File dir=new File(serverRealPath);
			if(!dir.exists()){
				dir.mkdir();
			}
			String imgtitle=employeeid+".png";
			File targetFile=new File(serverRealPath,imgtitle);
			OutputStream out=new FileOutputStream(targetFile);
			out.write(b);
			out.flush();
			out.close();
			
			String shujuimgurl="upload/"+imgtitle;
			Employee emp=new Employee();
			emp.setEmployeeId(employeeid);
			emp.setName(name);
			emp.setPassword(password);
			session=HibernateSessionFactory.getSession();
			transaction=session.beginTransaction();
			Department dep=(Department) session.get(Department.class, department);
			emp.setDepartment(dep);
			emp.setImgurl(shujuimgurl);
			System.out.println("saveemployee.action   Employee:"+emp.toString());
			session.save(emp);
			transaction.commit();
			HibernateSessionFactory.closeSession();
			
			json.put("succeetupian", true);
			fanhui(json);	
		} catch (FileNotFoundException e) {
			System.out.println("saveemployee.action   发生异常信息:FileNotFoundException e");
			json.put("succeetupian", false);
			fanhui(json);	
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("saveemployee.action   发生异常信息:IOException e");
			// TODO Auto-generated catch block
			json.put("succeetupian", false);
			fanhui(json);	
			e.printStackTrace();
		}
		
		
		
	}
}