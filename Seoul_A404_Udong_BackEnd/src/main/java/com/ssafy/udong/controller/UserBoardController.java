package com.ssafy.udong.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.udong.dto.CommentDto;
import com.ssafy.udong.dto.CommentResultDto;
import com.ssafy.udong.dto.LikeDto;
import com.ssafy.udong.dto.ReportDto;
import com.ssafy.udong.dto.UserBoardDto;
import com.ssafy.udong.dto.UserBoardParamDto;
import com.ssafy.udong.dto.UserBoardResultDto;
import com.ssafy.udong.service.UserBoardService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/board")
@Api(value = "/board")
public class UserBoardController {

	@Autowired
	UserBoardService service;

	private static final int SUCCESS = 1;
	private static final int FAIL = -1;

	@ApiOperation(value = "유저 글 쓰기", notes = "유저의 개인 피드에 노출되는 글을 작성합니다.")
	@PostMapping
	private ResponseEntity<String> inserBoard(@RequestBody UserBoardDto userBoardDto,
			@RequestParam(value = "file", required = false) List<MultipartFile> files) {
		int result = service.insertBoard(userBoardDto, files);

		if( result == SUCCESS ) {
			return new ResponseEntity<String>("게시물 작성 성공", HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("게시물 작성 실패", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "모든 유저 글 조회", notes = "한 유저의 개인 피드에 노출되는 모든 글을 조회합니다.")
	@GetMapping
	private ResponseEntity<UserBoardResultDto> listBoard(@RequestBody UserBoardParamDto userBoardParamDto){

		UserBoardResultDto userBoardResultDto;

		if( userBoardParamDto.getSearchWord().isEmpty() ) { //검색창이 비어 있으면
			userBoardResultDto = service.listBoard(userBoardParamDto);
		}else { // 안비어있으면 
			userBoardResultDto = service.searchWordListBoard(userBoardParamDto);
		}

		if( userBoardResultDto.getResult() == SUCCESS ) {
			return new ResponseEntity<UserBoardResultDto>(userBoardResultDto, HttpStatus.OK);
		}else {
			return new ResponseEntity<UserBoardResultDto>(userBoardResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation(value = "유저 글 조회", notes = "한 유저의 개인 피드에 노출되는 글 한 개를 조회합니다.")
	@GetMapping(value="/{boardId}")
	private ResponseEntity<UserBoardResultDto> detailBoard(@PathVariable String boardId){

		UserBoardResultDto userBoardResultDto = service.detailBoard(boardId);

		if( userBoardResultDto != null ) {
			return new ResponseEntity<UserBoardResultDto>(userBoardResultDto, HttpStatus.OK);
		}else {
			return new ResponseEntity<UserBoardResultDto>(userBoardResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "유저 글 수정", notes = "유저의 개인 피드에 노출되는 글을 수정합니다.")
	@PutMapping
	private ResponseEntity<String> updateBoardUpdate(@RequestBody UserBoardDto userBoardDto ){
		int result = service.updateBoard(userBoardDto);

		if( result == SUCCESS ) {
			return new ResponseEntity<String>("글 수정 완료", HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("글 수정 실패 ", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "유저 글 삭제", notes = "유저의 개인 피드에 노출되는 글을 삭제합니다.")
	@DeleteMapping
	private ResponseEntity<String> deleteBoard(@RequestBody UserBoardParamDto userBoardParamDto){
		String boardId = String.valueOf(userBoardParamDto.getBoardId());
		int result = service.deleteBoard(boardId);

		if( result == SUCCESS ) {
			return new ResponseEntity<String>("글 삭제 성공", HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("글 삭제 실패", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// 각 글id에 맞는 댓글 넣기
	@ApiOperation(value = "피드댓글 작성", notes = "개인피드에 노출되는 글에 댓글을 작성합니다.")
	@PostMapping(value="/comment")
	private ResponseEntity<String> createPostCom(@RequestBody CommentDto commentDto){

		int result = service.createUserCom(commentDto);

		if( result == SUCCESS ) {
			return new ResponseEntity<String>("댓글 작성 성공", HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("댓글 작성 실패", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	// 각 글id에 맞는 댓글 전체조회
	@ApiOperation(value = "게시 글 댓글 조회", notes = "개인피드 노출되는 글에 댓글을 조회합니다.")
	@GetMapping(value="/comment/{postId}")
	private ResponseEntity<CommentResultDto> selectPostCom(@PathVariable String postId){
		
		CommentResultDto commentResultDto;

		commentResultDto = service.selectUserCom(postId);

		if( commentResultDto.getResult() == SUCCESS ) {
			return new ResponseEntity<CommentResultDto>(commentResultDto, HttpStatus.OK);
		}else {
			return new ResponseEntity<CommentResultDto>(commentResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation(value = "개인피드 글 좋아요", notes = "개인피드에 노출되는 글에 좋아요를 합니다.")
	@PostMapping(value="/like")
	private ResponseEntity<String> createUserLike(@RequestBody LikeDto  likeDto){
		
		int result = service.createUserLike(likeDto);

		if( result == 1 ) {
			return new ResponseEntity<String>("개인피드 글 좋아요 성공", HttpStatus.OK);
		}else if( result == 2 ) {
			return new ResponseEntity<String>("개인피드 글 좋아요 취소 성공", HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("개인피드 글 좋아요 실패", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	// 댓글에 대한 좋아요기능
	@ApiOperation(value = "게시글 댓글에 좋아요", notes = "개인피드에 노출되는 글의 댓글에 좋아요를 합니다.")
	@PostMapping(value="/comment/like")
	private ResponseEntity<String> createUserCommLike(@RequestBody LikeDto  likeDto){
		
		int result = service.createUserCommLike(likeDto);

		if( result == 1 ) {
			return new ResponseEntity<String>("댓글 좋아요 성공", HttpStatus.OK);
		}else if( result == 2 ) {
			return new ResponseEntity<String>("댓글 좋아요 취소 성공", HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("댓글 좋아요 실패", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	// 글에 대한 신고기능
	@ApiOperation(value = "게시 글 신고하기", notes = "개인피드에 노출되는 글을 신고 합니다.")
	@PostMapping(value="/report")
	private ResponseEntity<String> createUserReport(@RequestBody ReportDto  reportDto){
		
		int result = service.createUserReport(reportDto);

		if( result == 1 ) {
			return new ResponseEntity<String>("게시글 신고 성공", HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("게시글 신고 실패", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	// 댓글에 대한 신고기능
	@ApiOperation(value = "게시 글 댓글 신고하기", notes = "개인피드에 노출되는 글의 댓글을 신고 합니다.")
	@PostMapping(value="/comment/report")
	private ResponseEntity<String> createUserCommReport(@RequestBody ReportDto  reportDto){
		
		int result = service.createUserCommReport(reportDto);

		if( result == 1 ) {
			return new ResponseEntity<String>("게시글 댓글 신고 성공", HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("게시글 댓글 신고 실패", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	
	
	

}