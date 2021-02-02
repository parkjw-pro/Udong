package com.ssafy.udong.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.udong.dto.StoreDto;
import com.ssafy.udong.service.StoreService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin
@RestController
@RequestMapping("/store")
@Api(value = "/store")
public class StoreController {

	@Autowired
	StoreService service;

	private static final int SUCCESS = 1;
	private static final int FAIL = -1;

//	// 상점리스트(검색어만)
//	@GetMapping(value = "/store/{searchWord}")
//	private ResponseEntity<List<StoreDto>> selectReview(@PathVariable String searchWord, HttpSession session) {
//
//
//		
//		List<StoreDto> list = service.SelectStore(searchWord);
//		
//		System.out.println("hhh");
//	//	System.out.println(list.get(0).getReviewContent());
//
//		try {
//			if (list != null)
//				return new ResponseEntity<List<StoreDto>>(list, HttpStatus.OK);
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return new ResponseEntity<List<StoreDto>>(HttpStatus.INTERNAL_SERVER_ERROR);
//	}

	@ApiOperation(value = "상점 조회(검색어+위치코드)", notes = "검색어와 위치 코드를 이용하여 상점을 조회합니다.\n" +
			"## 필수값\n" + " - searchWord : 검색어\n"
						+ " - areaCode : 지역 코드\n")
	@GetMapping(value = "/store/{searchWord}/{areaCode}")
	private ResponseEntity<List<StoreDto>> selectStore(@PathVariable String searchWord, @PathVariable String areaCode) {

		List<StoreDto> list = service.SelectDetailStore(searchWord, areaCode);

		try {
			if (list != null)
				return new ResponseEntity<List<StoreDto>>(list, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<List<StoreDto>>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ApiOperation(value = "상점 조회(검색어)", notes = "검색어를 이용하여 상점을 조회합니다.\n" +
			"## 필수값\n" + " - searchWord : 검색어\n")
	@GetMapping(value = "/store/{searchWord}")
	private ResponseEntity<List<StoreDto>> selectArea(@PathVariable String searchWord) {

		List<StoreDto> list = service.SelectArea(searchWord);
		
		try {
			if (list != null)
				return new ResponseEntity<List<StoreDto>>(list, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<List<StoreDto>>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
