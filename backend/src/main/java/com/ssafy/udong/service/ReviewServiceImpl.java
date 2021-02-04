package com.ssafy.udong.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.nio.file.Path;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.udong.dao.ReviewDao;
import com.ssafy.udong.dto.ImageDto;
import com.ssafy.udong.dto.ReportDto;
import com.ssafy.udong.dto.ReviewDto;
import com.ssafy.udong.dto.ReviewResultDto;

@Service
public class ReviewServiceImpl implements ReviewService {

	private final Path root = Paths.get("uploads");

	@Autowired
	ReviewDao dao;

	@Override
	public int createReview(ReviewDto reviewDto, MultipartFile[] files) {
		try {
			dao.createReview(reviewDto);

//			System.out.println(reviewDto.getReviewContent());

			if (files != null && !(files.length == 0)) {
				File uploadDir = new File(root + File.separator + "review" + File.separator + reviewDto.getUserId());
				if (!uploadDir.exists())
					uploadDir.mkdirs();

				List<String> fileNames = new ArrayList<>();
				Path path = root.resolve("review");
				Path path2 = path.resolve(reviewDto.getUserId());

				DateFormat sdFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
				Date nowDate = new Date();
				String tempDate = sdFormat.format(nowDate);

				for (MultipartFile file : files) {
					int rank = 1;
					try {

//						System.out.println("file service");
//						System.out.println(file.getOriginalFilename());
						Files.copy(file.getInputStream(),
								path2.resolve("udong_" + tempDate + file.getOriginalFilename()));

//						System.out.println("file service after");

					} catch (Exception e) {
						throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
					}

					fileNames.add(file.getOriginalFilename());
//					System.out.println(file.getOriginalFilename());

					String fileName = "udong_" + tempDate + file.getOriginalFilename();
					ImageDto imageDto = new ImageDto();
					imageDto.setFileName(fileName);
					imageDto.setFileSize(file.getSize());
					imageDto.setFileContentType(file.getContentType());

					String imageFileUrl = root + File.separator + "review" + File.separator + reviewDto.getUserId()
							+ "/" + fileName;
					imageDto.setFileUrl(imageFileUrl);

					dao.createReviewFile(imageDto);
					dao.connectionFile(reviewDto);

					rank++;

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}

		return 1;
	}

	@Override
	public List<ReviewDto> selectAllReview(String storeId) {
		try {
			List<ReviewDto> list = dao.selectAllReview(storeId);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<ReviewDto> selectMyReview(String userId) {
		try {
			List<ReviewDto> list = dao.selectMyReview(userId);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public ReviewResultDto selectReview(String reviewId) {
		try {
			dao.updateReviewReadCount(reviewId);  //리뷰 조회수 증가
			
			//리뷰 조회
			ReviewDto dto = dao.selectReview(reviewId);
			dto.setNickname(dao.findNickname(dto.getUserId()));

			//리뷰 사진 조회
			List<String> list = dao.selectFileUrl(reviewId);

			//리뷰 결과 dto 생성
			ReviewResultDto rdto = new ReviewResultDto();
			rdto.setDto(dto);
			rdto.setFileUrl(list);

			return rdto;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int reviewListTotalCount(String storeId) {
		try {
			return dao.reviewListTotalCount(storeId);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int updateReviewReadCount(String reviewId) {
		try {
			return dao.updateReviewReadCount(reviewId);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	/* file이 생략되어 있음 수정 필요한지 확인 */
	@Override
	public int updateReview(ReviewDto reviewDto, MultipartFile file) {
		try {
			return dao.updateReview(reviewDto);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	@Override
	public int deleteReview(String reviewId) {
		try {
			return dao.deleteReview(reviewId);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int createReviewReport(ReportDto reportDto) {
		try {
			return dao.createReviewReport(reportDto);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

}
