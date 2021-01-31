package com.ssafy.udong.dto;

import java.util.List;

public class UserBoardResultDto {
	private int result;
	private UserBoardDto dto;
	private List<UserBoardDto> list;
	private int count;
	List<String> fileUrl;
	

	public UserBoardResultDto() {
		super();
	}

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
	public UserBoardDto getDto() {
		return dto;
	}
	public void setDto(UserBoardDto dto) {
		this.dto = dto;
	}

	public List<UserBoardDto> getList() {
		return list;
	}
	public void setList(List<UserBoardDto> list) {
		this.list = list;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
}



