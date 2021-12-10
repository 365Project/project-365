package com.samyo.mapper;

import java.util.HashMap;
import java.util.List;

import com.samyo.domain.AnswerCountVO;
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

	public int trashUpdate(AnswerVO answer);

	public List<AnswerVO> readTrash(int member_num);

	

	//public String count(AnswerCountVO answercount);
	public Object count(AnswerCountVO answercount);

	public void setCount(AnswerCountVO answercount);

	public int updateCountUp(AnswerCountVO answercount);

	public int updateCountDown(AnswerCountVO answercount);

	public void deleteAnswer(AnswerVO answer);

	public int countSelect(AnswerCountVO answercount);

	public List<String> readRandomAnswer(int question_num);

	//public void test(AnswerVO answer);

	

	//public void deleteAnswer(int answer_num);

	

}
