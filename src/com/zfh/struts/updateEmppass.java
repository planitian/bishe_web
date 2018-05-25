package com.zfh.struts;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.zfh.Po.Employee;
import com.zfh.SessionFactory.HibernateSessionFactory;
import com.zfh.dao.employeeDao;

public class updateEmppass implements ServletRequestAware, ServletResponseAware {
	private HttpServletRequest request;
	private HttpServletResponse response;
	private String password;
	private Session session;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	private Transaction transaction;
	private JSONObject json = new JSONObject();

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

	// 返回的代码 集成一下
	public void fanhui(JSONObject json) {
		try {
			this.response.setHeader("Access-Control-Allow-Origin", "*");
			this.response.setContentType("text/json;charset=utf-8");
			this.response.setCharacterEncoding("UTF-8");
			PrintWriter resulit = this.response.getWriter();
			resulit.print(json.toString());
			resulit.flush();
			resulit.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out
					.println("updateEmppass.action    发生异常信息:IOException e");
			e.printStackTrace();
		}
	}

	public void getjson() {

		try {
			if ((password == null) || (password.length() == 0)) {
			       System.out.print("updateEmppass   参数为空");
			       json.put("success", false);
					fanhui(json);
			}
			employeeDao employeeDao=new employeeDao();
			
			boolean result=employeeDao.updatepassword(password);
			if(result){
				 json.put("success", true);
				 fanhui(json);
			}else{
				json.put("success", false);
				fanhui(json);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			json.put("success", false);
			fanhui(json);
		}
	}
}
