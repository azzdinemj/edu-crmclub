package com.wuxue.view.controllers;

import com.wuxue.utils.common.MD5Util;
import com.wuxue.utils.common.PicZoomUtils;
import com.wuxue.utils.contract.Response;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

//import com.wuxue.view.controllers.zhyou.castUtils.AsposeAndJaocbToCast;

//import org.apache.commons.fileupload.FileItemFactory;
//import org.apache.commons.fileupload.disk.DiskFileItemFactory;
//import org.apache.commons.fileupload.servlet.ServletFileUpload;

@Controller
public class FileUploadController{
	
//	private static final String IP = "http://192.168.118.131/file/";
//	private static final String LOCAL_PATH = "D:/home/server/file";

	//public static final String IP = "http://118.24.178.247:28080/files/";
    public static final String IP = "http://182.254.217.243:28080/files/";

	public static final String LOCAL_IP = "http://localhost:8081/files/";

	//public static final String LOCAL_PATH = "/home/data/tomcat/webapps/files/";
	public static final String LOCAL_PATH = "/home/tomcat/webapps/files/";


	//本地上传转换地址  ip
	//public static final String IP = "http://localhost:8081/files/";
	//public static final String LOCAL_PATH = "D:/java/apache-tomcat-9.0.6/apache-tomcat-9.0.6/webapps/files/";

	/**
	 * 上传图片 wenjian
	 * @param file
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/file/upload", method = RequestMethod.POST)
	public Response upFileImage(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request) {

		Response response = Response.newResponse();
		
		String webUrl = execute(file);

		String[] filename = webUrl.split("/");
		try {
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			Runtime.getRuntime().exec("chmod 777 -R " + LOCAL_PATH+ sdf.format(date) + "/" +filename[filename.length-1]);
		} catch (IOException e) {
			e.printStackTrace();
		}
//		System.out.print(LOCAL_PATH+filename[filename.length-1]);
//		File newfile = new File(LOCAL_PATH+filename[filename.length-1]);
//		newfile.setReadable(true,false);

		response.setData(webUrl);
		return response;
	}

	/**
	 * 批量上传图片
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/file/batchUpload", method = RequestMethod.POST)
	public Response batchUpFileImage(MultipartHttpServletRequest multipartHttpServletRequest, HttpServletRequest request) {
		Response response = Response.newResponse();
		
		List<MultipartFile> multipartFileList = multipartHttpServletRequest.getFiles("files");
		StringBuilder sb = new StringBuilder();
		
		for(MultipartFile file : multipartFileList){
			sb.append(execute(file) + ",");
		}
		
		String result = sb.toString();
		result = result.substring(0,result.length()-1);
		
		response.setData(result);
		return response;
	}
	
	
	/**
	 * 返回网络全路径
	 * @param file
	 * @return
	 */
	private static String  execute(MultipartFile file){
		String localPath = ""; //本地存储路径 完整
		String webUrl = ""; //网络访问路径 完整
		String fileName = file.getOriginalFilename();
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		// 图片名字不为空，说明上传了头像
		if (!fileName.equals("")) {
			// 截取图片后缀
			String suffix = fileName.substring(fileName.lastIndexOf('.'),fileName.length());
			
			// 将图片名称改为唯一
			fileName = MD5Util.string2MD5(String.valueOf(UUID.randomUUID()))+ suffix;
			
			// 组装本地路径
			localPath = LOCAL_PATH + sdf.format(date);
			
			// 组装网络路径
			webUrl = IP + sdf.format(date)+ "/" + fileName;

			//保存文件
			saveFile(file, localPath,fileName);

			if(!suffix.equals(".mp3")&&!suffix.equals(".flac")){
               //生成缩略图
				new PicZoomUtils().thumbnailImage(localPath+"/"+fileName,150,100,false);
			}


		}
		
		return webUrl;
	}
	

	/**
	 * 保存文件
	 * @param file
	 * @param filePath
	 */
	private static void saveFile(MultipartFile file, String filePath,String name) {
		File targetFile = new File(filePath);
		File fileSave = new File(filePath+"/"+name);
		if (!targetFile.exists()) {
			targetFile.mkdirs();
		}
		try {
			file.transferTo(fileSave);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}


}
