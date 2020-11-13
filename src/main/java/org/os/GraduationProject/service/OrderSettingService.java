package org.os.GraduationProject.service;

import org.os.GraduationProject.commons.Result;
import org.os.GraduationProject.pojo.OrderSetting;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

public interface OrderSettingService {
    String downLoad(HttpServletResponse response);

    Result editNumber(OrderSetting orderSetting);

    Result getOrderByMonth(String date);

    Result upLoad(MultipartFile upload);
}
