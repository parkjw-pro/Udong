package com.ssafy.udong.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class ReviewResultDto {
	
	ReviewDto dto;
	List<String> fileId;
	
	public ReviewResultDto() {}
	
	public ReviewDto getDto() {
		return dto;
	}
	public void setDto(ReviewDto dto) {
		this.dto = dto;
	}

	public List<String> getFileId() {
		return fileId;
	}

	public void setFileId(List<String> fileId) {
		this.fileId = fileId;
	}

	
	

	
	

}
