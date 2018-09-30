//package com.wuxue.view.controllers.zhyou.castUtils;
//
//
//import com.aspose.cells.SaveFormat;
//import com.aspose.cells.Workbook;
//
//import java.io.File;
//import java.io.FileOutputStream;
//
//
//public class excelToPdf {
//
//    static  String  sou2="C:/Users/hall/Desktop/e2p.xlsx";
//
//
////    public static boolean getLicense() {
////        boolean result = false;
////        try {
////            InputStream is = excelToPdf.class.getClassLoader().getResourceAsStream("license.xml"); //  license.xml应放在..\WebRoot\WEB-INF\classes路径下
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
//    public static void excelTopdf(String Address,String NewAddress) {
////        if (!getLicense()) {          // 验证License 若不验证则转化出的pdf文档会有水印产生
////            return ;
////        }
//        try {
//            long old = System.currentTimeMillis();
//            File pdfFile = new File(NewAddress);// 输出路径
//            Workbook wb = new Workbook(Address);// 原始excel路径
//            FileOutputStream fileOS = new FileOutputStream(pdfFile);
//            wb.save(fileOS, SaveFormat.PDF);
//            fileOS.close();
//            long now = System.currentTimeMillis();
//            System.out.println("共耗时：" + ((now - old) / 1000.0) + "秒\n\n" + "文件保存在:" + pdfFile.getPath()); //转化过程耗时
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }
//
//
//
//
//
//
//
//
//}