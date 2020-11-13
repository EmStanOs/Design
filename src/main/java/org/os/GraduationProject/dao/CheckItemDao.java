package org.os.GraduationProject.dao;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;
import org.os.GraduationProject.commons.QueryPageBean;
import org.os.GraduationProject.pojo.CheckItem;

import java.util.List;

@Mapper
public interface CheckItemDao {
    //分页查询所有检查项
    @SelectProvider(type = findItem.class, method = "find")
    List<CheckItem> findAllItemPage(QueryPageBean queryPageBean);

    class findItem {
        public String find(QueryPageBean queryPageBean) {
            return new SQL() {
                {
                    SELECT("*");
                    FROM("t_checkitem");
                    String page = (queryPageBean.getCurrentPage() - 1) * queryPageBean.getPageSize() + "," +
                            queryPageBean.getPageSize();
                    if (queryPageBean.getQueryString() != null) {
                        WHERE("code=#{queryString} or name=#{queryString}");
                    }
                    LIMIT(page);
                }
            }.toString();
        }
    }

    //根据ID查询检查项
    @Select("select * from t_checkitem where id=#{id}")
    CheckItem findCheckItemById(int id);

    //根据ID查询检查项是否包含在检查组内,结果为0表示没有包含
    @Select("select count(*) from t_checkgroup_checkitem where checkitem_id=#{id}")
    int findCheckItemRelation(int id);

    //查询所有检查项
    @Select("select * from t_checkitem")
    List<CheckItem> findAll();

    //根据ID删除检查项
    @Delete("delete from t_checkitem where id=#{id}")
    void deleteCheckItemById(int id);

    //新增检查项
    @Insert("insert into t_checkitem(code,name,sex,age,price,type,attention,remark) " +
            "values (#{code},#{name},#{sex},#{age},#{price},#{type},#{attention},#{remark})")
    void saveCheckItem(CheckItem checkItem);

    //修改检查项
    @Update("update t_checkitem set code=#{code},name=#{name},sex=#{sex},age=#{age},price=#{price},type=#{type}," +
            "attention=#{attention},remark=#{remark} where id=#{id}")
    void updateCheckItem(CheckItem checkItem);
}