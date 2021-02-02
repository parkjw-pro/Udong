package com.ssafy.udong.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.udong.dto.AreaDto;
import com.ssafy.udong.service.AreaService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@CrossOrigin
@RestController
@RequestMapping("/area")
@Api(value = "/area")
public class AreaController {

	@Autowired
	AreaService service;

	private static final int SUCCESS = 1;
	private static final int FAIL = -1;

	@ApiOperation(value = "지역 검색 - 검색어", 
			notes = "검색어를 이용하여 원하는 지역을 검색합니다.\n" + "## 필수값\n - searchWord : 검색어")
	@GetMapping(value = "/word/{searchWord}")
	private ResponseEntity<AreaDto> SearchArea(@PathVariable String searchWord) {
		AreaDto areaDto = service.SearchArea(searchWord);

		try {
			if (areaDto.getCity() != null)
				return new ResponseEntity<AreaDto>(areaDto, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<AreaDto>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ApiOperation(value = "지역 검색 - 코드", 
			notes = "코드를 이용하여 원하는 지역을 검색합니다.\n" + "## 필수값\n - areaCode : 지역 코드")
	@GetMapping(value = "/code/{areaCode}")
	private ResponseEntity<AreaDto> selectArea(@PathVariable String areaCode) {
		AreaDto areaDto = service.SearchAreaCode(areaCode);
		
		try {
			if (areaDto.getDongCode() != null)
				return new ResponseEntity<AreaDto>(areaDto, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<AreaDto>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
