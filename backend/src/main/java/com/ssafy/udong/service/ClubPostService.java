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

	int createClubPost(ClubPostDto clubPostDto, List<MultipartFile> files);

	ClubPostResultDto selectAllClubPost(ClubPostParamDto clubPostParamDto);

	ClubPostResultDto selectClubPostBySearchWord(ClubPostParamDto clubPostParamDto);

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
