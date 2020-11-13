package org.os.GraduationProject.service;

import org.os.GraduationProject.commons.PageResult;
import org.os.GraduationProject.commons.QueryPageBean;
import org.os.GraduationProject.commons.Result;
import org.os.GraduationProject.pojo.CheckGroup;

public interface CheckGroupService {
    PageResult findAllGroupPage(QueryPageBean queryPageBean);

    Result findCheckGroupById(int id);

    Result findItemByGroupId(int id);

    Result saveCheckGroup(CheckGroup checkGroup);

    Result updateCheckGroup(CheckGroup checkGroup);

    PageResult findAllGroup();

    Result deleteCheckGroup(int id);
}
