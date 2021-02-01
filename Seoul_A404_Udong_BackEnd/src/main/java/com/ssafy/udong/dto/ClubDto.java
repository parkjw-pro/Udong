package com.ssafy.udong.dto;

public class ClubDto {
	private String clubId;
	private String clubName;
	private String memberNumber;  //amount of member in a Club
	private String clubContent;  //explanation of a Club
	private String isOpen;  //1 means no need a confirm to access a Club
	private String createdAt;  //date of creation of a Club 
	private String areaCode;  //area where Club belongs
	private String userId;  //club leader's id
	private String fileId;  //representative image of a Club
	private String nickname;  //club leader's nickname
	
	public ClubDto() {	}

	public String getClubId() {
		return clubId;
	}

	public void setClubId(String clubId) {
		this.clubId = clubId;
	}

	public String getClubName() {
		return clubName;
	}

	public void setClubName(String clubName) {
		this.clubName = clubName;
	}

	public String getMemberNumber() {
		return memberNumber;
	}

	public void setMemberNumber(String memberNumber) {
		this.memberNumber = memberNumber;
	}

	public String getClubContent() {
		return clubContent;
	}

	public void setClubContent(String clubContent) {
		this.clubContent = clubContent;
	}

	public String getIsOpen() {
		return isOpen;
	}

	public void setIsOpen(String isOpen) {
		this.isOpen = isOpen;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

}
