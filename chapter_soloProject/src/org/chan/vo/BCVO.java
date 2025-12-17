package org.chan.vo;

import java.sql.Date;

public class BCVO {
	private int b_idx;
	private String title;
	private String content;
	private Date reg_date;
	
	public BCVO() {}

	public BCVO(int b_idx, String title, String content, Date reg_date) {
		this.b_idx = b_idx;
		this.title = title;
		this.content = content;
		this.reg_date = reg_date;
	}

	public int getB_idx() {
		return b_idx;
	}

	public void setB_idx(int b_idx) {
		this.b_idx = b_idx;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getReg_date() {
		return reg_date;
	}

	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
	
}
