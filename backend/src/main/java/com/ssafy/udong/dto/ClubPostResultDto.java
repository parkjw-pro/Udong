package com.ssafy.udong.dto;

import java.util.List;

public class ClubPostResultDto {
	
	private int result;
	private ClubPostDto dto;
	private List<ClubPostDto> list;
	private int count;
	List<String> fileUrl;
	
	public ClubPostResultDto() {
	}
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	public ClubPostDto getDto() {
		return dto;
	}
	public void setDto(ClubPostDto dto) {
		this.dto = dto;
	}
	public List<ClubPostDto> getList() {
		return list;
	}
	public void setList(List<ClubPostDto> list) {
		this.list = list;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public List<String> getFileUrl() {
		return fileUrl;
	}
	public void setFileUrl(List<String> fileUrl) {
		this.fileUrl = fileUrl;
	}
	
	

}
