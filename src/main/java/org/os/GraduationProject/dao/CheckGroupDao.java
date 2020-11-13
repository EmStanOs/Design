package org.os.GraduationProject.dao;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;
import org.os.GraduationProject.commons.QueryPageBean;
import org.os.GraduationProject.pojo.CheckGroup;

import java.util.List;

@Mapper
public interface CheckGroupDao {
    //动态SQL+分页
    @SelectProvider(type = findGroup.class, method = "find")
    List<CheckGroup> findAllGroupPage(QueryPageBean queryPageBean);

    class findGroup {
        public String find(QueryPageBean queryPageBean) {
            return new SQL() {
                {
                    SELECT("*");
                    FROM("t_checkgroup");
                    String page = (queryPageBean.getCurrentPage() - 1) * queryPageBean.getPageSize() + "," +
                            queryPageBean.getPageSize();
                    if (queryPageBean.getQueryString() != null) {
                        WHERE("code=#{queryString} or name=#{queryString} or helpCode=#{queryString}");
                    }
                    LIMIT(page);
                }
            }.toString();
        }
    }

    //根据ID查询检查组
    @Select("select * from t_checkgroup where id=#{id}")
    CheckGroup findCheckGroupById(int id);

    //根据ID查询检查组被检查项的ID集合
    @Select("select checkitem_id from t_checkgroup_checkitem where checkgroup_id=#{id}")
    List<Integer> findItemByGroupId(int id);

    //新增检查组
    @Insert("insert into t_checkgroup (code,name,helpCode,sex,remark,attention) values " +
            "(#{code},#{name},#{helpCode},#{sex},#{remark},#{attention})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void saveCheckGroup(CheckGroup checkGroup);

    //修改检查组
    @Update("update t_checkgroup set name=#{name},code=#{code},helpCode=#{helpCode}," +
            "sex=#{sex},remark=#{remark},attention=#{attention} where id=#{id}")
    void updateCheckGroup(CheckGroup checkGroup);

    //新增检查组-检查项关系
    @Insert("insert into t_checkgroup_checkitem (checkgroup_id,checkitem_id) values (#{groupId},#{itemId})")
    void saveRelation(int groupId, int itemId);

    //删除检查组-检查项关系
    @Delete("delete from t_checkgroup_checkitem where checkgroup_id=#{groupId}")
    void deleteRelation(int groupId);

    //查询所有检查组
    @Select("select * from t_checkgroup")
    List<CheckGroup> findAllGroup();

    /* 删除检查组
     * 1.查询检查组-套餐关系表,没有关联套餐:
     *      a.查询检查组-检查项关系表,删除检查组-检查项关系:
     *      b.根据检查组ID删除检查组
     * 2.有关联套餐则不能删除检查组
     */

    @Select("select count(*) from t_setmeal_checkgroup where checkgroup_id=#{groupId}")
        //根据检查组ID查询检查组-套餐关系表,
    int findGroupSetmealRelation(int groupId);

    @Delete("delete from t_checkgroup_checkitem where checkgroup_id=#{id}")
        //根据检查组ID删除检查组-检查项关系
    void deleteGroupItemRelation(int id);

    @Delete("delete from t_checkgroup where id=#{id}")
    void deleteCheckGroup(int id);
}
