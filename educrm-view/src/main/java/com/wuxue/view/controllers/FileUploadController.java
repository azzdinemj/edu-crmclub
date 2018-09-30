package com.wuxue.view.controllers;

import com.alibaba.fastjson.JSONObject;
import com.wuxue.utils.common.MD5Util;
import com.wuxue.utils.common.PicZoomUtils;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.controllers.zhyou.castUtils.AsposeAndJaocbToCast;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.*;

//import org.apache.commons.fileupload.FileItemFactory;
//import org.apache.commons.fileupload.disk.DiskFileItemFactory;
//import org.apache.commons.fileupload.servlet.ServletFileUpload;

@Controller
public class FileUploadController{
	
//	private static final String IP = "http://192.168.118.131/file/";
//	private static final String LOCAL_PATH = "D:/home/server/file";

//	public static final String IP = "http://118.24.189.48:8080/files/";  //中航
	public static final String IP = "http://118.126.114.169/";
	public static final String LOCAL_IP = "http://127.0.0.1/";

//	public static final String LOCAL_PATH = "/home/tomcat/webapps/files/";  //中航
	public static final String LOCAL_PATH = "/home/images/";


	//本地上传转换地址  ip
//	public static final String IP = "http://127.0.0.1/";
//	public static final String LOCAL_PATH = "D:/ceshi/";

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
		System.out.print(LOCAL_PATH+filename[filename.length-1]);
		File newfile = new File(LOCAL_PATH+filename[filename.length-1]);
		newfile.setReadable(true,false);

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

			if(suffix.equals(".ppt")||suffix.equals(".pptx")
					||suffix.equals(".doc") ||suffix.equals(".docx")
					||suffix.equals(".xls")||suffix.equals(".xlsx")){

                //读取已经上传在本地的office文件进行转化pdf
				AsposeAndJaocbToCast.validTypeToCastAspose(localPath+"/"+fileName);
			}


			//生成缩略图
			new PicZoomUtils().thumbnailImage(localPath+"/"+fileName,150,100,false);


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

	/**
	 * kindedtior 上传
	 * @param request
	 * @param response
	 */
	@RequestMapping("/file/upLoad3")
	public void upLoadImage3(HttpServletRequest request, HttpServletResponse response){
		try {
			PrintWriter out = response.getWriter();

			//本地站点文件保存目录路径  测试
//		   String savePath = "D:/java/images/";

			String savePath = "/home/tomcat/webapps/files/";//正式环境路径
//			String savePath = "/home/images/";//正式环境路径


			//文件保存目录URL
			String saveUrl  = request.getContextPath() + "/";

			//定义允许上传的文件扩展名
			HashMap<String, String> extMap = new HashMap<String, String>();
			extMap.put("image", "gif,jpg,jpeg,png,bmp");
			extMap.put("flash", "swf,flv");
			extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
			extMap.put("file", "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2");

			//最大文件大小
			long maxSize = 1000000;

			response.setContentType("text/html; charset=UTF-8");

//			if(!ServletFileUpload.isMultipartContent(request)){
//				out.println(getError("请选择文件。"));
//				return ;
//			}
			//检查目录
			File uploadDir = new File(savePath);
			if(!uploadDir.isDirectory()){
				out.println(getError("上传目录不存在。"));
				return ;
			}
			//检查目录写权限
			if(!uploadDir.canWrite()){
				out.println(getError("上传目录没有写权限。"));
				return ;
			}

			String dirName = request.getParameter("dir");
			if (dirName == null) {
				dirName = "image";
			}
			if(!extMap.containsKey(dirName)){
				out.println(getError("目录名不正确。"));
				return ;
			}
			//创建文件夹
			savePath += dirName + "/";
			saveUrl += dirName + "/";
			File saveDirFile = new File(savePath);
			if (!saveDirFile.exists()) {
				saveDirFile.mkdirs();
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			String ymd = sdf.format(new Date());
			savePath += ymd + "/";
			saveUrl += ymd + "/";
			File dirFile = new File(savePath);
			if (!dirFile.exists()) {
				dirFile.mkdirs();
			}

//			//创建一个DiskFileItemFactory工厂
//			FileItemFactory factory = new DiskFileItemFactory();
//			//创建一个文件上传解析器
//			ServletFileUpload upload = new ServletFileUpload(factory);
//			//设置编码
//			upload.setHeaderEncoding("UTF-8");
//
//			List items = upload.parseRequest(request);
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			Iterator item = multipartRequest.getFileNames();

			while (item.hasNext()) {
				String fileName = (String) item.next();

				MultipartFile file = multipartRequest.getFile(fileName);
				// 检查文件大小
				if (file.getSize() > maxSize) {
					out.println(getError("上传文件大小超过限制。"));
					return ;
				}

				// 检查扩展名
				String fileExt = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1).toLowerCase();

				if (!Arrays. asList(extMap.get(dirName).split(",")).contains(fileExt)) {
					out.println(getError("上传文件扩展名是不允许的扩展名。\n只允许" + extMap.get(dirName) + "格式。"));
					return ;
				}
				SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
				String newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + "." + fileExt;
				try {
					File uploadedFile = new File(savePath, newFileName);
					saveFile(file,savePath,newFileName);//保存
				} catch (Exception e) { }
				JSONObject obj = new JSONObject();
				obj.put("error", 0);

				//obj.put("url", IP+saveUrl+newFileName);  //服务器路径ip
				obj.put("url", LOCAL_IP+saveUrl+newFileName); //本地测试路径ip
				out.println(obj.toJSONString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

      return ;
	}

	//上传报错的提示方法
	private String getError(String message) {
		JSONObject obj = new JSONObject();
		obj.put("error", 1);
		obj.put("message", message);
		return obj.toJSONString();
	}

	@RequestMapping("/file/Manager3")
	public void fileManager3(HttpServletRequest request, HttpServletResponse response){
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//本地站点文件保存目录路径  测试
//		   String savePath = "D:/java/images/";

		String savePath = "/home/tomcat/webapps/files/";//正式环境路径
//		String savePath = "/home/images/";//正式环境路径
//
//		//文件保存目录URL
//		String saveUrl  = request.getContextPath() + "/";

//		String rootPath ="D:/Java/apache-tomcat-9.0.6/apache-tomcat-9.0.6/webapps/files/";
		String rootPath = "/home/tomcat/webapps/files/";//正式环境路径

//		String rootPath = "D:/java/images/";
		String rootUrl=request.getContextPath() + "/";

//		//根目录路径
//		String rootPath = request.getRealPath("/") + "attached/";
//		//根目录URL，可以指定绝对路径，比如 http://www.yoursite.com/attached/
//		String rootUrl  = request.getContextPath() + "/attached/";



		//图片扩展名
		String[] fileTypes = new String[]{"gif", "jpg", "jpeg", "png", "bmp"};

		//要打开的文件夹
		String dirName = request.getParameter("dir");
		if (dirName != null) {
			if(!Arrays.<String>asList(new String[]{"image", "flash", "media", "file"}).contains(dirName)){
				out.println("无效的目录");
				return;
			}
			rootPath += dirName + "/";
			rootUrl += dirName + "/";
			File saveDirFile = new File(rootPath);
			if (!saveDirFile.exists()) {
				saveDirFile.mkdirs();
			}
		}
		//根据path参数，设置各路径和URL
		String path = request.getParameter("path") != null ? request.getParameter("path") : "";
		String currentPath = rootPath + path;
		String currentUrl = rootUrl + path;
		String currentDirPath = path;
		String moveupDirPath = "";
		if (!"".equals(path)) {
			String str = currentDirPath.substring(0, currentDirPath.length() - 1);
			moveupDirPath = str.lastIndexOf("/") >= 0 ? str.substring(0, str.lastIndexOf("/") + 1) : "";
		}

		//排序形式，name or size or type
		String order = request.getParameter("order") != null ? request.getParameter("order").toLowerCase() : "name";

		//不允许使用..移动到上一级目录
		if (path.indexOf("..") >= 0) {
			out.println("Access is not allowed.");
			return;
		}
		//最后一个字符不是/
		if (!"".equals(path) && !path.endsWith("/")) {
			out.println("Parameter is not valid.");
			return;
		}
		//目录不存在或不是目录
		File currentPathFile = new File(currentPath);
		if(!currentPathFile.isDirectory()){
			out.println("Directory does not exist.");
			return;
		}

		//遍历目录取的文件信息
		List<Hashtable> fileList = new ArrayList<Hashtable>();
		if(currentPathFile.listFiles() != null) {
			for (File file : currentPathFile.listFiles()) {
				Hashtable<String, Object> hash = new Hashtable<String, Object>();
				String fileName = file.getName();
				if(file.isDirectory()) {
					hash.put("is_dir", true);
					hash.put("has_file", (file.listFiles() != null));
					hash.put("filesize", 0L);
					hash.put("is_photo", false);
					hash.put("filetype", "");
				} else if(file.isFile()){
					String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
					hash.put("is_dir", false);
					hash.put("has_file", false);
					hash.put("filesize", file.length());
					hash.put("is_photo", Arrays.<String>asList(fileTypes).contains(fileExt));
					hash.put("filetype", fileExt);
				}
				hash.put("filename", fileName);
				hash.put("datetime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(file.lastModified()));
				fileList.add(hash);
			}
		}

		if ("size".equals(order)) {
			Collections.sort(fileList, new SizeComparator());
		} else if ("type".equals(order)) {
			Collections.sort(fileList, new TypeComparator());
		} else {
			Collections.sort(fileList, new NameComparator());
		}
		JSONObject result = new JSONObject();
		result.put("moveup_dir_path", moveupDirPath);
		result.put("current_dir_path", currentDirPath);
		result.put("current_url", currentUrl);
		result.put("total_count", fileList.size());
		result.put("file_list", fileList);

		response.setContentType("application/json; charset=UTF-8");
		out.println(result.toJSONString());



	}


	class NameComparator implements Comparator {
		public int compare(Object a, Object b) {
			Hashtable hashA = (Hashtable) a;
			Hashtable hashB = (Hashtable) b;
			if (((Boolean) hashA.get("is_dir"))
					&& !((Boolean) hashB.get("is_dir"))) {
				return -1;
			} else if (!((Boolean) hashA.get("is_dir"))
					&& ((Boolean) hashB.get("is_dir"))) {
				return 1;
			} else {
				return ((String) hashA.get("filename"))
						.compareTo((String) hashB.get("filename"));
			}
		}
	}

	class SizeComparator implements Comparator {
		public int compare(Object a, Object b) {
			Hashtable hashA = (Hashtable) a;
			Hashtable hashB = (Hashtable) b;
			if (((Boolean) hashA.get("is_dir"))
					&& !((Boolean) hashB.get("is_dir"))) {
				return -1;
			} else if (!((Boolean) hashA.get("is_dir"))
					&& ((Boolean) hashB.get("is_dir"))) {
				return 1;
			} else {
				if (((Long) hashA.get("filesize")) > ((Long) hashB
						.get("filesize"))) {
					return 1;
				} else if (((Long) hashA.get("filesize")) < ((Long) hashB
						.get("filesize"))) {
					return -1;
				} else {
					return 0;
				}
			}
		}
	}

	class TypeComparator implements Comparator {
		public int compare(Object a, Object b) {
			Hashtable hashA = (Hashtable) a;
			Hashtable hashB = (Hashtable) b;
			if (((Boolean) hashA.get("is_dir"))
					&& !((Boolean) hashB.get("is_dir"))) {
				return -1;
			} else if (!((Boolean) hashA.get("is_dir"))
					&& ((Boolean) hashB.get("is_dir"))) {
				return 1;
			} else {
				return ((String) hashA.get("filetype"))
						.compareTo((String) hashB.get("filetype"));
			}
		}
	}


}
