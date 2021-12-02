package com.samyo.service;

import com.samyo.domain.MemberVO;

public interface MemberService {

	public int insertMember(MemberVO member) throws Exception;

	public int getMember(String email) throws Exception;
}
