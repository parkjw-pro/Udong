package com.ssafy.udong.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.udong.dto.StoreDto;
import com.ssafy.udong.dto.StoreParamDto;
@Mapper
public interface StoreDao {
	
//	public List<StoreDto> SelectStore(String searchWord);
	
	public List<StoreDto> SelectDetailStore(String searchWord, String areaCode);
	
	public List<StoreDto> SelectArea(String searchWord);

	public StoreDto selectStoreDetail(String storeId);

	public List<String> selectBestCtg(String dongCode);
	
	public List<String> selectBestStore(String dongCode);

	public List<StoreDto> selectBestCtgStore(StoreParamDto storeParamDto);

	public List<StoreDto> selectBestStore(StoreParamDto storeParamDto);

}
