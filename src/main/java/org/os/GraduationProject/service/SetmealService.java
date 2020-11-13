package org.os.GraduationProject.service;

import org.os.GraduationProject.commons.PageResult;
import org.os.GraduationProject.commons.QueryPageBean;
import org.os.GraduationProject.commons.Result;
import org.os.GraduationProject.pojo.Setmeal;
import org.springframework.web.multipart.MultipartFile;

public interface SetmealService {
    //新增套餐
    Result saveSetmeal(Setmeal setmeal);

    //修改套餐
    Result updateSetmeal(Setmeal setmeal);

    //根据ID查询套餐
    Result findSetmealById(int id);

    //分页查询所有套餐
    PageResult findAllSetmeal(QueryPageBean queryPageBean);

    //根据套餐ID查询包含的检查组
    Result findGroupBySetmealId(int id);

    //上传套餐图片
    Result imgUpload(MultipartFile imgFile);
}
