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
	public int createClubPost(ClubPostDto clubPostDto, List<MultipartFile> files) {
		try {
			clubPostDao.createClubPost(clubPostDto);

			if (files != null && !files.isEmpty()) {
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
	public ClubPostResultDto selectAllClubPost(ClubPostParamDto clubPostParamDto) {

		ClubPostResultDto clubPostResultDto = new ClubPostResultDto();
		try {

			int count = clubPostDao.clubPostTotalCount();
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
	public ClubPostResultDto selectClubPostBySearchWord(ClubPostParamDto clubPostParamDto) {

		ClubPostResultDto clubPostResultDto = new ClubPostResultDto();
		try {
			// 게시물 총개수
			int count = clubPostDao.clubPostBySearchWordTotalCount(clubPostParamDto);
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
	public ClubPostResultDto selectClubPost(String postId) {

		ClubPostDto clubPostDto = new ClubPostDto();
		ClubPostResultDto clubPostResultDto = new ClubPostResultDto();

		try {

			clubPostDao.updateClubPostViews(postId); // 조회수 1증가
			clubPostDto = clubPostDao.selectClubPost(postId); // 상세 리스트 가져오기

			List<String> list = clubPostDao.selectFileUrl(postId);

			clubPostResultDto.setDto(clubPostDto);
			clubPostResultDto.setFileUrl(list);

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
	public CommentResultDto selectClubPostComment(String postId) {

		CommentResultDto commentResultDto = new CommentResultDto();

		try {

			int count = clubPostDao.userPostCommentTotalCount();
			List<CommentDto> list = clubPostDao.selectClubPostComment(postId);

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
