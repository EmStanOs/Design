package org.os.GraduationProject.service.imp;

import org.os.GraduationProject.commons.MessageConstant;
import org.os.GraduationProject.commons.PageResult;
import org.os.GraduationProject.commons.QueryPageBean;
import org.os.GraduationProject.commons.Result;
import org.os.GraduationProject.dao.CheckItemDao;
import org.os.GraduationProject.pojo.CheckItem;
import org.os.GraduationProject.service.CheckItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CheckItemImp implements CheckItemService {
    @Autowired(required = false)
    CheckItemDao dao;

    @Override
    public PageResult findAllItemPage(QueryPageBean queryPageBean) {
        /* 分页查询 */
        List list = dao.findAllItemPage(queryPageBean);
        return new PageResult(list.size(), list);
    }

    @Override
    public Result saveCheckItem(CheckItem checkItem) {
        /* 新增检查项 */
        try {
            dao.saveCheckItem(checkItem);
            return new Result(true, MessageConstant.ADD_CHECKITEM_SUCCESS);
        } catch (Exception e) {
            return new Result(false, MessageConstant.ADD_CHECKITEM_FAIL);
        }
    }

    @Override
    public Result findCheckItemById(int id) {
        /* 根据ID查询检查项 */
        try {
            CheckItem item = dao.findCheckItemById(id);
            if (item==null){
                return new Result(false, "检查项不存在");
            }
            return new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS, item);
        } catch (Exception e) {
            return new Result(false, MessageConstant.QUERY_CHECKITEM_FAIL);
        }
    }

    @Override
    public Result updateCheckItem(CheckItem checkItem) {
        /* 修改检查项 */
        try {
            dao.updateCheckItem(checkItem);
            return new Result(true, MessageConstant.EDIT_CHECKITEM_SUCCESS);
        } catch (Exception e) {
            return new Result(false, MessageConstant.EDIT_CHECKITEM_FAIL);
        }
    }

    @Override
    public Result deleteCheckItemById(int id) {
        /* 根据ID删除检查项 */
        try {
            int checkItemRelation = dao.findCheckItemRelation(id);
            if (checkItemRelation > 0) {
                return new Result(false, MessageConstant.DELETE_CHECKITEM_FAIL);
            } else {
                dao.deleteCheckItemById(id);
                return new Result(true, MessageConstant.DELETE_CHECKITEM_SUCCESS);
            }
        } catch (Exception e) {
            return new Result(false, MessageConstant.DELETE_CHECKITEM_FAIL);
        }
    }

    @Override
    public PageResult findAll() {
        /* 查询所有检查项 */
        List list = dao.findAll();
        return new PageResult(list.size(), list);
    }

}
