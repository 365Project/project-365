package com.samyo.mapper;

import java.util.List;

import com.samyo.domain.AnswerVO;
import com.samyo.domain.QuestionVO;

public interface AnswerMapper {

	public void insertAnswer(AnswerVO answer);

	//public void selectAnswer(AnswerVO answer);

	public List<AnswerVO> selectAnswer(int question_num);

}
