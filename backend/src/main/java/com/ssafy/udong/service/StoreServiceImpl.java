package com.ssafy.udong.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.udong.dao.StoreDao;
import com.ssafy.udong.dto.StoreDto;
import com.ssafy.udong.dto.StoreParamDto;

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

	@Override
	public StoreDto selectStoreDetail(String storeId) {
		
		StoreDto dto = new StoreDto();
		
		try {
			dto = dao.selectStoreDetail(storeId);
					
			
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		
		return dto;

	}

	@Override
	public List<String> selectBestCtg(String dongCode) {
		
		List<String> list;
		
		try {
			list = dao.selectBestCtg(dongCode);
					
			
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		
		return list;
	}
	
	@Override
	public List<String> selectBestStore(String dongCode) {
		
		List<String> list;
		
		try {
			list = dao.selectBestStore(dongCode);
					
			
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		
		return list;
	}

	@Override
	public List<StoreDto> selectBestCtgStore(StoreParamDto storeParamDto) {
		
		List<StoreDto> list;
		
		try {
			list = dao.selectBestCtgStore(storeParamDto);
					
			
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		
		return list;
	}

	@Override
	public List<StoreDto> selectBestStore(StoreParamDto storeParamDto) {
		
		List<StoreDto> list;
		
		try {
			list = dao.selectBestStore(storeParamDto);
					
			
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		
		return list;
	}

	
	
}
