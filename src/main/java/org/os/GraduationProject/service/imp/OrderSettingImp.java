package org.os.GraduationProject.service.imp;

import org.os.GraduationProject.commons.MessageConstant;
import org.os.GraduationProject.commons.Result;
import org.os.GraduationProject.dao.OrderSettingDao;
import org.os.GraduationProject.pojo.OrderSetting;
import org.os.GraduationProject.service.OrderSettingService;
import org.os.GraduationProject.util.POIUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class OrderSettingImp implements OrderSettingService {
    @Autowired(required = false)
    OrderSettingDao dao;

    public String downLoad(HttpServletResponse response) {
        File file = new File("D:\\Project\\IDEA\\Design\\ordersetting_template.xlsx");
        if (file.exists()) {
            response.setContentType("application/vnd.ms-excel;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            try {
                response.setHeader("Content-Disposition", "attachment;fileName=" +
                        java.net.URLEncoder.encode("ordersetting_template.xlsx", "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            byte[] buffer = new byte[1024];
            FileInputStream fis = null; //文件输入流
            BufferedInputStream bis = null;
            OutputStream os; //输出流
            try {
                os = response.getOutputStream();
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer);
                    i = bis.read(buffer);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                assert bis != null;
                bis.close();
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "下载成功";
    }

    @Override
    public Result editNumber(OrderSetting orderSetting) {
        try {
            dao.editNumber(orderSetting);
            return new Result(true, MessageConstant.ORDERSETTING_SUCCESS);
        } catch (Exception e) {
            return new Result(false, MessageConstant.ORDERSETTING_FAIL);
        }
    }

    @Override
    public Result getOrderByMonth(String date) {
        String[] times = date.split("-");
        int bigYear, bigMonth;
        if (Integer.parseInt(times[1]) == 12) {
            bigYear = Integer.parseInt(times[0]) + 1;
            bigMonth = 1;
        } else {
            bigYear = Integer.parseInt(times[0]);
            bigMonth = Integer.parseInt(times[1]) + 1;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
        Date smallDate;
        Date bigDate;
        try {
            smallDate = dateFormat.parse(date);
            bigDate = dateFormat.parse("" + bigYear + "-" + bigMonth);
            List<OrderSetting> list = dao.getOrderByMonth(smallDate, bigDate);
            return new Result(true, MessageConstant.QUERY_ORDER_SUCCESS, list);
        } catch (Exception e) {
            return new Result(false, MessageConstant.QUERY_ORDER_FAIL);
        }
    }

    @Override
    public Result upLoad(MultipartFile upload) {
        SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy/MM/dd");
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
        try {
            List<String[]> list = POIUtils.readExcel(upload);
            for (String[] order : list) {
                Date temp = dateFormat1.parse(order[0]);
                Date date = dateFormat2.parse(dateFormat2.format(temp));
                dao.saveOrdersetting(new OrderSetting(date, Integer.parseInt(order[1])));
            }
            return new Result(true, MessageConstant.UPLOAD_SUCCESS);
        } catch (Exception e) {
            return new Result(false, "上传失败");
        }
    }
}
