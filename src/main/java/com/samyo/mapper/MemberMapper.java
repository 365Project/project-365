package com.samyo.mapper;

import com.samyo.domain.MemberVO;

public interface MemberMapper {

	public void insertMember(MemberVO member);
	
	public int getMember(String email);
}
