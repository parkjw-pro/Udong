package com.ssafy.udong.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ssafy.udong.dto.CommentDto;
import com.ssafy.udong.dto.CommentResultDto;
import com.ssafy.udong.dto.LikeDto;
import com.ssafy.udong.dto.ReportDto;
import com.ssafy.udong.dto.UserBoardDto;
import com.ssafy.udong.dto.UserBoardParamDto;
import com.ssafy.udong.dto.UserBoardResultDto;

public interface UserBoardService {

	//게시물 작성
    public int insertBoard(UserBoardDto userBoardDto, List<MultipartFile> files);
    
    //게시물 조회  검색단어가 있을경우 없을경우.(일단 컨텐츠로 조회)
    public UserBoardResultDto listBoard(UserBoardParamDto userBoardParamDto);
    public UserBoardResultDto searchWordListBoard(UserBoardParamDto userBoardParamDto);
    //게시물 상세조회
    public UserBoardResultDto detailBoard(String postId);
    
    //글수정
    public int updateBoard(UserBoardDto userBoardDto);
    
    //글삭제
    public int deleteBoard(String postId);
    //댓글 입력
    public int insertBoardDat(UserBoardDto userBoardDto);

	public int createUserCom(CommentDto commentDto);

	public CommentResultDto selectUserCom(String postId);

	public int createUserLike(LikeDto likeDto);

	public int createUserCommLike(LikeDto likeDto);

	public int createUserReport(ReportDto reportDto);

	public int createUserCommReport(ReportDto reportDto);

    
}

