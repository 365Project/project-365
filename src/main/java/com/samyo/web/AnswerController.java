package com.samyo.web;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.samyo.domain.AnswerVO;
import com.samyo.service.AnswerService;


@RestController
public class AnswerController {
	SimpleDateFormat format1 = new SimpleDateFormat ( "yyyy");
	SimpleDateFormat format2 = new SimpleDateFormat ( "MMdd");
			
	Date time = new Date();
			
	String year = format1.format(time);
	String date = format2.format(time);
			
	
	
	@Autowired
	private AnswerService answerService;
	
	/*@JsonFormat(pattern = "MMdd")
	Date Date;
	@JsonFormat(pattern = "yyyy")
	Date Year;*/
	
	

	//답변 작성하기 ,POST
	@RequestMapping("/answer/write")
	public AnswerVO Write() throws Exception {
		System.out.println("답변작성 페이지/ controller name: Write");
		System.out.println("year:"+year);
		System.out.println("date:"+date);
		
		AnswerVO answer = new AnswerVO();
		
		answer.setAnswer_num(1);
		answer.setAnswer_year(year);
		answer.setAnswer_date(date);
		answer.setAnswer("answer");
		answer.setPublic_answer("Y");
		answer.setQuestion_num(2);
		answer.setMember_num(2);
		answer.setAnswer_delete("N");
		answer.setDelete_date(null);

		answerService.insertAnswer(answer);
		return answer;
		
	}

		
	
	
	
	//내답변 조회(/diary)
	//내답변 수정
	//내답변 삭제

}

