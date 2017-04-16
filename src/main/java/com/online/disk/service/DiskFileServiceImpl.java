package com.online.disk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.disk.dao.DiskFileMapper;
import com.online.disk.model.DiskFile;

@Service("diskFileService")
public class DiskFileServiceImpl implements DiskFileService{
	@Autowired
	private DiskFileMapper diskFileMapper;
	@Override
	public int addFile(DiskFile diskFile) {
		return diskFileMapper.insert(diskFile);
	}

}
