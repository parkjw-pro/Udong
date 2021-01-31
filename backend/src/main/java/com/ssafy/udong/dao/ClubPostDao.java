package com.ssafy.udong.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.udong.dto.ClubPostDto;
import com.ssafy.udong.dto.ClubPostParamDto;
import com.ssafy.udong.dto.CommentDto;
import com.ssafy.udong.dto.ImageDto;
import com.ssafy.udong.dto.LikeDto;
import com.ssafy.udong.dto.ReportDto;
import com.ssafy.udong.dto.UserBoardDto;
import com.ssafy.udong.dto.UserBoardParamDto;

@Mapper
public interface ClubPostDao {
	
	//글쓰기
	public int createPost(ClubPostDto clubPostDto);
	public int createClubPostFile(ImageDto imageDto);
	public int connectionFile(ClubPostDto clubPostDto);


	//게시물 리스트 가져오기
	public List<ClubPostDto> listPost(ClubPostParamDto clubPostParamDto);
	public List<ClubPostDto> searchWordListPost(ClubPostParamDto clubPostParamDto);
	//게시물 상세가져오기 
	public ClubPostDto detailPost(String postId);
	//조회수 가져오고 증가시키기
	public int postUserReadCount(String postId); 
	public int postReadCountUpdate(String postId);
		
	//게시글 총개수 
	public int totalCountPostList();
	public int searchWordTotalCountPostList(ClubPostParamDto clubPostParamDto);

	//게시물 수정
	public int updatePost(ClubPostDto clubPostDto);
	
	//게시물 지우기
	public int deletePost(String postId);
	
	//댓글 입력
	public int createPostCom(CommentDto commentDto);
	//댓글 조회
	public List<ClubPostDto> selectPostDat(ClubPostDto clubPostDto);
	//파일 관련된것 
	
	public List<String> SelectFileUrl(String postId);
	public List<CommentDto> selectPostCom(String postId);
	public int totalCountcomList();
	public int createPostLike(LikeDto likeDto);
	public String selectPostLike(LikeDto likeDto);
	public void deletePostLike(LikeDto likeDto);
	public String selectPostCommLike(LikeDto likeDto);
	public void createPostCommLike(LikeDto likeDto);
	public void deletePostCommLike(LikeDto likeDto);
	public void createPostReport(ReportDto reportDto);
	public void createPostCommReport(ReportDto reportDto);
	
	

}
