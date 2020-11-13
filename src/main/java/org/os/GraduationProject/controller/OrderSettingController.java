package org.os.GraduationProject.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.os.GraduationProject.commons.Result;
import org.os.GraduationProject.pojo.OrderSetting;
import org.os.GraduationProject.service.OrderSettingService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin
@Api(tags = "OrderSettingController | 预约设置管理控制器")
public class OrderSettingController {
    final OrderSettingService service;

    public OrderSettingController(OrderSettingService service) {
        this.service = service;
    }

    @ResponseBody
    @GetMapping("/download")
    @ApiOperation(value = "下载模板文件")
    public String downLoad(HttpServletResponse response){
        return service.downLoad(response);
    }

    @ResponseBody
    @PostMapping("/editNumber")
    @ApiOperation(value="修改可预约数量")
    public Result editNumber(@RequestBody OrderSetting orderSetting){
        return service.editNumber(orderSetting);
    }

    @ResponseBody
    @GetMapping("/getOrderByMonth/{date}")
    @ApiOperation(value="根据日期查询")
    @ApiImplicitParam(paramType="path", name = "date", value = "日期", required = true, dataType = "String")
    public Result getOrderByMonth(@PathVariable("date") String date){
        return service.getOrderByMonth(date);
    }

    @ResponseBody
    @PostMapping("/upload")
    @ApiOperation(value="上传模板文件")
    public Result upload(@RequestParam(value = "excelFile") MultipartFile excelFile){
        return service.upLoad(excelFile);
    }
}
