package com.ssafy.udong.dto;

import java.util.List;

public class ClubResultDto {
	
	ClubDto dto;
	List<String> fileUrl;
	
	public ClubResultDto() {
		super();
	}
	
	public ClubDto getDto() {
		return dto;
	}
	public void setDto(ClubDto dto) {
		this.dto = dto;
	}
	public List<String> getFileUrl() {
		return fileUrl;
	}
	public void setFileUrl(List<String> fileUrl) {
		this.fileUrl = fileUrl;
	}
	
	
	
	

}
