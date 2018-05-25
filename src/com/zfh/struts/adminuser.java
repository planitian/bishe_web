package com.zfh.struts;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.zfh.Po.User;
import com.zfh.Po.Yuyue;
import com.zfh.dao.adminDao;

public class adminuser extends ActionSupport {
	private int currentpageNo;// 当前页码
	private int pageSize = 6;// 页面数据容量
	private int start;// 当前页第一条数据在list中的位置
	private int totalpage;// 总页数
	private List<Yuyue> data;
	private List<User> user;
	private String name;
	private String personid;

	public String getPersonid() {
		return personid;
	}

	public void setPersonid(String personid) {
		this.personid = personid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<User> getUser() {
		return user;
	}

	public void setUser(List<User> user) {
		this.user = user;
	}

	public int getCurrentpageNo() {
		return currentpageNo;
	}

	public void setCurrentpageNo(int currentpageNo) {
		this.currentpageNo = currentpageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getTotalpage() {
		int count = new adminDao().usercount();
		totalpage = (count + pageSize - 1) / pageSize;
		return totalpage;
	}

	public void setTotalpage(int totalpage) {
		this.totalpage = totalpage;
	}

	public List<Yuyue> getData() {
		return data;
	}

	public void setData(List<Yuyue> data) {
		this.data = data;
	}

	public boolean hasPreviousPage() {

		return this.currentpageNo > 1;
	}

	public boolean hasNextPage() {

		return this.currentpageNo < this.getTotalpage();
	}
	
	
	
	
	//查询所有的用户 总用户
	
	public String all(){
		  user=new ArrayList<User>();
			adminDao admin=new adminDao();
			user=admin.getuser(currentpageNo, pageSize);
			System.out.println(hasPreviousPage());
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession session = request.getSession();
			session.setAttribute("leixing", "all");
			if(user!=null){
			return SUCCESS;
			}else{
			return ERROR;
			}
	}
	
	//根据名字查询的用户 
	public String name(){
		 user=new ArrayList<User>();
		adminDao admin=new adminDao();
		user=admin.getuser(currentpageNo, pageSize, name);
		System.out.println(hasPreviousPage());
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		session.setAttribute("leixing", "name");
		if(user!=null){
		return SUCCESS;
		}else{
		return ERROR;
		}
	}
	
	//根据身份证查询用户
	public String id(){
		 data=new ArrayList<Yuyue>();
			adminDao admin=new adminDao();
			data=admin.idyuyue(personid);
			System.out.println("adminuser id方法"+data.toString());
//			HttpServletRequest request = ServletActionContext.getRequest();
//			HttpSession session = request.getSession();
//			session.setAttribute("leixing", "name");
			if(data!=null){
			return "id";
			}else{
			return ERROR;
			}
		
	}
}
