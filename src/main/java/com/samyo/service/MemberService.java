package com.samyo.service;

import java.util.HashMap;

public interface MemberService {

	public int insertMember(HashMap<String, Object> userInfo) throws Exception;

	public int getMember(Object id) throws Exception;
}
