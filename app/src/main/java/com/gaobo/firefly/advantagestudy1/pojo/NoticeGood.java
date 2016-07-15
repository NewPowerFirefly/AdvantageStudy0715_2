package com.gaobo.firefly.advantagestudy1.pojo;

import java.io.Serializable;

public class NoticeGood implements Serializable {
	
	private int goodId;
	private Notice notId;
	private PersonInfo perId;
	private boolean good;
	
	public NoticeGood() {
		super();
		// TODO Auto-generated constructor stub
	}
	public NoticeGood(int goodId, Notice notId, PersonInfo perId, boolean good) {
		super();
		this.goodId = goodId;
		this.notId = notId;
		this.perId = perId;
		this.good = good;
	}
	public NoticeGood(Notice notId, PersonInfo perId, boolean good) {
		super();
		this.notId = notId;
		this.perId = perId;
		this.good = good;
	}
	public int getGoodId() {
		return goodId;
	}
	public void setGoodId(int goodId) {
		this.goodId = goodId;
	}
	public Notice getNotId() {
		return notId;
	}
	public void setNotId(Notice notId) {
		this.notId = notId;
	}
	public PersonInfo getPerId() {
		return perId;
	}
	public void setPerId(PersonInfo perId) {
		this.perId = perId;
	}
	public boolean isGood() {
		return good;
	}
	public void setGood(boolean good) {
		this.good = good;
	}
	@Override
	public String toString() {
		return "NoticeGood [good=" + good + ", goodId=" + goodId + ", notId="
				+ notId + ", perId=" + perId + "]";
	}
}
