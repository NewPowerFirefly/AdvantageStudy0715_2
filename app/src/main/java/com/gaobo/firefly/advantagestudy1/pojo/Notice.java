package com.gaobo.firefly.advantagestudy1.pojo;

import java.io.Serializable;

public class Notice implements Serializable{
	
	private Integer id;
	private String notTeacher;
	private String title;
	private String content;
	private String date;


	public Notice() {
	}

	public Notice(Integer id, String notTeacher, String title, String content, String date) {
		this.id = id;
		this.notTeacher = notTeacher;
		this.title = title;
		this.content = content;
		this.date = date;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNotTeacher() {
		return notTeacher;
	}

	public void setNotTeacher(String notTeacher) {
		this.notTeacher = notTeacher;
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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}


	@Override
	public String toString() {
		return "Notice{" +
				"id=" + id +
				", notTeacher='" + notTeacher + '\'' +
				", title='" + title + '\'' +
				", content='" + content + '\'' +
				", date='" + date + '\'' +
				'}';
	}
}
