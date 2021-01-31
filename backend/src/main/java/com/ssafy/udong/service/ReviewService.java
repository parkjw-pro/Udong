package com.ssafy.udong.service;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.udong.dto.ReportDto;
import com.ssafy.udong.dto.ReviewDto;
import com.ssafy.udong.dto.ReviewResultDto;

public interface ReviewService {
	
	//리뷰작성
	public int createReview(ReviewDto reviewDto,List<MultipartFile> files);
	
	//리뷰수정
	public int UpdateReview(ReviewDto reviewDto, MultipartFile file);
	
	//특정 상호에 달린 리뷰 불러오기
	public List<ReviewDto> SelectAllReview(String store_id);
	
	//1개의 리뷰 상세보기
	public  ReviewResultDto SelectReview(String review_id);
	
	//내가 쓴 리뷰 보기.
	public List<ReviewDto> SelectMyReview(String user_id);
	
	//리뷰삭제
	public int DeleteReview(String review_id);
	
	//특정 상점에 달린 리뷰 갯수 조회
	public int ReviewListTotalCount(String store_id);
	
	//리뷰에 대한 조회수 업데이트
	public int UpdateReviewReadCount(String reviewId);

	public int createReviewReport(ReportDto reportDto);

	


}
