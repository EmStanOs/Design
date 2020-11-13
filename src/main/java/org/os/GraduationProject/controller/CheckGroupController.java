package org.os.GraduationProject.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.os.GraduationProject.commons.PageResult;
import org.os.GraduationProject.commons.QueryPageBean;
import org.os.GraduationProject.commons.Result;
import org.os.GraduationProject.pojo.CheckGroup;
import org.os.GraduationProject.service.CheckGroupService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@Api(tags = "CheckGroupController | 检查组管理控制器")
public class CheckGroupController {
    final CheckGroupService service;

    public CheckGroupController(CheckGroupService service) {
        this.service = service;
    }

    //新增检查组
    @ResponseBody
    @PostMapping("/saveCheckGroup")
    @ApiOperation(value = "新增检查组信息")
    public Result saveCheckGroup(@RequestBody CheckGroup checkGroup){
        return service.saveCheckGroup(checkGroup);
    }

    //修改检查组
    @ResponseBody
    @PostMapping("/updateCheckGroup")
    @ApiOperation(value = "修改检查组信息")
    public Result updateCheckGroup(@RequestBody CheckGroup checkGroup){
        return service.updateCheckGroup(checkGroup);
    }

    //修改检查组时回显检查组
    @ResponseBody
    @GetMapping("/findCheckGroupById/{id}")
    @ApiOperation(value = "根据检查组编号查询检查组")
    @ApiImplicitParam(paramType="path", name = "id", value = "检查组ID", required = true, dataType = "int")
    public Result findCheckGroupById(@PathVariable("id") Integer id){
        return service.findCheckGroupById(id);
    }

    //修改检查组时回显检查项
    @ResponseBody
    @GetMapping("/findItemByGroupId/{id}")
    @ApiOperation(value = "根据检查组编号查询包含的检查项")
    @ApiImplicitParam(paramType="path", name = "id", value = "检查组ID", required = true, dataType = "int")
    public Result findItemByGroupId(@PathVariable("id") int id){
        return service.findItemByGroupId(id);
    }

    //分页查询所有检查组
    @ResponseBody
    @PostMapping("/findAllGroupPage")
    @ApiOperation(value = "分页查询检查组信息")
    public PageResult findAllGroupPage(@RequestBody QueryPageBean queryPageBean){
        return service.findAllGroupPage(queryPageBean);
    }

    //查询所有检查组
    @ResponseBody
    @GetMapping("/findAllGroup")
    @ApiOperation(value = "查询检查组信息")
    public PageResult findAllGroup(){
        return service.findAllGroup();
    }

    //删除检查组
    @ResponseBody
    @GetMapping("/deleteGroup/{id}")
    @ApiOperation(value = "删除检查组信息")
    @ApiImplicitParam(paramType="path", name = "id", value = "删除的检查组ID", required = true, dataType = "int")
    public Result deleteGroup(@PathVariable("id") int id){
        return service.deleteCheckGroup(id);
    }
}
