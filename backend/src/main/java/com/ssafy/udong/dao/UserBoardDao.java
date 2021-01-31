package com.ssafy.udong.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ssafy.udong.dto.CommentDto;
import com.ssafy.udong.dto.ImageDto;
import com.ssafy.udong.dto.LikeDto;
import com.ssafy.udong.dto.ReportDto;
import com.ssafy.udong.dto.ReviewDto;
import com.ssafy.udong.dto.UserBoardDto;
import com.ssafy.udong.dto.UserBoardParamDto;

@Mapper
public interface UserBoardDao {

	//글쓰기
	public int insertBoard(UserBoardDto userBoardDto);
	public int createUserBoardFile(ImageDto imageDto);
	public int connectionFile(UserBoardDto userBoardDto);


	//게시물 리스트 가져오기
	public List<UserBoardDto> listBoard(UserBoardParamDto userBoardParamDto);
	public List<UserBoardDto> searchWordListBoard(UserBoardParamDto userBoardParamDto);
	//게시물 상세가져오기 
	public UserBoardDto detailBoard(String postId);
	//조회수 가져오고 증가시키기
	public int boardUserReadCount(String postId); 
	public int boardReadCountUpdate(String postId);
		
	//게시글 총개수 
	public int totalCountBoardList();
	public int searchWordTotalCountBoardList(UserBoardParamDto boardParamDto);

	//게시물 수정
	public int updateBoard(UserBoardDto userBoardDto);
	
	//게시물 지우기
	public int deleteBoard(String postId);
	
	//댓글 입력
	public int insertBoardDat(UserBoardDto userBoardDto);
	//댓글 조회
	public List<UserBoardDto> selectBoardDat(UserBoardDto userBoardDto);
	//파일 관련된것 
	
	public List<String> SelectFileUrl(String postId);
	public int createUserCom(CommentDto commentDto);
	public int totalCountcomList();
	public List<CommentDto> selectUserCom(String postId);
	public void createUserLike(LikeDto likeDto);
	public String selectUserLike(LikeDto likeDto);
	public void deleteUserLike(LikeDto likeDto);
	public String selectUserCommLike(LikeDto likeDto);
	public void createUserCommLike(LikeDto likeDto);
	public void deleteUserCommLike(LikeDto likeDto);
	public void createUserReport(ReportDto reportDto);
	public void createUserCommReport(ReportDto reportDto);


}