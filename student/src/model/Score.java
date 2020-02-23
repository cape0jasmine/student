package model;

import java.io.Serializable;

public class Score implements Serializable{

	private static final long serialVersionUID = 5705901874597555513L;
	
	private String id;
	private String student_id;
	private String subject;
	private String score;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStudent_id() {
		return student_id;
	}

	public void setStudent_id(String student_id) {
		this.student_id = student_id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "Score [id=" + id + ", student_id=" + student_id + ", subject=" + subject + ", score=" + score + "]";
	}
	
	

}
