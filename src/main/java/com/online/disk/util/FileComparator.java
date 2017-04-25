package com.online.disk.util;

import java.util.Comparator;

import com.online.disk.common.FileType;
import com.online.disk.model.OnlineFile;

/**
 * 网盘文件排序
 * @author wangpeng
 *
 */
public class FileComparator implements Comparator<OnlineFile>{

	@Override
	public int compare(OnlineFile o1, OnlineFile o2) {
		int o1FileType = o1.getFileType();
		int o2FileType = o2.getFileType();
		String o1Name = o1.getFileName();
		String o2Name = o2.getFileName();
		if(o1FileType == o2FileType){
			return o1Name.compareToIgnoreCase(o2Name);
		}else{
			if(o1FileType == FileType.DIRECTORY.getValue()){
				return 1;
			}else if(o2FileType == FileType.DIRECTORY.getValue()){
				return -1;
			}
			return o1Name.compareToIgnoreCase(o2Name);
		}
	}

}
