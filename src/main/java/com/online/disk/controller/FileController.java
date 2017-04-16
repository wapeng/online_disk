package com.online.disk.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import com.mysql.jdbc.StringUtils;
import com.online.disk.common.Constant;
import com.online.disk.model.DiskFile;
import com.online.disk.model.OnlineFile;
import com.online.disk.service.DiskFileService;
import com.online.disk.util.FileComparator;
import com.online.disk.util.QEncodeUtil;


@Controller
@RequestMapping("/file")
public class FileController extends BaseController{
	private String key = "123456";
	@Autowired
	private DiskFileService diskFileService;
	
	@RequestMapping("/toUpload")
	public String toUpLoad(){
		return "/upload";
	}
	
	@RequestMapping("/upload")
	@ResponseBody
	public Object upload(@RequestParam("myFile") CommonsMultipartFile file){
		long start = System.currentTimeMillis();
		InputStream ins = null;
		String fileName = file.getOriginalFilename();
		long size = file.getSize();
		String savePath = "E:\\data\\" + fileName;
		File outFile = new File(savePath);
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
		
		DiskFile diskFile = new DiskFile();
		diskFile.setFileClass(1);
		diskFile.setFileName(fileName);
		diskFile.setFilePath(savePath);
		diskFile.setFileSuffix(fileName.substring(fileName.lastIndexOf(".")));
		diskFileService.addFile(diskFile);
		
		DecimalFormat fromat = new DecimalFormat("#.#");
		logger.info("文件大小:" + fromat.format(sizeMb) + "Mb, 用时:" + fromat.format(timeMi) + "秒, 平均速度:" + fromat.format(speed) + "M/s");
		return "success";
	}

//	/**
//	 * 进入根目录
//	 * @return
//	 */
//	@RequestMapping("/toRootPath")
//	public ModelAndView toPath(){
//		ModelAndView mv = new ModelAndView("/file_list");
//		List<OnlineFile> list = new ArrayList<OnlineFile>();
//		list = this.listFile("/data/file");
//		Collections.sort(list, new FileComparator());
//		mv.addObject("list", list);
//		return mv;
//	}
	
	@RequestMapping("/toPath")
	public ModelAndView toPath(@RequestParam(value = "path", required = false) String path,
			@RequestParam(value = "isFile", required = false) Boolean isFile, 
			@RequestParam(value = "pro", required = false) String process){
		List<OnlineFile> list = new ArrayList<OnlineFile>();
		ModelAndView mv = new ModelAndView("/file_list");
		if(StringUtils.isNullOrEmpty(path)){
//			path = "/data/file";
			path = "F:\\";
		}else{
			path = StringEscapeUtils.escapeJava(path);
		}
		if(StringUtils.isNullOrEmpty(process)){
			process = Constant.OPT_IN;
		}
		if(isFile == null){
			isFile = false;
		}
		if(Constant.OPT_IN.equals(process)){
			if(isFile){
				
			}else{
				list = this.listFile(path);
			}
		}else if(Constant.OPT_OUT.equals(process)){
			
		}
		Collections.sort(list, new FileComparator());
		mv.addObject("parentPath", StringEscapeUtils.escapeJava(path));
		mv.addObject("list", list);
		return mv;
	}
	
	/**
	 * 获取文件夹下的列表
	 * @param path
	 * @return
	 */
	private List<OnlineFile> listFile(String path){
		List<OnlineFile> list = new ArrayList<OnlineFile>();
		File root = new File(path);
		if(root.exists() && root.isDirectory()){
			File[] files = root.listFiles();
			//文件夹列表
			for(File file : files){
				String fileName = file.getName();
				Long fileSize = null;
				boolean isFile = file.isFile();
				String filePath = file.getAbsolutePath();
				String fileSuffix = null;
				if(isFile){
					fileSize = file.length();
					fileSuffix = fileName.substring(fileName.lastIndexOf("."));
				}
				list.add(new OnlineFile(fileName, fileSize, StringEscapeUtils.escapeJava(filePath), isFile, fileSuffix, new Date(file.lastModified())));
			}
		}
		return list;
	}
	
	private String encryption(String str){
		return new BASE64Encoder().encodeBuffer(str.getBytes());
	}

	private String decrypt(String str){
		try {
			return new String(new BASE64Decoder().decodeBuffer(str)); 
		} catch (Exception e) {
			return null;
		}
	}
	
	public static void main(String[] args) {
		System.out.println(StringEscapeUtils.escapeJava("d:\\"));
	}
}