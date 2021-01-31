package com.ssafy.udong.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class ReviewResultDto {
	
	ReviewDto dto;
	List<String> fileUrl;
	
	public ReviewResultDto() {
		super();
	}
	public ReviewDto getDto() {
		return dto;
	}
	public void setDto(ReviewDto dto) {
		this.dto = dto;
	}
	public List<String> getFileUrl() {
		return fileUrl;
	}
	public void setFileUrl(List<String> fileUrl) {
		this.fileUrl = fileUrl;
	}
	
	
	
	

	
	

}
