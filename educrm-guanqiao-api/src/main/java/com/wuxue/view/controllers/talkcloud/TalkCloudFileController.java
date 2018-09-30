package com.wuxue.view.controllers.talkcloud;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wuxue.model.TalkCloudRoom;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.talkcloud.TalkCloudFileClient;
import com.wuxue.view.client.talkcloud.TalkCloudRoomClient;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 *
 * description: 
 * @auther: wh
 * @date: 2018/6/6 15:17
 */
@Controller
@RequestMapping(value = "/talkCloud/file")
public class TalkCloudFileController {

	@Autowired
	private TalkCloudFileClient talkCloudFileClient;

	//本地上传转换地址  ip
	public static  String IP = "http://localhost:8081/files/";
	public static  String LOCAL_PATH = "D:/java/apache-tomcat-9.0.6/apache-tomcat-9.0.6/webapps/files/talkCloud/";


	/**
	  * 文件上传  ,本地备份上传
	  * @param request
	  * @param model
	  * @param talkCloudFile
	  * @return
	 * @throws IOException 
	  */
//	@RequestMapping(value = "/uploadfile2",method = RequestMethod.POST)
//    public String uploadfile2(@RequestParam MultipartFile urlFile, HttpServletRequest request, Model model, TalkCloudFile talkCloudFile) throws IOException {
//
//		String newFileName = null;
//		Date date = new Date();
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/");
//		String strdate=sdf.format(date);
//		String realPath=LOCAL_PATH+strdate;
//		File targetFile = new File(realPath);
//		if (!targetFile.exists()) {
//			targetFile.mkdirs();
//		}
//
//		String fileName = urlFile.getOriginalFilename();
//		String ext = fileName.substring(fileName.lastIndexOf(".") + 1,fileName.length());
//		String uuid = UUID.randomUUID().toString();
//		newFileName = uuid + "." + ext;
//
//		FileUtils.copyInputStreamToFile(urlFile.getInputStream(),new File(realPath, newFileName));
//		String result = "";
//		if (newFileName != null) {
//
//			result = talkCloudFileClient.uploadfile(realPath + newFileName,talkCloudFile);
//		}
//        return result;
//    }

	@Autowired
	TalkCloudRoomClient talkCloudRoomClient;
	/**
	 * 文件上传
	 * @param request
	 * @param model
	 * @param serial
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/uploadfile",method = RequestMethod.POST)
	@ResponseBody
	public Response uploadfile(@RequestParam MultipartFile urlFile, HttpServletRequest request, Model model, String serial) throws IOException {

		Response response=Response.newResponse();
		String newFileName = null;
		String realPath = request.getSession().getServletContext().getRealPath("");
		String fileName = urlFile.getOriginalFilename();
		String ext = fileName.substring(fileName.lastIndexOf(".") + 1,fileName.length());
		String uuid = UUID.randomUUID().toString();
		newFileName = uuid + "." + ext;
		FileUtils.copyInputStreamToFile(urlFile.getInputStream(),new File(realPath, newFileName));
		String result = "";
		if (newFileName != null) {
			result = talkCloudFileClient.uploadfile(realPath + newFileName,serial);
		}
		JSONObject jsonObject=JSON.parseObject(result);
		if((int)jsonObject.get("code")==0){ //上传课件成功，filenum+1
			TalkCloudRoom talkCloudRoom=new TalkCloudRoom();
			talkCloudRoom.setSerial(serial);

			Response<List<TalkCloudRoom>> listResponse=talkCloudRoomClient.find(talkCloudRoom);
			if (listResponse.getData().size()>0){
				TalkCloudRoom talkCloudRoom1=new TalkCloudRoom();

				 talkCloudRoom1.setPkTalkCloudRoom(listResponse.getData().get(0).getPkTalkCloudRoom());
				 Integer i=listResponse.getData().get(0).getFilenum();
				 if(i==null){
					  talkCloudRoom1.setFilenum(1);
				 }else{
					 talkCloudRoom1.setFilenum(listResponse.getData().get(0).getFilenum()+1);
				 }
				 response=talkCloudRoomClient.saveTalkClourRoom(talkCloudRoom1);
			}else{
				response.setCode(1);
				response.setMessage("拓客云教室不存在!!!");

			}
		}
		return response;

	}

	/**
	 * 删除文件
	 * @param request
	 * @param model
	 * @param talkCloudFile
	 * @return
	 * @throws IOException
	 */
//	@ResponseBody
//	@RequestMapping(value = "/deleteFile",method = RequestMethod.POST)
//    public String deleteFile(HttpServletRequest request, Model model, TalkCloudFile talkCloudFile) throws IOException {
//  		String result = "";
//		if(talkCloudFile.getFileid()!=null&&!talkCloudFile.getFileid().equals("")){
//			 result = talkCloudFileClient.deleteFile(talkCloudFile);
//		}else {
//			result="{'message':'参数为空','code':1}";
//		}
//        return result;
//    }
	
}
