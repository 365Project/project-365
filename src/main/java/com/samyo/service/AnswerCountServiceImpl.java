package com.samyo.service;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.samyo.mapper.AnswerCountMapper;

@Service("answerCountService")
public class AnswerCountServiceImpl implements AnswerCountService{
	@Autowired
	private SqlSession sqlSession;

	@Override
	public int readCount(int question_num, int member_num) {
		
		AnswerCountMapper countMapper = sqlSession.getMapper(AnswerCountMapper.class);
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("question_num", question_num);
		map.put("member_num", member_num);
		
		int result =countMapper.readCount(map);
		return result;
	}
	

}
