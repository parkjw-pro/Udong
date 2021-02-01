package com.ssafy.udong.dto;

public class ImageDto {
	
	private String fileId;
	private String fileName;
	private Long fileSize;
	private String fileContentType;
	private String fileUrl;
	
	
	public ImageDto() {
	}
	
	public ImageDto(String fileName, String fileUrl) {
		this.fileName = fileName;
		this.fileUrl = fileUrl; 
	}


	public String getFileId() {
		return fileId;
	}


	public void setFileId(String fileId) {
		this.fileId = fileId;
	}


	public String getFileName() {
		return fileName;
	}


	public void setFileName(String fileName) {
		this.fileName = fileName;
	}


	public Long getFileSize() {
		return fileSize;
	}


	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}


	public String getFileContentType() {
		return fileContentType;
	}


	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}


	public String getFileUrl() {
		return fileUrl;
	}


	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}
	
	

	

}
