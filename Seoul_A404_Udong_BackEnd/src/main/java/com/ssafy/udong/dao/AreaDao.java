package com.ssafy.udong.dao;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.udong.dto.AreaDto;

@Mapper
public interface AreaDao {
	
	public AreaDto SearchDong(String searchWord);
	public AreaDto SearchGugun(String searchWord);
	public AreaDto SearchSido(String searchWord);
	public AreaDto SearchAreaCode(String searchWord);

}
