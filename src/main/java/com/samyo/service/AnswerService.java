package com.samyo.service;

import java.util.List;

import com.samyo.domain.AnswerVO;
import com.samyo.domain.QuestionVO;

public interface AnswerService {

	public int insertAnswer(AnswerVO answer) throws Exception;
	//public List<AnswerVO> ReadAnswer(AnswerVO answer) throws Exception;
	//public List<AnswerVO> ReadAnswer(int question_num, int member_num) throws Exception;
	public List ReadAnswer(int question_num) throws Exception;
	public List<AnswerVO> UpdateAnswer(int question_num, int member_num);
	
}
