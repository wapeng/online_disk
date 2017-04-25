package com.online.disk.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.online.disk.common.FileType;
import com.online.disk.model.DiskFile;
import com.online.disk.model.OnlineFile;
import com.online.disk.service.DiskFileService;
import com.online.disk.service.OnlineFileService;
import com.online.disk.util.FileComparator;
import com.online.disk.util.QEncodeUtil;


@Controller
@RequestMapping("/file")
public class FileController extends BaseController{
	private String key = "123456";
//	@Autowired
//	private DiskFileService diskFileService;
	@Autowired
	private OnlineFileService onlineFileService;
	
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
//		diskFileService.addFile(diskFile);
		
		DecimalFormat fromat = new DecimalFormat("#.#");
		logger.info("文件大小:" + fromat.format(sizeMb) + "Mb, 用时:" + fromat.format(timeMi) + "秒, 平均速度:" + fromat.format(speed) + "M/s");
		return "success";
	}
	
	@RequestMapping("/addDic")
	public ModelAndView addDic(@RequestParam(value = "parentId", required = true) long parentId,
			@RequestParam(value = "fileName", required = false) String fileName){
		ModelAndView mv = new ModelAndView("/file_list");
		OnlineFile file = new OnlineFile();
		file.setParentId(parentId);
		file.setFileName(fileName);
		file.setFileType(FileType.DIRECTORY.getValue());
		file.setLastModify(new Date());
		onlineFileService.addFile(file);
		
		List<OnlineFile> list = this.listFile(parentId);
		mv.addObject("list", list);
		return mv;
	}

	@RequestMapping("/toPath")
	public ModelAndView toPath(@RequestParam(value = "id", required = false) String fileId,
			@RequestParam(value = "fileType", required = false) String fileType,
			@RequestParam(value = "cmd", required = false) String cmd){
		List<OnlineFile> list = new ArrayList<OnlineFile>();
		ModelAndView mv = new ModelAndView("/file_list");
		if(StringUtils.isNullOrEmpty(fileId)){
			fileId = "-1";
		}
		if(StringUtils.isNullOrEmpty(cmd)){
			cmd = Constant.OPT_IN;
		}
		if(Constant.OPT_IN.equals(cmd)){
			if(StringUtils.isNullOrEmpty(fileType)){
				list = this.listFile(Long.valueOf(fileId));
			}else if(FileType.DIRECTORY.getValue() == Integer.parseInt(fileType)){
				
			}else{
				list = this.listFile(Long.valueOf(fileId));
			}
		}else if(Constant.OPT_OUT.equals(cmd)){
			OnlineFile thisFile = onlineFileService.selectById(Long.parseLong(fileId));
			list = this.listFile(thisFile.getParentId());
		}
		mv.addObject("fatherFileId", fileId);
		mv.addObject("list", list);
		return mv;
	}
	
	/**
	 * 获取文件夹下的列表
	 * @param path
	 * @return
	 */
	private List<OnlineFile> listFile(long fileId){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("parentId", fileId);
		List<OnlineFile> list = onlineFileService.querys(param);
		Collections.sort(list, new FileComparator());
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