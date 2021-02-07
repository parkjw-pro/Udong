package com.ssafy.udong.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ssafy.udong.dto.ClubDto;
import com.ssafy.udong.dto.ClubResultDto;
import com.ssafy.udong.dto.MemberDto;

public interface ClubService {

	public String createClub(ClubDto club, MultipartFile file);
	
	public String selectDuplicateClubName(String clubname, String areaCode);

	public ClubResultDto selectClub(String clubId);
	
	public List<String> selectFileUrl(String clubId);

	public List<ClubDto> selectAllClub();

	public int updateClub(ClubDto club, MultipartFile file);
	
	public int updateClubManager(String clubId, String userId);

	public int deleteClub(String clubId);
	
	public List<MemberDto> selectAllClubMember(String clubId);

	public List<MemberDto> selectAllClubMemberWaiting(String clubId);

	public int createClubMember(MemberDto member);

	public int createClubMemberWaiting(MemberDto member);

	public int deleteClubMember(String clubId, String userId);
	
	public int deleteClubMemberWaiting(String clubId, String userId);

	public List<ClubDto> selectAllClubMemberByUserId(String userId);

	public List<ClubDto> selectAllClubMemberWaitingByUserId(String userId);

	public String selectDuplicateClubName(String clubname);
	
}
