package com.ssafy.udong.service;

import java.util.List;

import com.ssafy.udong.dto.StoreDto;
import com.ssafy.udong.dto.StoreParamDto;


public interface StoreService {
	
//	public List<StoreDto> SelectStore(String searchWord);
	
	public List<StoreDto> SelectDetailStore(String searchWord, String areaCode);
	
	public List<StoreDto> SelectArea(String searchWord);

	public StoreDto selectStoreDetail(String storeId);

	public List<String> selectBestCtg(String dongCode);
	
	public List<String> selectBestStore(String dongCode);

	public List<StoreDto> selectBestCtgStore(StoreParamDto storeParamDto);

	public List<StoreDto> selectBestStore(StoreParamDto storeParamDto);
	
	

}
