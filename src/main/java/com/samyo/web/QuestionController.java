package com.samyo.web;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.samyo.domain.QuestionVO;
import com.samyo.service.QuestionService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/question")
public class QuestionController {

	@Autowired
	private QuestionService questionService;
	
	@GetMapping(value = "/{question_num}", produces = "application/json; charset=utf-8")
	public ResponseEntity<QuestionVO> getQuestion(@PathVariable("question_num") int question_num) throws Exception {
		System.out.println("넘어온 질문번호 :: " + question_num);

		Calendar today = Calendar.getInstance();
		// 올해 연도
		System.out.print("이 해의 연도 :: ");
		System.out.println(today.get(Calendar.YEAR));
		
		QuestionVO question = new QuestionVO();
		question = questionService.getQuestion(question_num);
		
		int year = today.get(Calendar.YEAR);
		//int year = 2020; // 윤년 test
		//yearChk(year); // 1: 윤년 2: 평년

		// 평년인지 체크 평년이면 true, 윤년이면 false
		if (yearChk(year) && question_num < 60) { // 평년이면서 1~2월
			
			//Calendar today = Calendar.getInstance();
			if (question_num != today.get(Calendar.DAY_OF_YEAR) + 1) {
				System.out.println("오늘 아님!1");
				return new ResponseEntity<>(question, HttpStatus.BAD_REQUEST);
				
			} else {
				System.out.println("오늘 맞음!");
				return new ResponseEntity<>(question, HttpStatus.OK);
			}
		
		} else if (yearChk(year) && question_num > 60){ // 평년이면서 3~12월
			
			if (question_num != today.get(Calendar.DAY_OF_YEAR) + 2) {
				System.out.println("오늘 아님!2");
				return new ResponseEntity<>(question, HttpStatus.BAD_REQUEST);
				
			} else {
				System.out.println("오늘 맞음!");
				return new ResponseEntity<>(question, HttpStatus.OK);
			}
			
			
			
		} else { // 윤년일떄
			
			if (question_num != today.get(Calendar.DAY_OF_YEAR) + 1) {
				System.out.println("오늘 아님!3");
				return new ResponseEntity<>(question, HttpStatus.BAD_REQUEST);
				
			} else {
				System.out.println("오늘 맞음!");
				return new ResponseEntity<>(question, HttpStatus.OK);
			}
			
		}
	
	}
	
	
	// 나의 일기장, 전체 조회 페이지 - 질문
	@GetMapping(value = "calendars/{question_num}", produces = "application/json; charset=utf-8")
	public ResponseEntity<QuestionVO> getQuestions(@PathVariable("question_num") int question_num) throws Exception {
		System.out.println("넘어온 질문번호 :: " + question_num);

		QuestionVO question = new QuestionVO();
		question = questionService.getQuestion(question_num);

		if (question == null) {
			System.out.println("잘못된 질문 번호. 잘못된 요청");
			return new ResponseEntity<>(question, HttpStatus.BAD_REQUEST);
		} else {
			System.out.println(question);
			return new ResponseEntity<>(question, HttpStatus.OK);
		}
	}
	
	// 윤년 || 평년 check
	static boolean yearChk(int year){
		// int yearResult = 0;
		
		if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0 ) {
			// 윤년
			System.out.println("올해는 윤년입니다.");
			return false;
		}else {
			//평년
			System.out.println("올해는 평년입니다.");
			return true;
		}
	}
	
	// 오늘인지 check - 메인화면에 오늘 질문만 뿌려주기 위해.
	static boolean todayChk(int question_num) {
		
		Calendar today = Calendar.getInstance();
		if (question_num != today.get(Calendar.DAY_OF_YEAR) + 1) {
			System.out.println("오늘 아님!");
			return false;
			
		} else {
			System.out.println("오늘 맞음!");
			return true;
		}
	}
	
	// 윤년 leap year 평년 common year
	/*
	static int commonYear(int question_num) {
		
		if (60 > question_num) {
			
			if (question_num != today.get(Calendar.DAY_OF_YEAR) + 1) {
				System.out.println("오늘 아님!");
				question = null;
				return new ResponseEntity<>(question, HttpStatus.BAD_REQUEST);
			} else {
				System.out.println(question);
				return new ResponseEntity<>(question, HttpStatus.OK);
			}

		} 
		
		if (60 < question_num) {
			
			if (question_num != today.get(Calendar.DAY_OF_YEAR) + 1) {
				System.out.println("오늘 아님!");
				question = null;
				return new ResponseEntity<>(question, HttpStatus.BAD_REQUEST);
			} else {
				System.out.println(question);
				return new ResponseEntity<>(question, HttpStatus.OK);
			}
			
		}
		
		return 0;
	}
	
	static int leapYear (int yearResult) {
		
		return 0;
	}
	*/
}