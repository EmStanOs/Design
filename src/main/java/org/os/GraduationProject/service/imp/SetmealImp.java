package org.os.GraduationProject.service.imp;

import org.os.GraduationProject.commons.*;
import org.os.GraduationProject.dao.CheckGroupDao;
import org.os.GraduationProject.dao.SetmealDao;
import org.os.GraduationProject.pojo.CheckGroup;
import org.os.GraduationProject.pojo.Setmeal;
import org.os.GraduationProject.service.SetmealService;
import org.os.GraduationProject.util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
public class SetmealImp implements SetmealService {
    @Autowired(required = false)
    SetmealDao dao;
    @Autowired(required = false)
    CheckGroupDao groupDao;

    private final RedisTemplate<Object, Object> jsonRedisTemplate;

    public SetmealImp(RedisTemplate<Object, Object> jsonRedisTemplate) {
        this.jsonRedisTemplate = jsonRedisTemplate;
    }

    @Override
    public Result saveSetmeal(Setmeal setmeal) {
        //新增套餐
        try {
            dao.saveSetmeal(setmeal);
            saveRelation(setmeal.getId(), setmeal.getCheckGroupIds());
            jsonRedisTemplate.opsForSet().add(MessageRedis.SETMEAL_PIC_DB_RESOURCES, setmeal.getImg());
            return new Result(true, MessageConstant.ADD_SETMEAL_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.ADD_SETMEAL_FAIL);
        }
    }

    private void saveRelation(int setmealId, Integer[] checkGroupIds) {
        for (int checkGroupId : checkGroupIds) {
            dao.saveRelation(setmealId, checkGroupId);
        }
    }

    @Override
    public Result updateSetmeal(Setmeal setmeal) {
        //修改套餐
        try {
            dao.updateSetmeal(setmeal);
            dao.deleteRelation(setmeal.getId());
            saveRelation(setmeal.getId(), setmeal.getCheckGroupIds());
            return new Result(true, "编辑套餐成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "编辑套餐失败");
        }
    }

    @Override
    public Result findSetmealById(int id) {
        //根据ID查询套餐
        try {
            Setmeal setmeal = dao.findSetmealById(id);
            if (setmeal == null) {
                return new Result(false, "套餐不存在");
            }
            return new Result(true, MessageConstant.QUERY_SETMEAL_SUCCESS, setmeal);
        } catch (Exception e) {
            return new Result(false, MessageConstant.QUERY_SETMEAL_FAIL);
        }
    }

    @Override
    public PageResult findAllSetmeal(QueryPageBean queryPageBean) {
        //分页查询所有套餐
        List list = dao.findAllSetmeal(queryPageBean);
        return new PageResult(list.size(), list);
    }

    @Override
    public Result findGroupBySetmealId(int id) {
        //根据套餐ID查询包含的检查组
        try {
            List<Integer> groupIds = dao.findGroupBySetmealId(id);
            List<CheckGroup> list = new ArrayList<>();
            for (int groupId : groupIds) {
                list.add(groupDao.findCheckGroupById(groupId));
            }
            return new Result(true, MessageConstant.QUERY_SETMEALLIST_SUCCESS, list);
        } catch (Exception e) {
            return new Result(false, MessageConstant.QUERY_SETMEALLIST_FAIL);
        }
    }

    @Override
    public Result imgUpload(MultipartFile imgFile) {
        //上传套餐图片
        try {
            String imgName = FileUploadUtil.saveFile(imgFile);
            jsonRedisTemplate.opsForSet().add(MessageRedis.SETMEAL_PIC_RESOURCES, imgName);
            return new Result(true, MessageConstant.UPLOAD_SUCCESS, imgName);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "上传失败");
        }
    }
}
