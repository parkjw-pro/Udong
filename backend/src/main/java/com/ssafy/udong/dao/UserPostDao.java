package com.ssafy.udong.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.udong.dto.CommentDto;
import com.ssafy.udong.dto.ImageDto;
import com.ssafy.udong.dto.LikeDto;
import com.ssafy.udong.dto.ReportDto;
import com.ssafy.udong.dto.UserPostDto;
import com.ssafy.udong.dto.UserPostParamDto;

@Mapper
public interface UserPostDao {
	//글쓰기
	public int createUserPost(UserPostDto userBoardDto);
	public int createUserPostFile(ImageDto imageDto);
	public int connectionFile(UserPostDto userBoardDto);

	//게시물 리스트 가져오기
	public List<UserPostDto> selectAllUserPost(UserPostParamDto userBoardParamDto);
	public List<UserPostDto> selectUserPostByUserId(UserPostParamDto userPostParamDto);
	public List<UserPostDto> selectUserPostBySearchWord(UserPostParamDto userBoardParamDto);
	public List<UserPostDto> selectUserPostByUserIdAndWord(UserPostParamDto userPostParamDto);

	//게시글 총개수 
	public int userPostTotalCount();
	public int userPostByUserIdTotalCount(UserPostParamDto userPostParamDto);
	public int userPostBySearchWordTotalCount(UserPostParamDto boardParamDto);
	public int userPostByUserIdAndWordTotalCount(UserPostParamDto userPostParamDto);
	
	//게시물 상세가져오기 
	public UserPostDto selectUserPost(String postId);
	public List<String> selectFileId(String postId);

	//게시물 수정
	public int updateUserPost(UserPostDto userBoardDto);
	
	//게시물 지우기
	public int deleteUserPost(String postId);
	
	//조회수 가져오고 증가시키기
	public int selectUserPostViews(String postId); 
	public int updateUserPostViews(String postId);
	
	//좋아요
	public void createUserPostLike(LikeDto likeDto);
	public String selectUserPostLike(LikeDto likeDto);
	public void deleteUserPostLike(LikeDto likeDto);
	
	//댓글 입력
	public int createUserPostComment(CommentDto commentDto);

	//댓글 조회
	public List<CommentDto> selectUserPostComment(String postId, int limit, int offset);
	public int userPostCommentTotalCount(String postId);
	
	//댓글 좋아요
	public void createUserPostCommentLike(LikeDto likeDto);
	public String selectUserPostCommentLike(LikeDto likeDto);
	public void deleteUserPostCommentLike(LikeDto likeDto);
	
	//신고
	public void createUserPostReport(ReportDto reportDto);
	public void createUserPostCommentReport(ReportDto reportDto);

}