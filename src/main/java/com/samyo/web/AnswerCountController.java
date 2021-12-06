package com.samyo.web;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.samyo.service.AnswerCountService;

@CrossOrigin(origins = "*")
@RestController
public class AnswerCountController {

	SimpleDateFormat format1 = new SimpleDateFormat ( "yyyy");
	SimpleDateFormat format2 = new SimpleDateFormat ( "MMdd");
			
	Date time = new Date();
			
	String year = format1.format(time);
	String date = format2.format(time);
	
	@Autowired
	private AnswerCountService answerCountService;
	
	//해당 날짜에 있는 답변 갯수 조회하기
	@GetMapping("/numbers/{question_num}/{member_num}")
	public int countAnswer(@PathVariable("question_num") int question_num,@PathVariable("member_num") int member_num) {
		System.out.println("수정 페이지/ controller name: read");
		System.out.println("member_num: "+member_num);
		System.out.println("question_num: "+question_num);
		
		int result = answerCountService.readCount(question_num,member_num);
		
		return result;
	}
	
}
