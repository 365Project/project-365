package com.samyo.service;

import com.samyo.domain.MemberDTO;

public interface MemberService {

	public int insertMember(MemberDTO member) throws Exception;

	public int getMember(String email) throws Exception;
}
