package com.ssafy.udong.dto;

public class BadgeDto {
	
	String badgeId;
	String badgeName;
	String content;
	String badgeUrl;
	String isMain;
	
	public BadgeDto() {
	}
	public String getBadgeId() {
		return badgeId;
	}
	public void setBadgeId(String badgeId) {
		this.badgeId = badgeId;
	}
	public String getBadgeName() {
		return badgeName;
	}
	public void setBadgeName(String badgeName) {
		this.badgeName = badgeName;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getBadgeUrl() {
		return badgeUrl;
	}
	public void setBadgeUrl(String badgeUrl) {
		this.badgeUrl = badgeUrl;
	}
	public String getisMain() {
		return isMain;
	}
	public void setisMain(String isMain) {
		this.isMain = isMain;
	}
	
	

}
