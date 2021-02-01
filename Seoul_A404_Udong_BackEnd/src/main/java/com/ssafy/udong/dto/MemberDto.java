package com.ssafy.udong.dto;

public class MemberDto {

	private String clubId;
	private String userId;
	private String type;
	private String content;
	private String requested_at;
	
	public MemberDto() {}
	
	public MemberDto(String clubId, String userId) {
		this.clubId = clubId;
		this.userId = userId;
	}
	
	public MemberDto(String clubId, String userId, String type) {
		this.clubId = clubId;
		this.userId = userId;
		this.type = type;
	}

	public MemberDto(String clubId, String userId, String content, String requested_at) {
		this.clubId = clubId;
		this.userId = userId;
		this.content = content;
		this.requested_at = requested_at;
	}

	public String getClubId() {
		return clubId;
	}
	public void setClubId(String clubId) {
		this.clubId = clubId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRequested_at() {
		return requested_at;
	}
	public void setRequested_at(String requested_at) {
		this.requested_at = requested_at;
	}
	
}
