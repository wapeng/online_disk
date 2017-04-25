package com.online.disk.util;

import java.io.File;

public class FiltUtil {
	
	/**
	 * 路径相加
	 * @param path1
	 * @param path2
	 * @return
	 */
	public static String addPath(String path1, String path2){
		if(path1.endsWith("/")){
			return path1 + path2;
		}else{
			return path1 + "/" + path2;
		}
	}
}
