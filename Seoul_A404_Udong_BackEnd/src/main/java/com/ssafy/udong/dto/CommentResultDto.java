package com.ssafy.udong.dto;

import java.util.List;

public class CommentResultDto {
	
	private int result;
	private CommentDto dto;
	private List<CommentDto> list;
	private int count;
	
	public CommentResultDto() {
	}
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	public CommentDto getDto() {
		return dto;
	}
	public void setDto(CommentDto dto) {
		this.dto = dto;
	}
	public List<CommentDto> getList() {
		return list;
	}
	public void setList(List<CommentDto> list) {
		this.list = list;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	

}
