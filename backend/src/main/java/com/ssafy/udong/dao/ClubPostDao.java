package com.ssafy.udong.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.udong.dto.ClubPostDto;
import com.ssafy.udong.dto.ClubPostParamDto;
import com.ssafy.udong.dto.CommentDto;
import com.ssafy.udong.dto.ImageDto;
import com.ssafy.udong.dto.LikeDto;
import com.ssafy.udong.dto.ReportDto;
import com.ssafy.udong.dto.UserPostDto;
import com.ssafy.udong.dto.UserPostParamDto;

@Mapper
public interface ClubPostDao {
	
	//글쓰기
	public int createClubPost(ClubPostDto clubPostDto);
	public int createClubPostFile(ImageDto imageDto);
	public int connectionFile(ClubPostDto clubPostDto);

	//게시물 리스트 가져오기
	public List<ClubPostDto> selectAllClubPost(ClubPostParamDto clubPostParamDto);
	public List<ClubPostDto> selectClubPostBySearchWord(ClubPostParamDto clubPostParamDto);
	
	public List<ClubPostDto> selectClubPostByClubId(ClubPostParamDto clubPostParamDto);
	public List<ClubPostDto> selectClubPostByClubIdAndSearchWord(ClubPostParamDto clubPostParamDto);
	
	public List<ClubPostDto> selectClubPostByUserId(ClubPostParamDto clubPostParamDto);
	public List<ClubPostDto> selectClubPostByUserIdAndSearchWord(ClubPostParamDto clubPostParamDto);
	
	//게시글 총개수 
	public int clubPostCount();
	public int clubPostBySearchWordCount(ClubPostParamDto clubPostParamDto);
	
	public int clubPostByClubIdCount(ClubPostParamDto clubPostParamDto);
	public int clubPostByClubIdAndSearchWordCount(ClubPostParamDto clubPostParamDto);
	
	public int clubPostByUserIdCount(ClubPostParamDto clubPostParamDto);
	public int clubPostByUserIdAndSearchWordCount(ClubPostParamDto clubPostParamDto);
	
	//게시물 상세가져오기 
	public ClubPostDto selectClubPost(String postId);
	public List<String> selectFileUrl(String postId);
	public List<String> selectFileId(String postId);
	
	//조회수 가져오고 증가시키기
	public int selectClubPostViews(String postId); 
	public int updateClubPostViews(String postId);

	//게시물 수정
	public int updateClubPost(ClubPostDto clubPostDto);
	
	//게시물 지우기
	public int deleteClubPost(String postId);
	
	//댓글 입력
	public int createClubPostComment(CommentDto commentDto);
	
	//댓글 조회
	public List<CommentDto> selectClubPostComment(String postId, int limit, int offset);
	public int userPostCommentTotalCount(String postId);
	
	//좋아요
	public String selectClubPostLike(LikeDto likeDto);
	public int createClubPostLike(LikeDto likeDto);
	public void deleteClubPostLike(LikeDto likeDto);
	
	//댓글 좋아요
	public String selectClubPostCommentLike(LikeDto likeDto);
	public void createClubPostCommentLike(LikeDto likeDto);
	public void deleteClubPostCommentLike(LikeDto likeDto);

	//신고
	public void createClubPostReport(ReportDto reportDto);
	public void createClubPostCommentReport(ReportDto reportDto);
	
	public List<ClubPostDto> selectPostDat(ClubPostDto clubPostDto);
	
}
