package com.ssafy.udong.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ssafy.udong.dto.ClubPostDto;
import com.ssafy.udong.dto.ClubPostParamDto;
import com.ssafy.udong.dto.ClubPostResultDto;
import com.ssafy.udong.dto.CommentDto;
import com.ssafy.udong.dto.CommentResultDto;
import com.ssafy.udong.dto.LikeDto;
import com.ssafy.udong.dto.ReportDto;

public interface ClubPostService {

	int createClubPost(ClubPostDto clubPostDto, MultipartFile[] files);

	//조건에 맞는 게시글 조회
	ClubPostResultDto selectAllClubPost(int limit, int offset);

	ClubPostResultDto selectClubPostBySearchWord(String searchWord, int limit, int offset);

	ClubPostResultDto selectClubPostByClubId(String clubId, int limit, int offset);

	ClubPostResultDto selectClubPostByClubIdAndSearchWord(String clubId, String searchWord, int limit, int offset);

	ClubPostResultDto selectClubPostByUserId(String userId, int limit, int offset);

	ClubPostResultDto selectClubPostByUserIdAndSearchWord(String userId, String searchWord, int limit, int offset);

	//게시글 한 개 조회
	ClubPostResultDto selectClubPost(String postId);

	int updateClubPost(ClubPostDto clubPostDto);

	int deleteClubPost(String postId);

	int createClubPostComment(CommentDto commentDto);

	CommentResultDto selectClubPostComment(String postId);

	int createClubPostLike(LikeDto likeDto);

	int createClubPostCommentLike(LikeDto likeDto);

	int createClubPostReport(ReportDto reportDto);

	int createClubPostCommentReport(ReportDto reportDto);

}
