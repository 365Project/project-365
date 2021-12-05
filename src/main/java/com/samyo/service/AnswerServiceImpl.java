package com.samyo.service;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.samyo.domain.AnswerVO;
import com.samyo.domain.QuestionVO;
import com.samyo.mapper.AnswerMapper;

@Service("answerService")

public class AnswerServiceImpl implements AnswerService {

	@Autowired
	private SqlSession sqlSession;
	
	
	@Override
	public int insertAnswer(AnswerVO answer) throws Exception {
		
		AnswerMapper answerMapper = sqlSession.getMapper(AnswerMapper.class);
		answerMapper.insertAnswer(answer);
		return 0;
	}


	@Override
	public List<AnswerVO> ReadAnswer(int question_num, int member_num) throws Exception {

		AnswerMapper answerMapper = sqlSession.getMapper(AnswerMapper.class);
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("question_num", question_num);
		map.put("member_num", member_num);
		
		List<AnswerVO> result =answerMapper.selectAnswer(map);
		return result;
	}


	@Override
	public AnswerVO UpdateAnswerPage(int answer_num) {

		System.out.println("수정 페이지/ service name: Update");
		
		AnswerMapper answerMapper = sqlSession.getMapper(AnswerMapper.class);
		
		AnswerVO result =answerMapper.updateAnswerPage(answer_num);
		return result;
	}
}
