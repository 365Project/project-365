package com.samyo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.samyo.domain.QuestionVO;
import com.samyo.service.QuestionService;

@RestController
public class QuestionController {

	@Autowired
	private QuestionService questionService;

	@RequestMapping(value = "/question/{question_num}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public QuestionVO getQuestion(@PathVariable("question_num") int question_num) throws Exception {
		System.out.println(question_num);

		QuestionVO question = new QuestionVO();
		question = questionService.getQuestion(question_num);

		System.out.println(question);
		return question;
	}
}