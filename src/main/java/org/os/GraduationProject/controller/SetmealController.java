package org.os.GraduationProject.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.os.GraduationProject.commons.PageResult;
import org.os.GraduationProject.commons.QueryPageBean;
import org.os.GraduationProject.commons.Result;
import org.os.GraduationProject.pojo.Setmeal;
import org.os.GraduationProject.service.SetmealService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin
@Api(tags = "SetmealController | 套餐管理控制器")
public class SetmealController {
    final SetmealService service;

    public SetmealController(SetmealService service) {
        this.service = service;
    }

    //新增套餐
    @ResponseBody
    @PostMapping("/saveSetmeal")
    @ApiOperation(value = "新增套餐信息")
    public Result saveSetmeal(@RequestBody Setmeal setmeal) {
        return service.saveSetmeal(setmeal);
    }

    //修改套餐
    @ResponseBody
    @PostMapping("/updateSetmeal")
    @ApiOperation(value = "修改套餐信息")
    public Result updateSetmeal(@RequestBody Setmeal setmeal) {
        return service.updateSetmeal(setmeal);
    }

    //修改套餐时回显套餐
    @ResponseBody
    @GetMapping("/findSetmealById/{id}")
    @ApiOperation(value = "根据套餐编号查询套餐")
    @ApiImplicitParam(paramType="path", name = "id", value = "套餐ID", required = true, dataType = "int")
    public Result findSetmealById(@PathVariable("id") int id) {
        return service.findSetmealById(id);
    }

    //修改套餐时回显检查组
    @ResponseBody
    @GetMapping("/findGroupBySetmealId/{id}")
    @ApiOperation(value = "根据套餐编号查询套餐包含的检查组")
    @ApiImplicitParam(paramType="path", name = "id", value = "套餐ID", required = true, dataType = "int")
    public Result findGroupBySetmealId(@PathVariable("id") int id) {
        return service.findGroupBySetmealId(id);
    }

    //分页查询所有套餐
    @ResponseBody
    @PostMapping("/findAllSetmealPage")
    @ApiOperation(value = "分页查询套餐信息")
    public PageResult findAllSetmealPage(@RequestBody QueryPageBean queryPageBean) {
        return service.findAllSetmeal(queryPageBean);
    }

    //套餐图片上传
    @PostMapping(value = "/imgUpload",headers="content-type=multipart/form-data")
    @ApiOperation(value="上传套餐图片")
    public Result imgUpload(@RequestParam(value = "imgFile") MultipartFile imgFile) {
        return service.imgUpload(imgFile);
    }
}
