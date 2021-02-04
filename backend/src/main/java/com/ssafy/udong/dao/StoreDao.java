package com.ssafy.udong.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.udong.dto.StoreDto;
@Mapper
public interface StoreDao {
	
//	public List<StoreDto> SelectStore(String searchWord);
	
	public List<StoreDto> SelectDetailStore(String searchWord, String areaCode);
	
	public List<StoreDto> SelectArea(String searchWord);

}
