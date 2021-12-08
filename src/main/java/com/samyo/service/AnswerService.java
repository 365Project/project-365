package com.samyo.service;

import java.util.List;

import com.samyo.domain.AnswerCountVO;
import com.samyo.domain.AnswerVO;
import com.samyo.domain.QuestionVO;

public interface AnswerService {

	public int insertAnswer(AnswerVO answer) throws Exception;
	//public List ReadAnswer(int question_num) throws Exception;
	//public AnswerVO UpdateAnswer(int question_num);
	public List<AnswerVO> readAnswer(int question_num, int member_num) throws Exception;
	public AnswerVO updateAnswerPage(int answer_num);
	//public AnswerVO UpdateAnswer(int answer_num, int member_num);
	public void updateAnswer(AnswerVO answer);
	public int updateDelete(AnswerVO answer);
	public int publicAnswer(AnswerVO answer);
	
	public int trashUpdate(AnswerVO answer);
	public List<AnswerVO> readTrash(int member_num);
	//public int deleteAnswer(int answer_num);
	
	public int count(AnswerCountVO answercount); //하나라도 답변이 존재하는지 판단
	public void setCount(AnswerCountVO answercount);
	//public int count(int question_num, int member_num);
	
	
	public int updateCountUp(AnswerCountVO answercount);
	public int updateCountDown(AnswerCountVO answercount);
	public int deleteAnswer(AnswerVO answer);
	public int countSelect(AnswerCountVO answercount); //달력에 뿌리기위해 답변 갯수 조회
	
	
	
	
	
}
