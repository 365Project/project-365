package com.samyo.service;

import java.util.List;

import com.samyo.domain.NickNameVO;

public interface NickNameService {
	public List<NickNameVO> getAdj() throws Exception;

	public List<NickNameVO> getNoun() throws Exception;
}
