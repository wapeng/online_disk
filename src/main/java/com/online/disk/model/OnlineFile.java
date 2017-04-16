package com.online.disk.model;

import java.util.Date;

public class OnlineFile {
	
	private String fileName;
	private String icon;
	private Long fileSize;
	private String filePath;
	private Boolean isFile;
	private String fileSuffix;
	private Date lastModifyTime;
	
	public OnlineFile(){}

	public OnlineFile(String fileName, Long fileSize, String filePath,
			Boolean isFile, String fileSuffix, Date lastModifyTime) {
		super();
		this.fileName = fileName;
		this.fileSize = fileSize;
		this.filePath = filePath;
		this.isFile = isFile;
		this.fileSuffix = fileSuffix;
		this.lastModifyTime = lastModifyTime;
	}
	
	private void format(){
		
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

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public Boolean getIsFile() {
		return isFile;
	}

	public void setIsFile(Boolean isFile) {
		this.isFile = isFile;
	}

	public String getFileSuffix() {
		return fileSuffix;
	}

	public void setFileSuffix(String fileSuffix) {
		this.fileSuffix = fileSuffix;
	}

	public Date getLastModifyTime() {
		return lastModifyTime;
	}

	public void setLastModifyTime(Date lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
}
