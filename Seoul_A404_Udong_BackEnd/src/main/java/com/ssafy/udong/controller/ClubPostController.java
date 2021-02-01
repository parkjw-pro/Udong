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

import com.ssafy.udong.dto.ClubPostDto;
import com.ssafy.udong.dto.ClubPostParamDto;
import com.ssafy.udong.dto.ClubPostResultDto;
import com.ssafy.udong.dto.CommentDto;
import com.ssafy.udong.dto.CommentResultDto;
import com.ssafy.udong.dto.LikeDto;
import com.ssafy.udong.dto.ReportDto;
import com.ssafy.udong.service.ClubPostService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping("/post")
@Api(value = "/post")
public class ClubPostController {

	@Autowired
	ClubPostService service;

	private static final int SUCCESS = 1;
	private static final int FAIL = -1;
// 
	@ApiOperation(value = "그룹에 글 쓰기", notes = "그룹에 노출되는 글을 작성합니다.")
	@PostMapping
	private ResponseEntity<String> createPost( @RequestBody ClubPostDto clubPostDto,
			@RequestParam(value = "file", required = false) List<MultipartFile> files) {
		System.out.println("그룹게시판");
		int result = service.createPost(clubPostDto, files);

		if( result == SUCCESS ) {
			return new ResponseEntity<String>("게시물 작성 성공", HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("게시물 작성 실패", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// 게시판 불러오기  검색어 조회시는 컨텐츠내용 조회
	@ApiOperation(value = "그룹 내 모든 글 조회", notes = "그룹 내 노출되는 모든 글을 조회합니다.")
	@GetMapping
	private ResponseEntity<ClubPostResultDto> listBoard(@RequestBody ClubPostParamDto clubPostParamDto){

		ClubPostResultDto clubPostResultDto;

		if( clubPostParamDto.getSearchWord().isEmpty() ) { //검색창이 비어 있으면
			clubPostResultDto = service.listPost(clubPostParamDto);
		}else { // 안비어있으면 
			clubPostResultDto = service.searchWordListPost(clubPostParamDto);
		}

		if( clubPostResultDto.getResult() == SUCCESS ) {
			return new ResponseEntity<ClubPostResultDto>(clubPostResultDto, HttpStatus.OK);
		}else {
			return new ResponseEntity<ClubPostResultDto>(clubPostResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	// 글 상세조회
	@ApiOperation(value = "게시 글 조회", notes = "그룹 피드에 노출되는 글 한 개를 조회합니다.")
	@GetMapping(value="/{postId}")
	private ResponseEntity<ClubPostResultDto> detailBoard(@PathVariable String postId){
		System.out.println("clubpost상세조회");

		ClubPostResultDto clubPostResultDto = service.detailPost(postId);

		if( clubPostResultDto != null ) {
			return new ResponseEntity<ClubPostResultDto>(clubPostResultDto, HttpStatus.OK);
		}else {
			return new ResponseEntity<ClubPostResultDto>(clubPostResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	//글 수정
	@ApiOperation(value = "게시 글 수정", notes = "그룹의 게시판에 노출되는 글을 수정합니다.")
	@PutMapping 
	private ResponseEntity<String> updateBoardUpdate(@RequestBody ClubPostDto clubPostDto ){
		int result = service.updatePost(clubPostDto);

		if( result == SUCCESS ) {
			return new ResponseEntity<String>("글 수정 완료", HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("글 수정 실패 ", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	//글 삭제

	@ApiOperation(value = "게시 글 삭제", notes = "그룹의 게시판에 노출되는 글을 삭제합니다.")
	@DeleteMapping
	private ResponseEntity<String> deleteBoard(@RequestBody ClubPostDto clubPostDto){
		int result = service.deletePost(clubPostDto.getPostId());

		if( result == SUCCESS ) {
			return new ResponseEntity<String>("글 삭제 성공", HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("글 삭제 실패", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	// 각 글id에 맞는 댓글 넣기
	@ApiOperation(value = "게시 글 댓글 작성", notes = "그룹 게시판에 노출되는 글에 댓글을 작성합니다.")
	@PostMapping(value="/comment")
	private ResponseEntity<String> createPostCom(@RequestBody CommentDto commentDto){

		int result = service.createPostCom(commentDto);

		if( result == SUCCESS ) {
			return new ResponseEntity<String>("댓글 작성 성공", HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("댓글 작성 실패", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	// 각 글id에 맞는 댓글 전체조회
	@ApiOperation(value = "게시 글 댓글 조회", notes = "그룹 게시판에 노출되는 글에 댓글을 조회합니다.")
	@GetMapping(value="/comment/{postId}")
	private ResponseEntity<CommentResultDto> selectPostCom(@PathVariable String postId){
		
		CommentResultDto commentResultDto;

		commentResultDto = service.selectPostCom(postId);

		if( commentResultDto.getResult() == SUCCESS ) {
			return new ResponseEntity<CommentResultDto>(commentResultDto, HttpStatus.OK);
		}else {
			return new ResponseEntity<CommentResultDto>(commentResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	// 글에 대한 좋아요기능
	@ApiOperation(value = "게시 글 좋아요", notes = "그룹 게시판에 노출되는 글에  좋아요를 합니다.")
	@PostMapping(value="/like")
	private ResponseEntity<String> createPostLike(@RequestBody LikeDto  likeDto){
		
		int result = service.createPostLike(likeDto);

		if( result == 1 ) {
			return new ResponseEntity<String>("게시글 좋아요 성공", HttpStatus.OK);
		}else if( result == 2 ) {
			return new ResponseEntity<String>("게시글 좋아요 취소 성공", HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("게시글 좋아요 실패", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	// 댓글에 대한 좋아요기능
	@ApiOperation(value = "게시글 댓글에 좋아요", notes = "그룹 게시판에 노출되는 글의 댓글에 좋아요를 합니다.")
	@PostMapping(value="/comment/like")
	private ResponseEntity<String> createPostCommLike(@RequestBody LikeDto  likeDto){
		
		int result = service.createPostCommLike(likeDto);

		if( result == 1 ) {
			return new ResponseEntity<String>("댓글 좋아요 성공", HttpStatus.OK);
		}else if( result == 2 ) {
			return new ResponseEntity<String>("댓글 좋아요 취소 성공", HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("댓글 좋아요 실패", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
	// 글에 대한 신고기능
	@ApiOperation(value = "게시 글 신고하기", notes = "그룹 게시판에 노출되는 글을 신고 합니다.")
	@PostMapping(value="/report")
	private ResponseEntity<String> createPostReport(@RequestBody ReportDto  reportDto){
		
		int result = service.createPostReport(reportDto);

		if( result == 1 ) {
			return new ResponseEntity<String>("게시글 신고 성공", HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("게시글 신고 실패", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	// 댓글에 대한 신고기능
	@ApiOperation(value = "게시 글 댓글 신고하기", notes = "그룹 게시판에 노출되는 글의 댓글을 신고 합니다.")
	@PostMapping(value="/comment/report")
	private ResponseEntity<String> createPostCommReport(@RequestBody ReportDto  reportDto){
		
		int result = service.createPostCommReport(reportDto);

		if( result == 1 ) {
			return new ResponseEntity<String>("게시글 댓글 신고 성공", HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("게시글 댓글 신고 실패", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


}