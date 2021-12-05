package com.samyo.mapper;

import java.util.HashMap;
import java.util.List;

import com.samyo.domain.AnswerVO;
import com.samyo.domain.QuestionVO;

public interface AnswerMapper {

	public void insertAnswer(AnswerVO answer);

	//public void selectAnswer(AnswerVO answer);

	public List<AnswerVO> selectAnswer(HashMap<String, Integer> map);

	public List<AnswerVO> updateAnswer(HashMap<String, Integer> map);

}
