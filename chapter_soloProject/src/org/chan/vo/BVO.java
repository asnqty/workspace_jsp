package org.chan.vo;

import java.sql.Date;

public class BVO {
	private int b_idx;
	private String writer;
	private String title;
	private String content; 
	private int hit;
	private Date reg_date;
	
	public BVO() {}

	public BVO(int b_idx, String writer, String title, String content, int hit, Date reg_date) {
		this.b_idx = b_idx;
		this.writer = writer;
		this.title = title;
		this.content = content;
		this.hit = hit;
		this.reg_date = reg_date;
	}

	public int getB_idx() {
		return b_idx;
	}

	public void setB_idx(int b_idx) {
		this.b_idx = b_idx;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
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

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public Date getReg_date() {
		return reg_date;
	}

	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
	
	
}
