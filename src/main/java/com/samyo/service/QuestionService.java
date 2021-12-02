package com.samyo.service;

import org.springframework.stereotype.Service;

import com.samyo.domain.QuestionVO;

@Service("questionService")
public interface QuestionService {
	
	public QuestionVO getQuestion(int question_num) throws Exception;
}
