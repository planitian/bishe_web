package com.zfh.struts;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import sun.misc.BASE64Encoder;

import com.zfh.Po.Department;
import com.zfh.Po.Empjian;
import com.zfh.Po.Employee;
import com.zfh.Po.Favourite;
import com.zfh.Po.Time;
import com.zfh.Po.User;
import com.zfh.Po.Yuyue;
import com.zfh.SessionFactory.HibernateSessionFactory;

public class request implements ServletRequestAware, ServletResponseAware {
    
	private HttpServletRequest request;
	private HttpServletResponse response;
	private Session session;
	private Transaction transaction;
	private JSONObject json;
	private JSONArray jsonArray;
	
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
	//用于返回数据的工具 自己分装的一个
	public void fanhui(Object json){
    	try {
    		if(json==null){
    			System.out.println("fanhui 的参数为空 检查仔细");
    		}else{
    			//System.out.println("json"+json.toString());
    		}
    		
			//this.response.setHeader("Access-Control-Allow-Origin", "*");
			this.response.setContentType("text/json;charset=utf-8");
			this.response.setCharacterEncoding("UTF-8");
			PrintWriter resulit=this.response.getWriter();
			resulit.print(json.toString());
			resulit.flush();
			resulit.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("request.action    发生异常信息:IOException e");
			e.printStackTrace();
		}
 }
	//得到inputstram流里面的json字符串
	public String requ(){
		try {
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
			return result;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
		
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	
	//读取图片并编码64
	public String base64bian(String imgurl){
		
		try {
			String serverRealPath=ServletActionContext.getServletContext().getRealPath("/");
			String realurl=serverRealPath+imgurl;
			InputStream in= new FileInputStream(realurl);
			byte[] data=new byte[in.available()];
			in.read(data);
			in.close();
			BASE64Encoder encoder=new BASE64Encoder();
			String result=encoder.encode(data);
			return result;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}

	
  //请求科室 返回科室集合 
	public void keshi(){
		session=HibernateSessionFactory.getSession();
		transaction=session.beginTransaction();
		String hql="from Department";
		List<Department> keshi=new ArrayList<Department>();
		keshi=session.createQuery(hql).list();
		List<String> Strkeshi=new ArrayList<String>();
		for(Department d:keshi){
			Strkeshi.add(d.getDepartment());
		}
		jsonArray=JSONArray.fromObject(Strkeshi);
		fanhui(jsonArray);
		
	}
	//获取医生的信息
	public void empl(){
		try {
			session=HibernateSessionFactory.getSession();
			transaction=session.beginTransaction();
			json=JSONObject.fromObject(requ());
			System.out.println(json.toString());
			String keshiid=json.getString("keshiid");
			String userid=json.getString("userid");
			System.out.println("userid"+userid);
			String hql="from Employee emp where emp.department=?";
			String hql1="from Favourite fav where fav.user=?";
			List<Favourite> fav=new ArrayList<Favourite>();
			fav=(List<Favourite>)session.createQuery(hql1).setString(0, userid).list();
			List<String> xiai=new ArrayList<String>();
			for(Favourite favour:fav){
				//System.out.println(String.valueOf(favour.getEmployee().getEmployeeId()));
				xiai.add(favour.getEmployee().getEmployeeId());
			}
			List<Employee> employees=new ArrayList<Employee>();
			employees=session.createQuery(hql).setString(0, keshiid).list();
			JSONArray emparr=new JSONArray();
			JSONObject emp=new JSONObject();
			Empjian empjian=new Empjian();
			for(Employee em:employees){
				emp.put("name", em.getName());
				emp.put("employeeid",em.getEmployeeId());
				String hql2="from Empjian empjian where empjian.id=?";
				empjian=(Empjian)session.createQuery(hql2).setString(0,String.valueOf(em.getEmployeeId())).uniqueResult();
				if(empjian==null){
					emp.put("jianjie", "没有简介，请添加");
					System.out.println("简介为空");
				}else{
					emp.put("jianjie", empjian.getJianjie());
				}
				
				if(xiai.contains(em.getEmployeeId())){
					emp.put("favour", true);
				}else{
					emp.put("favour", false);
				}
				//读取图片并编码64
				String img=base64bian(em.getImgurl());
				emp.put("touxiang", img);
				emparr.add(emp);
			}
			System.out.println("返回的json数据  employee数量"+emparr.size());
			transaction.commit();
			HibernateSessionFactory.closeSession();
			 fanhui(emparr);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			HibernateSessionFactory.closeSession();
			e.printStackTrace();
		}		
		
	}
	
	//添加医生到收藏列表
	public void shoucang (){
		try {
			session=HibernateSessionFactory.getSession();
			transaction=session.beginTransaction();
			JSONObject jsonObject=JSONObject.fromObject(requ());
			System.out.println("收藏函数"+"参数"+jsonObject.toString());
			if(jsonObject.getBoolean("fav")){
				String hql="from Favourite fav where fav.user=? and fav.employee=?";
				Favourite favourite=(Favourite)session.createQuery(hql).setString(0,jsonObject.getString("personid").toString()).setString(1, jsonObject.getString("employeeid").toString()).uniqueResult();
				session.delete(favourite);
				transaction.commit();
				HibernateSessionFactory.closeSession();
				JSONObject jsonObject2=new JSONObject();
				jsonObject2.put("success", true);
				fanhui(jsonObject2);
				System.out.println("收藏函数 返回的数据 "+jsonObject2.toString());
			}else{
			Employee employee=(Employee) session.get(Employee.class, jsonObject.get("employeeid").toString());
			User user=(User) session.get(User.class, jsonObject.get("personid").toString());
			Favourite favourite=new Favourite(user,employee);
			session.save(favourite);
			transaction.commit();
			HibernateSessionFactory.closeSession();
			JSONObject jsonObject2=new JSONObject();
			jsonObject2.put("success", true);
			fanhui(jsonObject2);
			System.out.println("收藏函数 返回的数据 "+jsonObject2.toString());
			}
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			HibernateSessionFactory.closeSession();
			e.printStackTrace();
		}catch (Exception e) {
			// TODO: handle exception
			HibernateSessionFactory.closeSession();
			e.printStackTrace();
		}
		
	}
	//获取医生的排诊
	public void time(){
		try {
			json=JSONObject.fromObject(requ());
			String employeeid=json.getString("employeeid");
			System.out.println("time 的参数：："+employeeid);
			session=HibernateSessionFactory.getSession();
			transaction=session.beginTransaction();
			String hql="from Time ti where ti.employee=?";
			List<Time> times=new ArrayList<Time>();
			times=(List<Time>)session.createQuery(hql).setString(0, employeeid).list();
//			try {
//				JsonConfig jsonConfig = new JsonConfig();
//				jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
//			    jsonConfig.setExcludes(new String[]{"course"});
//				jsonArray=JSONArray.fromObject(times,jsonConfig);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			jsonArray=new JSONArray();
//			jsonArray.add("hhhh");
//			System.out.println("查询的结果::"+jsonArray.toString());
//			fanhui(jsonArray);
			System.out.println(times.toString());
			transaction.commit();
			HibernateSessionFactory.closeSession();
			fanhui(times.toString());
		} catch (Exception e) {
			// TODO: handle exception
			HibernateSessionFactory.closeSession();
			e.printStackTrace();
		}
	}
	//增加预约的
	public void addyuyue(){
		try {
			Boolean cunzai=false;
			session=HibernateSessionFactory.getSession();
			transaction=session.beginTransaction();
			json=JSONObject.fromObject(requ());
			System.out.println("addyuyue 的参数"+json.toString());
			String hql="from Yuyue yu where yu.user=? and yu.employee=?";
			List<Yuyue> yuyues=(List<Yuyue>)session.createQuery(hql).setString(0, json.get("userid").toString()).setString(1, json.get("employeeid").toString())
					.list();
			if(yuyues!=null){
				for(Yuyue yu:yuyues){
					SimpleDateFormat formot=new SimpleDateFormat("yyyy-MM-dd");
					System.out.println(yu.getTime().toString());
//					String jiu=formot.format(yu.getTime().toString());
//					String xin=formot.format(json.getString("time"));
					String jiu=yu.getTime().toString().substring(0, 10);
					String xin=json.getString("time").substring(0,10);
					System.out.println(xin+jiu);
					System.out.println((jiu==xin));
					System.out.println(jiu.equals(xin));
					if(jiu.equals(xin)){
						cunzai=true;
						break;
					}
				}
			}
			if(cunzai==false){
			Yuyue yuyue=new Yuyue();
			Employee employee=(Employee) session.get(Employee.class, json.get("employeeid").toString());
			User user=(User) session.get(User.class, json.get("userid").toString());
			Timestamp timestamp=Timestamp.valueOf(json.getString("time"));
			System.out.println(timestamp.toString());
			yuyue.setEmployee(employee);
			yuyue.setTime(timestamp);
			yuyue.setUser(user);
			session.save(yuyue);
			transaction.commit();
			HibernateSessionFactory.closeSession();
			JSONObject jsonObject=new JSONObject();
			jsonObject.put("success", true);
			fanhui(jsonObject.toString());
			}else{
				transaction.commit();
				HibernateSessionFactory.closeSession();
				JSONObject jsonObject=new JSONObject();
				jsonObject.put("success", false);
				jsonObject.put("cunzai", true);
				fanhui(jsonObject.toString());
			}
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			HibernateSessionFactory.closeSession();
			e.printStackTrace();
		}catch (Exception e) {
			// TODO: handle exception
			HibernateSessionFactory.closeSession();
			e.printStackTrace();
		}
		
	}
	//请求预约表
	public void yuyue(){
		try {
			json=JSONObject.fromObject(requ());
			session=HibernateSessionFactory.getSession();
			transaction=session.beginTransaction();
			List<Yuyue> yuyues=new ArrayList<Yuyue>();
			String hql="from Yuyue yu where yu.user=?";
			yuyues=(List<Yuyue>)session.createQuery(hql).setString(0, json.getString("personid")).list();
			Timestamp now=new Timestamp(System.currentTimeMillis());
			jsonArray=new JSONArray();
			if(yuyues==null){
				String cunzai="kong";
				fanhui(cunzai);
			}else{
			for(Yuyue yu:yuyues){
				JSONObject json1=new JSONObject();
//				String yuma=yu.getYuyuema().substring(21);
				json1.put("yuyuema", yu.getYuyuema());
				json1.put("employee", yu.getEmployee().getName());
				json1.put("keshi", yu.getEmployee().getDepartment().getDepartment());
				//System.out.println("now.after(yu.getTime())"+now.after(yu.getTime()));
				//System.out.println(yu.getTime());
//				if(now.after(yu.getTime())){
//					continue;
//				}
				json1.put("time",timezhun(yu.getTime()));
				json1.put("xingqi", new SimpleDateFormat("E").format(yu.getTime()));
				jsonArray.add(json1);				
			}
			if(jsonArray.size()==0){
				String cunzai="kong";
				fanhui(cunzai);
			}else{
			fanhui(jsonArray);
			}
			}
			transaction.commit();
			HibernateSessionFactory.closeSession();
		
			//System.out.println("返回的参数"+jsonArray.toString());
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			HibernateSessionFactory.closeSession();
			e.printStackTrace();
		}catch (Exception e) {
			// TODO: handle exception
			HibernateSessionFactory.closeSession();
			e.printStackTrace();
		}
	}
	//用来转化 预约表中的时间
	public String timezhun(Timestamp timestamp){
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("MM-dd HH:mm");
		String date=simpleDateFormat.format(timestamp);
		return date;
		
	}
	//删除预约的
	public void removeyuyue(){
		try {
			json=JSONObject.fromObject(requ());
			System.out.println("removeyuyue的参数"+json.toString());
			session=HibernateSessionFactory.getSession();
			transaction=session.beginTransaction();
			Yuyue yuyue=(Yuyue) session.get(Yuyue.class, json.getString("yuyuema"));
			session.delete(yuyue);
			transaction.commit();
			HibernateSessionFactory.closeSession();
			String result="sucess";
			fanhui(result);	
		} catch (Exception e) {
			HibernateSessionFactory.closeSession();
			e.printStackTrace();
		}
	}
	
	//删除收藏的
	public void removefav(){
		try {
			json=JSONObject.fromObject(requ());
			System.out.println("removefav的参数"+json.toString());
			session=HibernateSessionFactory.getSession();
			transaction=session.beginTransaction();
			String hql="from Favourite fav where fav.user=? and fav.employee=?";
			Favourite favourite=(Favourite)session.createQuery(hql).setString(0, json.getString("userid")).setString(1, json.getString("employeeid")).uniqueResult();
			session.delete(favourite);
			transaction.commit();
			HibernateSessionFactory.closeSession();
			String result="sucess";
			fanhui(result);	
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	//请求收藏的
	public void requestfav(){
		try {
			json=JSONObject.fromObject(requ());
			session=HibernateSessionFactory.getSession();
			transaction=session.beginTransaction();
			String hql="from Favourite fav where fav.user=?";
			List<Favourite> favourites=new ArrayList<Favourite>();
			favourites=(List<Favourite>)session.createQuery(hql).setString(0, json.getString("userid")).list();
			if(favourites==null||favourites.size()==0){
				String cunzai="kong";
				fanhui(cunzai);
			}else{
				jsonArray=new JSONArray();
				Employee employee =new Employee();
				for(Favourite fav:favourites){
					JSONObject jsonObject=new JSONObject();
					employee=fav.getEmployee();
					jsonObject.put("emploimage", base64bian(employee.getImgurl()));
					jsonObject.put("keshi", fav.getEmployee().getDepartment().getDepartment());
					jsonObject.put("employeename", employee.getName());
					jsonObject.put("employeeid", employee.getEmployeeId());
					jsonArray.add(jsonObject);
				}
				fanhui(jsonArray);
			}
			transaction.commit();
			HibernateSessionFactory.closeSession();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace(); 
		}
	}
	//添加user
	public void adduser(){
		try {
			json=JSONObject.fromObject(requ());
			session=HibernateSessionFactory.getSession();
			transaction=session.beginTransaction();
			User user=new User(json.getString("sfz"),json.getString("mima"),json.getString("sex"),json.getString("phone"),json.getString("address"),
					json.getString("name"));
			session.save(user);
			transaction.commit();
			HibernateSessionFactory.closeSession();
			String result="success";
			fanhui(result);
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
	}
	//修改具体用户的信息
	public void updateuser(){
		try {
			json=JSONObject.fromObject(requ());
			session=HibernateSessionFactory.getSession();
			transaction=session.beginTransaction();
			User user=(User) session.get(User.class, json.getString("personid"));
			user.setPassword(json.getString("mima"));
			user.setPhone(json.getString("phone"));
			user.setAddress(json.getString("address"));
			user.setName(json.getString("name"));
			session.update(user);
			transaction.commit();
			HibernateSessionFactory.closeSession();
			String result="success";
			fanhui(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
