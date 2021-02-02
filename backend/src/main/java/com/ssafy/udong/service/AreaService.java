package com.ssafy.udong.service;

import com.ssafy.udong.dto.AreaDto;

public interface AreaService {

	public AreaDto SearchArea(String searchWord);

	public AreaDto SearchAreaCode(String areaCode);

}
