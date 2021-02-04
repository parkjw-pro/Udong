package com.ssafy.udong.dto;

import org.springframework.web.multipart.MultipartFile;

public class FileResultDto {
	
	ClubDto clubDto;
	MultipartFile files;
	ClubPostDto clubPostDto;
	ReviewDto reviewDto;
	UserPostDto userPostDto;
	
	public FileResultDto() {
		super();
	}
	public ClubDto getClubDto() {
		return clubDto;
	}
	public void setClubDto(ClubDto clubDto) {
		this.clubDto = clubDto;
	}
	public MultipartFile getFiles() {
		return files;
	}
	public void setFiles(MultipartFile files) {
		this.files = files;
	}
	public ClubPostDto getClubPostDto() {
		return clubPostDto;
	}
	public void setClubPostDto(ClubPostDto clubPostDto) {
		this.clubPostDto = clubPostDto;
	}
	public ReviewDto getReviewDto() {
		return reviewDto;
	}
	public void setReviewDto(ReviewDto reviewDto) {
		this.reviewDto = reviewDto;
	}
	public UserPostDto getUserPostDto() {
		return userPostDto;
	}
	public void setUserPostDto(UserPostDto userPostDto) {
		this.userPostDto = userPostDto;
	}
	
	
	

}
