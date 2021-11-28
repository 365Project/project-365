package com.samyo.service;

import org.springframework.stereotype.Service;

import com.samyo.domain.MemberDTO;

@Service
public interface MemberService {
	public int insertMember(MemberDTO member);
}
