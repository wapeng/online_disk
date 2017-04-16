package com.online.disk.util;

import java.util.Comparator;

import com.online.disk.model.OnlineFile;

/**
 * 网盘文件排序
 * @author wangpeng
 *
 */
public class FileComparator implements Comparator<OnlineFile>{

	@Override
	public int compare(OnlineFile o1, OnlineFile o2) {
		Boolean o1IsFile = o1.getIsFile();
		Boolean o2IsFile = o2.getIsFile();
		String o1Name = o1.getFileName();
		String o2Name = o2.getFileName();
		if(o1IsFile.compareTo(o2IsFile) == 0){
			return o1Name.compareToIgnoreCase(o2Name);
		}else{
			return o1IsFile.compareTo(o2IsFile);
		}
	}

}
