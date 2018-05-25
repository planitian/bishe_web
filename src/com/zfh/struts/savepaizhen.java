package com.zfh.struts;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.zfh.dao.adminDao;

public class savepaizhen extends ActionSupport {
 private  String[] saveemployee;
 private String[]  date;
 private String[]  amstart;
 private String[]  amend;
 private String[]  pmstart;
 private String[]  pmend;
 private  HttpSession request=ServletActionContext.getRequest().getSession();
	public String[] getSaveemployee() {
	return saveemployee;
}

public void setSaveemployee(String[] saveemployee) {
	this.saveemployee = saveemployee;
}

public String[] getDate() {
	return date;
}

public void setDate(String[] date) {
	this.date = date;
}

public String[] getAmstart() {
	return amstart;
}

public void setAmstart(String[] amstart) {
	this.amstart = amstart;
}

public String[] getAmend() {
	return amend;
}

public void setAmend(String[] amend) {
	this.amend = amend;
}

public String[] getPmstart() {
	return pmstart;
}

public void setPmstart(String[] pmstart) {
	this.pmstart = pmstart;
}

public String[] getPmend() {
	return pmend;
}

public void setPmend(String[] pmend) {
	this.pmend = pmend;
}

	/**
	 * @return
	 */
	public String execute() {
		adminDao admin=new adminDao();
		
		Boolean que=false;
		for(int y=0;y<date.length;y++){
			que=admin.savepaizhen(saveemployee[y], date[y], amstart[y], amend[y], pmstart[y], pmend[y]);
			//admin.test(saveemployee[i], date[i], amstart[i], amend[i], pmstart[i], pmend[i]);
			//que=true;
		}
		if(que){
			request.setAttribute("first", que);
			return SUCCESS;
		}
		else 
			return ERROR;
		
	}
}