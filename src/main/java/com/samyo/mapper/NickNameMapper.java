package com.samyo.mapper;

import java.util.List;

import com.samyo.domain.NickNameVO;

public interface NickNameMapper {

	public List<NickNameVO> getAdj() throws Exception;
	public List<NickNameVO> getNoun() throws Exception;
	
}
