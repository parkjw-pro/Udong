package com.ssafy.udong.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.udong.dto.ImageDto;
import com.ssafy.udong.dto.ReportDto;
import com.ssafy.udong.dto.ReviewDto;


@Mapper
public interface ReviewDao {
	
	//리뷰작성
	public int createReview(ReviewDto reviewDto);
	public int createReviewFile(ImageDto imageDto);
	public int connectionFile(ReviewDto reviewDto);
	
	//리뷰수정 1
	public int UpdateReview(ReviewDto reviewDto);
	
	//특정 상호에 달린 리뷰 불러오기
	public List<ReviewDto> SelectAllReview(String store_id);
	
	//내가 쓴 리뷰 보기. 1
	public List<ReviewDto> SelectMyReview(String user_id);
	
	//1개의 리뷰 상세보기
	public  ReviewDto SelectReview(String review_id);
	public List<String> SelectFileUrl(String id); 
	
	//리뷰삭제
	public int DeleteReview(String review_id);
	
	//특정 상점에 달린 리뷰 갯수 조회 1
	public int ReviewListTotalCount(String store_id);
	
	//리뷰에 대한 조회수 업데이트
	public int UpdateReviewReadCount(String reviewId);
	
	//닉네임찾기
	public String FindNick(String user_id);
	public void createReviewReport(ReportDto reportDto);

}
