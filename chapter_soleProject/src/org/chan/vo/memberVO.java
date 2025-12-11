package org.chan.vo;

import java.sql.Date;

public class memberVO {
	private int m_idx;
	private String mId;
	private String mPw;
	private String mName;
	private Date mRegDate;
	
	public memberVO() {}

	public memberVO(int m_idx, String mId, String mPw, String mName, Date mRegDate) {
		super();
		this.m_idx = m_idx;
		this.mId = mId;
		this.mPw = mPw;
		this.mName = mName;
		this.mRegDate = mRegDate;
	}

	public int getM_idx() {
		return m_idx;
	}

	public void setM_idx(int m_idx) {
		this.m_idx = m_idx;
	}

	public String getmId() {
		return mId;
	}

	public void setmId(String mId) {
		this.mId = mId;
	}

	public String getmPw() {
		return mPw;
	}

	public void setmPw(String mPw) {
		this.mPw = mPw;
	}

	public String getmName() {
		return mName;
	}

	public void setmName(String mName) {
		this.mName = mName;
	}

	public Date getmRegDate() {
		return mRegDate;
	}

	public void setmRegDate(Date mRegDate) {
		this.mRegDate = mRegDate;
	}
	
}
