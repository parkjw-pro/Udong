package com.ssafy.udong.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.ssafy.udong.dto.ClubDto;
import com.ssafy.udong.dto.ClubResultDto;
import com.ssafy.udong.dto.MemberDto;
import com.ssafy.udong.service.ClubService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin
@RestController
@RequestMapping("/club")
@Api(value = "/club")
public class ClubController {

	@Autowired
	ClubService clubService;
	
	private static final int SUCCESS = 1;
//	private static final int FAIL = -1;
	
	/* CLUB */
	@ApiOperation(value = "그룹 생성", notes = "새로운 그룹을 생성하고, 그룹장을 등록합니다.")
	@PostMapping
	public ResponseEntity<String> createClub(ClubDto club,
			@RequestParam(value = "file", required = false) MultipartFile file){
		System.out.println("club create");
		try {
			String s = clubService.createClub(club, file);
			if(s != "-1") {
				String result = "SUCCESS: club creation";
		//		member.setUserId(s);
				MemberDto member = new MemberDto(s, club.getUserId(), "1");
//				MemberDto member = new MemberDto();
//				member.setUserId(club.getUserId());
//				member.setType("1");
				member.setClubId(s);
				if(clubService.createClubMember(member) == SUCCESS) {
					result += " | club leader addition";
				}
				else {
					result += "FAILURE: club leader addition";
				}
				/* club 대표 사진 파일 업로드 코드 */
				return new ResponseEntity<String>(result, HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>("FAILURE: club creation", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ApiOperation(value = "그룹 정보 조회", notes = "그룹 정보를 조회합니다.")
	@GetMapping("/{clubId}")
	public ResponseEntity<ClubResultDto> selectClub(@PathVariable String clubId){
		try {
			ClubResultDto dto = clubService.selectClub(clubId);
			return new ResponseEntity<ClubResultDto>(dto, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<ClubResultDto>(HttpStatus.INTERNAL_SERVER_ERROR);
//			return new ResponseEntity<ClubDto>(new ClubDto(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "전체 그룹 정보 조회", notes = "모든 그룹의 정보를 조회합니다.")
	@GetMapping("/clubs")
	public ResponseEntity<List<ClubDto>> selectAllClub(){
		try {
			List<ClubDto> clubs = clubService.selectAllClub();
			return new ResponseEntity<List<ClubDto>>(clubs, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<ClubDto>>(HttpStatus.INTERNAL_SERVER_ERROR);
//			return new ResponseEntity<ClubDto>(new ClubDto(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "그룹 정보 수정", notes = "그룹의 이름, 설명, 대표 이미지를 수정합니다.")
	@PutMapping
	public ResponseEntity<String> updateClub(@RequestBody ClubDto club,
			@RequestParam(value = "file", required = false) MultipartFile file){
		try {
			if(clubService.updateClub(club, file) == 1)
				return new ResponseEntity<String>("SUCCESS: club update", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>("FAILURE: club update", HttpStatus.INTERNAL_SERVER_ERROR);
	}	

	@ApiOperation(value = "그룹장 변경", notes = "그룹의 대표인 그룹장을 다른 사용자로 변경합니다.")
	@PutMapping("/manager")
	public ResponseEntity<String> updateClubManager(@RequestBody ClubDto club){
		try {
			if(clubService.updateClubManager(club.getClubId(), club.getUserId()) == 1)
				return new ResponseEntity<String>("SUCCESS: club manager update", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>("FAILURE: club manager update", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ApiOperation(value = "그룹 삭제", notes = "그룹을 삭제합니다.")
	@DeleteMapping
	public ResponseEntity<String> deleteClub(@RequestBody ClubDto club){
		try {
			//삭제 전에 member들에게 다 알려야 함
			if(clubService.deleteClub(club.getClubId()) == SUCCESS)
				return new ResponseEntity<String>("SUCCESS: club deletion", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>("FAILURE: club deletion", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	

	/* CLUB MEMBER */
	@ApiOperation(value = "그룹 멤버 조회", notes = "한 그룹에 등록된 모든 멤버의 정보를 조회합니다.")
	@GetMapping("/{clubId}/member")
	public ResponseEntity<List<MemberDto>> selectAllClubMember(@PathVariable String clubId){  //read every member's info
		try {
			List<MemberDto> members = clubService.selectAllClubMember(clubId);
			return new ResponseEntity<List<MemberDto>>(members, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<List<MemberDto>>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ApiOperation(value = "그룹 멤버 조회", notes = "한 그룹에 등록된 모든 멤버의 정보를 조회합니다.")
	@GetMapping("/{clubId}/waiting")
	public ResponseEntity<List<MemberDto>> selectAllClubMemberWaiting(@PathVariable String clubId){  //read every member's info
		try {
			List<MemberDto> members = clubService.selectAllClubMemberWaiting(clubId);
			return new ResponseEntity<List<MemberDto>>(members, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<List<MemberDto>>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ApiOperation(value = "그룹 가입 신청", notes = "승인이 필요한 그룹일 경우 가입 신청을 한 사용자는 승인 대기 상태가 되고, 승인이 필요하지 않은 경우 바로 가입 처리 됩니다.")
	@PostMapping("/member")
	public ResponseEntity<String> applyClub(@RequestBody MemberDto member){
		try {
			if(clubService.selectClub(member.getClubId()).getDto().getIsOpen().equals("1")) {
				if(clubService.createClubMember(member) == SUCCESS) {
					return new ResponseEntity<String>("SUCCESS: club joining", HttpStatus.OK);
				}
			}else {
				if(clubService.createClubMemberWaiting(member) == SUCCESS) {
					return new ResponseEntity<String>("SUCCESS: club apply", HttpStatus.OK);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>("FAILURE: club apply", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ApiOperation(value = "그룹 가입 승인", notes = "승인 대기 중인 사용자의 가입을 승인하여 가입 처리합니다.")
	@PostMapping("/member/waiting")
	public ResponseEntity<String> joinClub(@RequestBody MemberDto member){
		try {
			member.setType("0");
			if(clubService.createClubMember(member) == SUCCESS) {
				return new ResponseEntity<String>("SUCCESS: club joining", HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>("FAILURE: club joining", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ApiOperation(value = "그룹 탈퇴", notes = "그룹에 가입되어있는 사용자를 그룹에서 탈퇴 처리합니다.")
	@DeleteMapping("/member")
	public ResponseEntity<String> withdrawClub(@RequestBody MemberDto member){
		try {
			if(clubService.deleteClubMember(member.getClubId(), member.getUserId()) == SUCCESS)
				return new ResponseEntity<String>("SUCCESS: club withdraw", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>("FAILURE: club withdraw", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	/* 승인 대기 정보 삭제 추가해야 함 */

	@ApiOperation(value = "가입한 그룹 조회", notes = "사용자가 가입한 모든 그룹의 정보를 조회합니다.")
	@GetMapping("/{userId}/member")
	//그룹장 nickname은 안 들어갔음
	public ResponseEntity<List<ClubDto>> selectAllJoinedClub(@PathVariable String userId){
		try {
			List<ClubDto> clubs = clubService.selectAllClubMemberByUserId(userId);
			return new ResponseEntity<List<ClubDto>>(clubs, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<List<ClubDto>>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ApiOperation(value = "승인 대기 중인 그룹 조회", notes = "사용자가 승인 대기 중인 모든 그룹의 정보를 조회합니다.")
	@GetMapping("/{userId}/waiting")
	//그룹장 nickname은 안 들어갔음
	public ResponseEntity<List<ClubDto>> selectAllAppliedClub(@PathVariable String userId){
		try {
			List<ClubDto> clubs = clubService.selectAllClubMemberWaitingByUserId(userId);
			return new ResponseEntity<List<ClubDto>>(clubs, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<List<ClubDto>>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
