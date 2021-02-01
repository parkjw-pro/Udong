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

	int createPost(ClubPostDto clubPostDto, List<MultipartFile> files);

	ClubPostResultDto listPost(ClubPostParamDto clubPostParamDto);

	ClubPostResultDto searchWordListPost(ClubPostParamDto clubPostParamDto);

	ClubPostResultDto detailPost(String postId);

	int updatePost(ClubPostDto clubPostDto);

	int deletePost(String postId);

	int createPostCom(CommentDto commentDto);

	CommentResultDto selectPostCom(String postId);

	int createPostLike(LikeDto likeDto);

	int createPostCommLike(LikeDto likeDto);

	int createPostReport(ReportDto reportDto);

	int createPostCommReport(ReportDto reportDto);

}
