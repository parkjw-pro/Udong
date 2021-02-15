package com.ssafy.udong.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.udong.dto.ReviewDto;
import com.ssafy.udong.dto.StoreDto;
import com.ssafy.udong.dto.StoreParamDto;
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
	@ApiOperation(value = "상점 조회(검색어+위치코드)", notes = "검색어와 위치 코드를 이용하여 상점을 조회합니다.")
	@PostMapping(value = "/stores")
	private ResponseEntity<List<StoreDto>> selectStore(@RequestBody StoreParamDto storeParamDto) {
		System.out.println("storeSearch");
		System.out.println(storeParamDto.getSearchWord());
		System.out.println(storeParamDto.getDongcode());

		List<StoreDto> list = service.SelectDetailStore(storeParamDto.getSearchWord(), storeParamDto.getDongcode());

		try {
			if (list != null)
				return new ResponseEntity<List<StoreDto>>(list, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<List<StoreDto>>(list, HttpStatus.OK);
	}

	@ApiOperation(value = "상점 조회(검색어)", notes = "검색어를 이용하여 상점을 조회합니다.")
	@GetMapping(value = "/store/{searchWord}")
	private ResponseEntity<List<StoreDto>> selectArea(@PathVariable String searchWord) {
		System.out.println("storeSearchword");
		List<StoreDto> list = service.SelectArea(searchWord);
		
		try {
			if (list != null)
				return new ResponseEntity<List<StoreDto>>(list, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<List<StoreDto>>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ApiOperation(value = "베스트 상점 조회(검색어)", notes = "지역코드를 이용하여 최고의 상점을 조회합니다.")
	@GetMapping(value = "/beststore/{dongcode}")
	private ResponseEntity<List<String>> selectBestCtg(@PathVariable String dongcode) {
		System.out.println("selectBestCtg");
		List<String> list = service.selectBestCtg(dongcode);
		
		try {
			if (list != null)
				return new ResponseEntity<List<String>>(list, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<List<String>>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ApiOperation(value = "상점조회", notes = "한 상점에 대한 모든 정보를 조회합니다.\n" +
			"## 필수값\n" + " - storeId : 정보를 조회할 상점 아이디\n")
	@GetMapping(value = "/{storeId}")
	private ResponseEntity<StoreDto> selectStoreDetail(@PathVariable String storeId) {
		System.out.println(storeId);
		try {
			StoreDto dto = service.selectStoreDetail(storeId);
			if (dto != null)
				System.out.println(dto.getStoreName());
				return new ResponseEntity<StoreDto>(dto, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<StoreDto>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
//	@ApiOperation(value = "상점조회", notes = "한 상점에 대한 모든 정보를 조회합니다.\n" +
//			"## 필수값\n" + " - storeId : 정보를 조회할 상점 아이디\n")
//	@GetMapping(value = "/{storeId}")
//	private ResponseEntity<StoreDto> selectBestStore(@PathVariable String storeId) {
//		System.out.println(storeId);
//		try {
//			StoreDto dto = service.selectStoreDetail(storeId);
//			if (dto != null)
//				System.out.println(dto.getStoreName());
//				return new ResponseEntity<StoreDto>(dto, HttpStatus.OK);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return new ResponseEntity<StoreDto>(HttpStatus.INTERNAL_SERVER_ERROR);
//	}
	
	@ApiOperation(value = "카테고리 상점 조회(검색어)", notes = "카테고리 이용하여 최고의 상점을 조회합니다.")
	@PostMapping(value = "/bestctgstore")
	private ResponseEntity<List<StoreDto>> selectBestCtgStore(@RequestBody StoreParamDto storeParamDto) {

		List<StoreDto> list = service.selectBestCtgStore(storeParamDto);
		//System.out.println(list.get(0).getStoreName());
		
		try {
			if (list != null)
				return new ResponseEntity<List<StoreDto>>(list, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<List<StoreDto>>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ApiOperation(value = "최다리뷰 상점 조회(검색어)", notes = " 최고의 상점을 조회합니다.")
	@PostMapping(value = "/beststore")
	private ResponseEntity<List<StoreDto>> selectBestStore(@RequestBody StoreParamDto storeParamDto) {

		List<StoreDto> list = service.selectBestStore(storeParamDto);
		//System.out.println(list.get(0).getStoreName());
		
		try {
			if (list != null)
				return new ResponseEntity<List<StoreDto>>(list, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<List<StoreDto>>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	
}
