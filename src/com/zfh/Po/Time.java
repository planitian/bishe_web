package com.zfh.Po;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import net.sf.json.JSONObject;

/**
 * Time entity. @author MyEclipse Persistence Tools
 */

public class Time implements java.io.Serializable {

	// Fields

	private Integer id;
	private Employee employee;
	private Timestamp amstart;
	private Timestamp amend;
	private Timestamp pmstart;
	private Timestamp pmend;
	private String xiuday;  //用于管理员从查看排诊的界面 把日期传到 修改排诊的界面  标准的日期格式
	private String date;//日期  带日月的格式
	private String am;
	private String pm;
	private String xingqi;//显示星期 在查看排诊界面
	
	
	
	public String getXiuday() {
		try {
			if(this.getAmstart()==null){
				Timestamp now = new Timestamp(System.currentTimeMillis()); 
				SimpleDateFormat biao=new SimpleDateFormat("yyyy-MM-dd");
				String now1=biao.format(now)+" 00:00:00";
				String jilu=biao.format(this.getPmstart())+" 00:00:00";
				Timestamp aa=Timestamp.valueOf(now1);
				Timestamp bb=Timestamp.valueOf(jilu);
				if(bb.after(aa)){
			SimpleDateFormat ts=new SimpleDateFormat("yyyy-MM-dd");
			return  ts.format(this.getPmstart());
			}else return null;
				
			}else{
				Timestamp now = new Timestamp(System.currentTimeMillis()); 
				SimpleDateFormat biao=new SimpleDateFormat("yyyy-MM-dd");
				String now1=biao.format(now)+" 00:00:00";
				String jilu=biao.format(this.getAmstart())+" 00:00:00";
				Timestamp aa=Timestamp.valueOf(now1);
				Timestamp bb=Timestamp.valueOf(jilu);
				if(bb.after(aa)){
			SimpleDateFormat ts=new SimpleDateFormat("yyyy-MM-dd");
			return  ts.format(this.getAmstart());
			}else return null;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public void setXiuday(String xiuday) {
		this.xiuday = xiuday;
	}

	

	// Constructors

	public String getXingqi() {
		if(this.getAmstart()==null){
			 
			SimpleDateFormat s=new SimpleDateFormat("E");
			return  s.format(this.getPmstart());
			}else{
				SimpleDateFormat s=new SimpleDateFormat("E");
				return  s.format(this.getAmstart());
			}
	}

	public void setXingqi(String xingqi) {
		this.xingqi = xingqi;
	}
//这个是不去除以前排诊记录的
//	public String getDate() {
//		if(this.getAmstart()==null){
//		 
//		SimpleDateFormat ts=new SimpleDateFormat("yyyy年MM月dd日");
//		return  ts.format(this.getPmstart());
//		}else{
//			SimpleDateFormat ts=new SimpleDateFormat("yyyy年MM月dd日");
//			return  ts.format(this.getAmstart());
//		}
//		
//	}
	
	//去除以前排诊记录的
	public String getDate() {
		try {
			System.out.println("调用了我");
			//System.out.println("this.getAmstart()"+this.getAmstart()+"  长度  "+this.getAmstart().toString().length());
			System.out.println("this.getAmstart()==null"+(this.getAmstart()==null));
			if(this.getAmstart()==null){
				//System.out.println("进入amstart"+this.getAmstart());
				System.out.println("输出pmstart"+this.getPmstart());
				Timestamp now = new Timestamp(System.currentTimeMillis()); 
				SimpleDateFormat biao=new SimpleDateFormat("yyyy-MM-dd");
				String now1=biao.format(now)+" 00:00:00";
				String jilu=biao.format(this.getPmstart())+" 00:00:00";
				Timestamp aa=Timestamp.valueOf(now1);
				Timestamp bb=Timestamp.valueOf(jilu);
				System.out.println(aa.toString());
				System.out.println(bb.toString());
				System.out.println("bb.after(aa)"+bb.after(aa));
				if(bb.after(aa)){
			        SimpleDateFormat ts=new SimpleDateFormat("yyyy年MM月dd日");
			        System.out.println("返回的日期数据"+ts.format(this.getPmstart()));
			         return  ts.format(this.getPmstart());
			         }else return null;	
			}else{
				System.out.println("进入pmstart"+this.getPmstart());
				System.out.println("输出amstart"+this.getAmstart());
				Timestamp now = new Timestamp(System.currentTimeMillis()); 
				SimpleDateFormat biao=new SimpleDateFormat("yyyy-MM-dd");
				String now1=biao.format(now)+" 00:00:00";
				String jilu=biao.format(this.getAmstart())+" 00:00:00";
				Timestamp aa=Timestamp.valueOf(now1);
				Timestamp bb=Timestamp.valueOf(jilu);
				System.out.println(aa.toString());
				System.out.println(bb.toString());
				System.out.println("bb.after(aa)"+bb.after(aa));
				if(bb.after(aa)){
			     SimpleDateFormat ts=new SimpleDateFormat("yyyy年MM月dd日");
			     System.out.println("返回的日期数据"+ts.format(this.getAmstart()));
			     return  ts.format(this.getAmstart());
			     }else return null;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
			return null;
		}
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getAm() {
		if(this.getAmstart()==null){
			return "上午没有排诊";
			
		}else{
			SimpleDateFormat ts=new SimpleDateFormat("HH点mm分");
			String am1=ts.format(this.getAmstart());
			String am2=ts.format(this.getAmend());
			String am=am1+"--"+am2;
			return am;
		}
		
	}

	public void setAm(String am) {
		this.am = am;
	}

	public String getPm() {
		if(this.getPmstart()==null){
			return "下午没有排诊";
			
		}else{
			SimpleDateFormat ts=new SimpleDateFormat("HH点mm分");
			String am1=ts.format(this.getPmstart());
			String am2=ts.format(this.getPmend());
			String am=am1+"--"+am2;
			return am;
		}
	}

	public void setPm(String pm) {
		this.pm = pm;
	}

	/** default constructor */
	public Time() {
	}

	/** minimal constructor */
	public Time(Employee employee) {
		this.employee = employee;
	}

	/** full constructor */
	public Time(Employee employee, Timestamp amstart, Timestamp amend,
			Timestamp pmstart, Timestamp pmend) {
		this.employee = employee;
		this.amstart = amstart;
		this.amend = amend;
		this.pmstart = pmstart;
		this.pmend = pmend;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Employee getEmployee() {
		return this.employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Timestamp getAmstart() {
		return this.amstart;
	}

	public void setAmstart(Timestamp amstart) {
		this.amstart = amstart;
	}

	public Timestamp getAmend() {
		return this.amend;
	}

	public void setAmend(Timestamp amend) {
		this.amend = amend;
	}

	public Timestamp getPmstart() {
		return this.pmstart;
	}

	public void setPmstart(Timestamp pmstart) {
		this.pmstart = pmstart;
	}

	public Timestamp getPmend() {
		return this.pmend;
	}

	public void setPmend(Timestamp pmend) {
		this.pmend = pmend;
	}

	public String getDatejson(){
		if(this.getAmstart()==null){

			SimpleDateFormat ts=new SimpleDateFormat("yyyy-MM-dd");
			return  ts.format(this.getPmstart());
			}else{
				SimpleDateFormat ts=new SimpleDateFormat("yyyy-MM-dd");
				return  ts.format(this.getAmstart());
			}
	}
	
	public String getAmjson() {
		if(this.getAmstart()==null){
			return "无排诊";

		}else{
			SimpleDateFormat ts=new SimpleDateFormat("HH:mm");
			String am1=ts.format(this.getAmstart());
			String am2=ts.format(this.getAmend());
			String am=am1+"-"+am2;
			return am;
		}

	}
	public String getPmjson() {
		if(this.getPmstart()==null){
			return "无排诊";

		}else{
			SimpleDateFormat ts=new SimpleDateFormat("HH:mm");
			String am1=ts.format(this.getPmstart());
			String am2=ts.format(this.getPmend());
			String am=am1+"-"+am2;
			return am;
		}
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("employeeid", getEmployee().getEmployeeId());
		jsonObject.put("date",getDatejson());
		jsonObject.put("xingqi", getXingqi());
		jsonObject.put("am", getAmjson());
		jsonObject.put("pm",getPmjson());
	    return	jsonObject.toString();
			
	}
	
	

}