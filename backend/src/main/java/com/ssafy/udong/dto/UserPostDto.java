package com.ssafy.udong.dto;

public class UserPostDto {
	private String postId ;
	private String userId;
	private String nickname;
	private String postContent;
	private String postViews;
	private String createdAt;
	private String isOpen;
	private String postLikeCount;
	
	private String postTag;
	private String isUpdated;
	private String updatedAt;
	private String postCommentCount;

	
	
	public UserPostDto() {}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPostId() {
		return postId;
	}

	public void setPostId(String postId) {
		this.postId = postId;
	}

	public String getPostContent() {
		return postContent;
	}

	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}

	public String getPostViews() {
		return postViews;
	}

	public void setPostViews(String postViews) {
		this.postViews = postViews;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getIsOpen() {
		return isOpen;
	}

	public void setIsOpen(String isOpen) {
		this.isOpen = isOpen;
	}

	public String getPostLikeCount() {
		return postLikeCount;
	}

	public void setPostLikeCount(String postLikeCount) {
		this.postLikeCount = postLikeCount;
	}

	public String getPostTag() {
		return postTag;
	}

	public void setPostTag(String postTag) {
		this.postTag = postTag;
	}

	public String getIsUpdated() {
		return isUpdated;
	}

	public void setIsUpdated(String isUpdated) {
		this.isUpdated = isUpdated;
	}

	public String getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getPostCommentCount() {
		return postCommentCount;
	}

	public void setPostCommentCount(String postCommentCount) {
		this.postCommentCount = postCommentCount;
	}

	
	
}
