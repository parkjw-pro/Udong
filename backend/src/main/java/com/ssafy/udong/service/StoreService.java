package com.ssafy.udong.service;

import java.util.List;

import com.ssafy.udong.dto.StoreDto;


public interface StoreService {
	
//	public List<StoreDto> SelectStore(String searchWord);
	
	public List<StoreDto> SelectDetailStore(String searchWord, String areaCode);
	
	public List<StoreDto> SelectArea(String searchWord);
	
	

}
