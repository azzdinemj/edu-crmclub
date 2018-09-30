package com.wuxue.view.client.talkcloud;

import com.alibaba.fastjson.JSONObject;
import com.wuxue.utils.https.HttpRequest;
import com.wuxue.utils.https.httpRequestSend;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * description: 第三方接口调用   file
 * @auther: wh
 * @date: 2018/6/6 15:19
 */
@Service
public class TalkCloudFileClient   {

	@Value("${talk.cloud_url}")
    protected String cloud_url;
	
	@Value("${talk.key}")
    protected String key;

	/**
	 * 上传文档
	 * @param filepath
	 * @param
	 * @return
	 */
	public String uploadfile(String filepath,String serial ) {
		
		String urlStr = cloud_url +"/WebAPI/uploadfile";           
		Map<String, String> fileMap = new HashMap<String, String>();

		fileMap.put("filedata", filepath);
		Map<String, String> textMap = new HashMap<String, String>();      
		textMap.put("key", key); //必填 企业id authkey
		textMap.put("serial", serial); //房间号
		textMap.put("conversion", "1"); //必填1：转换
		textMap.put("dynamicppt", "0");//是否是动态ppt   0: 非动态ppt   1: 动态ppt
		textMap.put("isopen", "1"); //选填 是否是公开文档 0：表示非公开文档 1：表示公开文档公开文档表示公司的其他房间都可以关联此文档，非公开文档表示只对本房间
		//textMap.put("isdefault",talkCloudFile.getIsdefault());//是否是缺省显示文件0:不是  1：是    只有上传到房间的文件才需要设置。

//		textMap.put("serial", talkCloudFile.getSerial()); //房间号
//		textMap.put("conversion", talkCloudFile.getConversion()); //必填1：转换
//		textMap.put("dynamicppt", talkCloudFile.getIsnamicppt());//是否是动态ppt   0: 非动态ppt   1: 动态ppt
//		textMap.put("isopen", "1"); //选填 是否是公开文档 0：表示非公开文档 1：表示公开文档公开文档表示公司的其他房间都可以关联此文档，非公开文档表示只对本房间
//		textMap.put("isdefault",talkCloudFile.getIsdefault());//是否是缺省显示文件0:不是  1：是    只有上传到房间的文件才需要设置。

        String result = httpRequestSend.formUpload(urlStr, textMap, fileMap);
		
		JSONObject json = JSONObject.parseObject(result);
		String responseXml="";
		if(!json.equals("")&&json.getString("result").equals("0")){
			//responseXml=json.toString();

			responseXml="{'message':'ok','code':0}";
		}else{
			responseXml=json.toString();
		}
		return responseXml;
	}

	
	/**
	 * 删除文档
	 * @param talkCloudFile
	 * @return
	 */
//	public String deleteFile(TalkCloudFile talkCloudFile) {
//
//			String url = cloud_url +"/WebAPI/deletefile";
//			List<ParamsBean> list = new ArrayList<ParamsBean>();
//			list.add(new ParamsBean("key", key)); //必填 企业id authkey
//			list.add(new ParamsBean("fileidarr[]",talkCloudFile.getFileid()));//必填，文件id
//			String result = HttpRequest.httpSend(url, list);
//
//			JSONObject json = JSONObject.parseObject(result);
//		    String responseXml="";
//			if(json.getString("result").equals("0")){
//				responseXml=json.toJSONString();
//			}
//			return responseXml;
//	}


}
