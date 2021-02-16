package com.ssafy.udong.service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.udong.dao.ClubPostDao;
import com.ssafy.udong.dto.ClubPostDto;
import com.ssafy.udong.dto.ClubPostParamDto;
import com.ssafy.udong.dto.ClubPostResultDto;
import com.ssafy.udong.dto.CommentDto;
import com.ssafy.udong.dto.CommentResultDto;
import com.ssafy.udong.dto.ImageDto;
import com.ssafy.udong.dto.LikeDto;
import com.ssafy.udong.dto.ReportDto;
import com.ssafy.udong.dto.UserPostDto;

@Service
public class ClubPostServiceImpl implements ClubPostService {

	private final Path root = Paths.get("uploads");

	@Autowired
	ClubPostDao clubPostDao;

	private static final int SUCCESS = 1;
	private static final int FAIL = -1;

	@Override
	public int createClubPost(ClubPostDto clubPostDto, MultipartFile[] files) {
		try {
			clubPostDao.createClubPost(clubPostDto);

			if (files != null && !(files.length == 0)) {
				File uploadDir = new File(
						root + File.separator + "clubPost" + File.separator + clubPostDto.getUserId());
				if (!uploadDir.exists())
					uploadDir.mkdirs();

				List<String> fileNames = new ArrayList<>();
				Path path = root.resolve("clubPost");
				Path path2 = path.resolve(clubPostDto.getUserId());

				DateFormat sdFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
				Date nowDate = new Date();
				String tempDate = sdFormat.format(nowDate);

				for (MultipartFile file : files) {

					try {

						System.out.println("file service");
						System.out.println(file.getOriginalFilename());
						Files.copy(file.getInputStream(),
								path2.resolve("udong_" + tempDate + file.getOriginalFilename()));

						System.out.println("file service after");

					} catch (Exception e) {
						throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
					}

					fileNames.add(file.getOriginalFilename());
					System.out.println(file.getOriginalFilename());

					String fileName = "udong_" + tempDate + file.getOriginalFilename();

					ImageDto imageDto = new ImageDto();
					imageDto.setFileName(fileName);
					imageDto.setFileSize(file.getSize());
					imageDto.setFileContentType(file.getContentType());

					String imageFileUrl = root + File.separator + "clubPost" + File.separator + clubPostDto.getUserId()
							+ "/" + fileName;
					imageDto.setFileUrl(imageFileUrl);

					clubPostDao.createClubPostFile(imageDto);
					clubPostDao.connectionFile(clubPostDto);

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		return 1;
	}

	@Override
	public ClubPostResultDto selectAllClubPost(int limit, int offset) {
		ClubPostResultDto clubPostResultDto = new ClubPostResultDto();
		
		ClubPostParamDto clubPostParamDto = new ClubPostParamDto();
		clubPostParamDto.setLimit(limit);
		clubPostParamDto.setOffset(offset);
		
		try {
			int count = clubPostDao.clubPostCount();
			List<ClubPostDto> list = clubPostDao.selectAllClubPost(clubPostParamDto);

			clubPostResultDto.setList(list);
			clubPostResultDto.setCount(count);
			clubPostResultDto.setResult(SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			clubPostResultDto.setResult(FAIL);
		}

		return clubPostResultDto;
	}

	@Override
	public ClubPostResultDto selectClubPostBySearchWord(String searchWord, int limit, int offset) {
		ClubPostResultDto clubPostResultDto = new ClubPostResultDto();	
		
		ClubPostParamDto clubPostParamDto = new ClubPostParamDto();
		clubPostParamDto.setSearchWord(searchWord);
		clubPostParamDto.setLimit(limit);
		clubPostParamDto.setOffset(offset);
		try {
			// 게시물 총개수
			int count = clubPostDao.clubPostBySearchWordCount(clubPostParamDto);
			List<ClubPostDto> list = clubPostDao.selectClubPostBySearchWord(clubPostParamDto);

			clubPostResultDto.setList(list);
			clubPostResultDto.setCount(count);
			clubPostResultDto.setResult(SUCCESS);

		} catch (Exception e) {
			e.printStackTrace();
			clubPostResultDto.setResult(FAIL);
		}

		return clubPostResultDto;
	}

	@Override
	public ClubPostResultDto selectClubPostByClubId(String clubId, int limit, int offset) {
		ClubPostResultDto clubPostResultDto = new ClubPostResultDto();
		
		ClubPostParamDto clubPostParamDto = new ClubPostParamDto();
		clubPostParamDto.setClubId(clubId);
		clubPostParamDto.setLimit(limit);
		clubPostParamDto.setOffset(offset);
		
		try {
			int count = clubPostDao.clubPostByClubIdCount(clubPostParamDto);
			List<ClubPostDto> list = clubPostDao.selectClubPostByClubId(clubPostParamDto);

			clubPostResultDto.setList(list);
			clubPostResultDto.setCount(count);
			clubPostResultDto.setResult(SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			clubPostResultDto.setResult(FAIL);
		}

		return clubPostResultDto;
	}

	@Override
	public ClubPostResultDto selectClubPostByClubIdAndSearchWord(String clubId, String searchWord, int limit,
			int offset) {
		ClubPostResultDto clubPostResultDto = new ClubPostResultDto();	
		
		ClubPostParamDto clubPostParamDto = new ClubPostParamDto();
		clubPostParamDto.setClubId(clubId);
		clubPostParamDto.setSearchWord(searchWord);
		clubPostParamDto.setLimit(limit);
		clubPostParamDto.setOffset(offset);
		try {
			// 게시물 총개수
			int count = clubPostDao.clubPostByClubIdAndSearchWordCount(clubPostParamDto);
			List<ClubPostDto> list = clubPostDao.selectClubPostByClubIdAndSearchWord(clubPostParamDto);

			clubPostResultDto.setList(list);
			clubPostResultDto.setCount(count);
			clubPostResultDto.setResult(SUCCESS);

		} catch (Exception e) {
			e.printStackTrace();
			clubPostResultDto.setResult(FAIL);
		}

		return clubPostResultDto;
	}

	@Override
	public ClubPostResultDto selectClubPostByUserId(String userId, int limit, int offset) {
		ClubPostResultDto clubPostResultDto = new ClubPostResultDto();
		
		ClubPostParamDto clubPostParamDto = new ClubPostParamDto();
		clubPostParamDto.setUserId(userId);
		clubPostParamDto.setLimit(limit);
		clubPostParamDto.setOffset(offset);
		
		try {

			int count = clubPostDao.clubPostByUserIdCount(clubPostParamDto);
			List<ClubPostDto> list = clubPostDao.selectClubPostByUserId(clubPostParamDto);
			
			clubPostResultDto.setList(list);
			clubPostResultDto.setCount(count);
			clubPostResultDto.setResult(SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			clubPostResultDto.setResult(FAIL);
		}

		return clubPostResultDto;
	}

	@Override
	public ClubPostResultDto selectClubPostByUserIdAndSearchWord(String userId, String searchWord, int limit,
			int offset) {
		ClubPostResultDto clubPostResultDto = new ClubPostResultDto();	
		
		ClubPostParamDto clubPostParamDto = new ClubPostParamDto();
		clubPostParamDto.setUserId(userId);
		clubPostParamDto.setSearchWord(searchWord);
		clubPostParamDto.setLimit(limit);
		clubPostParamDto.setOffset(offset);
		try {
			// 게시물 총개수
			int count = clubPostDao.clubPostByUserIdAndSearchWordCount(clubPostParamDto);
			List<ClubPostDto> list = clubPostDao.selectClubPostByUserIdAndSearchWord(clubPostParamDto);

			clubPostResultDto.setList(list);
			clubPostResultDto.setCount(count);
			clubPostResultDto.setResult(SUCCESS);

		} catch (Exception e) {
			e.printStackTrace();
			clubPostResultDto.setResult(FAIL);
		}

		return clubPostResultDto;
	}

	@Override
	public ClubPostResultDto selectClubPost(String postId) {

		ClubPostDto clubPostDto = new ClubPostDto();
		ClubPostResultDto clubPostResultDto = new ClubPostResultDto();

		try {

			clubPostDao.updateClubPostViews(postId); // 조회수 1증가
			clubPostDto = clubPostDao.selectClubPost(postId); // 상세 리스트 가져오기

			
			List<String> list = clubPostDao.selectFileId(postId);
			
			clubPostResultDto.setDto(clubPostDto);
			clubPostResultDto.setFileId(list);

			return clubPostResultDto;

		} catch (Exception e) {
			e.printStackTrace();
			// return null;
		}
		return clubPostResultDto;
	}

	@Override
	public int updateClubPost(ClubPostDto clubPostDto) {

		try {
			return clubPostDao.updateClubPost(clubPostDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int deleteClubPost(String postId) {

		try {
			return clubPostDao.deleteClubPost(postId);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}

	@Override
	public int createClubPostComment(CommentDto commentDto) {

		try {
			return clubPostDao.createClubPostComment(commentDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;

	}

	@Override
	public CommentResultDto selectClubPostComment(String postId, int limit, int offset) {

		CommentResultDto commentResultDto = new CommentResultDto();

		try {

			int count = clubPostDao.userPostCommentTotalCount(postId);
			List<CommentDto> list = clubPostDao.selectClubPostComment(postId, limit, offset);
			
			commentResultDto.setList(list);
			commentResultDto.setCount(count);
			commentResultDto.setResult(SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			commentResultDto.setResult(FAIL);
		}

		return commentResultDto;

	}

	@Override
	public int selectClubPostLike(String userId, String postId, String clubId) {
		try {
			LikeDto likeDto = new LikeDto();
			likeDto.setUserId(userId);
			likeDto.setPostId(postId);
			likeDto.setClubId(clubId);
			if (clubPostDao.selectClubPostLike(likeDto) != null)
				return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}


	@Override
	public int createClubPostLike(LikeDto likeDto) {
		try {
			if (clubPostDao.selectClubPostLike(likeDto) == null) {
				clubPostDao.createClubPostLike(likeDto);
				return 1;
			} else {
				clubPostDao.deleteClubPostLike(likeDto);
				return 2;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int selectClubPostCommentLike(String userId, String postId, String clubId, String commentId) {
		try {
			LikeDto likeDto = new LikeDto();
			likeDto.setUserId(userId);
			likeDto.setPostId(postId);
			likeDto.setClubId(clubId);
			likeDto.setCommentId(commentId);
			if (clubPostDao.selectClubPostCommentLike(likeDto) != null)
				return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	@Override
	public int createClubPostCommentLike(LikeDto likeDto) {
		try {
			if (clubPostDao.selectClubPostCommentLike(likeDto) == null) {
				clubPostDao.createClubPostCommentLike(likeDto);
				return 1;
			} else {
				clubPostDao.deleteClubPostCommentLike(likeDto);
				return 2;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}

	}

	@Override
	public int createClubPostReport(ReportDto reportDto) {
		try {
			clubPostDao.createClubPostReport(reportDto);
			return 1;

		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int createClubPostCommentReport(ReportDto reportDto) {
		try {
			clubPostDao.createClubPostCommentReport(reportDto);
			return 1;

		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

}
