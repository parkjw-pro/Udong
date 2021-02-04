package com.ssafy.udong.dto;

public class ReviewDto {
	
	private String reviewId;
	private String userId;
	private String storeId;
	private String reviewContent;
	private int views;
	private int rate;
	private String createdAt;
	private int reviewLikeCount;
	private String nickname;
	
	public ReviewDto() {}

	public String getReviewId() {
		return reviewId;
	}


	public void setReviewId(String reviewId) {
		this.reviewId = reviewId;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getStoreId() {
		return storeId;
	}


	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}


	public String getReviewContent() {
		return reviewContent;
	}


	public void setReviewContent(String reviewContent) {
		this.reviewContent = reviewContent;
	}


	public int getViews() {
		return views;
	}


	public void setViews(int views) {
		this.views = views;
	}


	public int getRate() {
		return rate;
	}


	public void setRate(int rate) {
		this.rate = rate;
	}


	public String getCreatedAt() {
		return createdAt;
	}


	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}


	public int getReviewLikeCount() {
		return reviewLikeCount;
	}


	public void setReviewLikeCount(int reviewLikeCount) {
		this.reviewLikeCount = reviewLikeCount;
	}


	public String getNickname() {
		return nickname;
	}


	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

}
