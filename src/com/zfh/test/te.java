package com.zfh.test;

import java.io.File;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.zfh.Po.Admin;
import com.zfh.Po.Department;
import com.zfh.Po.Empjian;
import com.zfh.Po.Employee;
import com.zfh.Po.Favourite;
import com.zfh.Po.User;
import com.zfh.Po.Yuyue;
import com.zfh.SessionFactory.HibernateSessionFactory;
import com.zfh.dao.employeeDao;

public class te {
	
	
  public void test(){
		Session session=HibernateSessionFactory.getSession();
		Transaction transaction=session.beginTransaction();
		User user=(User) session.get(User.class, "008");
		Employee employee=(Employee) session.get(Employee.class, "2018");
		Timestamp ts = new Timestamp(System.currentTimeMillis()); 
		
		String tsStr = "2013-05-09 11:49:45"; 
		ts=Timestamp.valueOf(tsStr);
		Yuyue yuyue=new Yuyue(user, employee, ts);
		session.save(yuyue);
		transaction.commit();
		HibernateSessionFactory.closeSession();
		SimpleDateFormat s=new SimpleDateFormat("yyyy年MM月dd日HH点mm分  星期:E");
		System.out.print(s.format(ts));
		
	  
  }
	
	public void date(){
		Timestamp ts = new Timestamp(System.currentTimeMillis()); 
		System.out.print(ts.toString()+"//n");
		Date day=new Date();    
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		System.out.println(df.format(day)); 
	}
	
	public void dd(){
		Timestamp now=new Timestamp(System.currentTimeMillis());
		SimpleDateFormat ts=new SimpleDateFormat("yyyy-MM-dd");
		String hh="2018-02-02 00:00:00";
		String now1=ts.format(now);
		String now2=now1+" 00:00:00";
		Timestamp aa=Timestamp.valueOf(hh);
		Timestamp bb=Timestamp.valueOf(now2);
		
		System.out.print(bb.after(aa));
	}
	
	public void aa(){
		String day="2018-02-02";
		String day1="2018-02-02";
		String day2="2018-02-06";
		System.out.println(day.equals(day1));
		System.out.println(day.equals(day2));
		
	}

	public void ff(){
		Session session=HibernateSessionFactory.getSession();
		Transaction transaction=session.beginTransaction();
		Employee emp=new Employee();
		Employee emp1=new Employee();
		emp=(Employee) session.get(Employee.class,"2014");
		 emp1.setEmployeeId("2014");
		 emp1.setName(emp.getName());
		 emp1.setDepartment(emp.getDepartment());
		 emp1.setImgurl(emp.getImgurl());
		 emp1.setPassword(emp.getPassword());
		System.out.print(emp);
		System.out.print(emp1);
		session.delete(emp);
		session.save(emp1);
		transaction.commit();
		HibernateSessionFactory.closeSession();
		System.out.print(emp1);
//		emp1.setEmployeeId("2036");
//		emp1.setName("悄悄2");	
//		//session.save(emp1);
//		transaction.commit();
//		HibernateSessionFactory.closeSession();
	}
	//文件修改测试
	
	public void wenjian(){
		//String serverRealPath=ServletActionContext.getServletContext().getRealPath("/upload");
		//   System.out.print(serverRealPath);
         try {
			File a=new File("F:/tomatupian/top.txt");
			System.out.print(a.toString());
			 File b=new File("F:/tomatupian/top.txt123");
			   System.out.print(a.renameTo(b));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	//实体类转成json
	
	public void json(){
		Session session=HibernateSessionFactory.getSession();
		Transaction transaction=session.beginTransaction();
		String hql="from User";
		 List<User> user=new ArrayList<User>();
		user=(List<User>)session.createQuery(hql).list();
		JSONArray jsonarry=JSONArray.fromObject(user);
		System.out.println(jsonarry);
		
	}
	
	//测试员工表和简介表

	public void em(){
		try {
			Session session=HibernateSessionFactory.getSession();
			Transaction transaction=session.beginTransaction();
			String hql="from Department";
			List<Department> keshi=new ArrayList<Department>();
			keshi=session.createQuery(hql).list();
			JSONArray json=JSONArray.fromObject(keshi);
			System.out.println(json.toString());
//			Employee employee=new Employee();
//			employee=session.get(Employee.class, "2014");
//     		System.out.println(employee.getEmpjian().getJianjie());
			//Empjian empjian=new Empjian("2016","sdasdad");
//			empjian.setId("2016");
//			empjian.setJianjie("sdhadahd");
//			empjian=session.get(Empjian.class, "2015");
//			System.out.println(empjian.getJianjie());
//			System.out.println(empjian.getEmployee().getName());
//			empjian.setEmployee(employee);
//			empjian.setJianjie("擅长看病，有这出国留学的经验");
//			System.out.println(empjian.getEmployee().getName());
//			session.save(empjian);
			//在调用delete方法之前,先调用clear方法     通过级联删除存在关联关系的一个对象时
			session.clear();
//			session.delete(empjian);
			//session.save(empjian);
//			session.delete(employee);
			transaction.commit();
			HibernateSessionFactory.closeSession();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	//list contain
	
	public void co(){
		List<String> jj=new ArrayList<String>();
	
		String[] list=new String[2];
		jj.add("2014");
		jj.add("2015");
		Boolean cunzai=jj.contains("2014");
		//JSONObject json1=JSONObject.fromObject(jj.toString());
	
		System.out.println(jj.toString());
		//System.out.println(json.toString());
		//System.out.println(json1.toString());
		System.out.println(cunzai);
	}
	//数组问题
	
	public void shuzu(){
	    String[] shuzi =new String[2];
	    
		shuzi[0]="hhhh";
		shuzi[1]="46545";
		JSONArray json=JSONArray.fromObject(shuzi);
		//String[] array=(String[]) json.toArray();
        System.out.println(shuzi.toString());
		Object[] array=json.toArray();
		System.out.println(array[0]);
		System.out.println(json.get(1));
		List<String> list=new ArrayList<String>();
		list.add("sadasda");
		list.add("54564");
		JSONArray json1=JSONArray.fromObject(list);
		String[] gg={"dasd","fdsf"};
		//System.out.print(gg.toString());
		//System.out.print(list.toString());
		System.out.println(json1.toString());
	}
	
	public void dizhi(){
		 Map map=new HashMap();
	        map.put("hhh","ggg");
		 System.out.print(map.get("hhh"));
	}
	
	public void kong(){
		Session session=HibernateSessionFactory.getSession();
		Transaction transaction=session.beginTransaction();
		String hql1="from Favourite fav where fav.user=?";
		List<Favourite> fav=new ArrayList<Favourite>();
		fav=(List<Favourite>)session.createQuery(hql1).setString(0, "001").list();
		List<String> xiai=new ArrayList<String>();
		for(Favourite favour:fav){
			//System.out.println(String.valueOf(favour.getEmployee().getEmployeeId()));
			xiai.add(favour.getEmployee().getEmployeeId());
		}
		System.out.println(xiai.toString());
		System.out.println(xiai.contains("2015"));
		System.out.println(fav.get(0).getEmployee().getName());
		transaction.commit();
		HibernateSessionFactory.closeSession();
		
	}
	//静态方法的测试
	
	public void jingtai(){
		String ss=test.hh();
		System.out.print(ss);
	}
	
	//时间戳
	
	public void shijian(){
		String tsStr = "2011-05-09 11:49:45";
		Timestamp timestamp=new Timestamp(System.currentTimeMillis());
		//System.out.println(System.currentTimeMillis());
		System.out.println(timestamp.toString());
	}
	
	//for循环跳出  对JSON的影响
	@Test
	public void forxun(){
		
		JSONArray jsonArray=new JSONArray();
		int h=100;
		for(int i=0;i<=100;i++){
			JSONObject json=new JSONObject();
			json.put("第"+i+"个", i);
		
			if(i==2){
				continue;
			}
			jsonArray.add(json);
		}
		System.out.println(jsonArray.toString());
	}
}
