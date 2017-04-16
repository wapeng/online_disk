package com.online.disk.controller;

import java.io.File;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.Date;


import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Controller
@RequestMapping("/")
public class IndexController {
	private Logger logger = LoggerFactory.getLogger(IndexController.class);
	@RequestMapping("index")
	public String toIndex(){
		logger.info("info....");
		logger.warn("warn....");
		logger.error("error....");
		return "index";
	}
	
	@RequestMapping("upload")
	@ResponseBody
	public Object upload(@RequestParam("myFile") CommonsMultipartFile file){
		System.out.println(new Date().toLocaleString());
		long start = System.currentTimeMillis();
		InputStream ins = null;
		String fileName = file.getOriginalFilename();
		long size = file.getSize();
		System.out.println(size);
		File outFile = new File("E:\\data\\" + fileName);
		try {
			if(!outFile.isFile()){
				if(!outFile.createNewFile()){
					return "创建文件失败, 无权限";
				}
			}
			ins = file.getInputStream();
			FileUtils.copyInputStreamToFile(ins, outFile);
			ins.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		Double sizeMb = size/1024/1024D;
		double timeMi = (end - start)/1000D;
		double speed = sizeMb/timeMi;
		
		DecimalFormat fromat = new DecimalFormat("#.#");
		logger.info("文件大小:" + fromat.format(sizeMb) + "Mb, 用时:" + fromat.format(timeMi) + "秒, 平均速度:" + fromat.format(speed) + "M/s");
		System.out.println(new Date().toLocaleString());
		return "success";
	}

}
