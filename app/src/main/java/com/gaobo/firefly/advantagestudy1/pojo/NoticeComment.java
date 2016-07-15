package com.gaobo.firefly.advantagestudy1.pojo;

import java.io.Serializable;

public class NoticeComment implements Serializable{


	private Integer comId;
	private PersonInfo perId;
	private Notice noticeId;
	private String comContent;
	private boolean comGood;
	private String date;

	public NoticeComment(String date, String comContent, Notice noticeId, PersonInfo perId) {
		this.date = date;
		this.comContent = comContent;
		this.noticeId = noticeId;
		this.perId = perId;
	}

	public NoticeComment(PersonInfo perId, Notice noticeId, String comContent, String date) {
		this.perId = perId;
		this.noticeId = noticeId;
		this.comContent = comContent;
		this.date = date;
	}

	public NoticeComment(PersonInfo perId, Notice noticeId, boolean comGood, String date) {
		this.perId = perId;
		this.noticeId = noticeId;
		this.comGood = comGood;
		this.date = date;
	}

	public NoticeComment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NoticeComment(Integer comId, PersonInfo perId, Notice noticeId,
						 String comContent, boolean comGood, String date) {
		super();
		this.comId = comId;
		this.perId = perId;
		this.noticeId = noticeId;
		this.comContent = comContent;
		this.comGood = comGood;
		this.date = date;
	}

	public Integer getComId() {
		return comId;
	}

	public void setComId(Integer comId) {
		this.comId = comId;
	}

	public PersonInfo getPerId() {
		return perId;
	}

	public void setPerId(PersonInfo perId) {
		this.perId = perId;
	}

	public Notice getNoticeId() {
		return noticeId;
	}

	public void setNoticeId(Notice noticeId) {
		this.noticeId = noticeId;
	}

	public String getComContent() {
		return comContent;
	}

	public void setComContent(String comContent) {
		this.comContent = comContent;
	}

	public boolean isComGood() {
		return comGood;
	}

	public void setComGood(boolean comGood) {
		this.comGood = comGood;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "NoticeComment [comContent=" + comContent + ", comGood="
				+ comGood + ", comId=" + comId + ", date=" + date
				+ ", noticeId=" + noticeId + ", perId=" + perId + "]";
	}



}
