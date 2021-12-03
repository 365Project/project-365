package com.samyo.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

public class AnswerVO {

	/*modelAttribute사용할때
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date Date; */
	
	
	/*@RequestParam (required = false) 
	@DateTimeFormat
	(pattern = "yyyy-MM-dd") Date Date;*/
	
	@JsonFormat(pattern = "yyyyMMdd")
	Date Date;
	
	private int answer_num;
	private Date answer_year;
	private Date answer_date;
	private String answer;
	private String public_answer;
	private int question_num;
	private int member_num;
	private String answer_delete;
	private Date delete_date;
	
	public Date getDate() {
		return Date;
	}
	public void setDate(Date date) {
		Date = date;
	}
	public int getAnswer_num() {
		return answer_num;
	}
	public void setAnswer_num(int answer_num) {
		this.answer_num = answer_num;
	}
	public Date getAnswer_year() {
		return answer_year;
	}
	public void setAnswer_year(Date answer_year) {
		this.answer_year = answer_year;
	}
	public Date getAnswer_date() {
		return answer_date;
	}
	public void setAnswer_date(Date answer_date) {
		this.answer_date = answer_date;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getPublic_answer() {
		return public_answer;
	}
	public void setPublic_answer(String public_answer) {
		this.public_answer = public_answer;
	}
	public int getQuestion_num() {
		return question_num;
	}
	public void setQuestion_num(int question_num) {
		this.question_num = question_num;
	}
	public int getMember_num() {
		return member_num;
	}
	public void setMember_num(int member_num) {
		this.member_num = member_num;
	}
	public String getAnswer_delete() {
		return answer_delete;
	}
	public void setAnswer_delete(String answer_delete) {
		this.answer_delete = answer_delete;
	}
	public Date getDelete_date() {
		return delete_date;
	}
	public void setDelete_date(Date delete_date) {
		this.delete_date = delete_date;
	}

	
	
	
	
	
	
	
	
	
	
	
}
