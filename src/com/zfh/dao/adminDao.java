package com.zfh.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.zfh.Po.Employee;
import com.zfh.Po.Time;
import com.zfh.Po.User;
import com.zfh.Po.Yuyue;
import com.zfh.SessionFactory.HibernateSessionFactory;

public class adminDao {
	private Session session;
	private Transaction transaction;
	private Boolean que=false;
	
	
	//�����õ�
	public void test(String employeeid,String date,String amstart,String amend,String pmstart,String pmend){
		System.out.println("����ʱ��"+pmstart.length());
		System.out.println(amstart.length());
		System.out.println(pmstart);
	}
	
	//����������ӵ�������Ϣ
	public Boolean savepaizhen(String employeeid,String date,String amstart,String amend,String pmstart,String pmend){
		try {
			System.out.println(pmstart);
			Timestamp as = new Timestamp(System.currentTimeMillis()); 
			Timestamp ae = new Timestamp(System.currentTimeMillis()); 
			Timestamp ps = new Timestamp(System.currentTimeMillis()); 
			Timestamp pe = new Timestamp(System.currentTimeMillis()); 
			if((amstart==null)||(amstart.length()==0)){
			
				as=null;
				ae=null;
			}else{
				amstart=date+" "+amstart;
				amend=date+" "+amend;
				ae=Timestamp.valueOf(amend);
				as=Timestamp.valueOf(amstart);
				
			}
			if((pmstart==null)||(pmstart.length()==0)){
				
				ps=null;
				pe=null;
			}else{
				pmstart=date+" "+pmstart;
				pmend=date+" "+pmend;
				ps=Timestamp.valueOf(pmstart);
			    pe=Timestamp.valueOf(pmend);
			}
			
						   
//			System.out.println(as.toString());
//			System.out.println(ae.toString());
//			System.out.println(ps.toString());
//			System.out.println(pe.toString());
			session=HibernateSessionFactory.getSession();
			transaction=session.beginTransaction();
			Employee employee=(Employee) session.get(Employee.class, employeeid);
			Time paizhen=new Time(employee, as, ae, ps, pe);
			session.saveOrUpdate(paizhen);
			System.out.print("�洢�ɹ�ʱ��");
			transaction.commit();
			que=true;
			return que;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	//����ҽ�����˵�ѡ�е��޸ĵ�������Ϣ
	public Boolean updatepaizhen(Integer id,String name,String date,String amstart,String amend,String pmstart,String pmend){
		
		
			try {
				System.out.println("adminDao pmstaet:"+pmstart);
				System.out.println("adminDao id:"+id);
				System.out.println("adminDao name:"+name);
				Timestamp as = new Timestamp(System.currentTimeMillis()); 
				Timestamp ae = new Timestamp(System.currentTimeMillis()); 
				Timestamp ps = new Timestamp(System.currentTimeMillis()); 
				Timestamp pe = new Timestamp(System.currentTimeMillis()); 
				if((amstart==null)||(amstart.length()==0)){
				
					as=null;
					ae=null;
				}else{
					amstart=date+" "+amstart;
					amend=date+" "+amend;
					ae=Timestamp.valueOf(amend);
					as=Timestamp.valueOf(amstart);
					
				}
				if((pmstart==null)||(pmstart.length()==0)){
					
					ps=null;
					pe=null;
				}else{
					pmstart=date+" "+pmstart;
					pmend=date+" "+pmend;
					ps=Timestamp.valueOf(pmstart);
				    pe=Timestamp.valueOf(pmend);	
				}
				
				session=HibernateSessionFactory.getSession();
				transaction=session.beginTransaction();
				Time paizhen=(Time) session.get(Time.class, id);
				System.out.println(paizhen.getDate());
				paizhen.setAmstart(as);
				paizhen.setAmend(ae);
				paizhen.setPmstart(ps);
				paizhen.setPmend(pe);
				
				session.update(paizhen);
				transaction.commit();
				HibernateSessionFactory.closeSession();
				System.out.print("adminDao���³ɹ�ʱ��");
				que=true;
				return que;
			} catch (HibernateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.print("adminDao����ʧ��ʱ��");
				return false;
			}
		
		
	}
	
	
	//�õ�����������Ϣ ����ҽ����
	public List<Time> getemptime(String employeeid){
		try {
			System.out.println(employeeid);
			session=HibernateSessionFactory.getSession();
			transaction=session.beginTransaction();
			String hql="from Time time where time.employee=? order by amstart asc";
			List<Time> result=new ArrayList<Time>();
			result=(List<Time>)session.createQuery(hql).setString(0, employeeid).list();
			System.out.println(result.size());
			for(Time re:result)
			System.out.println("ҽ���������ݲ�ѯ���"+re.getXingqi());
			transaction.commit();
			session.close();
//			for( Time ti:result)
//			System.out.println("����"+ti.getPmstart());
			return result;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}	
	}
	
	//���ݿ��Һŵõ����ҵ�����ҽ��
	public List<Employee> getemployees(String departmentid){
		 
		   try {
			System.out.println("adminDao getemployees ����   ���� Ϊ"+departmentid );
			   session=HibernateSessionFactory.getSession();
			   transaction=session.beginTransaction();
			   String hql="from Employee emp where emp.department=?";
			   List<Employee> empl=new ArrayList<Employee>();
			   empl=(List<Employee>)session.createQuery(hql).setString(0, departmentid).list();
			   if(empl!=null){
				   System.out.println("adminDao getemployees  List<Employee>"+empl );
			   }
			   return empl;
		} catch (HibernateException e) {
			System.out.println("adminDao getemployees �����쳣 HibernateException e" );
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}catch (Exception e) {
			System.out.println("adminDao getemployees �����쳣 Exception e" );
			// TODO Auto-generated catch block
			e.printStackTrace();	
			return null;
		}
	}
	
	//����Ա�õ�����ԤԼ��
	public int getallemployee(){
		try {
			session=HibernateSessionFactory.getSession();
			transaction=session.beginTransaction();
			String hql="select count(*) from Yuyue yuyue";
			Number nu;
			nu=(Number) session.createQuery(hql).uniqueResult();
			transaction.commit();
			HibernateSessionFactory.closeSession();
			if(nu==null){
				nu=100;
			}
			return nu.intValue();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.print("��������");
			return 404;
		}
		
	}
	
	//����Ա�õ�ԤԼ�������  ���ݴ���� ��ǰҳ�� ������
	public List<Yuyue> getYuyue(int CurrentpageNo,int pagesize){
		try {
			System.out.println("adminDao.java  ������ ���� CurrentpageNo:"+CurrentpageNo+"  pagesize: "+pagesize); 
			session=HibernateSessionFactory.getSession();
			transaction=session.beginTransaction();
			int start=(CurrentpageNo-1)*pagesize;// ��ǰҳ��һ��������list�е�λ��
			ArrayList<Yuyue> data=new ArrayList<Yuyue>();
			String hql="from Yuyue yuyue order by createtime desc ";
			data=(ArrayList<Yuyue>) session.createQuery(hql).setMaxResults(pagesize).setFirstResult(start).list();
			transaction.commit();
			HibernateSessionFactory.closeSession();
			return data;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	//����Ա�õ��û��ܸ���
	public int usercount(){
		try {
			session=HibernateSessionFactory.getSession();
			transaction=session.beginTransaction();
			String hql="select count(*) from User user";
			Number nu;
			nu=(Number) session.createQuery(hql).uniqueResult();
			transaction.commit();
			HibernateSessionFactory.closeSession();
			if(nu==null){
				nu=100;
			}
			return nu.intValue();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.print("��������");
			return 404;
		}
	}
	
	
	//����Ա�õ��û��������  ���ݴ���� ��ǰҳ�� ������
	public List<User> getuser(int CurrentpageNo,int pagesize){
		try {
			System.out.println("adminDao.java  ������ ���� CurrentpageNo:"+CurrentpageNo+"  pagesize: "+pagesize); 
			session=HibernateSessionFactory.getSession();
			transaction=session.beginTransaction();
			int start=(CurrentpageNo-1)*pagesize;// ��ǰҳ��һ��������list�е�λ��
			ArrayList<User> data=new ArrayList<User>();
			String hql="from User user ";
			data=(ArrayList<User>) session.createQuery(hql).setMaxResults(pagesize).setFirstResult(start).list();
			transaction.commit();
			HibernateSessionFactory.closeSession();
			return data;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	//����Ա�õ��û��������  ���ݴ���� ��ǰҳ�� ������ ���û�����
		public List<User> getuser(int CurrentpageNo,int pagesize,String name){
			try {
				System.out.println("adminDao.java  ������ ���� CurrentpageNo:"+CurrentpageNo+"  pagesize: "+pagesize+name); 
				session=HibernateSessionFactory.getSession();
				transaction=session.beginTransaction();
				int start=(CurrentpageNo-1)*pagesize;// ��ǰҳ��һ��������list�е�λ��
				ArrayList<User> data=new ArrayList<User>();
				String hql="from User user where user.name=? ";
				data=(ArrayList<User>) session.createQuery(hql).setString(0, name).setMaxResults(pagesize).setFirstResult(start).list();
				transaction.commit();
				HibernateSessionFactory.closeSession();
				return data;
			} catch (HibernateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}
		
		//�õ�ԤԼ���� �����û����֤
		public List<Yuyue> idyuyue(String personid){
			
			try {
				session=HibernateSessionFactory.getSession();
				transaction=session.beginTransaction();
				String hql="from Yuyue yu where yu.user=?";
				List<Yuyue> result=new ArrayList<Yuyue>();
				result=(List<Yuyue>)session.createQuery(hql).setString(0, personid).list();
				System.out.println(" adminDao   ���� idyuyue   result"+result);
				return result;
			} catch (HibernateException e) {
				System.out.println(" adminDao   �����쳣 ���� idyuyue  �쳣����:HibernateException e");
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
			
		}
}
