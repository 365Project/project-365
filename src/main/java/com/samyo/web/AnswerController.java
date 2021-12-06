package com.samyo.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.samyo.domain.AnswerVO;
import com.samyo.service.AnswerService;

@CrossOrigin(origins = "*")
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
	@RequestMapping("/answers/new")
	public int write() throws Exception {
		System.out.println("답변작성 페이지/ controller name: write");
		System.out.println("year:"+year);
		System.out.println("date:"+date);
		
		AnswerVO answer = new AnswerVO();
		
		//answer.setAnswer_num(6);
		//answer.setAnswer_year(year);
		//answer.setAnswer_date(date);
		answer.setAnswer_year("2021");
		answer.setAnswer_date("0103");
		answer.setAnswer("d님의 답변입니다!");
		answer.setPublic_answer("Y");
		answer.setQuestion_num(3);
		answer.setMember_num(2);
		answer.setAnswer_delete("N");
		answer.setDelete_date(null);

		int result = answerService.insertAnswer(answer);
		int result2=0;
		if (result == 1 ) {
			
			AnswerCountVO answercount = new AnswerCountVO();
			answercount.setMember_num(answer.getMember_num());
			answercount.setQuestion_num(answer.getQuestion_num());
			
			result2 = answerService.count(answercount);
			if (result2==1) { //셋팅
				answerService.setCount(answercount);
			}
			else {//업또는 다운
				answerService.updateCountUp(answercount);
				
			}
			//AnswerCountVO answercount = new AnswerCountVO();
			//answercount.setMember_num(answer.getMember_num());
			//answercount.setQuestion_num(answer.getQuestion_num());
			
			//result2 = answerService.updateCountUp(answercount);
			
		}
		else {
			System.out.println("실패!!!!!!!!!!!!");
			result2=0;
		}
		//return result2;
		return result;
		
	}
	
	//내답변(일기장) 전체 조회			
	@GetMapping("/answers/{question_num}/{member_num}")
	public List<AnswerVO> read(@PathVariable("question_num") int question_num,@PathVariable("member_num") int member_num) throws Exception {
		System.out.println("수정 페이지/ controller name: read");
		System.out.println("question_num: "+question_num);
		System.out.println("member_num: "+member_num);
		
		List<AnswerVO> answer = answerService.readAnswer(question_num,member_num);
		System.out.println("--2-");
	
		//콘솔찍어보기
		for (AnswerVO data:answer) {
			System.out.println("answer: "+ answer);
		}
		System.out.println("--3-");
		
		return answer;
	}
	
	//내답변 수정버튼>수정페이지
	@GetMapping("/answers/pages/{answer_num}")
	public AnswerVO UpdatePage(@PathVariable("answer_num") int answer_num) throws Exception {
		System.out.println("수정 페이지/ controller name: updatePage");
		
		
		AnswerVO result = answerService.updateAnswerPage(answer_num);
		
		return result;
		
	}
	
	//수정페이지 > 내용수정후 > 수정버튼
	@RequestMapping(value="/answers/pages/{answer_num}/{member_num}", method = {RequestMethod.GET, RequestMethod.PATCH} )
	public void update(@PathVariable("answer_num") int answer_num, @PathVariable("member_num") int member_num)throws Exception {
		System.out.println("수정기능 시작! : controller name : update");
		
		AnswerVO answer = new AnswerVO();
		answer.setAnswer("이건 바뀔 내용이에요 !!!!");
		answer.setPublic_answer("Y");
		answer.setAnswer_num(answer_num);
		answer.setMember_num(member_num);
		
		System.out.println("Answer: "+answer.getAnswer());
		System.out.println("public_answer: "+answer.getPublic_answer());
		System.out.println("Answer_num: " +answer.getAnswer_num());
		
		answerService.updateAnswer(answer);
		System.out.println("=========수정완료=========");
	
	}
	
	//내일기장 > 공개여부 버튼
	@RequestMapping(value="/settings/{answer_num}/{member_num}", method = {RequestMethod.GET, RequestMethod.PATCH} )
	public void publicAnswer(@PathVariable("answer_num") int answer_num, @PathVariable("member_num") int member_num)throws Exception {
		System.out.println("공개여부 변경 시작! : controller name : publicAnswer");
		
		AnswerVO answer = new AnswerVO();
		answer.setPublic_answer("Y");
		answer.setAnswer_num(answer_num);
		answer.setMember_num(member_num);
		
		System.out.println("public_answer: "+answer.getPublic_answer());
		System.out.println("Answer_num: " +answer.getAnswer_num());
		
		answerService.publicAnswer(answer);
		System.out.println("=========공개여부 수정완료=========");
	
	}
	
	
	//일기장> 내답변 삭제(휴지통으로)
	@RequestMapping(value="/answers/{answer_num}", method= {RequestMethod.GET, RequestMethod.PATCH})
	public void updateDelete(@PathVariable("answer_num") int answer_num, @PathVariable("member_num") int member_num) {
		
		System.out.println("삭제 수정기능 시작! : controller name : updateDelete");
		
		AnswerVO answer = new AnswerVO();
		answer.setAnswer_num(answer_num);
		answer.setMember_num(member_num);
		answer.setAnswer_delete("Y"); // Y=휴지통으로
		answer.setDelete_date("20211205"); //휴지통에 넣은 날짜
		
		System.out.println("Answer_delete: "+answer.getAnswer_delete());
		System.out.println("Delete_date: "+answer.getDelete_date());
		System.out.println("Answer_num: " +answer.getAnswer_num());
		System.out.println("member_num: " +answer.getMember_num());
		
		answerService.updateDelete(answer);
		System.out.println("=========삭제 수정완료=========");
		
	}
	
	//================== 휴지통 ==========================
	//휴지통 > 되돌리기 버튼(답변 복구)
	@RequestMapping(value="/trashes/settings/{answer_num}/{member_num}", method= {RequestMethod.GET, RequestMethod.PATCH})
	public void trashPublic(@PathVariable("answer_num") int answer_num, @PathVariable("member_num") int member_num) {
		
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
		
		answerService.trashPublic(answer);
		System.out.println("=========삭제 수정완료=========");
		
	}

	//휴지통 > 삭제한 답변 모두 보기
	@GetMapping("/trashes/{member_num}")
	public List<AnswerVO> trashRead(@PathVariable("member_num") int member_num) throws Exception {
		System.out.println("답변 복원하기 시작! : controller name : trashRead");
		System.out.println("member_num: "+member_num);
		
		List<AnswerVO> answer = answerService.readTrash(member_num);
	
		return answer;
	}
	
	
	
	
}

