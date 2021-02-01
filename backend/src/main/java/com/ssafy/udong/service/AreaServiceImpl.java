package com.ssafy.udong.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.udong.dao.AreaDao;
import com.ssafy.udong.dto.AreaDto;

@Service
public class AreaServiceImpl implements AreaService{
	
	@Autowired
	AreaDao dao;

	@Override
	public AreaDto SearchArea(String searchWord) {
		AreaDto dto = new AreaDto();
		
		dto = dao.SearchDong(searchWord);
		if(dto != null) {
			return dto;
		}
		
		dto = dao.SearchGugun(searchWord);
		if(dto != null) {
			return dto;
		}

		dto = dao.SearchSido(searchWord);
		if(dto != null) {
			return dto;
		}
		
		return null;
	}

	@Override
	public AreaDto SearchAreaCode(String areaCode) {
		AreaDto dto = new AreaDto();
		
		dto = dao.SearchAreaCode(areaCode);
		return dto;
	}

}
