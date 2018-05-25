package com.zfh.struts;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.zfh.Po.User;
import com.zfh.SessionFactory.HibernateSessionFactory;

public class loginuser implements ServletRequestAware, ServletResponseAware {
	private HttpServletRequest request;
	private HttpServletResponse response;
	private Session session;
	private Transaction transaction;
	private JSONObject json=new JSONObject();


	 
		
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
    		System.out.println("json"+json.toString());
			//this.response.setHeader("Access-Control-Allow-Origin", "*");
			this.response.setContentType("text/json;charset=utf-8");
			this.response.setCharacterEncoding("UTF-8");
			PrintWriter resulit=this.response.getWriter();
			resulit.print(json.toString());
			resulit.flush();
			resulit.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("loginuser.action    发生异常信息:IOException e");
			e.printStackTrace();
		}
 }

	public void getjson(){
		
			try {
				System.out.println("进入getjson");
				ServletInputStream s=request.getInputStream();
				ByteArrayOutputStream bao=new ByteArrayOutputStream();
				byte[] temp=new byte[1024];
				int len;
				while((len=s.read(temp))!=-1){
					bao.write(temp, 0, len);
				}
				bao.flush();
				bao.close();
				String result=bao.toString("UTF-8");
				System.out.println(result);
				 JSONObject paramas=JSONObject.fromObject(result);
				
				System.out.println("username参数"+paramas.getString("username"));
				System.out.println("password参数"+paramas.getString("password"));
				session=HibernateSessionFactory.getSession();
				transaction=session.beginTransaction();
				String hql="from User u where u.personId=? and u.password=?";
				User user=new User();
				user=(User)session.createQuery(hql).setString(0, paramas.getString("username")).setString(1,paramas.getString("password")).uniqueResult();
				if(user==null){
					json.put("cunzai", false);
					fanhui(json);
				}else{
					json.put("cunzai", true);
					json.put("username", user.getName());
					json.put("personid", user.getPersonId());
					json.put("phone", user.getPhone());
					json.put("address", user.getAddress());
					json.put("password", user.getPassword());
					json.put("sex", user.getSex());
					fanhui(json);
				}
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (HibernateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		
		
		
	}
}
