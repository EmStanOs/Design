package org.os.GraduationProject.dao;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;
import org.os.GraduationProject.commons.QueryPageBean;
import org.os.GraduationProject.pojo.Setmeal;

import java.util.List;

@Mapper
public interface SetmealDao {
    @Insert("insert into t_setmeal (name,code,helpCode,sex,age,price,remark,attention,img) values " +
            "(#{name},#{code},#{helpCode},#{sex},#{age},#{price},#{remark},#{attention},#{img})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void saveSetmeal(Setmeal setmeal);

    @Insert("insert into t_setmeal_checkgroup (setmeal_id,checkgroup_id) values (#{setmealId},#{groupId})")
    void saveRelation(int setmealId, int groupId);

    @Delete("delete from t_setmeal_checkgroup where setmeal_id=#{setmealId}")
    void deleteRelation(int setmealId);

    @Update("update t_setmeal set name=#{name},code=#{code},helpCode=#{helpCode},sex=#{sex},age=#{age},price=#{price}," +
            "remark=#{remark},attention=#{attention},img=#{img} where id=#{id}")
    void updateSetmeal(Setmeal setmeal);

    @Select("select * from t_setmeal where id=#{id}")
    Setmeal findSetmealById(int id);

    @SelectProvider(type = findSetmeal.class, method = "find")
    List<Setmeal> findAllSetmeal(QueryPageBean queryPageBean);

    class findSetmeal {
        public String find(QueryPageBean queryPageBean) {
            return new SQL() {
                {
                    SELECT("*");
                    FROM("t_setmeal");
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

    @Select("select checkgroup_id from t_setmeal_checkgroup where setmeal_id=#{id}")
    List<Integer> findGroupBySetmealId(int id);
}
