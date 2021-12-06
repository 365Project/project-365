package com.samyo.service;

import java.util.List;

import com.samyo.domain.AnswerVO;
import com.samyo.domain.QuestionVO;

public interface AnswerService {

	public int insertAnswer(AnswerVO answer) throws Exception;
	//public List ReadAnswer(int question_num) throws Exception;
	//public AnswerVO UpdateAnswer(int question_num);
	public List<AnswerVO> ReadAnswer(int question_num, int member_num) throws Exception;
	public AnswerVO UpdateAnswerPage(int answer_num);
	//public AnswerVO UpdateAnswer(int answer_num, int member_num);
	public void UpdateAnswer(AnswerVO answer);
	public void UpdateDelete(AnswerVO answer);
	public void publicAnswer(AnswerVO answer);
	public void TrashPublic(AnswerVO answer);
	
	
}
