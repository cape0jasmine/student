package model;

import java.io.Serializable;

public class Course implements Serializable{
	private static final long serialVersionUID = 3675795782517517393L;
	
	private String cid;
	private String cname;
	private String cgrade;

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getCgrade() {
		return cgrade;
	}

	public void setCgrade(String cgrade) {
		this.cgrade = cgrade;
	}

}
