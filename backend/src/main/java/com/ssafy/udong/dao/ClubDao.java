package com.ssafy.udong.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.udong.dto.ClubDto;
import com.ssafy.udong.dto.ImageDto;
import com.ssafy.udong.dto.MemberDto;


@Mapper
public interface ClubDao {

	int createClub(ClubDto club);
	
	int createClubFile(ImageDto imageDto);
	
	int createClubNoFile(ClubDto club);
	
	String selectDuplicateClubName(String clubname, String areaCode);

	String selectClubId();
	ClubDto selectClub(String clubId);
	
	List<String> selectFileUrl(String id);

	String selectNickname(String userId);
	
	List<ClubDto> selectAllClub();

	int updateClub(ClubDto club);
	int updateClubNoFile(ClubDto club);

	int updateClubManager(String clubId, String userId);

	int deleteClub(String clubId);

	List<MemberDto> selectAllClubMember(String clubId);

	List<MemberDto> selectAllClubMemberWaiting(String clubId);

	int createClubMember(MemberDto member);

	int addClubMemberNumber(String clubId);

	int reduceClubMemberNumber(String clubId);

	int createClubMemberWaiting(MemberDto member);

	int deleteClubMember(String clubId, String userId);

	int deleteClubMemberWaiting(String clubId, String userId);

	List<ClubDto> selectAllClubByUserId(String userId);

	List<ClubDto> selectAllClubWaitingByUserId(String userId);
	

	String selectDuplicateClubName(String clubname);


}
