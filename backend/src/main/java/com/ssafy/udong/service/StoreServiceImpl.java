package com.ssafy.udong.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.udong.dao.StoreDao;
import com.ssafy.udong.dto.StoreDto;

@Service
public class StoreServiceImpl implements StoreService{
	
	@Autowired
	StoreDao dao;

//	@Override
//	public List<StoreDto> SelectStore(String searchWord) {
//		
//		List<StoreDto> list;
//		
//		try {
//			list = dao.SelectStore(searchWord);
//					
//			
//		}catch(Exception e) {
//			e.printStackTrace();
//			return null;
//		}
//		
//		return list;
//		
//	}

	@Override
	public List<StoreDto> SelectDetailStore(String searchWord, String areaCode) {
		
		List<StoreDto> list;
		
		try {
			list = dao.SelectDetailStore(searchWord,areaCode);
					
			
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		
		return list;
		
	}

	@Override
	public List<StoreDto> SelectArea(String searchWord) {
		List<StoreDto> list;
		
		try {
			list = dao.SelectArea(searchWord);
					
			
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		
		return list;

	}

	
	
}
