package org.chan.vo;

import java.sql.Date;

public class commentVO {
	private int c_idx;
	private String writer;
	private String content;
	private Date reg_date;
	private int b_idx;
	
	public commentVO() {}

	public commentVO(int c_idx, String writer, String content, Date reg_date, int b_idx) {
		this.c_idx = c_idx;
		this.writer = writer;
		this.content = content;
		this.reg_date = reg_date;
		this.b_idx = b_idx;
	}

	public int getC_idx() {
		return c_idx;
	}

	public void setC_idx(int c_idx) {
		this.c_idx = c_idx;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
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

	public int getB_idx() {
		return b_idx;
	}

	public void setB_idx(int b_idx) {
		this.b_idx = b_idx;
	}
	
}
