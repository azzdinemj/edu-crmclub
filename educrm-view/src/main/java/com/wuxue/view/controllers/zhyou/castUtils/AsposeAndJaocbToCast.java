package com.wuxue.view.controllers.zhyou.castUtils;

public class AsposeAndJaocbToCast {
    static  String  sou="C:/Users/hall/Desktop/w2p.docx";
    static  String  sou2="C:/Users/hall/Desktop/e2p.xlsx";
    static  String  sou1="C:/Users/hall/Desktop/p2p.pptx";

    static String  str="http://sjj.crmclub.cn/files/e2p.xlsx";
    public static void main(String [] args){
        validTypeToCastAspose(sou);
    }

    /**
     * jaocb  需要 office支持
     * 判断文件类型并且执行类型转换
     * @param dic  需要转换的文件路径
     */
    public  static void validTypeToCastJaocb(String dic){
        String stype= getFileType(dic);//文件后缀名
        String surl=getFileName(dic);//文件路径
        if (stype.equals("doc")||stype.equals("docx")){
           Word2Pdf.word2pdf(dic,surl+".pdf");
        }
        if (stype.equals("ppt")||stype.equals("pptx")){
            Word2Pdf.ppt2pdf(dic,surl+".pdf");
        }
        if (stype.equals("xls")||stype.equals("xlsx")){
            Word2Pdf.excel2pdf(dic,surl+".pdf");
        }

    }

    /**
     * Aspose
     * 判断文件类型并且执行类型转换
     * @param dic  需要转换的文件路径
     */
    public  static void validTypeToCastAspose(String dic){
        String stype= getFileType(dic);//文件后缀名
        String surl=getFileName(dic);//文件路径
        if (stype.equals("doc")||stype.equals("docx")){
            wordToPdf.docToPdf(dic,dic+".pdf");
        }
        if (stype.equals("ppt")||stype.equals("pptx")){
           pptToPdf.pptTopdf(dic,dic+".pdf");
        }
        if (stype.equals("xls")||stype.equals("xlsx")){
            excelToPdf.excelTopdf(dic,dic+".pdf");
        }

    }



    /**
     * 保留文件名及路径不保留后缀
     */
    public static String getFileName(String pathandname) {

        int end = pathandname.lastIndexOf(".");
        if (end != -1) {
            return pathandname.substring(0,end);
        } else {
            return null;
        }
    }

    /**
     * 保留文件后缀名
     */
    public static String getFileType(String pathandname) {
        int start = pathandname.lastIndexOf(".");
        if (start != -1 ) {
            return pathandname.substring(start + 1);
        } else {
            return null;
        }
    }


}
