package org.os.GraduationProject.service.imp;

import org.os.GraduationProject.commons.MessageConstant;
import org.os.GraduationProject.commons.PageResult;
import org.os.GraduationProject.commons.QueryPageBean;
import org.os.GraduationProject.commons.Result;
import org.os.GraduationProject.dao.CheckGroupDao;
import org.os.GraduationProject.dao.CheckItemDao;
import org.os.GraduationProject.pojo.CheckGroup;
import org.os.GraduationProject.pojo.CheckItem;
import org.os.GraduationProject.service.CheckGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CheckGroupImp implements CheckGroupService {
    @Autowired(required = false)
    CheckGroupDao dao;
    @Autowired(required = false)
    CheckItemDao itemDao;

    @Override
    public PageResult findAllGroupPage(QueryPageBean queryPageBean) {
        List list = dao.findAllGroupPage(queryPageBean);
        return new PageResult(list.size(), list);
    }

    @Override
    public Result findCheckGroupById(int id) {
        try{
            CheckGroup group = dao.findCheckGroupById(id);
            return new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS,group);
        }catch (Exception e){
            return new Result(false, MessageConstant.QUERY_CHECKGROUP_FAIL);
        }
    }

    @Override
    public Result findItemByGroupId(int id) {
        try{
            List<Integer> itemIdList = dao.findItemByGroupId(id);
            List<CheckItem> list = new ArrayList<>();
            for(int itemId : itemIdList){
                list.add(itemDao.findCheckItemById(itemId));
            }
            return new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS,list);
        }catch (Exception e){
            return new Result(false, MessageConstant.QUERY_CHECKITEM_FAIL);
        }
    }

    @Override
    public Result saveCheckGroup(CheckGroup checkGroup) {
        try {
            dao.saveCheckGroup(checkGroup);
            saveRelation(checkGroup.getId(), checkGroup.getCheckItemIds());
            return new Result(true, MessageConstant.ADD_CHECKGROUP_SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false, MessageConstant.ADD_CHECKGROUP_FAIL);
        }
    }

    private void saveRelation(int groupId, Integer[] checkItemIds){
        for(int itemId : checkItemIds){
            dao.saveRelation(groupId, itemId);
        }
    }

    @Override
    public Result updateCheckGroup(CheckGroup checkGroup) {
        try{
            dao.updateCheckGroup(checkGroup);
            dao.deleteRelation(checkGroup.getId());
            saveRelation(checkGroup.getId(), checkGroup.getCheckItemIds());
            return new Result(true, MessageConstant.EDIT_CHECKGROUP_SUCCESS);
        }catch (Exception e){
            return new Result(false, MessageConstant.EDIT_CHECKGROUP_FAIL);
        }
    }

    @Override
    public PageResult findAllGroup() {
        List list = dao.findAllGroup();
        return new PageResult(list.size(), list);
    }

    @Override
    public Result deleteCheckGroup(int id) {
        try{
            if(dao.findGroupSetmealRelation(id)>0){
                return new Result(false, MessageConstant.DELETE_CHECKGROUP_FAIL);
            }
            dao.deleteGroupItemRelation(id);
            dao.deleteCheckGroup(id);
            return new Result(true, MessageConstant.DELETE_CHECKGROUP_SUCCESS);
        }catch (Exception e){
            return new Result(false, MessageConstant.DELETE_CHECKGROUP_FAIL);
        }
    }
}
