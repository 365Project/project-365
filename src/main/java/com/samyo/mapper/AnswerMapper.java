package com.samyo.mapper;

import java.util.HashMap;
import java.util.List;

import com.samyo.domain.AnswerVO;
import com.samyo.domain.QuestionVO;

public interface AnswerMapper {

	public void insertAnswer(AnswerVO answer);

	//public void selectAnswer(AnswerVO answer);

	public List<AnswerVO> selectAnswer(HashMap<String, Integer> map);

	public AnswerVO updateAnswerPage(int answer_num);


	//public AnswerVO updateAnswer(HashMap<String, Integer> map);

	public void updateAnswer(AnswerVO answer);

	public void updateDelete(AnswerVO answer);

	public void publicAnswer(AnswerVO answer);

	public void TrashPublic(AnswerVO answer);

}
