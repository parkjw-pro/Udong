package com.ssafy.udong.dto;

public class MemberDto {
	
	private String nickname;
	private String clubId;
	private String userId;
	private String type;
	private String content;
	private String createdAt;
	private String requestedAt;
	
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

	public MemberDto(String clubId, String userId, String content, String requestedAt) {
		this.clubId = clubId;
		this.userId = userId;
		this.content = content;
		this.requestedAt = requestedAt;
	}

	
	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
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
	public String getRequestedAt() {
		return requestedAt;
	}
	public void setRequestedAt(String requestedAt) {
		this.requestedAt = requestedAt;
	}
	
}
