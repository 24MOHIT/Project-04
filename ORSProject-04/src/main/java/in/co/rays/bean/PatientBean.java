package in.co.rays.bean;

import java.util.Date;

public class PatientBean extends BaseBean {

	private String name;
	private Date visitdate;
	private String mobile;
	private String decease;
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getVisitdate() {
		return visitdate;
	}

	public void setVisitdate(Date visitdate) {
		this.visitdate = visitdate;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getDecease() {
		return decease;
	}

	public void setDecease(String decease) {
		this.decease = decease;
	}

	@Override
	public String getKey() {
		return id + "";
	}

	@Override
	public String getValue() {
		return name;
	}

}
