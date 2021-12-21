package com.samyo.service;

import com.samyo.domain.QuestionVO;

public interface QuestionService {
	
	public QuestionVO getQuestion(int question_num) throws Exception;
}
