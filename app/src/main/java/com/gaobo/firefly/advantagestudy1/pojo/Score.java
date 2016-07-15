package com.gaobo.firefly.advantagestudy1.pojo;

public class Score {

	private Integer id;
	private String youNumber;
	private String subject;
	private float score;
	private String day;
	private boolean flag;
	
	
	public Score(String youNumber, String subject, float score, String day,
			boolean flag) {
		super();
		this.youNumber = youNumber;
		this.subject = subject;
		this.score = score;
		this.day = day;
		this.flag = flag;
	}


	public Score() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Score(Integer id, String youNumber, String subject, float score,
				 String day, boolean flag) {
		super();
		this.id = id;
		this.youNumber = youNumber;
		this.subject = subject;
		this.score = score;
		this.day = day;
		this.flag = flag;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getYouNumber() {
		return youNumber;
	}


	public void setYouNumber(String youNumber) {
		this.youNumber = youNumber;
	}


	public String getSubject() {
		return subject;
	}


	public void setSubject(String subject) {
		this.subject = subject;
	}


	public float getScore() {
		return score;
	}


	public void setScore(float score) {
		this.score = score;
	}


	public String getDay() {
		return day;
	}


	public void setDay(String day) {
		this.day = day;
	}


	public boolean isFlag() {
		return flag;
	}


	public void setFlag(boolean flag) {
		this.flag = flag;
	}


	@Override
	public String toString() {
		return "Score [day=" + day + ", flag=" + flag + ", id=" + id
				+ ", score=" + score + ", subject=" + subject + ", youNumber="
				+ youNumber + "]";
	}
	
	
	
	
}
