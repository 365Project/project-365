package com.samyo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.samyo.domain.QuestionVO;
import com.samyo.service.QuestionService;

@RestController
public class QuestionController {

	@Autowired
	private QuestionService questionService;

	@RequestMapping(value = "/question")
	public QuestionVO getQuestion() throws Exception {

		QuestionVO question = new QuestionVO();
		question.setQuestion_num(1);
		question.setQuestion("새해첫날입니다.");
		
		/*
		int question_num = 1;

		QuestionVO question = questionService.getQuestion(question_num);

		System.out.println("question !!!!!!!!!!!!!!!! ");

		 question.getQuestion_num(); question.getQuestion();
		 */
		
		return question;
	}
}