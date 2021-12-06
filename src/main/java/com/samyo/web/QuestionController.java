package com.samyo.web;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.samyo.domain.QuestionVO;
import com.samyo.service.QuestionService;

@RestController
@RequestMapping("/question")
public class QuestionController {

	@Autowired
	private QuestionService questionService;

	@GetMapping(value = "/{question_num}", produces = "application/json; charset=utf-8")
	public ResponseEntity<QuestionVO> getQuestion(@PathVariable("question_num") int question_num) throws Exception {
		System.out.println("넘어온 질문번호 :: " + question_num);

		// 받아온 질문 번호와 오늘 날짜를 비교하기 위해
		Calendar today = Calendar.getInstance();
		System.out.print("이 해의 며칠 :: ");
		System.out.println(today.get(Calendar.DAY_OF_YEAR) + 1);

		QuestionVO question = new QuestionVO();
		question = questionService.getQuestion(question_num);

		if (question_num != today.get(Calendar.DAY_OF_YEAR) + 1) {
			System.out.println("오늘 아님!");
			question = null;
			return new ResponseEntity<>(question, HttpStatus.BAD_REQUEST);
		} else {
			System.out.println(question);
			return new ResponseEntity<>(question, HttpStatus.OK);
		}
	}
	
	@GetMapping(value = "calendars/{question_num}", produces = "application/json; charset=utf-8")
	public ResponseEntity<QuestionVO> getQuestions(@PathVariable("question_num") int question_num) throws Exception {
		System.out.println("넘어온 질문번호 :: " + question_num);

		QuestionVO question = new QuestionVO();
		question = questionService.getQuestion(question_num);

		if (question == null) {
			System.out.println("366개의 질문이 아님. 잘못된 요청");
			return new ResponseEntity<>(question, HttpStatus.BAD_REQUEST);
		} else {
			System.out.println(question);
			return new ResponseEntity<>(question, HttpStatus.OK);
		}
	}
}