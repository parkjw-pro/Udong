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
	private static final int SUCCESS = 1;
	private static final int FAIL = -1;

	
	@Override
	public int createReview(ReviewDto reviewDto, List<MultipartFile> files) {
		
		try {
			dao.createReview(reviewDto);
			
			System.out.println(reviewDto.getReviewContent());

			 
			if( files != null && ! files.isEmpty() ) {
			File uploadDir = new File(root + File.separator + "review" + File.separator +reviewDto.getUserId());
			if (!uploadDir.exists()) uploadDir.mkdirs();
			
			List<String> fileNames = new ArrayList<>();
			Path path = root.resolve("review");
			Path path2 = path.resolve(reviewDto.getUserId());
			
			DateFormat sdFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
			Date nowDate = new Date();
			String tempDate = sdFormat.format(nowDate);
			
		    for(MultipartFile file : files) {
		    	int rank = 1;
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
			    
				String imageFileUrl = root + File.separator+ "review" + File.separator+ reviewDto.getUserId()+"/" + fileName;
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
	public int UpdateReview(ReviewDto reviewDto, MultipartFile file) {

		try {
			dao.UpdateReview(reviewDto);
			
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}

		return 1;
	}

	@Override
	public List<ReviewDto> SelectAllReview(String store_id) {
		
		List<ReviewDto> list;
		
		try {
			list = dao.SelectAllReview(store_id);
					
			
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		
		return list;
	}

	@Override
	public ReviewResultDto SelectReview(String review_id) {
		
		ReviewDto dto = new ReviewDto();
		ReviewResultDto rdto = new ReviewResultDto();
		
		try {
			dao.UpdateReviewReadCount(review_id);
			dto = dao.SelectReview(review_id);
			dto.setNickName(dao.FindNick(dto.getUserId()));
			
			List<String> list = dao.SelectFileUrl(review_id);
			rdto.setDto(dto);
			rdto.setFileUrl(list);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return rdto;
		
	}

	@Override
	public int DeleteReview(String review_id) {
		
		
		
		try {
			dao.DeleteReview(review_id);
					
			
		}catch(Exception e) {
			e.printStackTrace();
			return 0;
		}
		
		return 1;
	}

	@Override
	public int ReviewListTotalCount(String store_id) {
		int count = dao.ReviewListTotalCount(store_id);	
		return count;
	}

	@Override
	public int UpdateReviewReadCount(String reviewId) {
		
		dao.UpdateReviewReadCount(reviewId);
		
		return 1;
	}

	
	@Override
	public List<ReviewDto> SelectMyReview(String user_id) {
		
		List<ReviewDto> list;
		
		try {
			list = dao.SelectMyReview(user_id);
					
			
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		
		return list;
		
		
	}

	@Override
	public int createReviewReport(ReportDto reportDto) {
		try {
			dao.createReviewReport(reportDto);
			return 1;

		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}




}
