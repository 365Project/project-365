package com.samyo.web;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.samyo.domain.AnswerVO;
import com.samyo.service.AnswerService;


@RestController
public class AnswerController {
	
	SimpleDateFormat format1 = new SimpleDateFormat ( "yyyy");
	SimpleDateFormat format2 = new SimpleDateFormat ( "MMdd");
			
	Date time = new Date();
			
	String year = format1.format(time);
	String date = format2.format(time);
			
	
	
	@Autowired
	private AnswerService answerService;	

	//답변 작성하기 ,POST
	@RequestMapping("/answer/write")
	public AnswerVO Write() throws Exception {
		System.out.println("답변작성 페이지/ controller name: Write");
		System.out.println("year:"+year);
		System.out.println("date:"+date);
		
		AnswerVO answer = new AnswerVO();
		
		answer.setAnswer_num(4);
		//answer.setAnswer_year(year);
		//answer.setAnswer_date(date);
		answer.setAnswer_year("2023");
		answer.setAnswer_date("0101");
		answer.setAnswer("answer3");
		answer.setPublic_answer("Y");
		answer.setQuestion_num(1);
		answer.setMember_num(2);
		answer.setAnswer_delete("N");
		answer.setDelete_date(null);

		answerService.insertAnswer(answer);
		return answer;
		
	}
	
	//내답변 전체 조회			
	@GetMapping("/answer/read/{question_num}/{member_num}")
	public List<AnswerVO> Read(@PathVariable("question_num") int question_num,@PathVariable("member_num") int member_num) throws Exception {
		System.out.println("question_num: "+question_num);
		System.out.println("member_num: "+member_num);
		
		List<AnswerVO> answer = answerService.ReadAnswer(question_num,member_num);
		System.out.println("--2-");
	
		//콘솔찍어보기
		for (AnswerVO data:answer) {
			System.out.println("answer: "+ answer);
		}
		System.out.println("--3-");
		
		return answer;
	}
	
	//내답변 수정버튼>수정페이지
	@GetMapping("/answer/update/{answer_num}")
	public AnswerVO UpdatePage(@PathVariable("answer_num") int answer_num) throws Exception {
		System.out.println("수정 페이지/ controller name: Update");
		
		
		AnswerVO result = answerService.UpdateAnswerPage(answer_num);
		
		return result;
		
	}
	
	//수정페이지 > 내용수정후 > 수정버튼
	@RequestMapping(value="/answer/update/{answer_num}/{member_num}", method = {RequestMethod.GET, RequestMethod.POST} )
	public void Update(@PathVariable("answer_num") int answer_num, @PathVariable("member_num") int member_num)throws Exception {
		System.out.println("수정기능 시작! : controller name : Update");
		
		AnswerVO answer = new AnswerVO();
		answer.setAnswer("이건 바뀔 내용이에요 !!!!");
		answer.setPublic_answer("Y");
		answer.setAnswer_num(answer_num);
		answer.setMember_num(member_num);
		
		System.out.println("Answer: "+answer.getAnswer());
		System.out.println("public_answer: "+answer.getPublic_answer());
		System.out.println("Answer_num: " +answer.getAnswer_num());
		
		answerService.UpdateAnswer(answer);
		System.out.println("=========수정완료=========");
	
	}
	
	//내일기장 > 공개여부 버튼
	@RequestMapping(value="/answer/public/{answer_num}/{member_num}", method = {RequestMethod.GET, RequestMethod.POST} )
	public void PublicAnswer(@PathVariable("answer_num") int answer_num, @PathVariable("member_num") int member_num)throws Exception {
		System.out.println("공개여부 변경 시작! : controller name : PublicAnswer");
		
		AnswerVO answer = new AnswerVO();
		answer.setPublic_answer("Y");
		answer.setAnswer_num(answer_num);
		answer.setMember_num(member_num);
		
		System.out.println("public_answer: "+answer.getPublic_answer());
		System.out.println("Answer_num: " +answer.getAnswer_num());
		
		answerService.publicAnswer(answer);
		System.out.println("=========공개여부 수정완료=========");
	
	}
	
	
	//내답변 삭제(휴지통으로)
	@RequestMapping(value="/answer/update/delete/{answer_num}/{member_num}", method= {RequestMethod.GET, RequestMethod.POST})
	public void UpdateDelete(@PathVariable("answer_num") int answer_num, @PathVariable("member_num") int member_num) {
		
		System.out.println("삭제 수정기능 시작! : controller name : UpdateDelete");
		
		AnswerVO answer = new AnswerVO();
		answer.setAnswer_num(answer_num);
		answer.setMember_num(member_num);
		answer.setAnswer_delete("Y"); // Y=휴지통으로
		answer.setDelete_date("20211205"); //휴지통에 넣은 날짜
		
		System.out.println("Answer_delete: "+answer.getAnswer_delete());
		System.out.println("Delete_date: "+answer.getDelete_date());
		System.out.println("Answer_num: " +answer.getAnswer_num());
		System.out.println("member_num: " +answer.getMember_num());
		
		answerService.UpdateDelete(answer);
		System.out.println("=========삭제 수정완료=========");
		
	}
	
	//================== 휴지통 ==========================
	//내 답변 복구하기
	@RequestMapping(value="/answer/trash/public/{answer_num}/{member_num}", method= {RequestMethod.GET, RequestMethod.POST})
	public void TrashPublic(@PathVariable("answer_num") int answer_num, @PathVariable("member_num") int member_num) {
		
		System.out.println("답변 복원하기 시작! : controller name : TrashPublic");
		
		AnswerVO answer = new AnswerVO();
		answer.setAnswer_num(answer_num);
		answer.setMember_num(member_num);
		answer.setAnswer_delete("N"); // N=내일기장으로
		answer.setDelete_date(""); //휴지통에 넣은 날짜
		
		System.out.println("Answer_delete: "+answer.getAnswer_delete());
		System.out.println("Delete_date: "+answer.getDelete_date());
		System.out.println("Answer_num: " +answer.getAnswer_num());
		System.out.println("member_num: " +answer.getMember_num());
		
		answerService.TrashPublic(answer);
		System.out.println("=========삭제 수정완료=========");
		
	}

	
	
	
	
	
}

