package com.samyo.mapper;

import com.samyo.domain.MemberDTO;

public interface MemberMapper {

	public void insertMember(MemberDTO member);
	
	public int getMember(String email);
}
