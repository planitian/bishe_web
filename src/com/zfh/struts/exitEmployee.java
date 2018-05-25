package com.zfh.struts;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class exitEmployee extends ActionSupport {
 private HttpSession request=ServletActionContext.getRequest().getSession();
	/**
	 * @return
	 */
	public String execute() {
		// TODO Auto-generated method stub
		request.removeAttribute("emplo");
		return SUCCESS;
	}
}