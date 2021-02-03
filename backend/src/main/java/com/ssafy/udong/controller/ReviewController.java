package com.ssafy.udong.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.udong.dto.ReportDto;
import com.ssafy.udong.dto.ReviewDto;
import com.ssafy.udong.dto.ReviewResultDto;
import com.ssafy.udong.service.ReviewService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/review")
@Api(value = "/review")
public class ReviewController {

	@Autowired
	ReviewService service;

	private static final int SUCCESS = 1;
	private static final int FAIL = -1;


	@ApiOperation(value = "리뷰 수정", notes = "작성한 리뷰를 수정합니다.")
	@PutMapping
	private ResponseEntity<String> updateReview(@RequestBody ReviewDto reviewDto,
			@RequestParam(value = "file", required = false) MultipartFile file) {

		try {
			if (service.UpdateReview(reviewDto, file) == SUCCESS)
				return new ResponseEntity<String>("SUCCESS: review update", HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>("FAILURE: review update", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ApiOperation(value = "리뷰 작성", notes = "새로운 리뷰를 작성합니다.")
	@PostMapping
	private ResponseEntity<String> createReview(@RequestBody ReviewDto reviewDto,
			@RequestParam(value = "file", required = false) List<MultipartFile> files) {
		
//		System.out.println(reviewDto.getUserId());

		try {
			if (service.createReview(reviewDto, files) == SUCCESS)
				return new ResponseEntity<String>("SUCCESS: review creation", HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>("FAILURE: review creation", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ApiOperation(value = "리뷰 조회", notes = "리뷰 한 개를 조회합니다.")
	@GetMapping(value = "/{reviewId}")
	private ResponseEntity<ReviewResultDto> selectReview(@PathVariable String reviewId, HttpSession session) {

		ReviewResultDto resultDto = new ReviewResultDto();
		resultDto = service.SelectReview(reviewId);
		
//		System.out.println(reviewDto.getReviewContent());

		try {
			if (resultDto.getDto().getUserId() != "")
				System.out.println();
				return new ResponseEntity<ReviewResultDto>(resultDto, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<ReviewResultDto>(resultDto, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ApiOperation(value = "상점의 리뷰 조회", notes = "한 상점에 대해 작성된 모든 리뷰를 조회합니다.")
	@GetMapping(value = "/store/{storeId}")
	private ResponseEntity<List<ReviewDto>> selectAllReview(@PathVariable String storeId) {
		System.out.println("상점에 대한 리뷰 조회");

		List<ReviewDto> list = service.SelectAllReview(storeId);
	//	System.out.println(list.get(0).getReviewContent());

		try {
			if (list != null)
				return new ResponseEntity<List<ReviewDto>>(list, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<List<ReviewDto>>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ApiOperation(value = "사용자 리뷰 조회", notes = "한 명의 사용자가 작성한 모든 리뷰를 조회합니다.")
	@GetMapping(value = "/user/{userId}")
	private ResponseEntity<List<ReviewDto>> selectMyReview(@PathVariable String userId) {
		
		List<ReviewDto> list = service.SelectAllReview(userId);
	//	System.out.println(list.get(0).getReviewContent());

		try {
			if (list == null)
				return new ResponseEntity<List<ReviewDto>>(list, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<List<ReviewDto>>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ApiOperation(value = "리뷰 삭제", notes = "리뷰를 삭제합니다.")
	@DeleteMapping
	private ResponseEntity<String> DeleteReview(@RequestBody ReviewDto reviewDto) {

		if (service.DeleteReview(reviewDto.getReviewId()) == SUCCESS) {
			return new ResponseEntity<String>("SUCCESS: review deleted", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("FAILURE: review deleted", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "상점의 리뷰 개수 조회", notes = "한 상점에 대해 작성된 모든 리뷰의 개수를 조회합니다.")
	@GetMapping(value = "/store/{storeId}/count")
	private ResponseEntity<Integer> ReviewListTotalCount(@PathVariable String storeId) {

		return new ResponseEntity<Integer>(service.ReviewListTotalCount(storeId), HttpStatus.OK);

	}

	@ApiOperation(value = "상점의 리뷰 개수 변경", notes = "한 상점에 대해 작성된 리뷰의 수를 증가시킵니다.")
	@GetMapping(value = "/store/{storeId}/plus")
	private ResponseEntity<Integer> UpdateReviewReadCount(@PathVariable String store_id) {

		return new ResponseEntity<Integer>(service.UpdateReviewReadCount(store_id), HttpStatus.OK);

	}
	
	// 글에 대한 신고기능
	@ApiOperation(value = "리뷰 신고하기", notes = "리뷰를 신고 합니다.")
	@PostMapping(value="/report")
	private ResponseEntity<String> createReviewReport(@RequestBody ReportDto  reportDto){
		
		int result = service.createReviewReport(reportDto);

		if( result == 1 ) {
			return new ResponseEntity<String>("리뷰 신고 성공", HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("리뷰 신고 실패", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
