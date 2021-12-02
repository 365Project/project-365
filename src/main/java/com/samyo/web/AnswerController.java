package com.samyo.web;

import java.util.Date;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.samyo.domain.AnswerVO;


@RestController
public class AnswerController {
	

	@JsonFormat(pattern = "MMdd")
	Date Date;
	@JsonFormat(pattern = "yyyy")
	Date Year;
	
	
	//답변 장성하기 ,POST
	@RequestMapping("/answer/write")
	public AnswerVO Write() {
		System.out.println("답변작성 페이지로 넘어갑니다/ controller name: Write");
		
		AnswerVO answer = new AnswerVO();
		
		answer.setAnswer_num(1);
		answer.setAnswer_year(Year);
		answer.setAnswer_date(Date);
		answer.setAnswer("answer");
		answer.setPublic_answer("Y");
		answer.setQuestion_num(2);
		answer.setMember_num(2);
		answer.setAnswer_delete("N");
		answer.setDelete_date(Date);

		System.out.println("Date: "+ Date);

		return answer;
		
	}

		
	//test
		@RequestMapping("/answer/test")
		public AnswerVO Test() {
			System.out.println("테스트페이지로 이동합니다.");
			
			AnswerVO answer = new AnswerVO();
			answer.setAnswer_num(0);
			answer.setAnswer("히히히...");
			
			return answer;
			
		}
	
	
	
	//내답변 조회(/diary)
	//내답변 수정
	//내답변 삭제

}
