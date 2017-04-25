package com.online.disk.service;

import java.util.List;
import java.util.Map;

import com.online.disk.model.OnlineFile;

public interface OnlineFileService {
	
	List<OnlineFile> querys(Map<String, Object> param);
	
	OnlineFile selectById(long fileId);
	
	int addFile(OnlineFile file);

}
