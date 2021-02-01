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
import com.ssafy.udong.dto.UserBoardDto;

@Service
public class ClubPostServiceImpl implements ClubPostService {

	private final Path root = Paths.get("uploads");

	@Autowired
	ClubPostDao dao;

	private static final int SUCCESS = 1;
	private static final int FAIL = -1;

	@Override
	public int createPost(ClubPostDto clubPostDto, List<MultipartFile> files) {
		try {
			dao.createPost(clubPostDto);

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

					dao.createClubPostFile(imageDto);
					dao.connectionFile(clubPostDto);

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		return 1;
	}

	@Override
	public ClubPostResultDto listPost(ClubPostParamDto clubPostParamDto) {

		ClubPostResultDto clubPostResultDto = new ClubPostResultDto();
		try {

			int count = dao.totalCountPostList();
			List<ClubPostDto> list = dao.listPost(clubPostParamDto);

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
	public ClubPostResultDto searchWordListPost(ClubPostParamDto clubPostParamDto) {

		ClubPostResultDto clubPostResultDto = new ClubPostResultDto();
		try {
			// 게시물 총개수
			int count = dao.searchWordTotalCountPostList(clubPostParamDto);
			List<ClubPostDto> list = dao.searchWordListPost(clubPostParamDto);

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
	public ClubPostResultDto detailPost(String postId) {

		ClubPostDto clubPostDto = new ClubPostDto();
		ClubPostResultDto clubPostResultDto = new ClubPostResultDto();

		try {

			dao.postReadCountUpdate(postId); // 조회수 1증가
			clubPostDto = dao.detailPost(postId); // 상세 리스트 가져오기

			List<String> list = dao.SelectFileUrl(postId);

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
	public int updatePost(ClubPostDto clubPostDto) {

		try {
			return dao.updatePost(clubPostDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int deletePost(String postId) {

		try {
			return dao.deletePost(postId);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}

	@Override
	public int createPostCom(CommentDto commentDto) {

		try {
			return dao.createPostCom(commentDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;

	}

	@Override
	public CommentResultDto selectPostCom(String postId) {

		CommentResultDto commentResultDto = new CommentResultDto();

		try {

			int count = dao.totalCountcomList();
			List<CommentDto> list = dao.selectPostCom(postId);

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
	public int createPostLike(LikeDto likeDto) {

		try {
			if (dao.selectPostLike(likeDto) == null) {
				dao.createPostLike(likeDto);
				return 1;
			} else {
				dao.deletePostLike(likeDto);
				return 2;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int createPostCommLike(LikeDto likeDto) {
		try {
			if (dao.selectPostCommLike(likeDto) == null) {
				dao.createPostCommLike(likeDto);
				return 1;
			} else {
				dao.deletePostCommLike(likeDto);
				return 2;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}

	}

	@Override
	public int createPostReport(ReportDto reportDto) {
		try {
			dao.createPostReport(reportDto);
			return 1;

		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int createPostCommReport(ReportDto reportDto) {
		try {
			dao.createPostCommReport(reportDto);
			return 1;

		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

}
