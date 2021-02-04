package com.ssafy.udong.dto;

import java.util.List;

public class UserPostResultDto {
	private int result;
	private UserPostDto dto;
	private List<UserPostDto> list;
	private int count;
	List<String> fileUrl;

	public UserPostResultDto() {}

	public List<String> getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(List<String> fileUrl) {
		this.fileUrl = fileUrl;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public UserPostDto getDto() {
		return dto;
	}

	public void setDto(UserPostDto dto) {
		this.dto = dto;
	}

	public List<UserPostDto> getList() {
		return list;
	}

	public void setList(List<UserPostDto> list) {
		this.list = list;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
}
