//package com.wuxue.view.controllers.zhyou.castUtils;
//
//
//import com.aspose.slides.Presentation;
//import com.aspose.slides.SaveFormat;
//
//import java.io.File;
//import java.io.FileOutputStream;
//
//;
//
//public class pptToPdf {
//
//    static  String  sou1="C:/Users/hall/Desktop/p2p.pptx";
//
//
////    public static boolean getLicense() {
////        boolean result = false;
////        try {
////            InputStream is = AsposeToPdf.class.getClassLoader().getResourceAsStream("license.xml"); //  license.xml应放在..\WebRoot\WEB-INF\classes路径下
////            License aposeLic = new License();
////            aposeLic.setLicense(is);
////            result = true;
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
////            return result;
////        }
//
//
//
//
//
//    public static void pptTopdf(String Address,String NewAddress) {
//        // 验证License
////        if (!getLicense()) {
////            return;
////            }
//        try {
//            long old = System.currentTimeMillis();
//            File file = new File(NewAddress);// 输出pdf路径
//            Presentation pres = new Presentation(Address);//原始ppt路径
//            FileOutputStream fileOS = new FileOutputStream(file);
//            pres.save(fileOS, SaveFormat.Pdf);
//            fileOS.close();
//            long now = System.currentTimeMillis();
//            System.out.println("共耗时：" + ((now - old) / 1000.0) + "秒\n\n" + "文件保存在:" + file.getPath()); //转化过程耗时
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//
//
//
//
//}