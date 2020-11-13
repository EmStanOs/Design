package org.os.GraduationProject.service;

import org.os.GraduationProject.commons.PageResult;
import org.os.GraduationProject.commons.QueryPageBean;
import org.os.GraduationProject.commons.Result;
import org.os.GraduationProject.pojo.CheckItem;

public interface CheckItemService {
    PageResult findAllItemPage(QueryPageBean queryPageBean);

    Result saveCheckItem(CheckItem checkItem);

    //修改时做回显
    Result findCheckItemById(int id);

    Result updateCheckItem(CheckItem checkItem);

    Result deleteCheckItemById(int id);

    PageResult findAll();
}
