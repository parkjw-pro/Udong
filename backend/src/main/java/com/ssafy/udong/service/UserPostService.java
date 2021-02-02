package com.ssafy.udong.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ssafy.udong.dto.CommentDto;
import com.ssafy.udong.dto.CommentResultDto;
import com.ssafy.udong.dto.LikeDto;
import com.ssafy.udong.dto.ReportDto;
import com.ssafy.udong.dto.UserPostDto;
import com.ssafy.udong.dto.UserPostParamDto;
import com.ssafy.udong.dto.UserPostResultDto;

public interface UserPostService {

	// 게시물 작성
	public int createUserPost(UserPostDto userBoardDto, List<MultipartFile> files);

	// 게시물 조회 검색단어가 있을경우 없을경우.(일단 컨텐츠로 조회)
	public UserPostResultDto selectAllUserPost(UserPostParamDto userBoardParamDto);
	public UserPostResultDto selectUserPostBySearchWord(UserPostParamDto userBoardParamDto);

	// 게시물 상세조회
	public UserPostResultDto selectUserPost(String postId);

	// 글수정
	public int updateUserPost(UserPostDto userBoardDto);

	// 글삭제
	public int deleteUserPost(String postId);

	// 글 좋아요
	public int createUserPostLike(LikeDto likeDto);
	
	// 댓글 입력
	public int createUserPostComment(CommentDto commentDto);

	// 댓글 선택
	public CommentResultDto selectUserPostComment(String postId);

	// 댓글 좋아요
	public int createUserPostCommentLike(LikeDto likeDto);

	// 글 신고
	public int createUserPostReport(ReportDto reportDto);

	// 댓글 신고
	public int createUserPostCommentReport(ReportDto reportDto);

}
