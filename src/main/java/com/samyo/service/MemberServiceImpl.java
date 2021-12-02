package com.samyo.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.samyo.domain.MemberVO;
import com.samyo.mapper.MemberMapper;

@Service("memberService")
public class MemberServiceImpl implements MemberService {

	@Autowired
	private SqlSession sqlSession;

	@Override
	public int insertMember(MemberVO member) throws Exception {

		MemberMapper memberMapper = sqlSession.getMapper(MemberMapper.class);
		memberMapper.insertMember(member);
		return 0;
	}

	@Override
	public int getMember(String email) throws Exception {

		MemberMapper memberMapper = sqlSession.getMapper(MemberMapper.class);
		int res = memberMapper.getMember(email);

		return res;
	}

}
