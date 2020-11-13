package org.os.GraduationProject.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.os.GraduationProject.pojo.OrderSetting;

import java.util.Date;
import java.util.List;

@Mapper
public interface OrderSettingDao {
    @Update("update t_ordersetting set number=#{number} where id=#{id}")
    void editNumber(OrderSetting orderSetting);

    @Select("select * from t_ordersetting where orderDate>=#{smallDate} and orderDate<#{bigDate}")
    List<OrderSetting> getOrderByMonth(Date smallDate,Date bigDate);

    @Insert("insert into t_ordersetting (orderDate,number) values (#{orderDate},#{number})")
    void saveOrdersetting(OrderSetting orderSetting);
}
