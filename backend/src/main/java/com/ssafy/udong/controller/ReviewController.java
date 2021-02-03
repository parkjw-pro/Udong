package com.ssafy.udong.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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

@CrossOrigin
@RestController
@RequestMapping("/review")
@Api(value = "/review")
public class ReviewController {

	@Autowired
	ReviewService service;

	private static final int SUCCESS = 1;
	private static final int FAIL = -1;

	@ApiOperation(value = "리뷰 작성", notes = "새로운 리뷰를 작성합니다.\n" +
			"## 필수값\n" + " - userId : 리뷰를 작성한 사용자 아이디\n" + 
						" - storeId : 리뷰가 달릴 상점 아이디\n" + 
						" - reviewContent : 리뷰 내용\n" + 
						" - rate : 별점\n" + 
			"## 가능값\n" + " - files : 리뷰 사진 (List<MultipartFile> 형식)")
	@PostMapping
	private ResponseEntity<String> createReview(@RequestBody ReviewDto reviewDto,
			@RequestParam(value = "file", required = false) List<MultipartFile> files) {
		try {
			if (service.createReview(reviewDto, files) == SUCCESS)
				return new ResponseEntity<String>("SUCCESS: review creation", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>("FAILURE: review creation", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ApiOperation(value = "상점의 리뷰 조회", notes = "한 상점에 대해 작성된 모든 리뷰를 조회합니다.\n" +
			"## 필수값\n" + " - storeId : 리뷰를 조회할 상점 아이디\n")
	@GetMapping(value = "/store/{storeId}")
	private ResponseEntity<List<ReviewDto>> selectAllReview(@PathVariable String storeId) {
<<<<<<< HEAD
		System.out.println("상점에 대한 리뷰 조회");

		List<ReviewDto> list = service.SelectAllReview(storeId);
	//	System.out.println(list.get(0).getReviewContent());

=======
>>>>>>> backend
		try {
			List<ReviewDto> list = service.selectAllReview(storeId);
			if (list != null)
				return new ResponseEntity<List<ReviewDto>>(list, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<List<ReviewDto>>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ApiOperation(value = "사용자 리뷰 조회", notes = "한 명의 사용자가 작성한 모든 리뷰를 조회합니다.\n" +
			"## 필수값\n" + " - userId : 리뷰를 조회할 사용자 아이디\n")
	@GetMapping(value = "/user/{userId}")
	private ResponseEntity<List<ReviewDto>> selectMyReview(@PathVariable String userId) {
		try {
			List<ReviewDto> list = service.selectAllReview(userId);
			if (list != null)
				return new ResponseEntity<List<ReviewDto>>(list, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<List<ReviewDto>>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ApiOperation(value = "리뷰 조회", notes = "리뷰 한 개를 조회합니다.\n" +
			"## 필수값\n" + " - reviewId : 조회할 리뷰 아이디\n")
	@GetMapping(value = "/{reviewId}")
	private ResponseEntity<ReviewResultDto> selectReview(@PathVariable String reviewId) {
		try {
			ReviewResultDto resultDto = service.selectReview(reviewId);
			return new ResponseEntity<ReviewResultDto>(resultDto, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<ReviewResultDto>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ApiOperation(value = "리뷰 수정", notes = "작성한 리뷰를 수정합니다.\n" +
			"## 필수값\n" + " - reviewContent : 리뷰 내용\n" + 
			"## 가능값\n" + " - file : 리뷰 사진 (MultipartFile 형식)")
	@PutMapping
	private ResponseEntity<String> updateReview(@RequestBody ReviewDto reviewDto,
			@RequestParam(value = "file", required = false) MultipartFile file) {
		try {
			if (service.updateReview(reviewDto, file) == SUCCESS)
				return new ResponseEntity<String>("SUCCESS: review update", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>("FAILURE: review update", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ApiOperation(value = "리뷰 삭제", notes = "리뷰를 삭제합니다.\n" +
			"## 필수값\n" + " - reviewId : 삭제할 리뷰 아이디\n")
	@DeleteMapping
	private ResponseEntity<String> deleteReview(@RequestBody ReviewDto reviewDto) {
		try {
			if (service.deleteReview(reviewDto.getReviewId()) == SUCCESS) {
				return new ResponseEntity<String>("SUCCESS: review deleted", HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("FAILURE: review deleted", HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "상점의 리뷰 개수 조회", notes = "한 상점에 대해 작성된 모든 리뷰의 개수를 조회합니다.\n" +
			"## 필수값\n" + " - storeId : 조회할 상점 아이디\n")
	@GetMapping(value = "/store/{storeId}/count")
	private ResponseEntity<Integer> reviewListTotalCount(@PathVariable String storeId) {
		try {
			return new ResponseEntity<Integer>(service.reviewListTotalCount(storeId), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Integer>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "상점의 리뷰 개수 변경", notes = "한 상점에 대해 작성된 리뷰의 수를 증가시킵니다.\n" +
			"## 필수값\n" + " - storeId : 리뷰 개수를 변경할 상점 아이디\n")
	@PutMapping(value = "/store/{storeId}")
	private ResponseEntity<Integer> updateReviewReadCount(@PathVariable String storeId) {
		try {
			return new ResponseEntity<Integer>(service.updateReviewReadCount(storeId), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Integer>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation(value = "리뷰 신고하기", notes = "리뷰를 신고 합니다.\n" +
			"## 필수값\n" + " - userId : 리뷰를 작성한 사용자 아이디\n" + 
			" - reviewId : 리뷰 아이디\n" + 
			" - storeId : 리뷰가 달린 상점 아이디\n" + 
			" - content : 신고 내용\n" + 
			" - category : 신고 유형\n")
	@PostMapping(value="/report")
	private ResponseEntity<String> createReviewReport(@RequestBody ReportDto reportDto){
		try {
			if(service.createReviewReport(reportDto) == SUCCESS) {
				return new ResponseEntity<String>("SUCCESS: review report", HttpStatus.OK);
			}else {
				return new ResponseEntity<String>("FAILURE: review report", HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
