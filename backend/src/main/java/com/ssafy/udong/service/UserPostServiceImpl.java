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

import com.ssafy.udong.dao.UserPostDao;
import com.ssafy.udong.dto.CommentDto;
import com.ssafy.udong.dto.CommentResultDto;
import com.ssafy.udong.dto.ImageDto;
import com.ssafy.udong.dto.LikeDto;
import com.ssafy.udong.dto.ReportDto;
import com.ssafy.udong.dto.UserPostDto;
import com.ssafy.udong.dto.UserPostParamDto;
import com.ssafy.udong.dto.UserPostResultDto;

@Service
public class UserPostServiceImpl implements UserPostService {

	private final Path root = Paths.get("uploads");

	@Autowired
	UserPostDao userPostDao;

	private static final int SUCCESS = 1;
	private static final int FAIL = -1;

	// 글 작성
	@Override
	public int createUserPost(UserPostDto userPostDto, List<MultipartFile> files) {
		try {
			userPostDao.createUserPost(userPostDto);

			if (files != null && !files.isEmpty()) {
				File uploadDir = new File(
						root + File.separator + "userPost" + File.separator + userPostDto.getUserId());
				if (!uploadDir.exists())
					uploadDir.mkdirs();

				List<String> fileNames = new ArrayList<>();
				Path path = root.resolve("userPost");
				Path path2 = path.resolve(userPostDto.getUserId());

				DateFormat sdFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
				Date nowDate = new Date();
				String tempDate = sdFormat.format(nowDate);

				for (MultipartFile file : files) {
					int rank = 1;
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

					String imageFileUrl = root + File.separator + "userPost" + File.separator + userPostDto.getUserId()
							+ "/" + fileName;
					imageDto.setFileUrl(imageFileUrl);

					userPostDao.createUserPostFile(imageDto);
					userPostDao.connectionFile(userPostDto);

					rank++;

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		return 1;
	}

	// 글전체조회
	@Override
	public UserPostResultDto selectAllUserPost(int limit, int offset) {
		UserPostParamDto userPostParamDto = new UserPostParamDto();
		userPostParamDto.setLimit(limit);
		userPostParamDto.setOffset(offset);
		
		UserPostResultDto userPostResultDto = new UserPostResultDto();
		try {

			int count = userPostDao.userPostTotalCount();
			List<UserPostDto> list = userPostDao.selectAllUserPost(userPostParamDto);

			userPostResultDto.setList(list);
			userPostResultDto.setCount(count);
			userPostResultDto.setResult(SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			userPostResultDto.setResult(FAIL);
		}

		return userPostResultDto;
	}

	// 글 검색어조회
	@Override
	public UserPostResultDto selectUserPostBySearchWord(String searchWord, int limit, int offset) {
		UserPostParamDto userPostParamDto = new UserPostParamDto();
		userPostParamDto.setSearchWord(searchWord);
		userPostParamDto.setLimit(limit);
		userPostParamDto.setOffset(offset);

		UserPostResultDto userPostResultDto = new UserPostResultDto();
		try {
			// 게시물 총개수
			int count = userPostDao.userPostBySearchWordTotalCount(userPostParamDto);
			List<UserPostDto> list = userPostDao.selectUserPostBySearchWord(userPostParamDto);

			userPostResultDto.setList(list);
			userPostResultDto.setCount(count);
			userPostResultDto.setResult(SUCCESS);

		} catch (Exception e) {
			e.printStackTrace();
			userPostResultDto.setResult(FAIL);
		}

		return userPostResultDto;
	}

	// 글상세 조회
	@Override
	public UserPostResultDto selectUserPost(String postId) {

		UserPostDto userPostDto = new UserPostDto();
		UserPostResultDto userPostResultDto = new UserPostResultDto();

		try {
			userPostDao.updateUserPostViews(postId); // 조회수 1증가
			userPostDto = userPostDao.selectUserPost(postId); // 상세 리스트 가져오기

			List<String> list = userPostDao.selectFileUrl(postId);

			userPostResultDto.setDto(userPostDto);
			userPostResultDto.setFileUrl(list);

			return userPostResultDto;

		} catch (Exception e) {
			e.printStackTrace();
			// return null;
		}
		return userPostResultDto;
	}

	// 글 수정
	@Override
	public int updateUserPost(UserPostDto userPostDto) {

		try {
			return userPostDao.updateUserPost(userPostDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	// 글 삭제
	@Override
	public int deleteUserPost(String postId) {
		try {
			return userPostDao.deleteUserPost(postId);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int createUserPostComment(CommentDto commentDto) {
		try {
			return userPostDao.createUserPostComment(commentDto);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public CommentResultDto selectUserPostComment(String postId) {
		CommentResultDto commentResultDto = new CommentResultDto();
		try {
			int count = userPostDao.userPostCommentTotalCount();
			List<CommentDto> list = userPostDao.selectUserPostComment(postId);

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
	public int createUserPostLike(LikeDto likeDto) {
		try {
			if (userPostDao.selectUserPostLike(likeDto) == null) {
				userPostDao.createUserPostLike(likeDto);
				return 1;
			} else {
				userPostDao.deleteUserPostLike(likeDto);
				return 2;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int createUserPostCommentLike(LikeDto likeDto) {
		try {
			if (userPostDao.selectUserPostCommentLike(likeDto) == null) {
				userPostDao.createUserPostCommentLike(likeDto);
				return 1;
			} else {
				userPostDao.deleteUserPostCommentLike(likeDto);
				return 2;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int createUserPostReport(ReportDto reportDto) {
		try {
			userPostDao.createUserPostReport(reportDto);
			return 1;

		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int createUserPostCommentReport(ReportDto reportDto) {
		try {
			userPostDao.createUserPostCommentReport(reportDto);
			return 1;

		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public UserPostResultDto selectAllUserPostByUserId(String userId, int limit, int offset) {
		UserPostParamDto userPostParamDto = new UserPostParamDto();
		userPostParamDto.setUserId(userId);
		userPostParamDto.setLimit(limit);
		userPostParamDto.setOffset(offset);

		UserPostResultDto userPostResultDto = new UserPostResultDto();
		try {
			// 게시물 총개수
			int count = userPostDao.userPostByUserIdTotalCount(userPostParamDto);
			List<UserPostDto> list = userPostDao.selectUserPostByUserId(userPostParamDto);

			userPostResultDto.setList(list);
			userPostResultDto.setCount(count);
			userPostResultDto.setResult(SUCCESS);

		} catch (Exception e) {
			e.printStackTrace();
			userPostResultDto.setResult(FAIL);
		}

		return userPostResultDto;
	}

	@Override
	public UserPostResultDto selectUserPostByUserIdAndWord(String userId, String searchWord, int limit, int offset) {
		UserPostParamDto userPostParamDto = new UserPostParamDto();
		userPostParamDto.setUserId(userId);
		userPostParamDto.setSearchWord(searchWord);
		userPostParamDto.setLimit(limit);
		userPostParamDto.setOffset(offset);

		UserPostResultDto userPostResultDto = new UserPostResultDto();
		try {
			// 게시물 총개수
			int count = userPostDao.userPostByUserIdAndWordTotalCount(userPostParamDto);
			List<UserPostDto> list = userPostDao.selectUserPostByUserIdAndWord(userPostParamDto);

			userPostResultDto.setList(list);
			userPostResultDto.setCount(count);
			userPostResultDto.setResult(SUCCESS);

		} catch (Exception e) {
			e.printStackTrace();
			userPostResultDto.setResult(FAIL);
		}

		return userPostResultDto;
	}

}
