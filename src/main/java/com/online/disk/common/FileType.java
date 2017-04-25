package com.online.disk.common;

public enum FileType {
	
	DIRECTORY(1, "", "文件夹"),
	VIDEO(2, "", "视频"),
	AUDIO(3, "", "音频"),
	PICTURE(4, "", "图片"),
	
	UNKONWN(99,"", "未知格式");
	private int value;
	private String icon;
	private String desc;
	private FileType(int value, String icon, String desc){
		this.value = value;
		this.icon = icon;
		this.desc = desc;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}

	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
}
