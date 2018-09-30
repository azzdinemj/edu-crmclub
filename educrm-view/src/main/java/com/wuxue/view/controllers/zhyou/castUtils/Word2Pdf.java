package com.wuxue.view.controllers.zhyou.castUtils;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

import java.io.File;
import java.io.IOException;

/**
* @Description:  word、ppt、excel转pdf工具
* @author wanghao
* @date  18:03 2018/3/24
* @version V1.0
*/
public class Word2Pdf {

    static final int wdDoNotSaveChanges = 0;// 不保存待定的更改。
    static final int wdFormatPDF = 17;// word转PDF 格式
    static final int ppSaveAsPDF = 32;// ppt 转PDF 格式

    static  String  sou="C:/Users/hall/Desktop/w2p.docx";
    static  String  sou1="C:/Users/hall/Desktop/p2p.ppt";
    static  String  sou2="C:/Users/hall/Desktop/e2p.xlsx";


    public static void main(String[] args) throws IOException {
       Word2Pdf.validTypeToCast(sou2);
    }

    /**
     * 判断文件类型并且执行类型转换
     * @param dic  需要转换的文件路径
     */
    public  static void validTypeToCast(String dic){
        String stype= getFileType(dic);//文件后缀名
        String surl=getFileName(dic);//文件路径
        if (stype.equals("doc")||stype.equals("docx")){
            word2pdf(dic,surl+".pdf");
        }
        if (stype.equals("ppt")||stype.equals("pptx")){
            ppt2pdf(dic,surl+".pdf");
        }
        if (stype.equals("xls")||stype.equals("xlsx")){
            excel2pdf(dic,surl+".pdf");
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


    /**
     *
     * @param source 源文件路径
     * @param target 生成的pdf文件路径
     */
    public static void word2pdf(String source, String target) {
        System.out.println("启动Word");
        long start = System.currentTimeMillis();
        ActiveXComponent app = null;
        try {
            app = new ActiveXComponent("Word.Application");
            app.setProperty("Visible", false);

            Dispatch docs = app.getProperty("Documents").toDispatch();
            System.out.println("打开文档" + source);
            Dispatch doc = Dispatch.call(docs,//
                    "Open", //
                    source,// FileName
                    false,// ConfirmConversions
                    true // ReadOnly
            ).toDispatch();

            System.out.println("转换文档到PDF " + target);
            File tofile = new File(target);
            if (tofile.exists()) {
                tofile.delete();
            }
            Dispatch.call(doc,"SaveAs", target, wdFormatPDF);
            Dispatch.call(doc, "Close", false);
            long end = System.currentTimeMillis();
            System.out.println("转换完成..用时：" + (end - start) + "ms.");
        } catch (Exception e) {
            System.out.println("========Error:文档转换失败：" + e.getMessage());
        } finally {
            if (app != null)
                app.invoke("Quit", wdDoNotSaveChanges);
        }
    }


    public static void ppt2pdf(String source,String target){
        System.out.println("启动PPT");
        long start = System.currentTimeMillis();
        ActiveXComponent app = null;
        try {
            app = new ActiveXComponent("Powerpoint.Application");
            Dispatch presentations = app.getProperty("Presentations").toDispatch();
            System.out.println("打开文档" + source);
            Dispatch presentation = Dispatch.call(presentations,//
                    "Open",
                    source,// FileName
                    true,// ReadOnly
                    true,// Untitled 指定文件是否有标题。
                    false // WithWindow 指定文件是否可见。
            ).toDispatch();

            System.out.println("转换文档到PDF " + target);
            File tofile = new File(target);
            if (tofile.exists()) {
                tofile.delete();
            }
            Dispatch.call(presentation,//
                    "SaveAs", //
                    target, // FileName
                    ppSaveAsPDF);

            Dispatch.call(presentation, "Close");
            long end = System.currentTimeMillis();
            System.out.println("转换完成..用时：" + (end - start) + "ms.");
        } catch (Exception e) {
            System.out.println("========Error:文档转换失败：" + e.getMessage());
        } finally {
            if (app != null) app.invoke("Quit");
        }
    }


    public static void excel2pdf(String source, String target) {
        System.out.println("启动Excel");
        long start = System.currentTimeMillis();
        ActiveXComponent app = new ActiveXComponent("Excel.Application"); // 启动excel(Excel.Application)
        try {
            app.setProperty("Visible", false);
            Dispatch workbooks = app.getProperty("Workbooks").toDispatch();
            System.out.println("打开文档" + source);
            Dispatch workbook = Dispatch.invoke(workbooks, "Open", Dispatch.Method, new Object[]{source, new Variant(false),new Variant(false)}, new int[3]).toDispatch();
            Dispatch.invoke(workbook, "SaveAs", Dispatch.Method, new Object[] {
                    target, new Variant(57), new Variant(false),
                    new Variant(57), new Variant(57), new Variant(false),
                    new Variant(true), new Variant(57), new Variant(true),
                    new Variant(true), new Variant(true) }, new int[1]);
            Variant f = new Variant(false);
            System.out.println("转换文档到PDF " + target);
            Dispatch.call(workbook, "Close", f);
            long end = System.currentTimeMillis();
            System.out.println("转换完成..用时：" + (end - start) + "ms.");
        } catch (Exception e) {
            System.out.println("========Error:文档转换失败：" + e.getMessage());
        }finally {
            if (app != null){
                app.invoke("Quit", new Variant[] {});
            }
        }
    }

}