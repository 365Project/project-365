package com.samyo.web;

import org.springframework.web.bind.annotation.RequestMapping;

import com.samyo.domain.DiaryVO;

public class DiaryController {


	//답변 장성하기 ,POST
	@RequestMapping("/diary")
	public String Write(DiaryVO diary) {
		System.out.println("답변작성 페이지로 넘어갑니다:controller name Write");
		
		return null;
		
	}
	
	
	
	
	//내답변 조회
	//내답변 수정
	//내답변 삭제
}
