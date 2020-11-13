package org.os.GraduationProject.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.os.GraduationProject.commons.PageResult;
import org.os.GraduationProject.commons.QueryPageBean;
import org.os.GraduationProject.commons.Result;
import org.os.GraduationProject.pojo.CheckItem;
import org.os.GraduationProject.service.CheckItemService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin//跨域
@Api(tags = "CheckItemController | 检查项管理控制器")
public class CheckItemController {
    final CheckItemService service;

    public CheckItemController(CheckItemService service) {
        this.service = service;
    }

    //分页查询所有检查项
    @ResponseBody
    @PostMapping("/findAllItemPage")
    @ApiOperation(value = "分页查询检查项信息")
    public PageResult findAllItemPage(@RequestBody QueryPageBean queryPageBean) {
        return service.findAllItemPage(queryPageBean);
    }

    //新增检查项
    @ResponseBody
    @PostMapping("/saveCheckItem")
    @ApiOperation(value = "新增检查项信息")
    public Result saveCheckItem(@RequestBody CheckItem checkItem) {
        return service.saveCheckItem(checkItem);
    }

    //修改检查项
    @ResponseBody
    @PostMapping("/updateCheckItem")
    @ApiOperation(value = "修改检查项信息")
    public Result updateCheckItem(@RequestBody CheckItem checkItem) {
        return service.updateCheckItem(checkItem);
    }

    //修改检查项时回显检查项
    @ResponseBody
    @GetMapping("/findCheckItemById/{id}")
    @ApiOperation(value = "根据检查项ID查询检查项")
    @ApiImplicitParam(paramType="path", name = "id", value = "检查项ID", required = true, dataType = "int")
    public Result findCheckItemById(@PathVariable(name = "id") int id) {
        return service.findCheckItemById(id);
    }

    //删除检查项
    @ResponseBody
    @GetMapping("/deleteCheckItemById/{id}")
    @ApiOperation(value = "删除检查项信息")
    @ApiImplicitParam(paramType="path", name = "id", value = "检查项ID", required = true, dataType = "int")
    public Result deleteCheckItemById(@PathVariable(name = "id") int id) {
        return service.deleteCheckItemById(id);
    }

    //查询所有检查项
    @ResponseBody
    @GetMapping("/findAllItem")
    @ApiOperation(value = "查询检查项信息")
    public PageResult findAllItem() {
        return service.findAll();
    }
}
