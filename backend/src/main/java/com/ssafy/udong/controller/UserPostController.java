package com.ssafy.udong.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.ssafy.udong.dto.UserPostDto;
import com.ssafy.udong.dto.UserPostParamDto;
import com.ssafy.udong.dto.UserPostResultDto;
import com.ssafy.udong.service.ClubService;
import com.ssafy.udong.service.UserPostService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin
@RestController
@RequestMapping("/userpost")
@Api(value = "/userpost")
public class UserPostController {

	@Autowired
	UserPostService service;

	@Autowired
	ClubService clubService;
	private static final int SUCCESS = 1;
	private static final int FAIL = -1;

	@ApiOperation(value = "유저 글 쓰기", notes = "유저의 개인 피드에 노출되는 글을 작성합니다.\n" +
			"## 필수값\n" + " - userId : 작성자 아이디\n"
			 			+ " - postContent : 글 내용\n"
			 			+ " - isOpen : 공개 여부(true/false 또는 1/0으로 구분)\n" + 
			"## 가능값\n" + " - postTag : 태그\n")
	@PostMapping
	private ResponseEntity<String> createUserPost(UserPostDto userPostDto,
			@RequestParam(value = "file", required = false) MultipartFile[] files)throws NullPointerException {
		int result = service.createUserPost(userPostDto, files);
	
		if( result == SUCCESS ) {	
			return new ResponseEntity<String>("게시물 작성 성공", HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("게시물 작성 실패", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "유저 글 조회", notes = "사용자가 개인 피드에 작성한 모든 글을 조회하여 반환합니다.\n" +
			"## 필수값\n" + " - limit : 한 페이지에 노출될 게시글 수\n" + " - offset : 오프셋\n")
	@GetMapping
	private ResponseEntity<UserPostResultDto> selectAllUserPost(@RequestParam(value="limit") int limit, @RequestParam(value="offset") int offset){
		UserPostResultDto userPostResultDto = service.selectAllUserPost(limit, offset);

		if( userPostResultDto.getResult() == SUCCESS ) {
			return new ResponseEntity<UserPostResultDto>(userPostResultDto, HttpStatus.OK);
		}else {
			return new ResponseEntity<UserPostResultDto>(userPostResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}	
	
	@ApiOperation(value = "유저 별 유저 글 조회", notes = "한 명의 사용자가 개인 피드에 작성한 글을 조회하여 반환합니다.\n" +
			"## 필수값\n" + " - userId : 작성한 글을 조회할 사용자\n" + " - limit : 한 페이지에 노출될 게시글 수\n" + " - offset : 오프셋\n")
	@GetMapping(value="/user")
	private ResponseEntity<UserPostResultDto> selectAllUserPostByUserId(@RequestParam(value="userId") String userId, @RequestParam(value="limit") int limit, @RequestParam(value="offset") int offset){
		UserPostResultDto userPostResultDto = service.selectAllUserPostByUserId(userId, limit, offset);

		if( userPostResultDto.getResult() == SUCCESS ) {
			return new ResponseEntity<UserPostResultDto>(userPostResultDto, HttpStatus.OK);
		}else {
			return new ResponseEntity<UserPostResultDto>(userPostResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}	
	
	@ApiOperation(value = "검색어를 포함하는 유저 글 조회", notes = "사용자가 개인 피드에 작성한 모든 글 중 검색어를 포함하는 글을 조회하여 반환합니다.\n" +
			"## 필수값\n" + " - limit : 한 페이지에 노출될 게시글 수\n" + " - offset : 오프셋\n" + " - searchWord : 검색어\n")
	@GetMapping(value="/word")
	private ResponseEntity<UserPostResultDto> selectUserPostByWord(@RequestParam(value="searchWord") String searchWord, @RequestParam(value="limit") int limit, @RequestParam(value="offset") int offset){
		UserPostResultDto userPostResultDto = service.selectUserPostBySearchWord(searchWord, limit, offset);
		if( userPostResultDto.getResult() == SUCCESS ) {
			return new ResponseEntity<UserPostResultDto>(userPostResultDto, HttpStatus.OK);
		}else {
			return new ResponseEntity<UserPostResultDto>(userPostResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@ApiOperation(value = "유저 별 검색어를 포함하는 유저 글 조회", notes = "한 명의 사용자가 개인 피드에 작성한 글 중 검색어를 포함하는 글을 조회하여 반환합니다.\n" +
			"## 필수값\n" + " - userId : 작성한 글을 조회할 사용자\n" + " - searchWord : 검색어\n" + " - limit : 한 페이지에 노출될 게시글 수\n" + " - offset : 오프셋\n")
	@GetMapping(value="/user/word")
	private ResponseEntity<UserPostResultDto> selectUserPostByUserIdAndWord(@RequestParam(value="userId") String userId, @RequestParam(value="searchWord") String searchWord, @RequestParam(value="limit") int limit, @RequestParam(value="offset") int offset){
		UserPostResultDto userPostResultDto = service.selectUserPostByUserIdAndWord(userId, searchWord, limit, offset);
		if( userPostResultDto.getResult() == SUCCESS ) {
			return new ResponseEntity<UserPostResultDto>(userPostResultDto, HttpStatus.OK);
		}else {
			return new ResponseEntity<UserPostResultDto>(userPostResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation(value = "유저 글 상세 조회", notes = "한 유저의 개인 피드에 노출되는 글 한 개를 조회합니다.\n" +
			"## 필수값\n" + " - postId : 조회할 게시글 아이디\n")
	@GetMapping(value="/{postId}")
	private ResponseEntity<UserPostResultDto> selectUserPost(@PathVariable String postId){
		UserPostResultDto userPostResultDto = service.selectUserPost(postId);
		if( userPostResultDto != null ) {	
			return new ResponseEntity<UserPostResultDto>(userPostResultDto, HttpStatus.OK);
		}else {
			return new ResponseEntity<UserPostResultDto>(userPostResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "유저 글 수정", notes = "유저의 개인 피드에 노출되는 글을 수정합니다.\n" +
			"## 필수값\n" + " - postContent : 글 내용\n"
		 				+ " - isOpen : 공개 여부(true/false 또는 1/0으로 구분)\n" +
			"## 가능값\n" + " - postTag : 태그\n")
	@PutMapping
	private ResponseEntity<String> updateUserPost(@RequestBody UserPostDto userPostDto ){
		int result = service.updateUserPost(userPostDto);

		if( result == SUCCESS ) {
			return new ResponseEntity<String>("글 수정 완료", HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("글 수정 실패 ", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "유저 글 삭제", notes = "유저의 개인 피드에 노출되는 글을 삭제합니다.\n" +
			"## 필수값\n" + " - postId : 삭제할 게시글 아이디\n")
	@DeleteMapping
	private ResponseEntity<String> deleteUserPost(@RequestBody UserPostParamDto userPostParamDto){
		String postId = String.valueOf(userPostParamDto.getPostId());
		int result = service.deleteUserPost(postId);

		if( result == SUCCESS ) {
			return new ResponseEntity<String>("글 삭제 성공", HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("글 삭제 실패", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "피드댓글 작성", notes = "개인피드에 노출되는 글에 댓글을 작성합니다.\n" +
			"## 필수값\n" + " - postId : 댓글이 달릴 게시글 아이디\n"
			 			+ " - userId : 댓글을 다는 유저 아이디\n"
			 			+ " - commContent : 댓글 내용\n")
	@PostMapping(value="/comment")
	private ResponseEntity<String> createUserPostComment(@RequestBody CommentDto commentDto){
		// 각 글id에 맞는 댓글 넣기
		int result = service.createUserPostComment(commentDto);

		if( result == SUCCESS ) {
			return new ResponseEntity<String>("댓글 작성 성공", HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("댓글 작성 실패", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation(value = "게시 글 댓글 조회", notes = "개인피드 노출되는 글에 댓글을 조회합니다.\n" + 
			"## 필수값\n" + " - postId : 댓글이 달릴 게시글 아이디\n"
						+ " - limit : 한 페이지에 노출될 댓글 수\n" + " - offset : 오프셋\n")
	@GetMapping(value="/comment")
	private ResponseEntity<CommentResultDto> selectPostCom(@RequestParam(value="postId") String postId, @RequestParam(value="limit") int limit, @RequestParam(value="offset") int offset){
		// 각 글id에 맞는 댓글 전체조회
		CommentResultDto commentResultDto;

		commentResultDto = service.selectUserPostComment(postId, limit, offset);

		if( commentResultDto.getResult() == SUCCESS ) {
			return new ResponseEntity<CommentResultDto>(commentResultDto, HttpStatus.OK);
		}else {
			return new ResponseEntity<CommentResultDto>(commentResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation(value = "개인피드 글 좋아요 조회", notes = "개인피드에 노출되는 글에 좋아요를 했는지 조회합니다.\n" + "## 필수값\n"
			+ " - postId : 좋아요 표시될 게시글 아이디\n" + " - userId : 사용자 아이디\n")
	@GetMapping(value = "/like")
	private ResponseEntity<String> selectUserPostLike(@RequestParam(value="userId") String userId, @RequestParam(value="postId") String postId) {
		try {
			int result = service.selectUserPostLike(userId, postId);
			if(result != 0)
				return new ResponseEntity<String>("true", HttpStatus.OK);
			else
				return new ResponseEntity<String>("false", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("FAILURE: club post like select", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation(value = "개인피드 글 좋아요", notes = "개인피드에 노출되는 글에 좋아요를 합니다.\n" + 
			"## 필수값\n" + " - postId : 좋아요 표시될 게시글 아이디\n"
						+ " - userId : 좋아요를 누른 사용자 아이디\n")
	@PostMapping(value="/like")
	private ResponseEntity<String> createUserLike(@RequestBody LikeDto  likeDto){
		
		int result = service.createUserPostLike(likeDto);

		if( result == 1 ) {
			return new ResponseEntity<String>("개인피드 글 좋아요 성공", HttpStatus.OK);
		}else if( result == 2 ) {
			return new ResponseEntity<String>("개인피드 글 좋아요 취소 성공", HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("개인피드 글 좋아요 실패", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation(value = "게시글 댓글 좋아요 조회", notes = "개인피드에 노출되는 글의 댓글에 좋아요를 했는지 조회합니다.\n" + "## 필수값\n"
			+ " - userId : 사용자 아이디\n" + " - postId : 댓글이 달린 게시글 아이디\n"	+ " - commentId : 좋아요 표시될 댓글 아이디\n")
	@GetMapping(value = "/comment/like")
	private ResponseEntity<String> selectClubPostCommentLike(@RequestParam(value="userId") String userId, @RequestParam(value="postId") String postId, @RequestParam(value="commentId") String commentId ) {
		try {
			int result = service.selectUserPostCommentLike(userId, postId, commentId);
			if(result != 0)
				return new ResponseEntity<String>("true", HttpStatus.OK);
			else
				return new ResponseEntity<String>("false", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("FAILURE: club post comment like select", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// 댓글에 대한 좋아요기능
	@ApiOperation(value = "개인피드 댓글 좋아요", notes = "개인피드에 노출되는 글의 댓글에 좋아요를 합니다.\n" + 
			"## 필수값\n" + " - userId : 좋아요를 누른 사용자 아이디\n"
						+ " - postId : 댓글이 달린 게시글 아이디\n"
						+ " - commentId : 좋아요 표시될 댓글 아이디\n")
	@PostMapping(value="/comment/like")
	private ResponseEntity<String> createUserCommLike(@RequestBody LikeDto  likeDto){
		
		int result = service.createUserPostCommentLike(likeDto);

		if( result == 1 ) {
			return new ResponseEntity<String>("댓글 좋아요 성공", HttpStatus.OK);
		}else if( result == 2 ) {
			return new ResponseEntity<String>("댓글 좋아요 취소 성공", HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("댓글 좋아요 실패", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	// 글에 대한 신고기능
	@ApiOperation(value = "게시글 신고하기", notes = "개인피드에 노출되는 글을 신고합니다.\n" + 
			"## 필수값\n" + " - userId : 게시글을 신고한 사용자 아이디\n"
						+ " - postId : 게시글 아이디\n"
						+ " - content : 신고 내용\n"
						+ " - category : 신고 항목 구분\n")
	@PostMapping(value="/report")
	private ResponseEntity<String> createUserReport(@RequestBody ReportDto  reportDto){
		
		int result = service.createUserPostReport(reportDto);

		if( result == 1 ) {
			return new ResponseEntity<String>("게시글 신고 성공", HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("게시글 신고 실패", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	// 댓글에 대한 신고기능
	@ApiOperation(value = "게시글 댓글 신고하기", notes = "개인피드에 노출되는 글의 댓글을 신고합니다.\n" + 
			"## 필수값\n" + " - userId : 댓글을 신고한 사용자 아이디\n"
						+ " - postId : 댓글이 달린 게시글 아이디\n"
						+ " - commentId :댓글 아이디\n"
						+ " - content : 신고 내용\n"
						+ " - category : 신고 항목 구분\n")
	@PostMapping(value="/comment/report")
	private ResponseEntity<String> createUserCommReport(@RequestBody ReportDto  reportDto){
		
		int result = service.createUserPostCommentReport(reportDto);

		if( result == 1 ) {
			return new ResponseEntity<String>("게시글 댓글 신고 성공", HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("게시글 댓글 신고 실패", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("/download/{fileId}")
	public ResponseEntity<Resource> download(@PathVariable String fileId) throws IOException {
		System.out.println("다운로드" +fileId);
		List<String> url = clubService.selectFileUrl(fileId);
		System.out.println("가져온 url:" +url.get(0));
		System.out.println("./uploads/userpost/"+url.get(0).substring(17));
		String madeUrl = "./uploads/userpost/"+url.get(0).substring(17);
		Path path = Paths.get(madeUrl);
			
		String contentType = Files.probeContentType(path);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_TYPE, contentType);

		Resource resource = new InputStreamResource(Files.newInputStream(path));
		return new ResponseEntity<>(resource, headers, HttpStatus.OK);
	}
	
	
	
	
	

}