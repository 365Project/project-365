package com.samyo.mapper;

import java.util.HashMap;

public interface AnswerCountMapper {
	
	//public void readCount(int question_num, int member_num);

	public int readCount(HashMap<String, Integer> map);

}
