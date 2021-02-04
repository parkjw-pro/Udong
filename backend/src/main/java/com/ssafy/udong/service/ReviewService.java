package com.ssafy.udong.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ssafy.udong.dto.ReportDto;
import com.ssafy.udong.dto.ReviewDto;
import com.ssafy.udong.dto.ReviewResultDto;

public interface ReviewService {

	//리뷰작성
	public int createReview(ReviewDto reviewDto,MultipartFile[] files);
	
	//특정 상호에 달린 리뷰 불러오기
	public List<ReviewDto> selectAllReview(String storeId);
	
	//내가 쓴 리뷰 보기.
	public List<ReviewDto> selectMyReview(String userId);
	
	//1개의 리뷰 상세보기
	public  ReviewResultDto selectReview(String reviewId);
	
	//특정 상점에 달린 리뷰 갯수 조회
	public int reviewListTotalCount(String storeId);
	
	//리뷰에 대한 조회수 업데이트
	public int updateReviewReadCount(String reviewId);

	//리뷰수정
	public int updateReview(ReviewDto reviewDto, MultipartFile file);
	
	//리뷰삭제
	public int deleteReview(String reviewId);

	//리뷰 신고
	public int createReviewReport(ReportDto reportDto);


}
