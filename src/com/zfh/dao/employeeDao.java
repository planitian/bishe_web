package com.zfh.dao;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.zfh.Po.Employee;
import com.zfh.Po.Yuyue;
import com.zfh.SessionFactory.HibernateSessionFactory;

public class employeeDao {
	private Session session;
	private Transaction transaction;
	private HttpSession request = ServletActionContext.getRequest()
			.getSession();

	public List<Yuyue> getYuyue(int CurrentpageNo, int pagesize) {
		try {
			session = HibernateSessionFactory.getSession();
			transaction = session.beginTransaction();
			int start = (CurrentpageNo - 1) * pagesize;// 当前页第一条数据在list中的位置
			ArrayList<Yuyue> data = new ArrayList<Yuyue>();
			String eml = request.getAttribute("emplo").toString();
			String hql = "from Yuyue yuyue where yuyue.employee=? order by createtime desc ";
			data = (ArrayList<Yuyue>) session.createQuery(hql)
					.setString(0, eml).setMaxResults(pagesize)
					.setFirstResult(start).list();
			transaction.commit();
			HibernateSessionFactory.closeSession();
			return data;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public int getcount() {
		try {
			session = HibernateSessionFactory.getSession();
			transaction = session.beginTransaction();
			String hql = "select count(*) from Yuyue yuyue where yuyue.employee=?";
			String eml = request.getAttribute("emplo").toString();
			Number nu;
			nu = (Number) session.createQuery(hql).setString(0, eml)
					.uniqueResult();
			transaction.commit();
			HibernateSessionFactory.closeSession();
			if (nu == null) {
				nu = 100;
			}
			return nu.intValue();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.print("发生错误");
			return 404;
		}
	}
	
	//修改指定医生的密码

	public  boolean  updatepassword(String password) {
		try {
			session = HibernateSessionFactory.getSession();
			transaction = session.beginTransaction();
			//从session中获取当前的医生员工号
			String eml = request.getAttribute("emplo").toString();
			Employee emp = (Employee) session.get(Employee.class, eml);
			emp.setPassword(password);
			transaction.commit();
			HibernateSessionFactory.closeSession();
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.print("发生错误");
		    return false;
		}catch(Exception e){
			e.printStackTrace();
			System.out.print("发生错误");
			 return false;
		}
	}
}
