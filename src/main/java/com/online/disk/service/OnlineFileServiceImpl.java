package com.online.disk.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.disk.dao.OnlineFileMapper;
import com.online.disk.model.OnlineFile;

@Service("onlineFileService")
public class OnlineFileServiceImpl implements OnlineFileService{
	
	@Autowired
	private OnlineFileMapper onlineFileMapper;

	@Override
	public List<OnlineFile> querys(Map<String, Object> param) {
		return onlineFileMapper.querys(param);
	}

	@Override
	public OnlineFile selectById(long fileId) {
		return onlineFileMapper.selectByPrimaryKey(fileId);
	}

	@Override
	public int addFile(OnlineFile file) {
		return onlineFileMapper.insertSelective(file);
	}

}
