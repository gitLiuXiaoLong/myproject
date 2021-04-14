package com.study.example.rest.demo.controller;

import com.study.example.rest.demo.FileUtils;
import fr.opensagres.xdocreport.converter.ConverterTypeTo;
import fr.opensagres.xdocreport.converter.ConverterTypeVia;
import fr.opensagres.xdocreport.converter.Options;
import fr.opensagres.xdocreport.document.IXDocReport;
import fr.opensagres.xdocreport.document.registry.XDocReportRegistry;
import fr.opensagres.xdocreport.template.IContext;
import fr.opensagres.xdocreport.template.TemplateEngineKind;

import java.io.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.UUID;

public class IXDocReportTest {

    public static void main(String[] args) {
        String fileUrl = createReceivePdf(new Object());
        System.out.println(fileUrl);
    }

    /**
     * 创建收款单
     * @param  obj 需要 合同数据(合同包含的费用数据 合同的首期账单) 账单数据 费用数据 收款记录数据 审核记录
     * @return
     * @throws Exception
     */
    public static String createReceivePdf (Object obj){

        String ossFileUrl = null;

        //获取模板文件路径
        String templateUrl = "D:\\contractPdf\\ReceivePdfTem.docx";

        String randomMark = UUID.randomUUID().toString();

        // 1、模板的临时位置（为了得到流）
        String templTempUrl = getOperatingSystem() + "contractTemp" + randomMark + ".docx";

        InputStream in = null;

        try {
            //1.通过freemarker模板引擎加载文档，并缓存到registry中
            /*byte[] fileByte = fastdfsService.getFile(templateUrl);*/
            File f = new File(templateUrl);
            //FileUtil.writeBytesToFileNio(fileByte, templTempUrl);
            in = new FileInputStream(f);

            IXDocReport report = XDocReportRegistry
                    .getRegistry()
                    .loadReport(in, TemplateEngineKind.Freemarker);

            //2、匹配 数据、合同文本模板
            //LhbgContract csgsData = LhbgContractService.queryCdgsSalePdfData(contractId);
            Object data = new Object();
            ossFileUrl = matchCqgsDataTempl(data, report, randomMark);

            // 99、回收临时文件
            //FileUtils.deleteFile(templTempUrl);
        } catch (Exception e) {
            e.printStackTrace();
            /*logger.error("收款单生成错误:",e);*/
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return ossFileUrl;
    }

    private static String matchCqgsDataTempl ( Object csgsData, IXDocReport report, String randomMark ) {

        //pdf临时存储的地址
        String pdfTempName = randomMark + ".pdf";
        String pdfTempUrl = getOperatingSystem() + pdfTempName;

        OutputStream out = null;
        InputStream rtnIn = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance();
        DecimalFormat numberDecimalFormat = (DecimalFormat) numberFormat;
        numberDecimalFormat.applyPattern("#,###");
        numberDecimalFormat.setMaximumFractionDigits(2);
        try {
            IContext context = report.createContext();
            context.put("showName","首创一期");
            context.put("receiveDate","2020年1月1日");
            context.put("teamName","小米加步枪");
            context.put("contractSpaceNumList","Ao1");
            context.put("roomPrice","100,23");
            context.put("roomSitesetCount","30");
            context.put("totalAmount","7023938,89.00");
            context.put("paymentCycle","2020年1月1日");
            context.put("amountReceivable","1383734736");
            context.put("amountDeposit","小米加步枪");
            context.put("amountCost","小米加步枪");
            context.put("costPayCycle","小米加步枪");
            context.put("receiveAmount","小米加步枪");
            context.put("receiveTypeTxt","小米加步枪");
            context.put("remarks","小米加步枪");
            context.put("receiveTotal","小米加步枪");
            context.put("updateName","小米加步枪");
            context.put("examineName","小米加步枪");
            context.put("financeName","小米加步枪");

            out = new FileOutputStream(new File(pdfTempUrl));


            Options options = Options.getTo(ConverterTypeTo.PDF).via(ConverterTypeVia.XWPF);
            report.convert(context, options, out);

            // 99、回收临时文件
            //FileUtils.deleteFile(pdfTempUrl);
        } catch (Exception e) {
            e.printStackTrace();
            /* logger.error("收款单生成错误:",e);*/
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (rtnIn != null) {
                    rtnIn.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return pdfTempUrl;
    }

    /**
     * 获取不通操作系统下临时文件的存储路径
     *
     * @return
     */
    public static String  getOperatingSystem() {

        String os = System.getProperty("os.name").toLowerCase();
        //System.out.println(os);
        if (os.startsWith("win")) {
            String windowPath = "D:\\billPdf\\";
            File dir  = new File(windowPath);
            if(!dir.exists()) {
                dir.mkdir();
            }
            return windowPath;
        } else {
            File dir  = new File("/usr/local/temp/");
            if(!dir.exists()) {
                dir.mkdir();
            }
            return "/usr/local/temp/";
        }
    }
}
