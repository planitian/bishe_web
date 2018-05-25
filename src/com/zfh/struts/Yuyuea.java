package com.zfh.struts;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.zfh.Po.User;
import com.zfh.Po.Yuyue;
import com.zfh.dao.employeeDao;

public class Yuyuea extends ActionSupport implements ServletRequestAware{
	 protected HttpServletRequest request;
     private int currentpageNo;//��ǰҳ��
     private int pageSize=6;//ҳ����������
     private int start;//��ǰҳ��һ��������list�е�λ��
     private int totalpage;//��ҳ��
     private List<Yuyue> data;
     private List<User> user;
     public List<User> getUser() {
		return user;
	}

	public void setUser(List<User> user) {
		this.user = user;
	}

	public List<Yuyue> getData() {
		return data;
	}

	public void setData(List<Yuyue> data) {
		this.data = data;
	}

	public int getTotalpage() {
 		int count=new employeeDao().getcount();
 		totalpage=(count+pageSize-1)/pageSize;
 		return totalpage;
 	}

 	public void setTotalpage(int totalpage) {
 		this.totalpage = totalpage;
 	}

 	public int getStart() {
 		return start;
 	}

 	public void setStart(int start) {
 		this.start = start;
 	}

 	public int getCurrentpageNo() {
 		return currentpageNo;
 	}

 	public void setCurrentpageNo(int currentpageNo) {
 		this.currentpageNo = currentpageNo;
 	}
 	 
     public boolean hasPreviousPage(){
     	
     	return this.currentpageNo>1;
     }
     public boolean hasNextPage(){
    	
     	return this.currentpageNo<this.getTotalpage();
     }

	/**
	 * @return
	 */
	public String execute() {
	     data=new ArrayList<com.zfh.Po.Yuyue>();
		employeeDao employeeDao=new employeeDao();
		data=employeeDao.getYuyue(currentpageNo, pageSize);
		request.getSession().setAttribute("test", data);
		System.out.println(hasPreviousPage());
		if(data!=null){
		return SUCCESS;
		}else{
		return ERROR;
		}
		
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		this.request=arg0;
	}
}