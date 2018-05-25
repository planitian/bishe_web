package com.zfh.struts;

import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.zfh.Po.User;
import com.zfh.Po.Yuyue;
import com.zfh.dao.adminDao;
import com.zfh.dao.employeeDao;

public class adminyuyue extends ActionSupport {

	private int currentpageNo;// ��ǰҳ��
	private int pageSize = 6;// ҳ����������
	private int start;// ��ǰҳ��һ��������list�е�λ��
	private int totalpage;// ��ҳ��
	private List<Yuyue> data;
	private List<User> user;

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
		int count = new adminDao().getallemployee();
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
  
	/**
	 * @return
	 */
	public String execute() {
	     data=new ArrayList<com.zfh.Po.Yuyue>();
		adminDao admin=new adminDao();
		data=admin.getYuyue(currentpageNo, pageSize);
		System.out.println(hasPreviousPage());
		if(data!=null){
		return SUCCESS;
		}else{
		return ERROR;
		}
		
	}
}
