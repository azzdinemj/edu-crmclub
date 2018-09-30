package com.wuxue.view.controllers.zhyou.castUtils;

import com.aspose.words.Document;
import com.aspose.words.License;
import com.aspose.words.SaveFormat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

public class wordToPdf {

    static  String  sou="C:/Users/hall/Desktop/w2p.docx";

    public static boolean getLicense() {
        boolean result = false;
        try {
            InputStream is = wordToPdf.class.getClassLoader().getResourceAsStream("license.xml"); //  license.xml应放在..\WebRoot\WEB-INF\classes路径下
            License aposeLic = new License();
            aposeLic.setLicense(is);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
            return result;
        }



    public static void docToPdf(String Address,String NewAddress) {
          /*  if (!getLicense()) { // 验证License 若不验证则转化出的pdf文档会有水印产生
                return;
            }*/
        try {
            long old = System.currentTimeMillis();
            File file = new File(NewAddress); // 新建一个空白pdf文档
            FileOutputStream os = new FileOutputStream(file);
            Document doc = new Document(Address); // Address是将要被转化的word文档
            doc.save(os, SaveFormat.PDF);        // 全面支持DOC, DOCX, OOXML, RTF HTML,
                                                 // OpenDocument, PDF, EPUB, XPS, SWF
                                                // 相互转换
            os.flush();
            os.close();
            long now = System.currentTimeMillis();
            System.out.println("共耗时：" + ((now - old) / 1000.0) + "秒"); // 转化用时
        } catch (Exception e) {
            e.printStackTrace();
        }

    }



}