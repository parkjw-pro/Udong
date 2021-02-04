package com.ssafy.udong.service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.udong.dao.ClubDao;
import com.ssafy.udong.dao.ReviewDao;
import com.ssafy.udong.dto.ClubDto;
import com.ssafy.udong.dto.ClubResultDto;
import com.ssafy.udong.dto.ImageDto;
import com.ssafy.udong.dto.MemberDto;

@Service
public class ClubServiceImpl implements ClubService {

	private final Path root = Paths.get("uploads");

	@Autowired
	ClubDao clubDao;
	
	/* CLUB */
	@Override
	public String createClub(ClubDto Club, MultipartFile file) {
		try {
			if (file != null && !file.isEmpty()) {
				File uploadDir = new File(root + File.separator + "club" + File.separator + Club.getUserId());
				if (!uploadDir.exists())
					uploadDir.mkdirs();

				List<String> fileNames = new ArrayList<>();
				Path path = root.resolve("club");
				Path path2 = path.resolve(Club.getUserId());
				
				DateFormat sdFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
				Date nowDate = new Date();
				String tempDate = sdFormat.format(nowDate);

				try {
					System.out.println("file service");
					System.out.println(file.getOriginalFilename());
					Files.copy(file.getInputStream(), path2.resolve("udong_"+tempDate+file.getOriginalFilename()));

					System.out.println("file service after");

				} catch (Exception e) {
					throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
				}

				fileNames.add(file.getOriginalFilename());
				System.out.println(file.getOriginalFilename());


				String fileName = "udong_"+tempDate+file.getOriginalFilename();
				ImageDto imageDto = new ImageDto();
				imageDto.setFileName(fileName);
				imageDto.setFileSize(file.getSize());
				imageDto.setFileContentType(file.getContentType());

				String imageFileUrl = root + File.separator+ "club" + File.separator+ Club.getUserId()+"/" + fileName;
				imageDto.setFileUrl(imageFileUrl);

				clubDao.createClubFile(imageDto);
				clubDao.createClub(Club);
				

			}else {
				clubDao.createClubNoFile(Club);
			}
			

		} catch (Exception e) {
			e.printStackTrace();
			return "-1";
		}
		return clubDao.selectClubId();
	}

	@Override
	public String selectDuplicateClubName(String clubName, String areaCode) {
		try {
			return clubDao.selectDuplicateClubName(clubName, areaCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public ClubResultDto selectClub(String clubId) {
		ClubResultDto rdto = new ClubResultDto();
		try {
			ClubDto club = clubDao.selectClub(clubId);
			String clubLeaderNickname = clubDao.selectNickname(club.getUserId());
			club.setNickname(clubLeaderNickname);
			
			List<String> list = clubDao.selectFileUrl(club.getFileId());
			
			rdto.setDto(club);
			rdto.setFileUrl(list);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return rdto;
	}

	@Override
	public List<ClubDto> selectAllClub() {
		try {
			List<ClubDto> list = clubDao.selectAllClub();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int updateClub(ClubDto Club, MultipartFile file) {
		try {
			
			if (file != null && !file.isEmpty()) {
				File uploadDir = new File(root + File.separator + "club" + File.separator + Club.getUserId());
				if (!uploadDir.exists())
					uploadDir.mkdirs();

				List<String> fileNames = new ArrayList<>();
				Path path = root.resolve("club");
				Path path2 = path.resolve(Club.getUserId());

				try {

					System.out.println("file service");
					System.out.println(file.getOriginalFilename());
					Files.copy(file.getInputStream(), path2.resolve(file.getOriginalFilename()));

					System.out.println("file service after");

				} catch (Exception e) {
					throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
				}

				fileNames.add(file.getOriginalFilename());
				System.out.println(file.getOriginalFilename());

				String fileName = file.getOriginalFilename();
				ImageDto imageDto = new ImageDto();
				imageDto.setFileName(fileName);
				imageDto.setFileSize(file.getSize());
				imageDto.setFileContentType(file.getContentType());

				String imageFileUrl = root + "club" + Club.getUserId();
				imageDto.setFileUrl(imageFileUrl);

				clubDao.createClubFile(imageDto);
				clubDao.updateClub(Club);
				

			}else {
				clubDao.updateClubNoFile(Club);
			}
			// 사진 파일 바뀌었으면 이 부분 수정해야됨
			// file 테이블 내용도!
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		return 1;
	}

	@Override
	public int updateClubManager(String clubId, String userId) {
		try {
			return clubDao.updateClubManager(clubId, userId);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int deleteClub(String clubId) {
		try {
			return clubDao.deleteClub(clubId);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	/* CLUB MEMBER */
	@Override
	public List<MemberDto> selectAllClubMember(String clubId) {
		try {
			List<MemberDto> list = clubDao.selectAllClubMember(clubId);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<MemberDto> selectAllClubMemberWaiting(String clubId) {
		try {
			List<MemberDto> list = clubDao.selectAllClubMemberWaiting(clubId);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int createClubMember(MemberDto member) {
		try {
			clubDao.deleteClubMemberWaiting(member.getClubId(), member.getUserId());
			int create = clubDao.createClubMember(member);
			int update = clubDao.addClubMemberNumber(member.getClubId());
			if (create == 1 && update == 1)
				return 1;
			else
				return 0;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int createClubMemberWaiting(MemberDto member) {
		try {
			return clubDao.createClubMemberWaiting(member);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int deleteClubMember(String clubId, String userId) {
		try {
			int delete = clubDao.deleteClubMember(clubId, userId);
			int update = clubDao.reduceClubMemberNumber(clubId);
			if (delete == 1 && update == 1)
				return 1;
			else
				return 0;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	@Override
	public int deleteClubMemberWaiting(String clubId, String userId) {
		try {
			return clubDao.deleteClubMemberWaiting(clubId, userId);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public List<ClubDto> selectAllClubMemberByUserId(String userId) {
		try {
			List<ClubDto> list = clubDao.selectAllClubByUserId(userId);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<ClubDto> selectAllClubMemberWaitingByUserId(String userId) {
		try {
			List<ClubDto> list = clubDao.selectAllClubWaitingByUserId(userId);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String selectDuplicateClubName(String clubname) {
		try {
			return clubDao.selectDuplicateClubName(clubname);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
